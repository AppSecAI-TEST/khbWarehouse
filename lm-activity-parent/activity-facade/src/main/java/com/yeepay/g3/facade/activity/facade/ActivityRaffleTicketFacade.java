/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityRaffleTicketDTO;

/**
 * @Description 抽奖券信息对外服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午8:18:56
 * @version 1.0
 */
public interface ActivityRaffleTicketFacade {
	/**
	 * 根据主键id获取抽奖券信息
	 */
	public ActivityRaffleTicketDTO selectRaffleTicketById(long id);
	/**
	 * 保存抽奖券信息
	 */
	public void addActivityRaffleTicket(ActivityRaffleTicketDTO activityRaffleTicketDto);
	
	/**
	 * 根据实体类参数查询信息
	 * @param ActivityRaffleTicketDTO
	 * @return
	 */
	public List<ActivityRaffleTicketDTO> selectListByParams(ActivityRaffleTicketDTO activityRaffleTicketDto);
	 
	/**
	 * 查询有效抽奖券（有效状态，截止日期大于当前期）
	 * @param ActivityRaffleTicketDTO
	 * @return
	 */
	public List<ActivityRaffleTicketDTO> selectEffRaffleTicketList(ActivityRaffleTicketDTO activityRaffleTicketDto);
	/**
	 * 更新实体类（审批状态、操作人信息）
	 * @param ActivityRaffleTicketDTO
	 */
	public void updateActivityRaffleTicketById(ActivityRaffleTicketDTO activityRaffleTicketDto);
	

	/**
	 * 根据会员记录ID补发抽奖券
	 * @param userMemberOperecordId
	 */
	public void updateUserRaffleTicket(Long userMemberOperecordId);
	
}
