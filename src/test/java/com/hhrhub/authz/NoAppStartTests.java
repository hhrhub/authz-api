package com.hhrhub.authz;

import cn.hutool.core.net.url.UrlBuilder;
import com.hhrhub.authz.util.JwtTokenUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoAppStartTests {
    @Test
    public void test01() {
        UrlBuilder urlBuilder = UrlBuilder.ofHttp("https://www.baidu.com", Charset.defaultCharset());
        System.out.println(urlBuilder.getScheme());
        System.out.println(urlBuilder.build());
    }

    @Test
    public void test02() {

    }

    @Test
    public void test03() {
        String authorization = "Basic MTM3NDMwNjAzNjY3NjEwNDE5MzoxMjM0NTY=";
        String authStr = StringUtils.removeStart(authorization, JwtTokenUtils.BASIC_TYPE).trim();
        String[] authArr = new String(Base64.decodeBase64(authStr)).split(":");
        System.out.println(authorization);
        System.out.println(authStr);
        System.out.println(authArr[0]);
        System.out.println(authArr[1]);
    }

    @Test
    public void test04() {
        long time = 1616556299L;
        System.out.println(System.currentTimeMillis());
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(time));
        System.out.println(s);
    }

}
