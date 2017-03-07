package com.xinnet.service;

import com.xinnet.entity.RegisterResultDto;
import com.xinnet.entity.User;

public interface IUserService {
	
	public User getUserById(int userId);
	
	public RegisterResultDto add(User record,String code);

	User getUserByEmail(String email);

	User getUserByUserName(String userName);

	int adds(int a, int b);

	User getUserByParam(User record);
}
