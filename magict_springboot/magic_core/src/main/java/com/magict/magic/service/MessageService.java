package com.magict.magic.service;

import com.magict.magic.entity.Message;
import com.magict.magic.entity.vo.MessageVo;

import java.util.List;

/**
* 接口
* @author zp
* @date 2018-04-17 15:12:22
*/
public interface MessageService extends BaseService {
    List<MessageVo> getMessages();
}
