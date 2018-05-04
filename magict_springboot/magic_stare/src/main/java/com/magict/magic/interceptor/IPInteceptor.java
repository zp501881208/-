package com.magict.magic.interceptor;

import com.magict.magic.util.RequestUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZP
 * @date 2018/4/2217:11
 * @description:
 */
public class IPInteceptor extends BaseInterceptor{

    private static String[] urls = {"images/","js/","css/"};

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
//        for (String s : urls) {
//            if(url.contains(s)){
//                return true;
//            }
//        }
        
        if(null!=getSession(httpServletRequest).getAttribute("ip")){
            logger.info("====已有IP:{}======访问路径:{}",getSession(httpServletRequest).getAttribute("ip"),url);
        }else{
            String ip = RequestUtil.getIpAddress(httpServletRequest);
            httpServletRequest.getSession().setAttribute("ip",ip);
            logger.info("====IP:{}======访问路径:{}",ip,url);
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
