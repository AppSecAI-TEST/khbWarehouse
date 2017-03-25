/**
 * @author 陈大涛
 * 2016-8-22下午8:24:17
 */
package com.yeepay.g3.facade.activity.facade;

import java.math.BigDecimal;
import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-22下午8:24:17
 */
public interface ActivityInvForProTripSecondDiscountFacade {
	/**
	 * 查询打折限额剩余数
	 * @author 陈大涛
	 * 2016-8-22下午8:32:08
	 */
	public String queryTripSecondDiscountSurplusNum(String actionCode,String activityCode);
	
	/**
	 * 根据份额,规则id计算理财金额
	 * @author 陈大涛
	 * 2016-8-24上午9:12:01
	 */
	public BigDecimal queryTripSecondDiscountByBuyNum(Integer buyNum,Integer ruleId);
	/**
	 * 查询旅游半价活动期间赠送的京东卡名单
	 */
	public List<ActivityUserPrizeDTO> queryTourSendJDCard();
}
