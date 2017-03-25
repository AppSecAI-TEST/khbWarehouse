/**
 * @author cdt
 * @date 2016-5-18
 * @time 下午4:39:09
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityInfo;
import com.yeepay.g3.core.activity.service.ActivityService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityInfoDTO;
import com.yeepay.g3.facade.activity.enums.ActivityStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityFacade;

/**
 * @author cdt
 * @date 2016-5-18
 * @time 下午4:39:09
 */
@Service
public class ActivityFacadeImpl implements ActivityFacade{
	@Autowired
	private ActivityService activityServiceImpl;
	@Override
	public void insertActivity(ActivityInfoDTO activityInfoDto, String actions,
			String creator) throws Exception {
		ActivityInfo activityInfo=EntityDTOConvert.toTarget(activityInfoDto, ActivityInfo.class);
		activityServiceImpl.insertActivity(activityInfo, actions, creator);
	}
	@Override
	public ActivityInfoDTO queryActivityById(Long id){
		ActivityInfo activityInfo=activityServiceImpl.queryActivityById(id);
		ActivityInfoDTO result=EntityDTOConvert.toTarget(activityInfo, ActivityInfoDTO.class);
		return result;
	}
	@Override
	public void updateActivity(ActivityInfoDTO activityInfoDto, String actions,
			String creator) throws Exception {
		// TODO Auto-generated method stub
		ActivityInfo activityInfo=EntityDTOConvert.toTarget(activityInfoDto, ActivityInfo.class);
		activityServiceImpl.updateActivity(activityInfo, actions, creator);
	}
	@Override
	public void checkActivity(ActivityInfoDTO activityInfoDto,String status,String CHECKOR) {
		if("EFFECTIVE".equals(status)){
			activityInfoDto.setActivityStatus(ActivityStatusEnum.EFFECTIVE);
		}else if("RETURN_BACK".equals(status)){
			activityInfoDto.setActivityStatus(ActivityStatusEnum.RETURN_BACK);
		}
		activityInfoDto.setCheckedTime(new Date());
		activityInfoDto.setCheckor(CHECKOR);
		ActivityInfo activityInfo=EntityDTOConvert.toTarget(activityInfoDto, ActivityInfo.class);
		activityServiceImpl.updateCheckActivity(activityInfo);
	}
	@Override
	public List<ActivityInfoDTO> selectEffActivityList() {
		// TODO Auto-generated method stub
		ActivityInfoDTO activityInfoDto = new ActivityInfoDTO();
		activityInfoDto.setActivityStatus(ActivityStatusEnum.EFFECTIVE);
		List<ActivityInfoDTO> activityInfoDtoList = new ArrayList<ActivityInfoDTO>();
		if(null != activityInfoDto) {
			ActivityInfo activityInfo=EntityDTOConvert.toTarget(activityInfoDto, ActivityInfo.class);
			List<ActivityInfo> activityList = activityServiceImpl.selectActivityList(activityInfo);
			activityInfoDtoList = EntityDTOConvert.toTragetList(activityList, ActivityInfoDTO.class);
		}
		return activityInfoDtoList;
	}
	@Override
	public String queryActivityCodeByActionCode(String actionCode) {
		String activityCode = activityServiceImpl.queryActivityCodeByActionCode(actionCode);
		return activityCode;
	}

}
