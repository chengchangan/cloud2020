package com.cca.springcloud.filter;

import com.cca.springcloud.authentication.ApiAuthenticator;
import com.cca.springcloud.authentication.AuthResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * 鉴权 filter
 *
 * @author cca
 * @date 2020/9/12 16:01
 */
@Component
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {


    @Autowired
    private ApiAuthenticator apiAuthenticator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();

        try {
            AuthResult result = apiAuthenticator.auth(exchange);
            if (!result.isSuccess()) {
                // TODO: 2020/9/14
                log.info("鉴权失败：{}", result.getMessage());
                return response.setComplete();
            } else {
                log.info("********** 鉴权成功 ************");
            }
        } catch (Exception e) {
            // TODO: 2020/9/14
            log.info("鉴权错误：{}，堆栈：", e.getMessage(), e);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
