/**
 * @descrption
 * @author 陈大涛
 * 2016-7-27下午5:01:41
 */
package com.yeepay.g3.core.activity.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityInvForProOrder;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.facade.lmlc.async.event.LMTradeMessageEvent;

/**
 * @author 陈大涛
 * 2016-7-27下午5:01:41
 */
public interface ActivityInvForProOrderService {

	/**
	 * 新增订单（下单）
	 * @author 陈大涛
	 * 2016-7-27下午5:02:36
	 */
	public void insertInvForProOrder(ActivityInvForProOrder activityInvForProOrder);
	
	/**
	 * 更新订单
	 * @author 陈大涛
	 * 2016-7-27下午4:37:17
	 */
	public void updateInvForProOrder(ActivityInvForProOrder activityInvForProOrder);
	
	/**
	 * 根据id查询订单信息
	 * @author 陈大涛
	 * 2016-7-28上午11:02:37
	 */
	public ActivityInvForProOrder queryInvForProOrderDetailById(Long id);
	
	/**
	 * 定时取消超时订单状态
	 * @author 陈大涛
	 * 2016-7-28下午3:50:55
	 */
	public void updateOrderStatusTiming(Date deadLine);
	/**
	 * 定时发送未支付订单消息
	 * @author 陈大涛
	 * 2016-7-28下午5:22:01
	 */
	public void sendMessageForNoPaidOrderTiming(Date deadLine);
	/**
	 * 我的订单
	 * @author 陈大涛
	 * 2016-7-29上午11:20:58
	 */
	public List<Map<String,Object>> queryOrderAndProInfoListByMemberNoAndStatus(ActivityInvForProOrder activityInvForProOrder);
	
	/**
	 * 根据orderCode查询订单信息
	 * @author 陈大涛
	 * 2016-7-29下午4:34:16
	 */
	public ActivityInvForProOrder queryInvForProOrderDetailByOrderCode(String orderCode);
	
	/**
	 * 投资换产品购买成功 修改订单状态 
	 * @author 陈大涛
	 * 2016-8-22下午6:09:58
	 */
	public void updateOrderStatusByMQProcessor(LMTradeMessageEvent event);
	
	/**
	 * 投资换产品活动发送京东卡
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	public void sendPrizeForOrder(LMTradeMessageEvent event,ActivityMemberOperecord memberOperecord); 
	
	/**
	 * 投资换产品第二份半价限额已使用数以及活动期间未支付的打折失效的订单自动取消
	 * @author 陈大涛
	 * 2016-8-23下午1:52:50
	 */
	public void secondDiscountByMQProcessor(LMTradeMessageEvent event);
}
