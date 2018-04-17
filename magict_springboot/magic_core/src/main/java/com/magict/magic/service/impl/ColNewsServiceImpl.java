package com.magict.magic.service.impl;


import com.magict.magic.service.ColNewsService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  zp
 * @date 创建时间：2017年12月12日 下午2:37:41 
 * @version 1.0
 * @return  
*/
@Service
public class ColNewsServiceImpl extends BaseServiceImpl implements ColNewsService {


	
	@Override
	public List<String> colNews(String[][] urls) {
		List<String> repeatlist = new ArrayList<String>();
		for(int i=0;i<urls.length;i++){
			repeatlist.add(urls[i][0]);
			collect(urls[i][0],urls[i][1],repeatlist);
		}
		List<String> list = new ArrayList<>();
		removeDuplicate(list, repeatlist);
		return list;
	}

	
	public void removeDuplicate(List<String> list,List<String> repeatlist){
		for(String str:repeatlist){
			if(!list.contains(str)){
				list.add(str);
			}
		}
	}
	
	
	public void collect(String strUrl,String codeType,List<String> list) {
        try {  
            URL url = new URL(strUrl);  
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),codeType));
            
            String strRead = ""; // new 一个字符串来装载 BufferedReader 读取到的内容  
            // 定义1个规则用于获取a标签的数据  
            String aPattern = "(?i)<a[^>]*?href=\"([^\"]*)\"[^>]*>[^<>]*</a>";  
            int count = 0; 
            int countZhang = 0;
            while (null != (strRead = br.readLine())) {  
                // 捕获a标签数据
                String strGet = getMatchData(aPattern, strRead);
                if(""!=strGet&&rule1(strGet)){
                	//保留a标签
                	if(strGet.length()>6){
                		list.add(strGet);
                		countZhang++;
                	}
                	//去除连接
                	 /*Pattern p = Pattern.compile("<a[^>]*>([^<]*)</a>"); 
                     Matcher m = p.matcher(strGet);
                     while(m.find()) { 
                         if(m.group(1).length()>6){
                        	 list.add(m.group(1));
                        	 countZhang++;
                         }
                     }*/
                }
            }  
            br.close();
            logger.info(strUrl+"【共收集到" + countZhang + "条记录】");
            
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	public boolean rule1(String strGet){
		return strGet.indexOf("涨")>0||strGet.indexOf("上升")>0||strGet.indexOf("扩大")>0||strGet.indexOf("江苏银行")>0||strGet.indexOf("震荡")>0;
	}
	
	public static boolean rule2(String strGet){
		return strGet.indexOf("吃鸡")>0;
	}
	
	
	public String getMatchData(String pattern, String htmlStr) {  
        Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(  
                htmlStr);  
        // 返回捕获到的数据  
        return m.find() == true ? m.group() : "";  
    } 
}
