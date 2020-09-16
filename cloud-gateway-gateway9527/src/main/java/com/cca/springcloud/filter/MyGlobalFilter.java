package com.cca.springcloud.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.cca.springcloud.authentication.ApiAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * 添加网关全局过滤器
 * 可以实现:
 * 日志
 * 鉴权
 */
@Testing
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        MultiValueMap<String, String> queryParams = request.getQueryParams();
        List<String> username = queryParams.get("username");
        if (CollectionUtil.isEmpty(username) || StrUtil.isBlank(username.get(0))) {
            log.info("username：参数为空，请求不合法");

            // 返回 http状态码
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            // 如果想返回响应结果，参考：https://www.jianshu.com/p/9f00e0e1681c
            return response.setComplete();
        }
//        apiAuthenticator.auth() todo 增加权限校验

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
