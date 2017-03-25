package com.yeepay.g3.core.activity.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivitySrcFlowPlat;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowPlatService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowPlatDTO;
import com.yeepay.g3.facade.activity.facade.ActivitySrcFlowPlatFacade;

@Service
public class ActivitySrcFlowPlatFacadeImpl implements ActivitySrcFlowPlatFacade{

	@Autowired
	private ActivitySrcFlowPlatService activitySrcFlowPlatServiceImpl;
	
	@Override
	public void addSrcFlowPlat(ActivitySrcFlowPlatDTO activitySrcFlowPlatDTO) {
		// TODO Auto-generated method stub
		ActivitySrcFlowPlat activitySrcFlowPlat=EntityDTOConvert.toTarget(activitySrcFlowPlatDTO, ActivitySrcFlowPlat.class);
		activitySrcFlowPlatServiceImpl.addSrcFlowPlat(activitySrcFlowPlat);
		
	}

	@Override
	public void deleteSrcFlowPlat(long id) {
		// TODO Auto-generated method stub
		activitySrcFlowPlatServiceImpl.deleteSrcFlowPlat(id);
		
	}

	@Override
	public void updateSrcFlowPlat(ActivitySrcFlowPlatDTO activitySrcFlowPlatDTO) {
		// TODO Auto-generated method stub
		ActivitySrcFlowPlat activitySrcFlowPlat=EntityDTOConvert.toTarget(activitySrcFlowPlatDTO, ActivitySrcFlowPlat.class);
		activitySrcFlowPlatServiceImpl.updateSrcFlowPlat(activitySrcFlowPlat);
		
	}

	@Override
	public ActivitySrcFlowPlatDTO selectSrcFlowPlat(long id) {
		// TODO Auto-generated method stub
		ActivitySrcFlowPlat activitySrcFlowPlat=activitySrcFlowPlatServiceImpl.selectSrcFlowPlat(id);
		ActivitySrcFlowPlatDTO activitySrcFlowPlatDTO=EntityDTOConvert.toTarget(activitySrcFlowPlat, ActivitySrcFlowPlatDTO.class);
		return activitySrcFlowPlatDTO;
	}

	@Override
	public ActivitySrcFlowPlatDTO getSrcPlowPlatBySrcNo(String srcNo,
			String mobileNo) {
		// TODO Auto-generated method stub
		ActivitySrcFlowPlat activitySrcFlowPlat=activitySrcFlowPlatServiceImpl.getSrcPlowPlatBySrcNo(srcNo,mobileNo);
		ActivitySrcFlowPlatDTO activitySrcFlowPlatDTO=EntityDTOConvert.toTarget(activitySrcFlowPlat, ActivitySrcFlowPlatDTO.class);
		return activitySrcFlowPlatDTO;
	}

}
