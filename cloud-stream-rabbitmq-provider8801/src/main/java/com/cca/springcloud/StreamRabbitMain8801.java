package com.cca.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author cca
 * @date 2020/9/23 19:44
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMain8801.class, args);
    }
}
