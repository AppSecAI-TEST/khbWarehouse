/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.boss.activity.listener;

import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <p>Title: Yeepay 组件初始化</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 14-5-22 0:52
 */
public class ComponentInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConfigurationUtils.init();
		RemoteServiceFactory.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		RemoteServiceFactory.destory();
		ConfigurationUtils.destory();
	}

}
