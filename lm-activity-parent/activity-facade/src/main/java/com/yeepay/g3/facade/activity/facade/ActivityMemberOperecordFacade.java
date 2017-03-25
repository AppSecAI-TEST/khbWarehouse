package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityMemberOperecordDTO;

/**
 * 
 * @author hongbin.kang
 * @date 2016年10月9日上午11:39:24
 */
public interface ActivityMemberOperecordFacade{
	
	/**
	 * 查询用户的操作记录
	 * @author hongbin.kang
	 * @date 2016年10月9日 上午11:40:26
	 * @return
	 */
	public List<ActivityMemberOperecordDTO> queryActivityMemberOperecordList(ActivityMemberOperecordDTO activityMemberOperecordDTO);
	
}