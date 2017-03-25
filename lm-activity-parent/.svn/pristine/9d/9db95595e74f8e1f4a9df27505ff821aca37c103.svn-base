/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityInvForProInfoDTO;

/**
 * @Description 投资换产品信息对外服务接口
 * @author hongbin.kang
 * @CreateTime 2016年7月27日 下午2:18:56
 * @version 1.0
 */
public interface ActivityInvForProInfoFacade {
	/**
	 * 根据主键id获取投资换产品信息(不包含图片信息)
	 */
	public ActivityInvForProInfoDTO selectInvForProInfoById(long id);
	
	/**
	 * 根据主键id获取投资换产品信息(不包含图片信息)
	 */
	public ActivityInvForProInfoDTO selectAllInvForProInfoById(long id);
	/**
	 * 保存投资换产品信息
	 * @param ruleIdStr 
	 */
	public void addActivityInvForProInfo(ActivityInvForProInfoDTO InvForProDto, String ruleIdStr);
	
	/**
	 * 根据实体类参数查询信息
	 * @param ActivityInvForProInfoDTO
	 * @return
	 */
//	public List<ActivityInvForProInfoDTO> selectListByParams(ActivityInvForProInfoDTO InvForProDto);
	 
	/**
	 * 查询有效投资换产品（有效状态）
	 * @param ActivityInvForProInfoDTO
	 * @return
	 */
	public List<ActivityInvForProInfoDTO> selectEffInvForProInfoList(ActivityInvForProInfoDTO InvForProDto);
	/**
	 * 更新实体类（审批状态、操作人信息）
	 * @param ruleIdStr 
	 * @param ActivityInvForProInfoDTO
	 */
	public void updateActivityInvForProAndRule(ActivityInvForProInfoDTO InvForProDto, String ruleIdStr);
	public void updateActivityInvForProStatusById(
			ActivityInvForProInfoDTO invForProInfoDto);
	
	
}
