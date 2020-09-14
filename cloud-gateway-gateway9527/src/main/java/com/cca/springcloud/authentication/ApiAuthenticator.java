package com.cca.springcloud.authentication;

import org.springframework.http.HttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * @author cca
 * @date 2020/9/11 15:07
 */
public interface ApiAuthenticator {

    AuthResult auth(ServerWebExchange exchange);

    AuthResult auth(ApiRequest apiRequest);

}
