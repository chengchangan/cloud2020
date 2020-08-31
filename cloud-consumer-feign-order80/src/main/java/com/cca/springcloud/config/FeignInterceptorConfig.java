package com.cca.springcloud.config;

import cn.hutool.json.JSONUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cca
 * @date 2020/8/31 10:28
 */
@Slf4j
public class FeignInterceptorConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("{}", JSONUtil.toJsonStr(requestTemplate));
    }

}
