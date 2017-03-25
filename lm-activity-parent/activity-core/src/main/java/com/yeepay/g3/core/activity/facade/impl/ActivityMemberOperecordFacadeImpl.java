package com.yeepay.g3.core.activity.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.core.activity.service.ActivityMemberOperecordService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityMemberOperecordDTO;
import com.yeepay.g3.facade.activity.facade.ActivityMemberOperecordFacade;

@Service
public class ActivityMemberOperecordFacadeImpl implements ActivityMemberOperecordFacade {
	@Autowired
	private ActivityMemberOperecordService activityMemberOperecordServiceImpl;

	@Override
	public List<ActivityMemberOperecordDTO> queryActivityMemberOperecordList(ActivityMemberOperecordDTO activityMemberOperecordDTO) {
		ActivityMemberOperecord activityMemberOperecord = new ActivityMemberOperecord();
		activityMemberOperecord = EntityDTOConvert.toTarget(activityMemberOperecordDTO, ActivityMemberOperecord.class);
		List<ActivityMemberOperecord> memberOperecordList = activityMemberOperecordServiceImpl.queryActivityMemberOperecordList(activityMemberOperecord);
		List<ActivityMemberOperecordDTO> result=EntityDTOConvert.toTragetList(memberOperecordList, ActivityMemberOperecordDTO.class);
		return result;
	}
}
