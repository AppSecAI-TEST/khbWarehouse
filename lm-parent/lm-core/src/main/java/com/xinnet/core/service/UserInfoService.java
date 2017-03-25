package com.xinnet.core.service;

import java.util.Map;

import com.xinnet.core.entity.User;
import com.xinnet.core.entity.UserInfo;

public interface UserInfoService {

	/**
	 * 会员编号查询用户活动详细信息
	 * @param memberNo
	 * @return ActivityUserInfo
	 */
	UserInfo selectUserInfoByMemberNo(String memberNo);
	
	/**
	 * 更新或修改用户分值信息
	 * @param userInfo
	 * @param memberNo
	 * @param bindCardScore
	 */
	public void updateOrInitUserScore(UserInfo userInfo, String memberNo, int bindCardScore);

	/**
	 * 根据获取的用户的微信信息，保存
	 * @param userInfo
	 * @param resultMap
	 */
	void updateUserInfoWX(UserInfo userInfo,
			Map<String, Object> resultMap);

	public User selectUserInfoById(String id);

}
