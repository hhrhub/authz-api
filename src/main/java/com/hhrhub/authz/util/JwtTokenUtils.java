package com.hhrhub.authz.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String BEARER_TYPE = "Bearer";
    public static final String BASIC_TYPE = "Basic";

    public static final String ISSUER = "hhrhub";
    public static final String SCOPE_CLAIM = "scope";
    public static final String PRIVATE_KEY = "HmIZ9Q1da3TPJPbsR5w5yFKK7eTKG6yFzhB2Yw8SJ6HEKiX0";

    public static final long EXPIRE_ORIGIN_SEC = 3600L;
    public static final long EXPIRE_REMEMBER_SEC = 604800L;

    public static String createToken(String subject, List<String> scope, boolean isRememberMe) {
        long expireSec = isRememberMe ? EXPIRE_REMEMBER_SEC : EXPIRE_ORIGIN_SEC;
        Map<String, Object> map = new HashMap<>();
        map.put(SCOPE_CLAIM, scope);
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(Base64.decodeBase64(PRIVATE_KEY)))
                .setClaims(map)
                .setIssuer(ISSUER)
                .setSubject(subject)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expireSec * 1000))
                .compact();
    }
}
