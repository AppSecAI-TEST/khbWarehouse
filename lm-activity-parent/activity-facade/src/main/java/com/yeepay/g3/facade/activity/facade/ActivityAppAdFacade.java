/**
 * @author 陈大涛
 * 2016-8-3上午9:02:17
 */
package com.yeepay.g3.facade.activity.facade;

import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;

/**
 * @Description app广告
 * @author 陈大涛
 * 2016-8-3上午9:02:17
 */
public interface ActivityAppAdFacade {

	/**
	 * 新增app广告
	 * @author 陈大涛
	 * 2016-8-3上午9:03:00
	 */
	public void insertAppAd(ActivityAppAdDTO activityAppAdDTO);
	
	/**
	 * 更改app广告信息
	 * @author 陈大涛
	 * 2016-8-3上午9:32:49
	 */
	public void updateAppAd(ActivityAppAdDTO activityAppAdDTO);
	
	/**
	 * 根据id查询详情
	 * @author 陈大涛
	 * 2016-8-3上午9:37:13
	 */
	public ActivityAppAdDTO queryAppAdDetail(Long id);
	
	/**
	 * 查询唯一有效广告
	 * @author 陈大涛
	 * 2016-8-3下午4:44:30
	 */
	public ActivityAppAdDTO queryAppAdForApp();

}
