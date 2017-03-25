/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityEventDTO;

/**
 * @Description 活动事件管理对外服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:22:34
 * @version 1.0
 */
public interface ActivityEventFacade {

	/**
	 * 活动事件新增
	 * @param eventDto
	 * @param coupons
	 */
	public void addEvent(ActivityEventDTO eventDto, String coupons);
	
	/**
	 * 根据ID删除事件，及关联优惠券记录
	 * @param id
	 */
	public void deleteEventById(Long id);
	
	/**
	 * 查询所有事件
	 * @param params
	 * @return
	 */
	public List<ActivityEventDTO> getAllEventList();

	/**
	 * 根据ID查询事件信息
	 * @param id
	 */
	public ActivityEventDTO selectEventById(Long id);
	
	/**
	 * 根据规则ID查询事件列表
	 * @param ruleId
	 */
	public List<ActivityEventDTO> selectByRuleId(Long ruleId);
	
	/**
	 * 修改活动事件
	 * @param eventDto
	 * @param coupons
	 */
	public void updateEvent(ActivityEventDTO eventDto, String coupons);
	
}
