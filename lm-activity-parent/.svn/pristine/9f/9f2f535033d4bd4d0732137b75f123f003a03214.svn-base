package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInfoDTO;

/**
 * @Description 投资换产品信息业务逻辑处理接口
 * @author hongbin.kang
 * @CreateTime 2016年7月27日 下午2:13:50
 * @version 1.0
 */
public interface ActivityInvForProInfoService {

	/**
	 * 根据主键id获取投资换产品信息(不包含图片信息)
	 */
	public ActivityInvForProInfo selectInvForProInfoById(long id);
	
	/**
	 * 根据主键id获取投资换产品信息(不包含图片信息)
	 */
	public ActivityInvForProInfo selectAllInvForProInfoById(long id);
	/**
	 * 新增投资换产品信息
	 * @param ruleIdStr 
	 * @param ActivityInvForProInfo
	 * @return
	 */
	public void addActivityInvForProInfo(ActivityInvForProInfo activityInvForProInfo, String ruleIdStr);
	
	/**
	 * 根据条件查询投资换产品列表
	 * 条件包含（投资换产品名称、类型、状态、时间有效期等）
	 * @param params
	 * @return
	 */
	public List<ActivityInvForProInfo> selectListByParams(Map<String, Object> params);
	
	/**
	 * 查询有效投资换产品列表
	 * 条件包含（投资换产品状态为审核通过）
	 * @param params
	 * @return
	 */
	public List<ActivityInvForProInfo> selectEffInvForProInfoList(Map<String, Object> params);
	
	/**
	 * 查询事件关联投资换产品
	 * 条件包含（投资换产品状态，隐含条件：截止日期大于当前日期）
	 * @param params
	 * @return
	 */
	public List<ActivityInvForProInfo> selectByEventCode(Map<String, Object> params);
	
	/**
	 * 改变规则关联
	 */
	public void updateActivityInvForProAndRule(ActivityInvForProInfo activityInvForProInfo, String ruleIdStr);
	public void updateActivityInvForProStatusById(
			ActivityInvForProInfo activityInvForProInfo);
}
