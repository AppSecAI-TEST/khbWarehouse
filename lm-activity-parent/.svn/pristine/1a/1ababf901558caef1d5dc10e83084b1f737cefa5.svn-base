package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityRaffleTicket;
import com.yeepay.g3.core.activity.service.ActivityMemberOperecordService;
import com.yeepay.g3.core.activity.service.ActivityRaffleTicketService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityRaffleTicketDTO;
import com.yeepay.g3.facade.activity.enums.RaffleTicketStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityRaffleTicketFacade;
@Service
public class ActivityRaffleTicketFacadeImpl implements
		ActivityRaffleTicketFacade {
	
	@Autowired
	private ActivityRaffleTicketService activityRaffleTicketServiceImpl;
	
	@Autowired
	private ActivityMemberOperecordService activityMemberOperecordServiceImpl;

	@Override
	public ActivityRaffleTicketDTO selectRaffleTicketById(long id) {
		ActivityRaffleTicket activityRaffleTicket = activityRaffleTicketServiceImpl.selectRaffleTicketById(id);
		ActivityRaffleTicketDTO activityRaffleTicketDto = new ActivityRaffleTicketDTO();
		activityRaffleTicketDto = EntityDTOConvert.toTarget(activityRaffleTicket, ActivityRaffleTicketDTO.class);
		return activityRaffleTicketDto;
	}

	@Override
	public void addActivityRaffleTicket(
			ActivityRaffleTicketDTO activityRaffleTicketDto) {
		ActivityRaffleTicket activityRaffleTicket = new ActivityRaffleTicket();
		activityRaffleTicket = EntityDTOConvert.toTarget(activityRaffleTicketDto, ActivityRaffleTicket.class);
		activityRaffleTicket.setCouponStatus(RaffleTicketStatusEnum.CHECKING);//将抽奖券的状态至为待审核
		activityRaffleTicketServiceImpl.addActivityRaffleTicket(activityRaffleTicket);

	}

	@Override
	public List<ActivityRaffleTicketDTO> selectListByParams(
			ActivityRaffleTicketDTO activityRaffleTicketDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityRaffleTicketDTO> selectEffRaffleTicketList(
			ActivityRaffleTicketDTO activityRaffleTicketDto) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("couponStatus", activityRaffleTicketDto.getCouponStatus());
		List<ActivityRaffleTicketDTO> raffleTicketDtoList = new ArrayList<ActivityRaffleTicketDTO>();
		//查询审核
		List<ActivityRaffleTicket> raffleTicketList = activityRaffleTicketServiceImpl.selectEffRaffleTicketList(param);
		raffleTicketDtoList = EntityDTOConvert.toTragetList(raffleTicketList, ActivityRaffleTicketDTO.class);
		return raffleTicketDtoList;
	}

	@Override
	public void updateActivityRaffleTicketById(
			ActivityRaffleTicketDTO activityRaffleTicketDto) {
		if(activityRaffleTicketDto != null && activityRaffleTicketDto.getId() != null) {
			//判断是否非法调用接口
			ActivityRaffleTicket activityRaffleTicket = new ActivityRaffleTicket();
			activityRaffleTicket = EntityDTOConvert.toTarget(activityRaffleTicketDto, ActivityRaffleTicket.class);
			activityRaffleTicketServiceImpl.updateActivityRaffleTicketById(activityRaffleTicket);
			
			
		}
	}

	@Override
	public void updateUserRaffleTicket(Long userMemberOperecordId) {
		
		activityMemberOperecordServiceImpl.updateUserRaffleTicket(userMemberOperecordId);
	}

}
