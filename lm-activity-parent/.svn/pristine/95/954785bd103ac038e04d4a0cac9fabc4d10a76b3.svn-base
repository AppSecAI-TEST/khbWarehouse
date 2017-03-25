/**
 * @author 陈大涛
 * 2016-8-22下午8:40:52
 */
package com.yeepay.g3.core.activity.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityActionRelaDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProRuleXTDao;
import com.yeepay.g3.core.activity.dao.ActivityPrizeDao;
import com.yeepay.g3.core.activity.entity.ActivityInvForProRuleXT;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.service.ActivityInvForProTripSecondDiscountService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.facade.activity.enums.TripSecondDiscountSurplusNumResultCode;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @Description
 * @author 陈大涛 2016-8-22下午8:40:52
 */
@Service
public class ActivityInvForProTripSecondDiscountServiceImpl implements
		ActivityInvForProTripSecondDiscountService {
	Logger logger = LoggerFactory
			.getLogger(ActivityInvForProTripSecondDiscountServiceImpl.class);
	@Resource
	private ActivityActionRelaDao activityActionRelaDaoImpl;
	@Resource
	private ActivityPrizeDao activityPrizeDaoImpl;
	@Autowired
	private ActivityInvForProRuleXTDao activityInvForProRuleDaoImpl;

	@Override
	public String queryTripSecondDiscountSurplusNum(String actionCode,
			String activityCode) {
		logger.info(
				"[queryTripSecondDiscountSurplusNum] actionCode={},activityCode={}",
				actionCode, activityCode);
		// 1.查询是否有活动并有效
		Map<String, String> params = new HashMap<String, String>();
		params.put("actionCode", actionCode);
		params.put("activityCode", activityCode);
		Integer activityCount = activityActionRelaDaoImpl
				.queryActivityByActionCode(params);
		// 失效则返回NO_ACITITY
		if (activityCount == null || activityCount == 0) {
			return TripSecondDiscountSurplusNumResultCode.NO_ACITITY.toString();
		}
		// 2.查询限额剩余量
		List<ActivityPrize> prizeList = activityPrizeDaoImpl.query(
				"selectByActionCode", actionCode);
		if (prizeList == null || prizeList.size() == 0) {
			return TripSecondDiscountSurplusNumResultCode.NO_PRIZE.toString();
		} else if (prizeList.size() != 1) {
			return TripSecondDiscountSurplusNumResultCode.ERROR_PRIZE
					.toString();
		}
		// 总数 total,已用数grantNUm
		Long total = prizeList.get(0).getPrizeTotalCount() == null ? 0
				: prizeList.get(0).getPrizeTotalCount();
		Long grantNum = prizeList.get(0).getPrizeGrantCount() == null ? 0
				: prizeList.get(0).getPrizeGrantCount();
		Long surplus = total - grantNum;
		logger.info(
				"[queryTripSecondDiscountSurplusNum] total={},grantNum={},surplus={}",
				total, grantNum, surplus);
		return surplus.toString();
	}

	@Override
	public BigDecimal queryTripSecondDiscountByBuyNum(Integer buyNum,
			Integer ruleId) {
		//参数判断
		if(buyNum==null||buyNum==0||ruleId==null){
			logger.error("[queryTripSecondDiscountByBuyNum] 参数错误 buyNum={},ruleId={}",buyNum,ruleId);
			return null;
		}
		// 1.规则详情
		ActivityInvForProRuleXT ruleXT = (ActivityInvForProRuleXT) activityInvForProRuleDaoImpl
				.queryOne("selectByPrimaryKey", Long.valueOf(ruleId));
		if(ruleXT==null){
			logger.error("[queryTripSecondDiscountByBuyNum] 投资规则不存在");
			return null;
		}
		// 2.根据人数，打折限额剩余数计算总金额
		// 查询第二次投资打折限额
		Integer surplus = 0;
		try {
			String surplusNum = queryTripSecondDiscountSurplusNum(
					Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION,
					Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY);
			if (TripSecondDiscountSurplusNumResultCode.NO_ACITITY.toString()
					.equals(surplusNum)) {
				logger.error("[queryTripSecondDiscountByBuyNum] 查询第二次投资打折限额 ,没有活动或活动失效");
			} else if (TripSecondDiscountSurplusNumResultCode.NO_PRIZE
					.toString().equals(surplusNum)) {
				logger.error("[queryTripSecondDiscountByBuyNum] 查询第二次投资打折限额 ,没有奖品");
			} else if (TripSecondDiscountSurplusNumResultCode.ERROR_PRIZE
					.toString().equals(surplusNum)) {
				logger.error("[queryTripSecondDiscountByBuyNum] 查询第二次投资打折限额,奖品配置错误");
			} else {
				surplus = Integer.valueOf(surplusNum);
			}
		} catch (Exception e) {
			logger.error("[queryTripSecondDiscountByBuyNum] 查询第二次投资打折限额异常 ", e);
		}
		// 计算打折之后的总金额
		Integer buyNumInt = Integer.valueOf(buyNum);
		BigDecimal total = ruleXT.getPrice().multiply(new BigDecimal(buyNum));
		logger.info(
				"[queryTripSecondDiscountByBuyNum] 计算打折之后的总金额buyNumInt={},price={},surplus={}",
				buyNumInt, ruleXT.getPrice(), surplus);
		if (surplus != 0 && buyNumInt > 1) {
			Float buyNumF = null;
			if (buyNumInt / 2 > surplus) {// 超出限额
				buyNumF = (float) (buyNumInt - surplus * 2 + surplus * 1.5);
			} else {
				buyNumF = (float) (buyNumInt / 2 * 1.5 + buyNumInt % 2);
			}
			total = ruleXT.getPrice().multiply(new BigDecimal(buyNumF));
		}
		return total;
	}

}
