package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityUserRaffleticket;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityUserRaffleticketDao extends GenericDao<ActivityUserRaffleticket> {

	public int deleteByPrimaryKey(Long id);

	public ActivityUserRaffleticket selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityUserRaffleticket record);
	
}