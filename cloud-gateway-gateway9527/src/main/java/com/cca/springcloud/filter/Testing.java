package com.cca.springcloud.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * @author cca
 * @date 2020/9/16 19:21
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnProperty(value = "getway.testing", havingValue = "false",matchIfMissing = true)
public @interface Testing {
}
