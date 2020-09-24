package com.cca.springcloud.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

/**
 * @author cca
 * @date 2020/9/24 10:16
 */
@EnableBinding(Sinks.class)
@Slf4j
public class StudyConsumer {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(value = Sinks.INPUT)
    public void processInput(Message<String> message) {
        String payload = message.getPayload();
        log.info("我是消费者--Input，----消息内容：" + payload + ", 端口号：" + serverPort);
    }

    @StreamListener(value = Sinks.TRADE_INPUT)
    public void processTrade(Message<String> message) {
        String payload = message.getPayload();
        log.info("我是消费者--Trade，----消息内容：" + payload + ", 端口号：" + serverPort);
    }
}
