package com.magict.magic.service.impl;

import com.magict.magic.entity.Message;
import com.magict.magic.entity.vo.MessageVo;
import com.magict.magic.mapper.MessageMapper;
import com.magict.magic.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 实现
* @author zp
* @date 2018-04-17 15:06:10
*/
@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public List<MessageVo> getMessages() {
        return messageMapper.demoA();
    }
}
