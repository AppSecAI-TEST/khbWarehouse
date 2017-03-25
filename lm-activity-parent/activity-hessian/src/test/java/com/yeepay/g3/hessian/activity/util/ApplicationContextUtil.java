package com.yeepay.g3.hessian.activity.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Description spring配置文件bean实例初始化工具类
 * @company YeePay
 * @author zhenping.zhou
 * @since 2013-4-12 上午10:46:31
 */
public class ApplicationContextUtil {
	
	private static final ApplicationContextUtil util = new ApplicationContextUtil();
	
	private ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:activity-core-spring/applicationContext.xml");

	private ApplicationContextUtil() {
	}
	
	public static ApplicationContextUtil getInstence() {
		
		return util;
	}
	
	public ApplicationContext getApplicationContext() {
		return ctx;
	}
}
