package com.magict.magic.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.magict.magic.component.WechatConfig;
import com.magict.magic.entity.WxUser;
import com.magict.magic.service.WxUserService;
import com.magict.magic.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信拦截器
 * @author ZP
 * @date 2018-4-14 13:59:54
 *
 */
public class WXInterceptor extends BaseInterceptor{
	
	@Autowired
	WxUserService wxUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { 
		//开发环境自行调整是否微信登录，仅允许修改development下，site.properties
//		if(SiteConfig.environment.equals("development")){
//			return true;
//		}
		
		HttpSession session = request.getSession();
		//判断是ios浏览器还是android浏览器
		if(request.getHeader("user-agent").toLowerCase().indexOf("iphone")==-1){
			session.setAttribute("browser","android");
		}
		else{
			session.setAttribute("browser","ios");
		}
		//ajax不做处理
		if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))||("POST").equals(request.getMethod())){ //如果是ajax请求响应头会有x-requested-with
			return true;
		}
		//如果没有微信用户，说明需要从微信获取
		if(session.getAttribute("weixinUser")==null){
			//获取code
			String code = request.getParameter("code");
			if(code == null&&("GET").equals(request.getMethod())){
				//拼接请求链接
				String requestUrl = request.getRequestURL().toString();
				String param = request.getQueryString();
				if(param!=null){
					requestUrl += "?"+param;
				}
				String redirectUrl = getOAuthUrl(requestUrl);
				response.sendRedirect(redirectUrl);
				return false;
			}
			else{
				//获取openId
				String openId = (String)session.getAttribute("openid");
				String accessToken = (String)session.getAttribute("access_token");
				try{
					if(openId == null ||openId.isEmpty()||accessToken ==null ||accessToken.isEmpty()){
						//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
						String requestUrl =getOAuth2AceessToken(code);
						JSONObject jsonObject = JSONObject.parseObject(HttpUtil.doGet(requestUrl));
						openId = jsonObject.getString("openid");
						if(StringUtils.isBlank(openId))
						{
							String redirectUrl = getOAuthUrl(String.format("http://%s:%s%s",
									request.getServerName(), request.getServerPort(),
									request.getContextPath()));
							response.sendRedirect(redirectUrl);
							return false;
						}

						session.setAttribute("openid",openId);
						accessToken = jsonObject.getString("access_token");
						session.setAttribute("access_token",accessToken);
					}
				}catch (Exception e){
					String redirectUrl = getOAuthUrl(String.format("http://%s:%s%s",
							request.getServerName(), request.getServerPort(),
							request.getContextPath()));
					response.sendRedirect(redirectUrl);
					return false;
				}
				String requestUrl =getUserInfo(accessToken, openId);
				JSONObject jsonObject = JSONObject.parseObject(HttpUtil.doGet(requestUrl));
				logger.debug("##################################");
				logger.debug(jsonObject.toString());
				logger.debug("##################################");
				//数据转化，获取微信用户将参数插入或更新
				WxUser weixinUser = JSONObject.parseObject(jsonObject.toJSONString(), WxUser.class);
				session.setAttribute("weixinUser", weixinUser);

				if(weixinUser!=null){
					/*WxUser checkUser = wxUserService.findWxUserByOpenid(weixinUser.getOpenid());
					if(checkUser==null){//首次登录插表
//						wxUserService.addWxUser(weixinUser);
						session.setAttribute("weixinUser",weixinUser);
					}else{//二次登录更新信息
						checkUser.setNickname(weixinUser.getNickname());
						checkUser.setSex(weixinUser.getSex());
						checkUser.setProvince(weixinUser.getProvince());
						checkUser.setCity(weixinUser.getCity());
						checkUser.setCountry(weixinUser.getCountry());

//						wxUserService.update
						session.setAttribute("weixinUser",checkUser);
					}*/
				}
			}
		}else{
			//特殊路径需要重新获取用户更新session
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//ajax不做处理
		if ((request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))||("POST").equals(request.getMethod())){ //如果是ajax请求响应头会有x-requested-with
			return ;
		}
		//设置浏览器类型，从而跳转不同界面
		modelAndView.getModel().put("browser", request.getSession().getAttribute("browser"));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public String getOAuthUrl(String url) throws UnsupportedEncodingException{
		String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		requestUrl = requestUrl.replace("APPID", WechatConfig.appID);
		requestUrl = requestUrl.replace("REDIRECT_URI", URLEncoder.encode(url,"UTF-8"));

		return requestUrl;
	}
	public String getOAuth2AceessToken(String code) throws UnsupportedEncodingException{
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", WechatConfig.appID);
		requestUrl = requestUrl.replace("SECRET", WechatConfig.appsecret);
		requestUrl = requestUrl.replace("CODE", code);
		return requestUrl;

	}
	public String getUserInfo(String accessToken,String openid) throws UnsupportedEncodingException{
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openid);
		return requestUrl;

	}
}
