package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityRaffleTicket;


public interface ActivityRaffleTicketService {
	/**
	 * 根据主键id获取抽奖券信息
	 */
	public ActivityRaffleTicket selectRaffleTicketById(long id);
	/**
	 * 保存抽奖券信息
	 */
	public void addActivityRaffleTicket(ActivityRaffleTicket activityRaffleTicket);
	
	/**
	 * 根据实体类参数查询信息
	 * @param ActivityRaffleTicket
	 * @return
	 */
	public List<ActivityRaffleTicket> selectListByParams(ActivityRaffleTicket activityRaffleTicket);
	 
	/**
	 * 查询有效抽奖券（有效状态，截止日期大于当前期）
	 * @param ActivityRaffleTicket
	 * @return
	 */
	public List<ActivityRaffleTicket> selectEffRaffleTicketList(Map<String, Object> param);
	/**
	 * 更新实体类（审批状态、操作人信息）
	 * @param ActivityRaffleTicket
	 */
	public void updateActivityRaffleTicketById(ActivityRaffleTicket activityRaffleTicket);
	
}
