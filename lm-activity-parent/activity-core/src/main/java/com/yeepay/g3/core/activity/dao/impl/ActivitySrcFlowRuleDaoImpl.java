package com.yeepay.g3.core.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivitySrcFlowRuleDao;
import com.yeepay.g3.core.activity.entity.ActivitySrcFlowRule;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;
/**
 * @Title: 渠道发放流量规则服务类
 * @Description: 渠道发放流量规则服务dao实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午2:51:32
 * @version 2016-7-20
 */
@Repository
public class ActivitySrcFlowRuleDaoImpl extends GenericDaoDefault<ActivitySrcFlowRule> implements ActivitySrcFlowRuleDao {

	@Override
	public ActivitySrcFlowRule selectByPrimaryKey(Long id) {
		return (ActivitySrcFlowRule) this.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public void updateByPrimaryKey(ActivitySrcFlowRule activitySrcFlowRule) {
		this.update("updateByPrimaryKey", activitySrcFlowRule);
	}

	@Override
	public List<ActivitySrcFlowRule> selectByParams(Map<String, Object> params) {
		return this.query("selectByParams", params);
	}

}
