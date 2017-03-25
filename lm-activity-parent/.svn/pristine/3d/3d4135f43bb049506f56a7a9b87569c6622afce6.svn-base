
package com.yeepay.g3.core.activity.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityPrizeDao;
import com.yeepay.g3.core.activity.dao.ActivityUserPrizeDao;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @author hongbin.kang
 * 
 */
@Repository
public class ActivityUserPrizeDaoImpl extends  GenericDaoDefault<ActivityUserPrize> implements ActivityUserPrizeDao {


	@Override
	public ActivityUserPrize selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityUserPrize record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ActivityUserPrize> selectByPrizeId(Long id) {
		return this.query("selectByPrizeId", id);
	}}
