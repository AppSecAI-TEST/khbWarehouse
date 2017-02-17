package com.xinnet.action;

import com.xinnet.service.IUserService;
import com.xinnet.service.impl.UserServiceImpl;

public class Proxyu {

	public static void main(String[] args) {
		IUserService u = new UserServiceImpl();
		JDKProxy proxy = new JDKProxy(u);
		IUserService proxyUser = (IUserService) proxy.createProxyInstance(u);
		Integer a = proxyUser.adds(2, 3);
		System.out.println(a);
		
		
		
		
	}
}
