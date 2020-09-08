package com.cca.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cca
 * @date 2020/9/8 18:42
 */
@Configuration
public class GetWayConfig {


    /**
     * 使用 java配置的方式进行，路由规则的转发
     *
     */
    @Bean
    public RouteLocator customLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        // 如果访问地址，能匹配上 predicate（patterns）， 则使用 uri + predicate => 作为转发地址
        routes.route("payment_route_1", r -> r.path("/mil").uri("http://news.baidu.com")).build();
        return routes.build();
    }
}
