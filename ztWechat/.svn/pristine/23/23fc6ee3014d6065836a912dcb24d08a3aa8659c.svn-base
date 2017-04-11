package com.yeepay.g3.app.lmweChat.interceptor;

import org.springframework.stereotype.Component;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.web.emvc.EmvcActionInvocation;
import com.yeepay.g3.utils.web.emvc.EmvcInterceptor;

@Component
public class LoggerInterceptor implements EmvcInterceptor {
	protected Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	@Override
	public String intercept(EmvcActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (Exception e) {
			return "SYSTEM_EXCEPTION";
		}
	}

}
