package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivitySrcFlowRuleDao;
import com.yeepay.g3.core.activity.entity.ActivitySrcFlowRule;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowRuleService;
/**
 * @Title: 渠道发放流量规则业务逻辑处理实现类
 * @Description: 渠道发放流量规则业务逻辑处理service实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午3:00:51
 * @version 2016-7-20
 */
@Service
public class ActivitySrcFlowRuleServiceImpl implements
		ActivitySrcFlowRuleService {

	@Autowired
	private ActivitySrcFlowRuleDao activitySrcFlowRuleDaoImpl;
	@Override
	public void deleteByPrimaryKey(Long id) {
		activitySrcFlowRuleDaoImpl.delete("deleteByPrimaryKey", id);
	}

	@Override
	public void addSrcFlowRule(ActivitySrcFlowRule activitySrcFlowRule) {
		activitySrcFlowRuleDaoImpl.add(activitySrcFlowRule);
	}

	@Override
	public ActivitySrcFlowRule selectByPrimaryKey(Long id) {
		return activitySrcFlowRuleDaoImpl.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(ActivitySrcFlowRule activitySrcFlowRule) {
		activitySrcFlowRuleDaoImpl.updateByPrimaryKey(activitySrcFlowRule);
	}

	@Override
	public List<ActivitySrcFlowRule> selectRuleListByParams(
			Map<String, Object> map) {
		return activitySrcFlowRuleDaoImpl.selectByParams(map);
	}

}
