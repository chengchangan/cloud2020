package com.cca.springcloud.filter;

import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 缓存requestBody
 *  获取异步请求体信息
 *  放入 Attributes 里，便便后续取出
 *
 * @author cca
 * @date 2020/9/12 14:11
 */
@Component
@Slf4j
public class CacheRequestBodyFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("-----------------全局过滤器CacheRequestBodyFilter---------------------");
        System.out.println("-----------------请求方式---------------------");
        ServerRequest serverRequest = new DefaultServerRequest(exchange);

        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String method = serverHttpRequest.getMethodValue();
        // read & modify body
        if ("POST".equals(method)) {

            Mono<String> bodyToMono = serverRequest.bodyToMono(String.class);
            return bodyToMono.flatMap(body -> {
                exchange.getAttributes().put("requestBody", body);
                log.info("body: {}", body);
                ServerHttpRequest newRequest = new ServerHttpRequestDecorator(serverHttpRequest) {
                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.putAll(super.getHeaders());
                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                        return httpHeaders;
                    }

                    @Override
                    public Flux<DataBuffer> getBody() {
                        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
                        DataBuffer bodyDataBuffer = nettyDataBufferFactory.wrap(body.getBytes());
                        return Flux.just(bodyDataBuffer);
                    }
                };
                return chain.filter(exchange.mutate().request(newRequest).build());
            });
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return -99;
    }
}
