package com.magict.magic.service.impl;

import com.magict.magic.entity.BaseEntity;
import com.magict.magic.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper使用范例
 * 参考资料http://www.mybatis.tk/
 *  List<Country> selectByWeekendSql = mapper.selectByExample(new Example.Builder(Country.class)
 .where(WeekendSqls.<Country>custom().andLike(Country::getCountryname, "%a%")
 .andGreaterThan(Country::getCountrycode, "123"))
 .build());
 * @param <T>
 */
public class BaseServiceImpl<T extends BaseEntity> implements BaseService {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());



}
