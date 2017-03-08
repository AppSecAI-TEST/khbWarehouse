package com.xinnet.entity;

public class LoginResultDto extends BaseResult {

	
	private static final long serialVersionUID = 4671032522984724800L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LoginResultDto [user=" + user + ", getResult()="
				+ getResult() + ", getMseeage()=" + getMseeage() + "]";
	}
	
	
}
