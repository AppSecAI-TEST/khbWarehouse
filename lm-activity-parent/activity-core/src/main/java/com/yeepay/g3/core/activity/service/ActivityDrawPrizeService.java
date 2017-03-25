/**
 * @author 陈大涛
 * 2016-6-1下午3:56:46
 */
package com.yeepay.g3.core.activity.service;

import com.yeepay.g3.core.activity.entity.ActivityPrize;

/**
 * @author 陈大涛
 * 2016-6-1下午3:56:46
 */
public interface ActivityDrawPrizeService {

	/**
	 * 大转盘抽奖
	 * @author 陈大涛
	 * 2016-6-1下午4:02:54
	 */
	public ActivityPrize updateUserPrize(String memberNo,String actionCode,String activityCode) throws Exception ;
	
	/**
	 * 大转盘抽奖内定接口
	 * @author 陈大涛
	 * 2016-6-29上午10:06:14
	 */
	public ActivityPrize updateUserPrizeSpecial (String memberNo,String actionCode,String activityCode,Integer prizeId) throws Exception ;
}
