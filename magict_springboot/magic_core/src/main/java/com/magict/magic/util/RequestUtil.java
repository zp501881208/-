package com.magict.magic.util;

import org.apache.commons.lang3.StringUtils;

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
}
