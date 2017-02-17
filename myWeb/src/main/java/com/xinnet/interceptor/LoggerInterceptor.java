package com.xinnet.interceptor;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author hongbin.kang
 * @date 2016年9月29日下午3:32:42
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	private long beginTime = 0;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			String requestURI = request.getRequestURI();
			if (StringUtils.isNotBlank(requestURI)) {
				long timeConsume = System.currentTimeMillis() - beginTime;
					logger.info("{requestURI="+request.getRequestURI()+"},{cost_time="
							+ timeConsume + "}");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		
		beginTime = System.currentTimeMillis();
		String requestURI =request.getRequestURI();
		if (StringUtils.isNotBlank(requestURI)) {
			beginTime = System.currentTimeMillis();
			Map paramMap = request.getParameterMap();
			Iterator it = paramMap.keySet().iterator();
			StringBuffer sb = new StringBuffer("{requestURI="+request.getRequestURI()+"},");
			while (it.hasNext()) {
				Object key = it.next();
				sb.append("{");
				sb.append(key).append("=")
				.append(request.getParameter(key.toString()));
				sb.append("},");
			}
			String userAgent = request.getHeader("user-agent");
			sb.append("{user-agent=").append(userAgent).append("},");
			String realIP = request.getHeader("X-Real-IP");
			if(StringUtils.isEmpty(realIP)) {
				realIP = request.getRemoteAddr();
			}
			sb.append("{client-ip=").append(realIP).append("}");
			logger.info(sb.toString());
		}
		return true;
	}
	
}
