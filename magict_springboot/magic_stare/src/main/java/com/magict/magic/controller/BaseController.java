package com.magict.magic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制层
 * @author ZP
 * @// TODO: 2018/4/13  
 */
public class BaseController {
	protected transient final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取跟路径
	 * @param request
	 * @return
	 */
	public String getRootPath(HttpServletRequest request){
		return String.format("http://%s%s",request.getServerName(),request.getContextPath());
	}

}
