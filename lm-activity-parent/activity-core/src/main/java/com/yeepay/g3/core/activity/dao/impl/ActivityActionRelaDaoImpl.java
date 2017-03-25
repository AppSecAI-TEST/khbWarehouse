/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午8:25:02
 */
package com.yeepay.g3.core.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityActionRelaDao;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.entity.ActivityActionRela;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午8:25:02
 */
@Repository
public class ActivityActionRelaDaoImpl extends  GenericDaoDefault<ActivityActionRela> implements ActivityActionRelaDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityActionRela selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityActionRela record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ActivityAction> queryActionByActivityId(Long id) {
		List<ActivityAction> result = this.query("queryActionByActivityId", id);
		return result;
	}

	@Override
	public void deleteByActivityId(Long id) {
		this.delete("deleteByActivityId", id);
		
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.dao.ActivityActionRelaDao#queryActivityByActionCode(java.lang.String)
	 */
	@Override
	public Integer queryActivityByActionCode(Map<String,String> params) {
		Integer result = (Integer)this.queryOne("queryActivityByActionCode", params);
		return result;
	}

}
