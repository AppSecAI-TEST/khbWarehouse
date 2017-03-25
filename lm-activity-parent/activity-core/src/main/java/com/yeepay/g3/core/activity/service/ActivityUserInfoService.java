package com.yeepay.g3.core.activity.service;

import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityUserInfo;
import com.yeepay.g3.facade.activity.dto.ActivityUserInfoDTO;

public interface ActivityUserInfoService {

	/**
	 * 会员编号查询用户活动详细信息
	 * @param memberNo
	 * @return ActivityUserInfo
	 */
	ActivityUserInfo selectUserInfoByMemberNo(String memberNo);
	
	/**
	 * 更新或修改用户分值信息
	 * @param userInfo
	 * @param memberNo
	 * @param bindCardScore
	 */
	public void updateOrInitUserScore(ActivityUserInfo userInfo, String memberNo, int bindCardScore);

	/**
	 * 根据获取的用户的微信信息，保存
	 * @param userInfo
	 * @param resultMap
	 */
	void updateUserInfoWX(ActivityUserInfo userInfo,
			Map<String, Object> resultMap);

}
