/**
 * @author 陈大涛
 * 2016-8-22下午8:39:58
 */
package com.yeepay.g3.core.activity.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-22下午8:39:58
 */
public interface ActivityInvForProTripSecondDiscountService {

	/**
	 * 查询打折限额剩余数
	 * @author 陈大涛
	 * 2016-8-22下午8:32:08
	 */
	public String queryTripSecondDiscountSurplusNum(String actionCode,String activityCode);
	
	/**
	 * 根据份额,理财期限计算理财金额
	 * @author 陈大涛
	 * 2016-8-24上午9:12:01
	 */
	public BigDecimal queryTripSecondDiscountByBuyNum(Integer buyNum,Integer ruleId);
}
