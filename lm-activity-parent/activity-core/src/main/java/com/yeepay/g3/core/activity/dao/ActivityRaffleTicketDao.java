package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityRaffleTicket;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityRaffleTicketDao extends GenericDao<ActivityRaffleTicket> {

	public int deleteByPrimaryKey(Long id);

	public ActivityRaffleTicket selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityRaffleTicket record);
}