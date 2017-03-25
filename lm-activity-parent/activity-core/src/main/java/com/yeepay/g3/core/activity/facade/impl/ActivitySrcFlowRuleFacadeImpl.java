package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivitySrcFlowRule;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowRuleService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowRuleDTO;
import com.yeepay.g3.facade.activity.facade.ActivitySrcFlowRuleFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @Title: 渠道发放流量规则管理业务实现类
 * @Description: 渠道发放流量规则管理业务facade实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午4:02:25
 * @version 2016-7-20
 */
@Service
public class ActivitySrcFlowRuleFacadeImpl implements ActivitySrcFlowRuleFacade {

	@Autowired
	private ActivitySrcFlowRuleService activitySrcFlowRuleServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivitySrcFlowRuleFacadeImpl.class);
	
	@Override
	public void addSrcFlowRule(ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO) {
		logger.info("ActivitySrcFlowRuleFacadeImpl:[addSrcFlowRule] activitySrcFlowRuleDTO={}",activitySrcFlowRuleDTO);
		ActivitySrcFlowRule activitySrcFlowRule = new ActivitySrcFlowRule();
		activitySrcFlowRule = EntityDTOConvert.toTarget(activitySrcFlowRuleDTO, ActivitySrcFlowRule.class);
		activitySrcFlowRuleServiceImpl.addSrcFlowRule(activitySrcFlowRule);
	}
	
	@Override
	public void updateSrcFlowRule(ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO) {
		logger.info("ActivitySrcFlowRuleFacadeImpl:[addSrcFlowRule] activitySrcFlowRuleDTO={}",activitySrcFlowRuleDTO);
		ActivitySrcFlowRule activitySrcFlowRule = new ActivitySrcFlowRule();
		activitySrcFlowRule = EntityDTOConvert.toTarget(activitySrcFlowRuleDTO, ActivitySrcFlowRule.class);
		activitySrcFlowRuleServiceImpl.updateByPrimaryKey(activitySrcFlowRule);
	}

	@Override
	public ActivitySrcFlowRuleDTO selectSrcFlowRuleById(Long id) {
		logger.info("ActivitySrcFlowRuleFacadeImpl:[selectSrcFlowRuleById] id={}",id);
		ActivitySrcFlowRule activitySrcFlowRule = activitySrcFlowRuleServiceImpl.selectByPrimaryKey(id);
		ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO = new ActivitySrcFlowRuleDTO();
		activitySrcFlowRuleDTO = EntityDTOConvert.toTarget(activitySrcFlowRule, ActivitySrcFlowRuleDTO.class);
		return activitySrcFlowRuleDTO;
	}

	@Override
	public void deleteSrcFlowRule(Long id) {
		logger.info("ActivitySrcFlowRuleFacadeImpl:[deleteSrcFlowRule] id={}",id);
		activitySrcFlowRuleServiceImpl.deleteByPrimaryKey(id);
	}

	@Override
	public List<ActivitySrcFlowRuleDTO> getSrcFlowRule(Map<String, Object> map) {
		List<ActivitySrcFlowRule> ruleList = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
		List<ActivitySrcFlowRuleDTO> list = EntityDTOConvert.toTragetList(ruleList, ActivitySrcFlowRuleDTO.class);
		return list;
	}

}
