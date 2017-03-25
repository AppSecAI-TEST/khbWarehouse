package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityRuleEventRelation;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 事件规则关联数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午5:01:17
 * @version 1.0
 */
public interface ActivityRuleEventRelationDao extends GenericDao<ActivityRuleEventRelation> {

	public int deleteByPrimaryKey(Long id);

	public ActivityRuleEventRelation selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityRuleEventRelation record);
}