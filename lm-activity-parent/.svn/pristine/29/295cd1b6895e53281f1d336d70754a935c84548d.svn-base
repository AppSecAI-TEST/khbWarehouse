/**
 * 
 */
package com.yeepay.g3.core.activity.utils;

import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * @Description 常量类
 * @author zhenping.zhou
 * @CreateTime 2015年12月16日 下午7:13:02
 * @version 1.0
 */
public class Constant {
	
	public static final String ACTION_SUFFIX = "_ACTION"; //用于定义事件动作编码的后缀，如BIND_CARD，则事件编码需定义为BIND_CARD_ACTION
	
	public static final String RAFFLE_TICKET_SUFFIX = "_RAFFLE_TICKET"; //用于定义事件动作赠送抽奖券的抽奖券编码后缀，如BIND_CARD，则抽奖券需定义为BIND_CARD_RAFFLE_TICKET
	
	public static final String INVFORPRO_SUFFIX = "_INVFORPRO";//用户定义旅游送京东卡事件动作编码的后缀，如INVEST,则抽奖券需定义为INVEST_INVFORPRO
	
	public static final String TOUR_HALF_PRIZE = "TOUR_JINGDONG_CARD";//旅游半价活动赠送的奖品编号
	
	public static final String TOUR_HALF_ACTIVITY = "TOUR_JINGDONG_ACTIVITY";//旅游投资送京东卡活动编号
	
	public static final String TOUR_HALF_ACTION = "INVEST_INVFORPRO";//旅游投资送京东卡事件编号
	
	public static final String BIND_CARD_SCORE_CODE = "LMACTIVITY_BIND_CARD_SCORE"; //懒猫活动绑卡送分值key
	
	public static final String FIRST_INVEST_SCORE_CODE = "LMACTIVITY_FIRST_INVEST_SCORE"; //懒猫活动首次投资送分值key
	
	public static final String BINDCARD_INVEST_RAFFLE_CODE = "LMACTIVITY_BINDCARD_INVEST_RAFFLE"; //懒猫活动人人得抽奖机会活动配置
	/**
	 * 投资换产品第二次投资半价活动code
	 */
	public static final String INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY="INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY";
	/**
	 * 投资换产品第二次投资半价事件code
	 */
	public static final String INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION="INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION";
	/**
	 * redis存储有效期
	 */
	public static final int REDIS_EXPIRE_TIME = 7200;
	
	/**
	 * 兑换码发送短信的模板编号
	 */
	public static final String TAMPLATNO = "redeemCodeMsg";
	
	/**定时取消订单 失效时间，单位（分钟） **/
	public static String getUpdateOrderStatusTimingDeadLine(){
		return  (String) ConfigurationUtils.getConfigParam("config_type_text_resources", "updateOrderStatusTimingDeadLine").getValue();
	}
	/**定时发送未支付订单消息 时间限制，单位（分钟） **/
	public static String getSendMessageForNoPaidOrderTimingDeadLine(){
		return  (String) ConfigurationUtils.getConfigParam("config_type_text_resources", "sendMessageForNoPaidOrderTimingDeadLine").getValue();
	}
}
