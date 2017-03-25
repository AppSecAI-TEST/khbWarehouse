/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:57
 */
package com.yeepay.g3.core.activity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityPrizeDao;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:57
 */
@Repository
public class ActivityPrizeDaoImpl extends  GenericDaoDefault<ActivityPrize> implements ActivityPrizeDao {

	@Override
	public ActivityPrize selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ActivityPrize record) {
		return 0;
	}

	@Override
	public List<ActivityPrize> selectByActionId(Long id) {
		List<ActivityPrize> result=this.query("selectByActionId", id);
		return result;
	}

	@Override
	public List<ActivityPrize> selectLeaveAll() {
		List<ActivityPrize> result=this.query("selectLeaveAll");
		return result;
	}


}
