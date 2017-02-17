package com.xinnet.service;

import com.xinnet.entity.User;

public interface IUserService {
	
	public User getUserById(int userId);
	
	public int adds(int a,int b);
	
	public void add(User record);
}
