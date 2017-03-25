package com.yeepay.g3.facade.activity.facade;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.facade.activity.dto.ActivityUserInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;

/**
 * @author hongbin.kang
 * 
 */

public interface ActivityUserInfoFacade{
	
	/**
	 * 根据奖品id获取奖品的DTO
	 * @author hongbin.kang
	 * 
	 */
	public ActivityUserInfoDTO selectUserInfoByMemberNo(String memberNo);

	/**
	 * 更新奖品的信息
	 * @author hongbin.kang
	 * @param userPrizeDto
	 */
	public void updateActivityUserInfoById(ActivityUserPrizeDTO userPrizeDto);

	/**
	 * 根据openid查询用户的昵称等信息，存入用户信息表
	 * @author hongbin.kang
	 * @param userInfo
	 * @param openId
	 */
	public void modifyUserInfoByWX(ActivityUserInfoDTO userInfo, String openId);

	/**
	 * 从微信服务器获取用户的基本信息
	 * @param opind
	 * @return
	 */
	public Map<String, Object> queryWXUserInfo(String openId);
}