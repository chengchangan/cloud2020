package com.cca.springcloud;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author cca
 * @date 2020/9/11 16:56
 */
public class TestAuth {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:9527/payment/hystrix/ok/3";
        String body = "{\"userName\":\"cc\"}";
        url = sign(url, body);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> entity = template.postForEntity(url, request, String.class);
        System.out.println(entity.toString());
    }

    private static String appId = "123";
    private static String secret = "12345";
    private static Long timestamp = System.currentTimeMillis();
    private static String format = "json";
    private static String v = "1.0";


    private static String sign(String urlStr, String body) {
        StringBuilder url = new StringBuilder(urlStr);
        url.append("?app_id=").append(appId)
                .append("&timestamp=").append(timestamp)
                .append("&format=").append(format)
                .append("&v=").append(v)
                .append("&token=").append(createSign(body));
        return url.toString();
    }

    private static String createSign(String data) {
        StringBuilder signParams = new StringBuilder(secret);
        TreeMap<String, String> params = new TreeMap<>();
        params.put("app_id", appId);
        params.put("timestamp", String.valueOf(timestamp));
        params.put("format", format);
        params.put("v", v);
        params.put("data", data);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            signParams.append(entry.getKey()).append(entry.getValue());
        }
        String signParam = signParams.append(secret).toString();
        return DigestUtils.md5DigestAsHex(signParam.getBytes());
    }


}
