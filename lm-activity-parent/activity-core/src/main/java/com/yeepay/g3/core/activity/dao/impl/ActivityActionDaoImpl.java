/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:57
 */
package com.yeepay.g3.core.activity.dao.impl;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityActionDao;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:57
 */
@Repository
public class ActivityActionDaoImpl extends  GenericDaoDefault<ActivityAction> implements ActivityActionDao {
	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}
	@Override
	public int updateByPrimaryKey(ActivityAction record) {
		return 0;
	}
	@Override
	public ActivityAction selectByPrimaryKey(Long id) {
	//TODO
		return null;
	}
}
