package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityInvForProRuleXT;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProRuleXTDTO;

public interface ActivityInvForProRuleService {

	/**
	 * 新增投资换产品规则信息
	 * @param ActivityInvForProRule
	 * @return
	 */
	public ActivityInvForProRuleXT selectInvForProRuleById(long id);
	/**
	 * 新增投资换产品规则信息
	 * @param ActivityInvForProRule
	 * @return
	 */
	public void addActivityInvForProRule(ActivityInvForProRuleXT activityInvForProRule);
	
	/**
	 * 根据条件查询投资换产品规则列表
	 * 条件包含（投资换产品规则名称、类型、状态、时间有效期等）
	 * @param params
	 * @return
	 */
	public List<ActivityInvForProRuleXT> selectListByParams(Map<String, Object> params);
	
	/**
	 * 查询有效投资换产品规则列表
	 * 条件包含（投资换产品规则状态为审核通过）
	 * @param params
	 * @return
	 */
	public List<ActivityInvForProRuleXT> selectEffInvForProRuleList();
	
	/**
	 * 查询事件关联投资换产品规则
	 * 条件包含（投资换产品规则状态，隐含条件：截止日期大于当前日期）
	 * @param params
	 * @return
	 */
	public List<ActivityInvForProRuleXT> selectByEventCode(Map<String, Object> params);
	
	/**
	 * 根据主键ID更新投资换产品规则信息（审核时更新投资换产品规则状态、操作人信息）
	 * @param ActivityInvForProRule
	 */
	public void updateActivityInvForProRuleById(ActivityInvForProRuleXT activityInvForProRule);
	/**
	 * 根据产品id查询规则
	 * @param id
	 * @return
	 */
	public List<ActivityInvForProRuleXT> selectRuleByProductId(Long id);
	/**
	 * 查询所有的规则
	 * @return
	 */
	public List<ActivityInvForProRuleXT> selectForProRuleList();


}
