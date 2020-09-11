package com.cca.springcloud.authentication;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 对url的一些操作
 * 例如:
 * 1、对原有的url 提取,token appId等信息 ----> 解密
 *
 * @author cca
 * @date 2020/9/11 14:14
 */
public class ApiRequest {
    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    /**
     * 拼装URL 方式可变动
     */
    public String buildUrl(String secret) {
        StringBuilder builder = new StringBuilder(secret);
        return builder
                .append(baseUrl)
                .append(appId)
                .append(timestamp)
                .append(secret).toString();
    }

    public static ApiRequest createFromFullUrl(String fullUrl) {
        Map<String, String> parse = parse(fullUrl);
        return new ApiRequest(parse.get("baseUrl"), parse.get("token"), parse.get("appId"), Long.parseLong(parse.get("timestamp")));
    }


    private static Map<String, String> parse(String fullUrl) {
        HashMap<String, String> urlParams = new HashMap<>();
        int index = fullUrl.indexOf("?");
        if (index > 0) {
            urlParams.put("baseUrl", fullUrl.substring(0, index));
            String substring = fullUrl.substring(index + 1);
            if (!StringUtils.isEmpty(substring)) {
                String[] kv = substring.split("&");
                for (String vkStr : kv) {
                    String[] kvArr = vkStr.split("=");
                    if (kvArr.length == 2) {
                        urlParams.put(kvArr[0].trim(), kvArr[1].trim());
                    }
                }
            }
        } else {
            urlParams.put("baseUrl", fullUrl);
        }
        return urlParams;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
