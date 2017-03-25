package com.yeepay.g3.core.activity.service;

import com.yeepay.g3.core.activity.entity.ActivityAppSuggestion;


/**
 * 
 * @Description APP用户反馈意见逻辑处理接口
 * @author ping.zhu
 * @CreateTime 2016年8月08日 下午4:09:23
 * @version 1.0
 */
public interface ActivityAppSuggestionService {
	
	/**
	 * 添加反馈意见
	 * @param activityAppSuggestion
	 */
	public void insertSuggestion(ActivityAppSuggestion activityAppSuggestion);
}
