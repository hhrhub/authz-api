package com.hhrhub.authz.controller;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import com.hhrhub.authz.core.cache.AuthorizeCode;
import com.hhrhub.authz.core.common.Result;
import com.hhrhub.authz.core.constant.CacheKeys;
import com.hhrhub.authz.core.constant.Placeholders;
import com.hhrhub.authz.core.enums.GrantType;
import com.hhrhub.authz.core.enums.ResponseType;
import com.hhrhub.authz.core.enums.ResultType;
import com.hhrhub.authz.core.model.Client;
import com.hhrhub.authz.core.model.Permission;
import com.hhrhub.authz.core.response.TokenResponse;
import com.hhrhub.authz.service.ClientService;
import com.hhrhub.authz.util.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 参照RFC-6749 The OAuth 2.0 Authorization Framework
 * https://tools.ietf.org/html/rfc6749#section-4.1.1
 */
@Slf4j
@RestController
@RequestMapping("/v1/oauth")
public class OAuthController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RedissonClient redisson;

    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> token(@RequestParam(value = "grant_type") String grantType,
                                   @RequestParam(value = "code") String code,
                                   @RequestParam(value = "redirect_url") String redirectUrl,
                                   @RequestParam(value = "client_id") String clientId,
                                   @RequestHeader(value = "Authorization") String authorization) {
        log.info("the request is coming...code: {}", code);
        if (!GrantType.AUTHORIZATION_CODE.getName().equals(grantType)) {
            return new ResponseEntity<>(Result.failed(ResultType.INVALID_GRANT), HttpStatus.BAD_REQUEST);
        }
        if (!authorization.startsWith(JwtTokenUtils.BASIC_TYPE)) {
            return new ResponseEntity<>(Result.failed(ResultType.ACCESS_DENIED), HttpStatus.BAD_REQUEST);
        }
        String authStr = StringUtils.removeStart(authorization, JwtTokenUtils.BASIC_TYPE).trim();
        String[] authArr = new String(Base64.decodeBase64(authStr)).split(":");
        if (!clientId.equals(authArr[0])) {
            return new ResponseEntity<>(Result.failed(ResultType.INVALID_REQUEST), HttpStatus.BAD_REQUEST);
        }
        Client client = clientService.getById(clientId);
        if (client == null) {
            return new ResponseEntity<>(Result.failed(ResultType.INVALID_REQUEST), HttpStatus.BAD_REQUEST);
        }
        if (!client.getCredential().equals(authArr[1])) {
            return new ResponseEntity<>(Result.failed(ResultType.INVALID_CREDENTIAL), HttpStatus.BAD_REQUEST);
        }
        RBucket<AuthorizeCode> bucket = redisson.getBucket(CacheKeys.AUTHORIZATION_CODE_REQUEST
                .replace(Placeholders.CODE, code));
        if (!bucket.isExists()) {
            return new ResponseEntity<>(Result.failed(ResultType.INVALID_CODE), HttpStatus.BAD_REQUEST);
        }
        AuthorizeCode authorizeCode = bucket.get();
        if (!authorizeCode.getRedirectUrl().equals(redirectUrl)) {
            return new ResponseEntity<>(Result.failed(ResultType.INVALID_REDIRECT_URL), HttpStatus.BAD_REQUEST);
        }
        String accessToken = JwtTokenUtils.createToken(clientId, authorizeCode.getScope(), false);
        bucket.delete();
        TokenResponse response = new TokenResponse();
        response.setAccessToken(accessToken);
        response.setExpireIn(JwtTokenUtils.EXPIRE_ORIGIN_SEC);
        response.setTokenType(JwtTokenUtils.BEARER_TYPE);
        return new ResponseEntity<>(Result.succeed(response), HttpStatus.OK);
    }

    @GetMapping("/authorize")
    public void authorize(@RequestParam(value = "response_type") String responseType,
                          @RequestParam(value = "client_id") String clientId,
                          @RequestParam(value = "redirect_url") String redirectUrl,
                          @RequestParam(value = "scope", required = false) String scope,
                          @RequestParam(value = "state", required = false) String state,
                          HttpServletResponse response) throws IOException {

        //Todo:/authenticate 认证用户名密码

        UrlBuilder urlBuilder = UrlBuilder.ofHttp(redirectUrl, Charset.defaultCharset());
        UrlQuery urlQuery = new UrlQuery();
        log.info("the request is coming....");
        if (!ResponseType.CODE.getName().equals(responseType)) {
            log.error("response type is invalid: {}", responseType);
            urlQuery.add("error", ResultType.INVALID_REQUEST.getMsg());
            if (StringUtils.isNotBlank(state)) {
                urlQuery.add("state", state);
            }
            response.sendRedirect(urlBuilder.setQuery(urlQuery).build());
            return;
        }

        Client client = clientService.getById(clientId);
        if (client == null) {
            log.error("client is null:{} ", clientId);
            urlQuery.add("error", ResultType.UNAUTHORIZED_CLIENT.getMsg());
            if (StringUtils.isNotBlank(state)) {
                urlQuery.add("state", state);
            }
            response.sendRedirect(urlBuilder.setQuery(urlQuery).build());
            return;
        }

        AuthorizeCode authorizeCode = new AuthorizeCode();
        authorizeCode.setClientId(Long.parseLong(clientId));
        authorizeCode.setRedirectUrl(redirectUrl);
        authorizeCode.setState(state);
        String code = RandomStringUtils.randomAlphanumeric(22);
        authorizeCode.setCode(code);

        if (StringUtils.isNotBlank(scope)) {
            List<Permission> permissions = client.getPermissions();
            List<String> existedCodes = permissions.stream().map(Permission::getCode).collect(Collectors.toList());
            List<String> requestCodes = Arrays.asList(scope.split("\\s+"));
            if (!existedCodes.containsAll(requestCodes)) {
                log.error("scope is invalid: {}", scope);
                urlQuery.add("error", ResultType.INVALID_SCOPE.getMsg());
                if (StringUtils.isNotBlank(state)) {
                    urlQuery.add("state", state);
                }
                response.sendRedirect(urlBuilder.setQuery(urlQuery).build());
                return;
            }
            authorizeCode.setScope(requestCodes);
        }

        RBucket<AuthorizeCode> bucket = redisson.getBucket(CacheKeys.AUTHORIZATION_CODE_REQUEST
                .replace(Placeholders.CODE, code));
        bucket.set(authorizeCode, 600, TimeUnit.SECONDS);
        urlQuery.add("code", code);
        if (StringUtils.isNotBlank(state)) {
            urlQuery.add("state", state);
        }
        String url = urlBuilder.setQuery(urlQuery).build();
        log.info("redirect to {} and code:{}", url, code);
        response.sendRedirect(url);
    }

    @PostMapping("/introspect")
    public ResponseEntity<?> introspect() {
        return null;
    }
}
