package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.service.ActivityInvForProInfoService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInfoDTO;
import com.yeepay.g3.facade.activity.enums.InvForProInfoStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProInfoFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;
@Service
public class ActivityInvForProInfoFacadeImpl implements ActivityInvForProInfoFacade {
	private Logger logger = LoggerFactory.getLogger(ActivityInvForProInfoFacadeImpl.class);
	@Autowired
	private ActivityInvForProInfoService activityInvForProInfoServiceImpl;

	@Override
	public ActivityInvForProInfoDTO selectInvForProInfoById(long id) {
		ActivityInvForProInfo activityInvForProInfo = new ActivityInvForProInfo();
		activityInvForProInfo = activityInvForProInfoServiceImpl.selectInvForProInfoById(id);
		ActivityInvForProInfoDTO invForProInfoDto = new ActivityInvForProInfoDTO();
		invForProInfoDto = EntityDTOConvert.toTarget(activityInvForProInfo, ActivityInvForProInfoDTO.class);
		return invForProInfoDto;
	}
	
	@Override
	public ActivityInvForProInfoDTO selectAllInvForProInfoById(long id) {
		ActivityInvForProInfoDTO invForProInfoDto = new ActivityInvForProInfoDTO();
		//先获取缓存数据
		ActivityInvForProInfoDTO data=(ActivityInvForProInfoDTO) SmartCacheUtils.get("ActivityInvForProInfoDTOForId"+id);
		logger.info("[selectAllInvForProInfoById] 缓存数据 没有打印图片 data={}",data);
		if(data!=null){
			invForProInfoDto=data;
		}else{//如果缓存没有则查数据库，再记入缓存
			ActivityInvForProInfo activityInvForProInfo = new ActivityInvForProInfo();
			activityInvForProInfo = activityInvForProInfoServiceImpl.selectAllInvForProInfoById(id);
			invForProInfoDto = EntityDTOConvert.toTarget(activityInvForProInfo, ActivityInvForProInfoDTO.class);
			logger.info("[selectAllInvForProInfoById] 缓存数据为空，查询数据库  没有打印图片  invForProInfoDto={}",invForProInfoDto);
			SmartCacheUtils.set("ActivityInvForProInfoDTOForId"+id, invForProInfoDto, Constant.REDIS_EXPIRE_TIME);//记入缓存
		}
		return invForProInfoDto;
	}

	@Override
	public void addActivityInvForProInfo(ActivityInvForProInfoDTO InvForProInfoDto,String ruleIdStr) {
		ActivityInvForProInfo activityInvForProInfo = new ActivityInvForProInfo();
		activityInvForProInfo = EntityDTOConvert.toTarget(InvForProInfoDto, ActivityInvForProInfo.class);
		activityInvForProInfo.setStatus(InvForProInfoStatusEnum.CHECKING);//初始化状态为待审核
		activityInvForProInfoServiceImpl.addActivityInvForProInfo(activityInvForProInfo,ruleIdStr);

	}

	/*@Override
	public List<ActivityInvForProInfoDTO> selectListByParams(ActivityInvForProInfoDTO InvForProInfoDto) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<ActivityInvForProInfoDTO> selectEffInvForProInfoList(ActivityInvForProInfoDTO invForProInfoDto) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null == invForProInfoDto) {
			return null;
		}
		params.put("status", invForProInfoDto.getStatus());
		List<ActivityInvForProInfo> InvForProInfoList = activityInvForProInfoServiceImpl.selectEffInvForProInfoList(params);
		List<ActivityInvForProInfoDTO> InvForProInfoDtoList = new ArrayList<ActivityInvForProInfoDTO>();
		InvForProInfoDtoList = EntityDTOConvert.toTragetList(InvForProInfoList, ActivityInvForProInfoDTO.class);
		return InvForProInfoDtoList;
	}

	@Override
	public void updateActivityInvForProAndRule(ActivityInvForProInfoDTO InvForProInfoDto,String ruleIdStr) {
		if(null != InvForProInfoDto && null != InvForProInfoDto.getId()) {
			ActivityInvForProInfo activityInvForProInfo = new ActivityInvForProInfo();
			activityInvForProInfo = EntityDTOConvert.toTarget(InvForProInfoDto, ActivityInvForProInfo.class);
			activityInvForProInfoServiceImpl.updateActivityInvForProAndRule(activityInvForProInfo,ruleIdStr);
			//清空缓存
			logger.info("[updateActivityInvForProAndRule] 清空缓存ActivityInvForProInfoDTOForId"+InvForProInfoDto.getId());
			SmartCacheUtils.remove("ActivityInvForProInfoDTOForId"+InvForProInfoDto.getId());
		}

	}

	@Override
	public void updateActivityInvForProStatusById(
			ActivityInvForProInfoDTO invForProInfoDto) {
		if(null != invForProInfoDto && null != invForProInfoDto.getId()) {
			ActivityInvForProInfo activityInvForProInfo = new ActivityInvForProInfo();
			activityInvForProInfo = EntityDTOConvert.toTarget(invForProInfoDto, ActivityInvForProInfo.class);
			activityInvForProInfoServiceImpl.updateActivityInvForProStatusById(activityInvForProInfo);
		}
	}

	

	

}
