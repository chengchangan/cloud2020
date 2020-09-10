package com.cca.sprngcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author cca
 * @date 2020/9/10 10:20
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerMain3344.class, args);
    }

}