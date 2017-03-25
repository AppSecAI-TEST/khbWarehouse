package com.yeepay.g3.facade.activity.facade;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowRuleDTO;

/**
 * @Title: 渠道发放流量规则业务处理逻辑接口
 * @Description: 渠道发放流量规则业务facade
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午3:56:01
 * @version 2016-7-20
 */
public interface ActivitySrcFlowRuleFacade{
	
	/**
	 * 添加渠道发放流量的规则
	 * @param in - ActivitySrcFlowRuleDTO activitySrcFlowRuleDTO
	 * @return 
	 */
	public void addSrcFlowRule(ActivitySrcFlowRuleDTO ActivitySrcFlowRuleDTO);
	
	/**
	 * 修改渠道发放流量规则
	 * @param in - ActivitySrcFlowRuleDTO ActivitySrcFlowRuleDTO
	 * @return 
	 */
	public void updateSrcFlowRule(ActivitySrcFlowRuleDTO ActivitySrcFlowRuleDTO);
	
	/**
	 * 根据id查询渠道发放流量规则
	 * @param in - id
	 * @return ActivitySrcFlowRuleDTO
	 */
	public ActivitySrcFlowRuleDTO selectSrcFlowRuleById(Long id);
	
	/**
	 * 根据id删除渠道发放流量规则
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	public void deleteSrcFlowRule(Long id);
	
	/**
	 * 查询规则是否存在
	 * @param in - Map<String,String>
	 * @return ActivitySrcFlowRuleDTO
	 */
	public List<ActivitySrcFlowRuleDTO> getSrcFlowRule(Map<String,Object> map);
}