package com.cca.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cca
 * @date 2020/9/27 20:14
 */
@Slf4j
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;


    @GetMapping("/config/info/get")
    public String getConfigInfo() {
        log.info("********** 来获取 nacos 的config了");
        return configInfo;
    }

}
