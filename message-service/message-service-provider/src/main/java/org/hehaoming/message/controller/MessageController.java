package org.hehaoming.message.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hehaoming.message.domain.query.AddMessage;
import org.hehaoming.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("message")
@Api(tags = {"消息增删改查"})
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("addMessage")
    @ApiOperation(value = "添加消息")
    public String addMessage(@Valid @RequestBody AddMessage addMessage) throws Exception {
        return messageService.addMessage(addMessage);
    }


}
