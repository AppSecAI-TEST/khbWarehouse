/**
 * 
 */
package com.yeepay.g3.core.activity.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityEventDao;
import com.yeepay.g3.core.activity.entity.ActivityEvent;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:37:43
 * @version 1.0
 */
@Repository
public class ActivityEventDaoImpl extends GenericDaoDefault<ActivityEvent> implements
		ActivityEventDao {

	@Override
	public ActivityEvent get(Serializable paramSerializable) {
		return null;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityEvent selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityEvent> selectByRuleId(Long ruleId) {
		return this.query("selectByRuleId", ruleId);
	}

}
