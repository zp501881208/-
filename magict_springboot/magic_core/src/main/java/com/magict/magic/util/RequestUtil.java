package com.magict.magic.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 网络请求相关工具类
 * @author ZP
 * @date 2018-4-14 14:34:04
 */
public class RequestUtil {

    /**
     * map转换成k=v字符串
     * @param map
     * @return
     */
    public static String mapToUrl(Map<String,String> map){
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> es = map.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        for (int i=1;it.hasNext();i++) {
            Map.Entry<String, String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if (StringUtils.isNotBlank(v)) {
                sb.append(k).append("=").append(v);
            }
            if(i!=map.size()){
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 获取真实ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip!=null){
            return ip.split(",")[0];
        }
        return ip;
    }
}
