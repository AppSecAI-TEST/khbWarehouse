package com.yeepay.g3.core.activity.dao;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivitySrcFlowRule;
import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * @Title: 渠道发放流量规则服务接口
 * @Description: 渠道发放流量规则服务dao
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午12:25:40
 * @version 2016-7-20
 */

public interface ActivitySrcFlowRuleDao extends GenericDao<ActivitySrcFlowRule> {

	public ActivitySrcFlowRule selectByPrimaryKey(Long id);

	public void updateByPrimaryKey(ActivitySrcFlowRule srcFlowRule);
	
	public List<ActivitySrcFlowRule> selectByParams(Map<String, Object> params);
}