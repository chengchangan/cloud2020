package com.cca.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cca
 * @date 2020/9/27 20:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfiMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfiMain.class,args);
    }
}
