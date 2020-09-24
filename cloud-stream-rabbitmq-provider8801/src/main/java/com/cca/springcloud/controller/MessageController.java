package com.cca.springcloud.controller;

import com.cca.springcloud.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cca
 * @date 2020/9/23 19:45
 */
@RestController
@Slf4j
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping("/message/send")
    public void send() {
        messageService.send();
    }
}
