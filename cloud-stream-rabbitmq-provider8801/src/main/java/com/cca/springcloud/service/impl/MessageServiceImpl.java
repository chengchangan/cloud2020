package com.cca.springcloud.service.impl;

import com.cca.springcloud.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @author cca
 * @date 2020/9/23 19:46
 */
@Slf4j
@EnableBinding(Source.class)
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageChannel output;

    @Override
    public void send() {


        Message<String> message1 = MessageBuilder.withPayload("我是一条message ， 路由key： normal")
                .setHeaderIfAbsent("type", "normal").build();
        log.info("消息内容：{}", message1.toString());

        Message<String> message2 = MessageBuilder.withPayload("我是一条message ， 路由key： trade")
                .setHeaderIfAbsent("type", "trade").build();
        log.info("消息内容：{}", message2.toString());

        output.send(message1);
        output.send(message2);
    }
}
