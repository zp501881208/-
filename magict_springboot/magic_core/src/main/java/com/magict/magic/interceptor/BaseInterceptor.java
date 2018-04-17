package com.magict.magic.interceptor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 基础拦截器
 * @author ZP
 * @date 2018-4-14 13:58:51
 */
public abstract class BaseInterceptor implements HandlerInterceptor {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());    //初始化toString 默认样式
    static {
        ToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
    }
}
