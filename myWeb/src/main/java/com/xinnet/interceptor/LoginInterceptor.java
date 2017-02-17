package com.xinnet.interceptor;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.xinnet.annotation.NotLogin;
import com.xinnet.entity.User;
import com.xinnet.utils.HttpUtil;

/**
 * 登陆拦截器
 * @author hongbin.kang
 * @date 2016年9月2日上午11:35:24
 */

public class LoginInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private static final String CONTEXT_PATH = "ctx";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		request.setAttribute(CONTEXT_PATH, request.getContextPath());
		
		//有NotLogin注解，不需要认证权限
		NotLogin notLogin = ((HandlerMethod) handler).getMethodAnnotation(NotLogin.class);
		if(null != notLogin) {
			return true;
		}
		User UserDto = (User) request.getSession().getAttribute("User");
		if(null == UserDto){
			logger.info("[LoginInterceptor] 用户已经超时，跳转至登录页面");
			
			//1:判断是否是ajax请求，ajax session 过期处理     $(document ).ajaxComplete(function(event,xhr,settings)
            if (request.getHeader("x-requested-with") != null 
                    && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {   
                //向http头添加 状态 sessionstatus
                response.setHeader("sessionstatus","timeout");   /*xhr.getResponseHeader("sessionstatus") == "timeout"*/
                response.setStatus(403);
                logger.info("ajax request,用户超时");
                return false;
            }
            
            //2.普通的请求，登陆失效跳转登陆页面
            String returnUrl = request.getRequestURI();//请求的url
			String queryString = HttpUtil.getRequsetParams(request);//拼装请求参数
			if(!"".equals(queryString)) {
				returnUrl = returnUrl + "?" + queryString;
			}
			//地址encode编码
			returnUrl = URLEncoder.encode(returnUrl, "UTF-8");
			String toLoginUrl = request.getContextPath()+"/account/toLogin?returnUrl="+ returnUrl;
			//跳转登陆页面
			logger.info("登陆地址：toLoginUrl={}",toLoginUrl);
			response.sendRedirect(toLoginUrl); 
			return false;
		}
		return true;
	}
}
