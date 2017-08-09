package com.xinnet.service;

import java.util.List;

import com.xinnet.entity.LoginResultDto;
import com.xinnet.entity.RegisterResultDto;
import com.xinnet.entity.User;

public interface IUserService {
	
	public User getUserById(int userId);
	
	public RegisterResultDto add(User record,String code);

	User getUserByEmail(String email);

	User getUserByUserName(String userName);

	int adds(int a, int b);

	User getUserByParam(User record);

	public LoginResultDto loginByParam(String userName, String passWord);
	
	void add(User record) throws Exception;
	
	void batchInsert(List<User> uList);
}
