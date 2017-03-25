/**
 * @author 陈大涛
 * 2016-8-22下午8:34:53
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityInfo;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.core.activity.service.ActivityInvForProTripSecondDiscountService;
import com.yeepay.g3.core.activity.service.ActivityPrizeService;
import com.yeepay.g3.core.activity.service.ActivityService;
import com.yeepay.g3.core.activity.service.ActivityUserPrizeService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;
import com.yeepay.g3.facade.activity.enums.ActivityStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProTripSecondDiscountFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-22下午8:34:53
 */
@Service
public class ActivityInvForProTripSecondDiscountFacadeImpl implements
		ActivityInvForProTripSecondDiscountFacade {
	@Autowired
	private ActivityInvForProTripSecondDiscountService activityInvForProTripSecondDiscountServiceImpl;
	@Autowired
	private ActivityPrizeService activityPrizeServiceImpl;
	@Autowired
	private ActivityService activityServiceImpl;
	@Autowired
	private ActivityUserPrizeService activityUserPrizeServiceImpl;
	
	private Logger logger = (Logger) LoggerFactory.getLogger(ActivityInvForProTripSecondDiscountFacadeImpl.class);
	@Override
	public String queryTripSecondDiscountSurplusNum(String actionCode,String activityCode) {
		String result = activityInvForProTripSecondDiscountServiceImpl.queryTripSecondDiscountSurplusNum(actionCode, activityCode);
		return result;
	}

	@Override
	public BigDecimal queryTripSecondDiscountByBuyNum(Integer buyNum,
			Integer ruleId) {
		BigDecimal  result = activityInvForProTripSecondDiscountServiceImpl.queryTripSecondDiscountByBuyNum(buyNum, ruleId);
		return result;
	}
	@Override
	public List<ActivityUserPrizeDTO> queryTourSendJDCard() {
		//查询已发奖品的订单表
		//1.查询活动信息，事件，状态判断
		String actionCode = Constant.TOUR_HALF_ACTIVITY;
		ActivityInfo activity = activityServiceImpl.selectActivityByActionCoude(actionCode);
		Date currentDate = new Date();
		if(activity.getStartTime().getTime() > currentDate.getTime() || activity.getEndTime().getTime() < currentDate.getTime()){
			logger.info("[queryTourSendJDCard] ERROR={}","活动事件未到或已过期");
			return null;
		}
		
		if(!ActivityStatusEnum.EFFECTIVE.equals(activity.getActivityStatus())){
			logger.info("[queryTourSendJDCard] ERROR={}","活动无效");
			return null;
		}
		//2.根据事件编码查询奖品信息
		List<ActivityPrize> activityPrizeList = activityPrizeServiceImpl.queryByActionCode(Constant.TOUR_HALF_ACTION);
		//奖品不存在或者奖品配置错误
		if(activityPrizeList == null || activityPrizeList.size() != 1){
			logger.info("[queryTourSendJDCard] ERROR={}","奖品不存在或者奖品配置错误");
			return null;
		}
		ActivityPrize activityPrize = activityPrizeList.get(0);
		if(!PrizeStatusEnum.EFFECTIVE.equals(activityPrize.getPrizeStatus())){
			logger.info("[queryTourSendJDCard] ERROR={}","奖品无效");
			return null;
		}
		//3.根据奖品id查询用户奖品表
		List<ActivityUserPrize> userPrizeList = activityUserPrizeServiceImpl.selectUserPrizeByPrizeId(activityPrize.getId());
		//转换dto
		List<ActivityUserPrizeDTO> list = new ArrayList<ActivityUserPrizeDTO>();
		list = EntityDTOConvert.toTragetList(userPrizeList, ActivityUserPrizeDTO.class);
		return list;
	}
}
