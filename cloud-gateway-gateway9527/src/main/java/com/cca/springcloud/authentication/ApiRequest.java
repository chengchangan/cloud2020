package com.cca.springcloud.authentication;

import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;
import java.util.TreeMap;

/**
 * 对url的一些操作
 * 例如:
 * 1、对原有的url 提取,token appId等信息 ----> 解密
 *
 * @author cca
 * @date 2020/9/11 14:14
 */
public class ApiRequest {

    private String appId;
    private long timestamp;
    private String format;
    private String version;
    private String data;
    private String token;

    public ApiRequest() {

    }

    public ApiRequest(String token, String appId, long timestamp) {
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    /**
     * 拼装URL 方式可变动
     */
    public String buildSignParam(String secret) {
        StringBuilder signParams = new StringBuilder(secret);
        TreeMap<String, String> params = new TreeMap<>();
        params.put("app_id", this.appId);
        params.put("timestamp", String.valueOf(this.timestamp));
        params.put("format", this.format);
        params.put("v", this.version);
        params.put("data", this.data);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            signParams.append(entry.getKey()).append(entry.getValue());
        }
        return signParams.append(secret).toString();
    }

    public static ApiRequest createFromFullUrl(ServerWebExchange exchange) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        ApiRequest request = new ApiRequest();
        // appId
        request.appId = queryParams.getFirst("app_id");
        Assert.notNull(request.appId, "app_id 为空");
        // timestamp
        String timestamp = queryParams.getFirst("timestamp");
        Assert.notNull(timestamp, "timestamp 为空");
        request.timestamp = Long.parseLong(timestamp);
        // format
        request.format = queryParams.getFirst("format");
        Assert.notNull(request.format, "format 为空");
        // v
        request.version = queryParams.getFirst("v");
        Assert.notNull(request.version, "v 为空");
        // body
        request.data = exchange.getAttribute("requestBody");
        request.token = queryParams.getFirst("token");
        return request;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormat() {
        return format;
    }

    public String getVersion() {
        return version;
    }

    public String getData() {
        return data;
    }

    public String getToken() {
        return token;
    }
}
