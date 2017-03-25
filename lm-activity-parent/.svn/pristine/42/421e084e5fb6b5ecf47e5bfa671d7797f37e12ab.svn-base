package com.yeepay.g3.facade.activity.facade;

import java.util.Map;

import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageResultDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;

/**
 * @author 陈大涛
 * 2016-5-31下午4:00:15
 */
public interface ActivityWXSendMessageFacade {

	/**
	 * 发送微信推送消息
	 * @author 陈大涛
	 * 2016-5-31下午4:00:31
	 */
	public ActivityWXSendMessageResultDTO sendWxMessage(ActivityWXSendMessageEnum type,ActivityWXSendMessageDTO dataDto);
	
	/**
	 * 获取微信用户信息
	 * @author 陈大涛
	 * 2016-6-1上午11:06:22
	 */
	public Map<String,Object> getWxUserInfo(String openId);
	
	/**
	 * 兑付发送微信推送消息
	 * @author 陈大涛
	 * 2016-7-25下午3:31:46
	 */
	public void sendWxMessageForMemberNo(ActivityWXSendMessageEnum type,String memberNo,String orderNo);
	
}
