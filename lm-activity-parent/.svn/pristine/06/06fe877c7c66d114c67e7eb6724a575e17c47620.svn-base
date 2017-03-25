/**
 * 
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityRule;
import com.yeepay.g3.core.activity.service.ActivityRuleService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityRuleDTO;
import com.yeepay.g3.facade.activity.enums.CouponRuleStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityRuleFacade;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @Description 活动规则对外接口处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:32:56
 * @version 1.0
 */
@Service
public class ActivityRuleFacadeImpl implements ActivityRuleFacade {

	@Autowired
	private ActivityRuleService activityRuleServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityEventFacadeImpl.class);
	
	@Override
	public void addRule(ActivityRuleDTO ruleDto, String events) {
		
		ActivityRule activityRule = new ActivityRule();
		
		activityRule = EntityDTOConvert.toTarget(ruleDto, ActivityRule.class);
		activityRule.setRuleStatus(CouponRuleStatusEnum.CHECKING);
		//设置当天最后时间为有效期截止日期
		Date invalidTime = DateUtils.getDayEnd(activityRule.getInvalidTime());
		activityRule.setInvalidTime(invalidTime);
		
		activityRuleServiceImpl.addRule(activityRule, events);
	}

	@Override
	public void deleteRuleById(Long id) {
	}

	@Override
	public void updateActivityRuleById(ActivityRuleDTO ruleDto) {
		if(ruleDto != null && ruleDto.getId() != null) {
			//判断是否非法调用接口
			if(StringUtils.isNotEmpty(ruleDto.getCheckor())) {
				ActivityRule activityRule = new ActivityRule();
				activityRule = EntityDTOConvert.toTarget(ruleDto, ActivityRule.class);
				activityRuleServiceImpl.updateActivityRuleById(activityRule);
			}
		}
	}

	@Override
	public ActivityRuleDTO selectRuleById(Long id) {
		ActivityRuleDTO activityRuleDto = new ActivityRuleDTO();
		ActivityRule activityRule = activityRuleServiceImpl.selectRuleById(id);
		activityRuleDto = EntityDTOConvert.toTarget(activityRule, ActivityRuleDTO.class);
		return activityRuleDto;
	}

	@Override
	public void updateRuleById(ActivityRuleDTO ruleDto, String events) {
		logger.info("[updateRuleById] ruleDto={},events={}",ruleDto,events);
		ActivityRule activityRule = new ActivityRule();
		activityRule = EntityDTOConvert.toTarget(ruleDto, ActivityRule.class);
		activityRuleServiceImpl.updateRuleById(activityRule, events);
	}

}
