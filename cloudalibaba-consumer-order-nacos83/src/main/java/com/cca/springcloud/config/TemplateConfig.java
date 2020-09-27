package com.cca.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author cca
 * @date 2020/9/27 19:43
 */
@Configuration
public class TemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }
}
