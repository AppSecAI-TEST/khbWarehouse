package com.yeepay.g3.app.async;


import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yeepay.g3.app.enums.UserLoginTypeEnum;
import com.yeepay.g3.facade.activity.async.event.AppMemberOperecord;
import com.yeepay.g3.utils.async.send.AsyncEventSender;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

public class LMAsyncSendUtil {
	private static final Logger logger = LoggerFactory.getLogger(LMAsyncSendUtil.class);
//	static ApplicationContext cxt;
//	static {
//		cxt = new ClassPathXmlApplicationContext(
//				"lmweChat-app-emvc-spring/spring-async-send.xml");
//	}
	/* 事件所属系统 */
	private static final String SYSTEM = "LMWECHAT";
	
	/* 同步key，例如二代商户入账如果要同步串行的执行，可以将商户编号作为syncKey */
	private static final String SYNC_KEY = "com.yeepay.g3.app.lmweChat.async.event";
	
	/* 消息发送对应的RabbitMQ的exchange名称 */
	public static final String EXCHANGE_APP_MEMBER_OPERECORD = "com.yeepay.lm.app_exchangeAppMemberOperecord";
		
	public static void sendAsyncMsg(AppMemberOperecord event,String activety){
			try {
				event.setEventKey(com.yeepay.g3.utils.common.CommonUtils.getUUID());
//				logger.info("[sendAsyncMessage] event={}", event.getLoginType());
				//事件唯一ID(eventKey+eventType+system组合唯一约束)
				event.setEventType(event.getEventKey()+event.getLoginType()+SYSTEM);
				event.setSystem(SYSTEM);
				event.setSyncProcess(true);
				event.setSyncKey(SYNC_KEY);   //设置消息串行处理
//				System.out.println(event);
				AsyncEventSender.send(activety, event);//事务成功提交之后才发送
			} catch (Exception e) {
				logger.error("[sendAsyncMessage] err, event=" + event + ", cause=" + e.getMessage(), e);
			}
	}
	public static void main(String args[]){
		for(int i=0;i<5;i++){
		AppMemberOperecord appMemberOperecord=new AppMemberOperecord();
		appMemberOperecord.setClientIp("192.11.22."+i);
		appMemberOperecord.setImei("测试"+i);
		appMemberOperecord.setLoginType(UserLoginTypeEnum.ANDROID.toString());
		appMemberOperecord.setMacAddress("测试"+i);
		appMemberOperecord.setMemberNo(String.valueOf(i));
		appMemberOperecord.setMemberTel(String.valueOf(1888888888)+i);
		appMemberOperecord.setOperateTime(new Date());
		appMemberOperecord.setSrcNo("0001");
		appMemberOperecord.setUserAgent("测试"+i);
		LMAsyncSendUtil.sendAsyncMsg(appMemberOperecord,EXCHANGE_APP_MEMBER_OPERECORD);
		}
	}
}
