package com.yeepay.g3.facade.activity.facade;

import com.yeepay.g3.facade.activity.dto.ActivityAppSuggestionDTO;


/**
 * 
 * @Description APP用户反馈意见对外服务接口
 * @author ping.zhu
 * @CreateTime 2016年8月08日 下午4:12:53
 * @version 1.0
 */
public interface ActivityAppSuggestionFacade {
	
    /**
     * 添加app用户反馈意见
     * @param activityAppSuggestionDTO
     */
	public void addUserSuggerstion(ActivityAppSuggestionDTO activityAppSuggestionDTO);
}
