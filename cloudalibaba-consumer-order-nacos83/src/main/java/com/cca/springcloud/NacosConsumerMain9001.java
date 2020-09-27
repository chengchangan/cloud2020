package com.cca.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cca
 * @date 2020/9/27 19:39
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain9001.class, args);
    }
}
