/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.lmweChat.interceptor;

import com.yeepay.g3.utils.common.exception.YeepayRuntimeException;
import com.yeepay.g3.utils.web.emvc.EmvcActionInvocation;
import com.yeepay.g3.utils.web.emvc.EmvcInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author baitao.ji
 * @version 1.0.0
 * @since 2013-05-06
 */
@Component
public class ExceptionInterceptor implements EmvcInterceptor {
//统一获取异常 如果action没有try 这里补上
	@Override
	public String intercept(EmvcActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();	
		} catch (Exception e) {
			if (e instanceof YeepayRuntimeException) {
				invocation.getMVCFacade().getRequest().setAttribute("exception", e);
			} else {
				e = new YeepayRuntimeException(e.getMessage(), e);
				invocation.getMVCFacade().getRequest().setAttribute("exception", e);
			}
			return "SYSTEM_EXCEPTION";
		}
	}

}
