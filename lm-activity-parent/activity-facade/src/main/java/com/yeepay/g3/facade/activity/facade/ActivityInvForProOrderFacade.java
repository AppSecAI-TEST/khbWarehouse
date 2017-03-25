/**
 * @author 陈大涛
 * 2016-7-27下午4:12:46
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityInvForProInsertOrderResultDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProOrderAndProInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProOrderDTO;

/**
 * @author 陈大涛
 * 2016-7-27下午4:12:46
 */
public interface ActivityInvForProOrderFacade {

	/**
	 * 新增订单（下单）
	 * @author 陈大涛
	 * 2016-7-27下午4:37:06
	 */
//	public String insertInvForProOrder(ActivityInvForProOrderDTO activityInvForProOrderDTO);
	
	/**
	 * 新增订单（下单）
	 * @author 陈大涛
	 * 2016-7-27下午4:37:06
	 */
	public ActivityInvForProInsertOrderResultDTO insertInvForProOrder(String productId,String number, String ruleId,String memberNo);
	/**
	 * 更新订单
	 * @author 陈大涛
	 * 2016-7-27下午4:37:17
	 */
	public void updateInvForProOrder(ActivityInvForProOrderDTO activityInvForProOrderDTO);
	/**
	 *  根据id查询订单信息
	 * @author 陈大涛
	 * 2016-7-27下午4:37:32
	 */
	public ActivityInvForProOrderDTO queryInvForProOrderDetailById(Long id);
	
	/**
	 * 定时取消未支付订单
	 * @author 陈大涛
	 * 2016-7-27下午6:38:49
	 */
	public void updateOrderStatusTiming();
	
	/**
	 * 定时发送未支付订单消息
	 * @author 陈大涛
	 * 2016-7-28下午5:22:01
	 */
	public void sendMessageForNoPaidOrderTiming();
	
	/**
	 * 我的订单
	 * @author 陈大涛
	 * 2016-7-29上午11:20:58
	 */
	public List<ActivityInvForProOrderAndProInfoDTO> queryOrderAndProInfoListByMemberNoAndStatus(ActivityInvForProOrderDTO activityInvForProOrderDTO);
	
	/**
	 * 根据orderCode查询订单和产品信息
	 * @author 陈大涛
	 * 2016-7-29下午4:34:16
	 */
	public ActivityInvForProOrderAndProInfoDTO queryInvForProOrderDetailByOrderCode(String orderCode);
	
	
	
}
