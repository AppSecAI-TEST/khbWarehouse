package com.yeepay.g3.core.activity.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yeepay.g3.core.activity.entity.ActivityAppSuggestion;
import com.yeepay.g3.core.activity.service.ActivityAppSuggestionService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityAppSuggestionDTO;
import com.yeepay.g3.facade.activity.facade.ActivityAppSuggestionFacade;

/**
 * @Description App用户反馈信息对外接口实现类
 * @author ping.zhu
 * @CreateTime 2016年8月08日 下午4:38:30
 * @version 1.0
 */
@Controller
public class ActivityAppSuggestionFacadeImpl implements ActivityAppSuggestionFacade{

	@Resource
	private ActivityAppSuggestionService activityAppSuggestionServiceImpl;
	
	@Override
	public void addUserSuggerstion(ActivityAppSuggestionDTO activityAppSuggestionDTO) {
		ActivityAppSuggestion activityAppSuggestion = EntityDTOConvert.toTarget(activityAppSuggestionDTO, ActivityAppSuggestion.class);
		activityAppSuggestionServiceImpl.insertSuggestion(activityAppSuggestion);
	}

}
