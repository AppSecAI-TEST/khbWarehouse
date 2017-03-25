/**
 * @author 陈大涛
 * 2016-8-2下午5:03:40
 */
package com.yeepay.g3.core.activity.dao.impl;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityAppAdDao;
import com.yeepay.g3.core.activity.entity.ActivityAppAd;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-2下午5:03:40
 */
@Repository
public class ActivityAppAdDaoImpl  extends  GenericDaoDefault<ActivityAppAd> implements ActivityAppAdDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityAppAd selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityAppAd record) {
		// TODO Auto-generated method stub
		return 0;
	}


}
