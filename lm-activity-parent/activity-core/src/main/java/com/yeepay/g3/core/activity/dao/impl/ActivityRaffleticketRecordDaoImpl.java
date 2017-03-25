package com.yeepay.g3.core.activity.dao.impl;


import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityRaffleticketRecordDao;
import com.yeepay.g3.core.activity.entity.ActivityRaffleticketRecord;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;
@Repository
public class ActivityRaffleticketRecordDaoImpl extends GenericDaoDefault<ActivityRaffleticketRecord> implements ActivityRaffleticketRecordDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityRaffleticketRecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityRaffleticketRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

}