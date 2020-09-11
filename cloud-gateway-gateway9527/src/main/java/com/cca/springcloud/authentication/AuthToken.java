package com.cca.springcloud.authentication;

import org.springframework.util.DigestUtils;

/**
 * 该类处理token相关的事情
 * 例如：生成token，校验token，等
 *
 * @author cca
 * @date 2020/9/11 13:57
 */
public class AuthToken {

    /**
     * token
     */
    private String token;
    /**
     * token 创建时间
     */
    private long createTime;
    /**
     * token 过期时间
     * 默认过期1分钟
     */
    private long expiredTimeInterval = 60 * 1000;


    /**
     * token是否过期
     *
     * @return
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > createTime + expiredTimeInterval;
    }

    /**
     * 验证token是否正确
     *
     * @param authToken
     * @return
     */
    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }

    /**
     * 根据请求信息生成 服务器token
     */
    public static AuthToken generate(ApiRequest apiRequest, String secret) {
        // 1、拼装url
        String url = apiRequest.buildUrl(secret);
        // 2、创建token （md5、rsa。。）
        String token = DigestUtils.md5DigestAsHex(url.getBytes());
        // 3、创建AuthToken
        return new AuthToken(token, System.currentTimeMillis());
    }

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public String getToken() {
        return this.token;
    }
}
