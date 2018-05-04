package com.magict.magic.controller;

import com.magict.magic.entity.WxUser;
import com.magict.magic.service.ColNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author  zp
 * @date 创建时间：2017年12月12日 下午5:47:01 
 * @version 1.0
 * @return  
*/
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
//	@Autowired
//	WxUserService wxUserService;
	@Autowired
	ColNewsService colNewsService;
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;

	public static int  cnt;
	/**
	 * 收集信息的地址和对应编码格式
	 */
	private static String[][] urls = {{"http://stock.10jqka.com.cn/","GBK"},
			{"http://www.10jqka.com.cn/","GBK"},
			{"http://finance.sina.com.cn/stock/","UTF-8"},
			{"http://finance.sina.com.cn","UTF-8"},
			{"http://news.baidu.com/finance","UTF-8"},
			{"http://news.baidu.com","UTF-8"},
			{"http://news.baidu.com/n?cmd=4&class=stock&pn=1","GBK"},
			{"http://news.baidu.com/n?cmd=4&class=money&pn=1","GBK"},
			{"http://news.baidu.com/n?cmd=4&class=hongguan&pn=1","GBK"},
			{"http://news.baidu.com/n?cmd=4&class=hg_guonei&cls=hg_guonei","GBK"}
			};

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String findTodayMessage(HttpServletRequest request, Model model){
		logger.debug("###########/message/list############");
//		WxUser wxUser = wxUserService.findAll();
		cnt++;
		/*Object cnt =  redisTemplate.opsForValue().get("cnt");
		Integer cnt0 = 1;
		if(null!=cnt){
			cnt0 = Integer.parseInt(cnt.toString());
			cnt0++;
		}
		redisTemplate.opsForValue().set("cnt",cnt0);*/
		List<String> list = colNewsService.colNews(urls);
		logger.info("==msgList.size==="+list.size());
		model.addAttribute("msgList", list);
		model.addAttribute("cnt", cnt);
		model.addAttribute("IP",request.getSession().getAttribute("ip"));
		logger.info("==cnt==="+cnt);

		return "message";
	}

	@RequestMapping("/chat")
	public String chatView(HttpServletRequest request, Model model){
		model.addAttribute("userIP",request.getSession().getAttribute("ip"));
		return "TestWebSocket";
	}
}
