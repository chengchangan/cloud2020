package com.cca.springcloud.authentication.impl;

import com.cca.springcloud.authentication.ApiAuthenticator;
import org.springframework.util.DigestUtils;

/**
 * @author cca
 * @date 2020/9/11 16:56
 */
public class Test {

    public static void main(String[] args) {
        String str = buildStr();
        System.out.println("请求url：" + str);
        ApiAuthenticator authenticator = new DefaultApiAuthenticator();
        authenticator.auth(str);

    }

    private static String buildStr() {
        String secret = "123456";
        long timestamp = System.currentTimeMillis();
        String str = "www.baidu.com?appId=123&timestamp=" + timestamp;
        String signUrl = buildUrl(secret, timestamp);
        System.out.println("signUrl: " + signUrl);
        String token = DigestUtils.md5DigestAsHex(signUrl.getBytes());
        System.out.println("token 之前：" + str);
        str = str + "&token=" + token;
        return str;
    }


    private static String buildUrl(String secret, long timestamp) {
        String baseUrl = "www.baidu.com";
        String appId = "123";
        StringBuilder builder = new StringBuilder(secret);
        return builder
                .append(baseUrl)
                .append(appId)
                .append(timestamp)
                .append(secret).toString();
    }
}
