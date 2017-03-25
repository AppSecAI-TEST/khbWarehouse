/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:10:41
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.service.ActivityActionService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityActionDTO;
import com.yeepay.g3.facade.activity.facade.ActivityActionFacade;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:10:41
 */
@Service
public class ActivityActionFacadeImpl implements ActivityActionFacade {
	@Autowired
	private ActivityActionService activityActionServiceImpl;
	
@Override
public void insertActivityAction(ActivityActionDTO actionDto,String prizes,String odds,String versions,String levels){
	ActivityAction activityAction=EntityDTOConvert.toTarget(actionDto, ActivityAction.class);
	activityActionServiceImpl.insertActivityAction(activityAction, prizes, odds,versions,levels);
}

@Override
public ActivityActionDTO getActionDetail(Long ActionId) {
	ActivityAction activityAction= activityActionServiceImpl.getActionDetail(ActionId);
	ActivityActionDTO activityActionDto=EntityDTOConvert.toTarget(activityAction, ActivityActionDTO.class);
	return activityActionDto;
}

@Override
public void updateActivityAction(ActivityActionDTO actionDto, String prizes,
		String odds, String versions,String levels) {
	ActivityAction action=EntityDTOConvert.toTarget(actionDto, ActivityAction.class);
	activityActionServiceImpl.updateActivityAction(action, prizes, odds, versions,levels);
}

@Override
public List<ActivityActionDTO> queryActionAll() {
	List<ActivityAction> activityActionList=activityActionServiceImpl.queryActionAll();
	List<ActivityActionDTO> result=EntityDTOConvert.toTragetList(activityActionList, ActivityActionDTO.class);
	return result;
}
}
