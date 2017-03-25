/**
 * 
 */
package com.yeepay.g3.core.activity.queue;


import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yeepay.g3.core.activity.dao.ActivityInvForProInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProOrderDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProOrderFlowDao;
import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProOrder;
import com.yeepay.g3.core.activity.entity.ActivityInvForProOrderFlow;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.core.activity.service.ActivityInvForProOrderService;
import com.yeepay.g3.core.activity.service.ActivityMemberOperecordService;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowPlatService;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowRuleService;
import com.yeepay.g3.core.activity.utils.JSONObjectUtils;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderStatusEnum;
import com.yeepay.g3.facade.lmlc.async.event.LMTradeMessageEvent;
import com.yeepay.g3.utils.async.rabbitmq.receive.EventProcessor;
import com.yeepay.g3.utils.common.CheckUtils;

/**
 * @Description 信托交易事件接收器
 * @author zhenping.zhou
 * @CreateTime 2016年5月16日 下午2:50:30
 * @version 1.0
 */
public class LMTradeMessageProcessor implements EventProcessor {
	
	private static Logger logger = Logger.getLogger(LMTradeMessageProcessor.class);
	
	@Autowired
	private ActivityMemberOperecordService activityMemberOperecordServiceImpl;
	
	@Autowired
	private ActivitySrcFlowRuleService activitySrcFlowRuleServiceImpl;
	
	@Autowired
	private ActivitySrcFlowPlatService activitySrcFlowPlatServiceImpl;
	@Autowired
	private ActivityInvForProOrderService activityInvForProOrderServiceImpl;
	@Override
	public void process(Object obj) {
		logger.info("LMTradeMessageProcessor事件处理 : " + obj);
		if(obj != null) {
			LMTradeMessageEvent event = (LMTradeMessageEvent) obj;
			
			//1.会员操作
			ActivityMemberOperecord memberOperecord = new ActivityMemberOperecord();
			memberOperecord.setMemberNo(event.getMemberNo());
			memberOperecord.setMemberTel(event.getMobileNo());
			memberOperecord.setOperateAmount(event.getAmount());
			memberOperecord.setOperateTime(event.getTradeTime());
			memberOperecord.setOperateType(event.getType());
			//用户操作时的ip地址
			try {//如果是json
				Map<String,String> map = (Map<String, String>) JSONObjectUtils.jsonToMap(event.getClientIp(), String.class, String.class);
				memberOperecord.setClientIp(map.get("ip"));
				memberOperecord.setClientMac(map.get("macAddress"));
				memberOperecord.setClientImei(map.get("imei"));
				memberOperecord.setUserAgent(map.get("userAgent"));
				logger.info("[process] json格式clientIp="+event.getClientIp());
			} catch (Exception e) {//如果不是json
				logger.info("[process] 非json格式clientIp="+event.getClientIp());
				try {//判断clientIp是否过长，如果过长，怎不记录
					memberOperecord.setClientIp(event.getClientIp());
				} catch (Exception e2) {
					logger.info("[process] 非json格式clientIp过长="+event.getClientIp());
				}
				
			}
			activityMemberOperecordServiceImpl.addActivityMemberOperecord(memberOperecord);
			
			//判断是否为投资换产品，如果是，则不发生抽奖券幸运值，流量
			if(CheckUtils.isEmpty(event.getGoodsCode())){
				//2.发送抽奖券和幸运值
				try{
					activityMemberOperecordServiceImpl.addUserRaffleTicket(memberOperecord);
				}catch(Exception e){
					logger.error("[process] info=发送投资抽奖券和幸运值异常,ERROR={}",e);
				}
				//3.投资发流量
				try {
					activityMemberOperecordServiceImpl.sendInvestFlow(event, memberOperecord);
				} catch (Exception e) {
					logger.error("[process] info=投资发放流量异常,ERROR={}",e);
				}
			}else{
				//4.投资换产品购买成功 修改订单状态 ,
				try {
					activityInvForProOrderServiceImpl.updateOrderStatusByMQProcessor(event);
					//投资换产品，前十名赠送京东卡
					activityInvForProOrderServiceImpl.sendPrizeForOrder(event,memberOperecord);
				} catch (Exception e) {
					logger.error("[process] info=投资换产品购买成功 修改订单状态异常,ERROR={}",e);
				}
				//5.投资换产品第二次半价活动
				try {
					activityInvForProOrderServiceImpl.secondDiscountByMQProcessor(event);
				} catch (Exception e) {
					 logger.error("[process] info=投资换产品第二次半价活动异常,ERROR={}",e);
				}
			}
			
		}
	}
}

