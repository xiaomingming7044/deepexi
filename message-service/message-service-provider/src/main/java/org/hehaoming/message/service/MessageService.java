package org.hehaoming.message.service;


import org.hehaoming.message.constant.Constant;
import org.hehaoming.message.domain.query.AddMessage;
import org.hehaoming.message.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageService {

    @Resource
    private MessageMapper messageMapper;

    public String addMessage(AddMessage addMessage){
        messageMapper.saveMessage(addMessage);
        return  Constant.SUCCESS;
    }


}