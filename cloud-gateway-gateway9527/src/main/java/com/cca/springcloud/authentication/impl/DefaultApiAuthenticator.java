package com.cca.springcloud.authentication.impl;

import com.cca.springcloud.authentication.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author cca
 * @date 2020/9/11 15:46
 */
@Component
@Slf4j
public class DefaultApiAuthenticator implements ApiAuthenticator {

    @Resource
    private CredentialStorage defaultCredentialStorage;


    @Override
    public AuthResult auth(String url) {
        ApiRequest request = ApiRequest.createFromFullUrl(url);
        return auth(request);
    }

    @Override
    public AuthResult auth(ApiRequest apiRequest) {
        // 1、获取request信息 创建 AuthToken
        AuthToken clientToken = new AuthToken(apiRequest.getToken(), apiRequest.getTimestamp());
        // 2、校验 client_token
        if (clientToken.isExpired()) {
            return AuthResult.failed("token is expired");
        }
        // 3、根据请求信息生成服务器token
        String secret = defaultCredentialStorage.getSecretByAppId(apiRequest.getAppId());
        if (StringUtils.isEmpty(secret)) {
            return AuthResult.failed("invalid appId");
        }
        AuthToken serverToken = AuthToken.generate(apiRequest, secret);
        // 4、比对client 和 server 的token
        if (!clientToken.match(serverToken)) {
            return AuthResult.failed("token valid failed");
        }
        return AuthResult.success();
    }
}
