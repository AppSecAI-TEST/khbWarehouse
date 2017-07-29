package com.xinnet.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.dao.IdentifyingCodeMapper;
import com.xinnet.dao.UserMapper;
import com.xinnet.entity.IdentifyingCode;
import com.xinnet.entity.LoginResultDto;
import com.xinnet.entity.RegisterResultDto;
import com.xinnet.entity.User;
import com.xinnet.service.IUserService;
import com.xinnet.utils.CheckParamUtils;
import com.xinnet.utils.EncryptUtils;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IdentifyingCodeMapper dentifyingCodeMapper;
	
	
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public User getUserByEmail(String email) {
		logger.info("[getUserByEmail]-email={}",email);
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(email)) {
			return null;
		}else {
			User record = new User();
			record.setEmail(email);
			return userMapper.selectByParam(record);
		}
	}
	
	@Override
	public User getUserByParam(User record) {
		logger.info("[getUserByParam]-User={}",record);
		return userMapper.selectByParam(record);
	}
	
	@Override
	public User getUserByUserName(String userName) {
		logger.info("[getUserByUserName]-userName={}",userName);
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(userName)) {
			return null;
		}else {
			User record = new User();
			record.setUserName(userName);
			return userMapper.selectByParam(record);
		}
	}

	@Override
	public int adds(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public RegisterResultDto add(User record,String code) {
		CheckParamUtils.isEmpty(record);
		RegisterResultDto dto = new RegisterResultDto();
		User user = getUserByEmail(record.getEmail());
		logger.info("[getUserByEmail]-User={}",user);
		user = getUserByUserName(record.getUserName());
		logger.info("[getUserByUserName]-User={}",user);
		if(null != user) {
			dto.setResult("false");
			dto.setMseeage("用户已经存在");
			logger.info("[RegisterResultDto]-dto={}",dto);
			return dto;
		}
		
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("email", record.getEmail());
		param.put("time", new Date());
		param.put("code", code);
		param.put("effective", "true");
		IdentifyingCode identifyingCode = dentifyingCodeMapper.selectByParam(param);
		if(null == identifyingCode) {
			dto.setResult("false");
			dto.setMseeage("验证码失效");
			logger.info("[RegisterResultDto]-dto={}",dto);
			return dto;
		} else {
			dto.setResult("success");
			identifyingCode.setEffective("false");
			dentifyingCodeMapper.updateByPrimaryKeySelective(identifyingCode);
		}
		record.setPassWord(EncryptUtils.MD5(record.getPassWord()));
		record.setCreatTime(new Date());
		userMapper.insertSelective(record);
		dto.setUser(record);
		logger.info("[RegisterResultDto]-dto={}",dto);
		return dto;
	}

	@Override
	public LoginResultDto loginByParam(String userName, String passWord) {
		logger.info("userName={},passWord={}",userName,passWord);
		LoginResultDto dto = new LoginResultDto();
		if(null == getUserByUserName(userName)) {
			dto.setResult("false");
			dto.setMseeage("该用户不存在");
			return dto;
		}
		User param = new User();
		param.setUserName(userName);
		param.setPassWord(EncryptUtils.MD5(passWord));
		
		User user = getUserByParam(param);
		if(null == user) {
			dto.setResult("false");
			dto.setMseeage("密码错误");
			return dto;
		}
		dto.setUser(user);
		dto.setResult("success");
		return dto;
	}

	@Override
	public void add(User record) throws Exception {
		userMapper.insertSelective(record);
		throw new Exception();
	}

	@Override
	public void batchInsert(List<User> uList) {
		userMapper.batchInsert(uList);
	}
}
