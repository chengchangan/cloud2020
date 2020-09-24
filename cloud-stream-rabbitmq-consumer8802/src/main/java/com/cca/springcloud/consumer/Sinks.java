package com.cca.springcloud.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author cca
 * @date 2020/9/24 14:04
 */
public interface Sinks {

    String INPUT = "input";

    @Input(Sinks.INPUT)
    SubscribableChannel input();

    String TRADE_INPUT = "trade-input";

    @Input(Sinks.TRADE_INPUT)
    SubscribableChannel tradeInput();
}