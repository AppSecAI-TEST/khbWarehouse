/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import com.yeepay.g3.facade.activity.dto.ActivityRuleDTO;

/**
 * @Description 活动规则管理对外服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:22:34
 * @version 1.0
 */
public interface ActivityRuleFacade {

	/**
	 * 活动规则新增
	 * @param ruleDto
	 * @param events
	 */
	public void addRule(ActivityRuleDTO ruleDto, String events);
	
	/**
	 * 根据ID删除规则，及关联优惠券记录
	 * @param id
	 */
	public void deleteRuleById(Long id);
	
	/**
	 * 根据ID审核规则记录
	 * @param id
	 */
	public void updateActivityRuleById(ActivityRuleDTO ruleDto);

	/**
	 * 根据ID查询规则信息
	 * @param id
	 */
	public ActivityRuleDTO selectRuleById(Long id);
	
	/**
	 * 修改规则，及对应事件
	 * @param in - ruleDto
	 * @param in - events
	 * @return 
	 */
	public void updateRuleById(ActivityRuleDTO ruleDto,String events);
}
