package com.xinnet.annotation;

import org.springframework.stereotype.Component;

import com.xinnet.entity.User;


/**
 * 通过线程局部变量管理当前登录用户对象
 */
@Component
public class UserHolder {
	//线程变量
	private static ThreadLocal<User> userLocal = new ThreadLocal<User>();
	/**
	 * 删除用户信息
	 */
	public void clean() {
		userLocal.remove();
	}
	/**
	 * 获取用户信息
	 */
	public User getUser() {
		return userLocal.get();
	}
	/**
	 * 设置用户信息
	 */
	public void setUser(User user) {
		userLocal.set(user);
	}

}
