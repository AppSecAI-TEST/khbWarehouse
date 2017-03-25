package com.yeepay.g3.core.activity.service;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityEvent;

/**
 * @Description 活动事件业务逻辑处理接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月25日 下午2:13:50
 * @version 1.0
 */
public interface ActivityEventService {

	/**
	 * 活动事件新增
	 * @param event
	 * @param coupons
	 */
	public void addEvent(ActivityEvent event, String coupons);
	
	/**
	 * 根据ID删除事件，及关联优惠券记录
	 * @param id
	 */
	public void deleteEventById(Long id);
	
	/**
	 * 查询事件列表
	 * @return
	 */
	public List<ActivityEvent> getAllEventList();

	/**
	 * 根据ID查询事件信息
	 * @param id
	 */
	public ActivityEvent selectEventById(Long id);

	/**
	 * 根据规则ID获取事件列表
	 * @param id
	 * @return
	 */
	public List<ActivityEvent> selectByRuleId(Long ruleId);
	
	/**
	 * 修改活动事件
	 * 包括：删除优惠券关联，更改事件，添加优惠券关联
	 * @param in - event
	 * @param in - coupons
	 * @return 
	 */
	public void updateEvent(ActivityEvent event, String coupons);
}
