package com.magict.magic.service.impl;

import com.magict.magic.entity.WxUser;
import com.magict.magic.mapper.WxUserMapper;
import com.magict.magic.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

/**
 * 范例
 *         List<WxUser> wxUserList = wxUserMapper.selectByExample(new Example.Builder(WxUser.class)
 .andWhere(WeekendSqls.<WxUser>custom().andEqualTo(WxUser::getOpenid,openid))
 .build());
 WeekendSqls<WxUser> wxUserWeekendSqls = WeekendSqls.<WxUser>custom().andLike(WxUser::getNickname, "%test%");
 Example example = Example.builder(WxUser.class).andWhere(wxUserWeekendSqls).build();
 List<WxUser> wxUserList = wxUserMapper.selectByExample(example);

 * 微信用户表实现
* @author zp
* @date 2018-04-14 22:47:35
*/
@Service("wxUserService")
public class WxUserServiceImpl extends BaseServiceImpl<WxUser> implements WxUserService {
    @Autowired
    WxUserMapper wxUserMapper;

    @Override
    public WxUser findByOpenid(String openid) {
        List<WxUser> wxUserList = wxUserMapper.selectByExample(new Example.Builder(WxUser.class)
                .andWhere(WeekendSqls.<WxUser>custom().andEqualTo(WxUser::getOpenid,openid))
                .build());
        if(!wxUserList.isEmpty()){
            return wxUserList.get(0);
        }
        return null;
    }

    @Override
    public WxUser selectByPrimaryKey(Integer id) {
        return wxUserMapper.selectByPrimaryKey(id);
    }
}
