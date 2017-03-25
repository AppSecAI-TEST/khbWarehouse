package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivitySrcFlowRule;

/**
 * @Title: 渠道发放流量规则逻辑处理接口
 * @Description: 渠道发放流量规则逻辑处理service
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午2:52:30
 * @version 2016-7-20
 */
public interface ActivitySrcFlowRuleService {
	/**
	 * 根据id删除一条规则
	 * @param in -Long id
	 * @return 
	 */
    public void deleteByPrimaryKey(Long id);

    /**
     * 添加一条规则
     * @param in -ActivitySrcFlowRule activitySrcFlowRule
     * @return 
     */
    public void addSrcFlowRule(ActivitySrcFlowRule activitySrcFlowRule);

    /**
     * 根据id查询单条规则
     * @param in - Long id
     * @return ActivitySrcFlowRule
     */
    public ActivitySrcFlowRule selectByPrimaryKey(Long id);
    
    /**
     * 根据id修改一条规则
     * @param in - ActivitySrcFlowRule activitySrcFlowRule
     * @return 
     */
    public void updateByPrimaryKey(ActivitySrcFlowRule activitySrcFlowRule);
    
    /**
     * 根据某些条件查询规则
     * @param in - Map<String,Object> map，参数
     * @return List<ActivitySrcFlowRule>
     */
    public List<ActivitySrcFlowRule> selectRuleListByParams(Map<String,Object> map);
    
}