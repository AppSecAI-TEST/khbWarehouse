package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityActionDTO;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午3:54:02
 */

public interface ActivityPrizeFacade{
	/**
	 * 获取所有奖品
	 * @author cdt
	 * @date 2016-5-16
	 * @time 下午3:55:57
	 */
	public List<ActivityPrizeDTO> selectLeaveAll();

	/**
	 * 根据事件id获取对应的奖品信息
	 * @author cdt
	 * @date 2016-5-17
	 * @time 下午1:55:11
	 */
	public List<ActivityPrizeDTO> selectByActionId(Long ActionId);
	
	/**
	 * 根据奖品id获取奖品的DTO
	 * @author hongbin.kang
	 * 
	 */
	public ActivityPrizeDTO selectPrizeById(Long id);

	/**
	 * 更新奖品的信息
	 * @author hongbin.kang
	 * @param prizeDto
	 */
	public void updateActivityPrizeById(ActivityPrizeDTO prizeDto);

	/**
	 * 添加奖品
	 * @author hongbin.kang
	 * @param prizeDto
	 */
	public void addActivityPrize(ActivityPrizeDTO prizeDto);
	
	/**
	 * 根据活动编码查询有效奖品列表
	 * @author 陈大涛
	 * 2016-6-29下午2:22:33
	 */
	public List<ActivityPrizeDTO> queryByActionCode(String actionCode);
	
}