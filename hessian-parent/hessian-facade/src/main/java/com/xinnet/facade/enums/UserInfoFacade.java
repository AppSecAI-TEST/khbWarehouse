package com.xinnet.facade.enums;

import java.util.Map;

import com.xinnet.facade.dto.UserInfoDTO;

/**
 * @author hongbin.kang
 * 
 */

public interface UserInfoFacade{
	
	/**
	 * 根据奖品id获取奖品的DTO
	 * @author hongbin.kang
	 * 
	 */
	public UserInfoDTO selectUserInfoByMemberNo(String memberNo);


	public UserInfoDTO selectUserInfoById(String id);
	/**
	 * 根据openid查询用户的昵称等信息，存入用户信息表
	 * @author hongbin.kang
	 * @param userInfo
	 * @param openId
	 */
	public void modifyUserInfoByWX(UserInfoDTO userInfo, String openId);

	/**
	 * 从微信服务器获取用户的基本信息
	 * @param opind
	 * @return
	 */
	public Map<String, Object> queryWXUserInfo(String openId);
}