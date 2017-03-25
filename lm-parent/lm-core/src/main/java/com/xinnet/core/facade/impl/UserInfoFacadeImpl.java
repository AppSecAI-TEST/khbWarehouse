/**
 * @author hongbin.kang
 * @date 2016-6-1
 * @time 下午4:10:41
 */
package com.xinnet.core.facade.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.core.entity.User;
import com.xinnet.core.service.UserInfoService;
import com.xinnet.core.utils.EntityDTOConvert;
import com.xinnet.facade.dto.UserInfoDTO;
import com.xinnet.facade.facade.UserInfoFacade;





/**
 * @author hongbin.kang
 *
 */
@Service
public class UserInfoFacadeImpl implements UserInfoFacade {
	
	@Autowired
	private UserInfoService userInfoServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoFacadeImpl.class);
	@Override
	public UserInfoDTO selectUserInfoByMemberNo(String memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyUserInfoByWX(UserInfoDTO userInfo, String openId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> queryWXUserInfo(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoDTO selectUserInfoById(String id) {
		User user = userInfoServiceImpl.selectUserInfoById(id);
		UserInfoDTO dto = EntityDTOConvert.toTarget(user, UserInfoDTO.class);
		return dto;
	}

	
	
}
