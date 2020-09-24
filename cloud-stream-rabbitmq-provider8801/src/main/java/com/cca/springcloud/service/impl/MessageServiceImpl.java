package com.cca.springcloud.service.impl;

import com.cca.springcloud.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

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
        String uuid = UUID.randomUUID().toString();
        log.info("流水号：" + uuid);
        output.send(MessageBuilder.withPayload(uuid).build());
    }
}
