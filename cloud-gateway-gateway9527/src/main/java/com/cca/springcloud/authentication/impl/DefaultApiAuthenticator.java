package com.cca.springcloud.authentication.impl;

import com.cca.springcloud.authentication.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

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
    public AuthResult auth(ServerWebExchange exchange) {
        ApiRequest request = ApiRequest.createFromFullUrl(exchange);
        return auth(request);
    }

    @Override
    public AuthResult auth(ApiRequest apiRequest) {
        // 1、创建token
        AuthToken clientToken = new AuthToken(apiRequest.getToken(), apiRequest.getTimestamp());
        // 2、校验过期
        if (clientToken.isExpired()) {
            return AuthResult.failed("token is expired");
        }
        // 3、根据appId，获取secret
        String secret = defaultCredentialStorage.getSecretByAppId(apiRequest.getAppId());
        if (StringUtils.isEmpty(secret)) {
            return AuthResult.failed("invalid appId");
        }
        // 4、生成服务器token
        AuthToken serverToken = AuthToken.generate(apiRequest, secret);
        // 5、比对client 和 server 的token
        if (!clientToken.match(serverToken)) {
            return AuthResult.failed("token valid failed");
        }
        return AuthResult.success();
    }
}
