package com.magict.magic.mapper;

import com.magict.magic.entity.Message;
import com.magict.magic.entity.vo.MessageVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* 
* @author zp
* @date 2018-04-17 15:08:00
*/
public interface MessageMapper extends Mapper<Message> {
    List<MessageVo> demoA();
}
