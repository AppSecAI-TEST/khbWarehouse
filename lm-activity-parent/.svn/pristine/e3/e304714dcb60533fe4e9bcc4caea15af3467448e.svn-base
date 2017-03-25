package com.yeepay.g3.core.activity.queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yeepay.g3.core.activity.entity.AppMemberLoginLog;
import com.yeepay.g3.core.activity.service.AppMemberLoginLogService;
import com.yeepay.g3.facade.activity.async.event.AppMemberOperecord;
import com.yeepay.g3.utils.async.rabbitmq.receive.EventProcessor;

/**
 * @Description APP操作记录事件接收器
 * @author ping.zhu
 * @CreateTime 2016年9月21日 下午2:22:43
 * @version 1.0
 */
public class AppMemberOperecordProcessor implements EventProcessor{
	
	private static Logger logger = Logger.getLogger(AppMemberOperecordProcessor.class);

	@Autowired
	AppMemberLoginLogService appMemberLoginLogServiceImpl;
	
	@Override
	public void process(Object obj) {
		// TODO Auto-generated method stub
		logger.info("AppMemberOperecordProcessor事件处理 " + obj);
		if(null!=obj){
			AppMemberOperecord event = (AppMemberOperecord) obj;
			AppMemberLoginLog appMemberLoginLog=new AppMemberLoginLog();
			appMemberLoginLog.setClientIp(event.getClientIp());
			appMemberLoginLog.setImei(event.getImei());
			appMemberLoginLog.setLoginType(event.getLoginType());
			appMemberLoginLog.setMacAddress(event.getMacAddress());
			appMemberLoginLog.setMemberNo(event.getMemberNo());
			appMemberLoginLog.setMemberTel(event.getMemberTel());
			appMemberLoginLog.setOperateTime(event.getOperateTime());
			appMemberLoginLog.setSrcNo(event.getSrcNo());
			appMemberLoginLog.setUserAgent(event.getUserAgent());
			appMemberLoginLogServiceImpl.insertAppMemberLoginLog(appMemberLoginLog);
		}
	}
	
	public static void main(String args[]){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"activity-core-spring/spring-async-receive.xml");
		System.out.println("------------消息接收器启动成功!");
	}

}
