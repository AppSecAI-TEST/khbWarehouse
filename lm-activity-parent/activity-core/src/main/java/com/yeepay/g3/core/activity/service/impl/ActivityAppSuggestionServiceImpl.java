package com.yeepay.g3.core.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityAppSuggestionDao;
import com.yeepay.g3.core.activity.entity.ActivityAppSuggestion;
import com.yeepay.g3.core.activity.service.ActivityAppSuggestionService;

/**
 * 
 * @Description APP用户反馈意见逻辑处理实现类
 * @author ping.zhu
 * @CreateTime 2016年8月08日 下午4:12:53
 * @version 1.0
 */
@Service
public class ActivityAppSuggestionServiceImpl implements ActivityAppSuggestionService{

	@Autowired
	private ActivityAppSuggestionDao activityAppSuggestionDaoImpl;
	
	@Override
	public void insertSuggestion(ActivityAppSuggestion activityAppSuggestion) {
		activityAppSuggestionDaoImpl.add(activityAppSuggestion);
	}

}
