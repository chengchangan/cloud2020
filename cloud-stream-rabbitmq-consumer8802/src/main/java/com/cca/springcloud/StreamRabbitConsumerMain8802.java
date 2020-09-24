package com.cca.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author cca
 * @date 2020/9/24 10:14
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitConsumerMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitConsumerMain8802.class, args);
    }
}
