/**
 * 
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityEvent;
import com.yeepay.g3.core.activity.service.ActivityEventService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityEventDTO;
import com.yeepay.g3.facade.activity.facade.ActivityEventFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @Description 活动事件对外接口处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:32:56
 * @version 1.0
 */
@Service
public class ActivityEventFacadeImpl implements ActivityEventFacade {

	@Autowired
	private ActivityEventService activityEventServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityEventFacadeImpl.class);
	
	@Override
	public void addEvent(ActivityEventDTO eventDto, String coupons) {
		
		logger.info("[addEvent] eventDto={},coupons={}",eventDto,coupons);
		
		ActivityEvent activityEvent = new ActivityEvent();
		
		activityEvent = EntityDTOConvert.toTarget(eventDto, ActivityEvent.class);
		
		activityEventServiceImpl.addEvent(activityEvent, coupons);
	}

	@Override
	public void deleteEventById(Long id) {
	}

	@Override
	public List<ActivityEventDTO> getAllEventList() {
		List<ActivityEventDTO> list = null;
		
		List<ActivityEvent> eventList = activityEventServiceImpl.getAllEventList();
		if(eventList != null) {
			list = EntityDTOConvert.toTragetList(eventList, ActivityEventDTO.class);
		}
		logger.info("[getAllEventList] list={}",list);
		return list;
	}

	@Override
	public ActivityEventDTO selectEventById(Long id) {
		logger.info("[selectEventById] id={}",id);
		ActivityEventDTO activityEventDto = new ActivityEventDTO();
		ActivityEvent activityEvent = activityEventServiceImpl.selectEventById(id);
		activityEventDto = EntityDTOConvert.toTarget(activityEvent, ActivityEventDTO.class);
		logger.info("[selectEventById] activityEventDto={}",activityEventDto);
		return activityEventDto;
	}

	@Override
	public List<ActivityEventDTO> selectByRuleId(Long ruleId) {
		logger.info("[selectByRuleId] ruleId={}",ruleId);
		List<ActivityEventDTO> list = null;
		List<ActivityEvent> eventList = activityEventServiceImpl.selectByRuleId(ruleId);
		if(eventList != null) {
			list = EntityDTOConvert.toTragetList(eventList, ActivityEventDTO.class);
		}
		logger.info("[selectByRuleId] list={}",list);
		return list;
	}

	@Override
	public void updateEvent(ActivityEventDTO eventDto, String coupons) {
		
		logger.info("[updateEvent] eventDto={},coupons={}",eventDto,coupons);
		ActivityEvent activityEvent = new ActivityEvent();
		activityEvent = EntityDTOConvert.toTarget(eventDto, ActivityEvent.class);
		activityEventServiceImpl.updateEvent(activityEvent, coupons);
		
		
	}

}
