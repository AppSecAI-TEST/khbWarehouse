package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityRule;

/**
 * @Description 活动规则业务逻辑处理接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月25日 下午2:13:50
 * @version 1.0
 */
public interface ActivityRuleService {

	/**
	 * 活动规则新增
	 * @param rule
	 * @param coupons
	 */
	public void addRule(ActivityRule rule, String events);
	
	/**
	 * 根据ID删除规则
	 * @param id
	 */
	public void deleteRuleById(Long id);
	
	/**
	 * 根据ID修改规则
	 * @param id
	 */
	public void updateActivityRuleById(ActivityRule rule);
	
	/**
	 * 修改规则，包括删除事件关联，修改规则，添加事件关联
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	public void updateRuleById(ActivityRule rule,String events);

	/**
	 * 根据参数获取规则列表
	 * @param params
	 * @return
	 */
	public List<ActivityRule> selectByParams(Map<String, Object> params);

	/**
	 * 根据ID查询规则信息
	 * @param id
	 */
	public ActivityRule selectRuleById(Long id);
}
