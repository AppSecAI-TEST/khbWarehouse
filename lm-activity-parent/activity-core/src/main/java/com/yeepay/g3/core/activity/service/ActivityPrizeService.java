/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:13:40
 */
package com.yeepay.g3.core.activity.service;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityPrize;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:13:40
 */
public interface ActivityPrizeService {
	/**
	 * 获取所有奖品
	 * @author cdt
	 * @date 2016-5-16
	 * @time 下午4:15:55
	 */
	public List<ActivityPrize> queryLeaveAll();
	
	/**
	 * 根据事件id获取对应的奖品信息
	 * @author cdt
	 * @date 2016-5-17
	 * @time 下午1:55:11
	 */
	public List<ActivityPrize> queryByActionId(Long ActionId);
	
	/**
	 * 根据奖品id获取奖品的Entity
	 * @author hongbin.kang
	 * 
	 */
	public ActivityPrize queryPrizeById(Long id);
	
	/**
	 * 根据主键ID更新奖品信息（审核时更新商品状态、操作人信息）
	 * @author hongbin.kang
	 * @param ActivityPrize
	 */
	public void updateActivityPrizeById(ActivityPrize activityPrize);

	/**
	 * 添加奖品
	 * @param activityPrize
	 */
	public void insertActivityPrize(ActivityPrize activityPrize);
	
	/**
	 * 根据活动编码查询有效奖品列表
	 * @author 陈大涛
	 * 2016-6-29下午2:20:49
	 */
	public List<ActivityPrize> queryByActionCode(String actionCode);
}
