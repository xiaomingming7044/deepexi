package org.hehaoming.message.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hehaoming.message.domain.dto.MessageDTO;
import org.hehaoming.message.domain.query.AddMessage;
import org.hehaoming.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("message")
@Api(tags = {"消息增删改查"})
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("addMessage")
    @ApiOperation(value = "添加消息")
    public String addMessage(@Valid @RequestBody AddMessage addMessage){
        return messageService.addMessage(addMessage);
    }

    @GetMapping("find")
    @ApiOperation(value = "查询所有消息")
    public List<MessageDTO> find(){
        return messageService.find();
    }

}
