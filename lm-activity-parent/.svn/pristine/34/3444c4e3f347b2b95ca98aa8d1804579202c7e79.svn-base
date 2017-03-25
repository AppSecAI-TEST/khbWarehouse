/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityRuleDao;
import com.yeepay.g3.core.activity.dao.ActivityRuleEventRelationDao;
import com.yeepay.g3.core.activity.entity.ActivityEvent;
import com.yeepay.g3.core.activity.entity.ActivityRule;
import com.yeepay.g3.core.activity.entity.ActivityRuleEventRelation;
import com.yeepay.g3.core.activity.service.ActivityRuleService;
import com.yeepay.g3.facade.activity.enums.CouponRuleStatusEnum;

/**
 * @Description 活动规则业务逻辑处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午11:45:09
 * @version 1.0
 */
@Service
public class ActivityRuleServiceImpl implements ActivityRuleService {
	
	@Autowired
	private ActivityRuleDao activityRuleDaoImpl;
	
	@Autowired
	private ActivityRuleEventRelationDao activityRuleEventRelationDaoImpl;

	@Override
	public void addRule(ActivityRule rule, String events) {
		activityRuleDaoImpl.add(rule);
		//创建规则事件关系bean
		if(rule.getId() != null && StringUtils.isNotEmpty(events)) {
			String[] eventIdArr = events.split(",");
			ActivityRuleEventRelation activityRuleEventRelation = null;
			for(String eventId : eventIdArr) {
				activityRuleEventRelation = new ActivityRuleEventRelation();
				activityRuleEventRelation.setEventId(Long.valueOf(eventId));
				activityRuleEventRelation.setRuleId(rule.getId());
				activityRuleEventRelation.setRuleCode(rule.getRuleCode());
				activityRuleEventRelation.setCreator(rule.getCreator());
				
				activityRuleEventRelationDaoImpl.add(activityRuleEventRelation);
			}
		}
	}

	@Override
	public void deleteRuleById(Long id) {

	}

	@Override
	public void updateActivityRuleById(ActivityRule rule) {
		activityRuleDaoImpl.update(rule);
	}

	@Override
	public List<ActivityRule> selectByParams(Map<String, Object> params) {
		return activityRuleDaoImpl.selectByParams(params);
	}

	@Override
	public ActivityRule selectRuleById(Long id) {
		return (ActivityRule) activityRuleDaoImpl.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public void updateRuleById(ActivityRule rule,String events) {
		//1.根据id查询旧规则的信息
		ActivityRule activityRule = (ActivityRule) activityRuleDaoImpl.queryOne("selectByPrimaryKey", rule.getId());
		//2.删除规则关联的事件列表
		activityRuleEventRelationDaoImpl.delete("deleteByRuleId", rule.getId());
		//2.根据id修改规则，包括状态改为待审核
		rule.setVersion(activityRule.getVersion());
		rule.setRuleStatus(CouponRuleStatusEnum.CHECKING);
		activityRuleDaoImpl.update(rule);
		//3.添加规则与事件关联关系
		if(StringUtils.isNotEmpty(events)) {
			ActivityRuleEventRelation activityRuleEventRelation = null;
			String[] eventIdArr = events.split(",");
			for(String eventId : eventIdArr) {
				activityRuleEventRelation = new ActivityRuleEventRelation();
				activityRuleEventRelation.setEventId(Long.valueOf(eventId));
				activityRuleEventRelation.setRuleId(rule.getId());
				activityRuleEventRelation.setRuleCode(rule.getRuleCode());
				activityRuleEventRelation.setCreator(rule.getCreator());
				activityRuleEventRelationDaoImpl.add(activityRuleEventRelation);
			}
		}
		
	}

}
