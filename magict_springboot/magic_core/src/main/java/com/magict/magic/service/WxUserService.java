package com.magict.magic.service;

import com.magict.magic.entity.WxUser;

/**
* 微信用户表接口
* @author zp
* @date 2018-04-14 22:47:35
*/
public interface WxUserService extends BaseService{
    WxUser findByOpenid(String openid);

    WxUser selectByPrimaryKey(Integer id);
}
