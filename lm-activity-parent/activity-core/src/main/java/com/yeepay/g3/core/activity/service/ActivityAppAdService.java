/**
 * @author 陈大涛
 * 2016-8-3上午9:04:34
 */
package com.yeepay.g3.core.activity.service;

import com.yeepay.g3.core.activity.entity.ActivityAppAd;
import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-3上午9:04:34
 */
public interface ActivityAppAdService {

	/**
	 * 新增app广告
	 * @author 陈大涛
	 * 2016-8-3上午9:05:20
	 */
	public void insertAppAd(ActivityAppAd activityAppAd);
	
	/**
	 * 更改app广告信息
	 * @author 陈大涛
	 * 2016-8-3上午9:32:49
	 */
	public void updateAppAd(ActivityAppAd activityAppAd);
	
	/**
	 * 根据id查询详情
	 * @author 陈大涛
	 * 2016-8-3上午9:37:13
	 */
	public ActivityAppAd queryAppAdDetail(Long id);
	
	/**
	 * 查询唯一有效广告
	 * @author 陈大涛
	 * 2016-8-3下午4:44:30
	 */
	public ActivityAppAd queryAppAdForApp();
}
