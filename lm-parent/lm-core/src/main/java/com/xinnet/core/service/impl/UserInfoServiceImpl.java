/**
 * @author hongbin.kang
 * @date 2016-6-1
 * @time 下午4:26:30
 */
package com.xinnet.core.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.core.dao.UserMapper;
import com.xinnet.core.entity.User;
import com.xinnet.core.entity.UserInfo;
import com.xinnet.core.service.IUserInfoService;

/**
 * @author hongbin.kang
 *
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

	/*@Autowired
	private UserInfoDao userInfoDao;*/
	
	@Resource
	private UserMapper userMapper;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Override
	public UserInfo selectUserInfoByMemberNo(String memberNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrInitUserScore(UserInfo userInfo, String memberNo,
			int bindCardScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInfoWX(UserInfo userInfo,
			Map<String, Object> resultMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User selectUserInfoById(String id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(Integer.valueOf(id));
	}
	
	
	

}
