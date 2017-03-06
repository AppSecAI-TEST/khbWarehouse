package com.xinnet.service;

import com.xinnet.entity.RegisterResultDto;
import com.xinnet.entity.User;

public interface IUserService {
	
	public User getUserById(int userId);
	
	public int adds(int a,int b);
	
	public RegisterResultDto add(User record,String code);
}
