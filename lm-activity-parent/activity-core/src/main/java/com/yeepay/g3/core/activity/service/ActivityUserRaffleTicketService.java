package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityUserRaffleticket;
import com.yeepay.g3.facade.activity.dto.ActivityUserRaffleticketDTO;

public interface ActivityUserRaffleTicketService {

	List<ActivityUserRaffleticket> selectListByParams(ActivityUserRaffleticketDTO activityUserRaffleTicketDto);
	
	/**
	 * 根据会员编号，查询抽奖会员的手机号
	 * @author 陈大涛
	 * 2016-6-17上午9:27:40
	 */
	public ActivityUserRaffleticket queryUserRaffleticketByMemberNO(String memberNo);
	
	/**
	 * 批量发放抽奖券
	 * @author 陈大涛
	 * 2016-7-19下午6:10:29
	 */
	public void addActivityUserRaffleTicketList(String memberNoList,Integer num,Long id,Long version,Integer grantCount,String actionCode,String activityCode) throws Exception;
}
