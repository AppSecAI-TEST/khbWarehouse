package com.yeepay.g3.core.activity.dao.impl;
import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityRaffleTicketDao;
import com.yeepay.g3.core.activity.entity.ActivityRaffleTicket;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * 抽奖券的服务
 * @author Admin
 *
 */
@Repository
public class ActivityRaffleTicketDaoImpl extends GenericDaoDefault<ActivityRaffleTicket> implements
		ActivityRaffleTicketDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityRaffleTicket selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityRaffleTicket record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}