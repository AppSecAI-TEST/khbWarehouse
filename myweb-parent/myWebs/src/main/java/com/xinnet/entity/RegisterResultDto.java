package com.xinnet.entity;

public class RegisterResultDto extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829452664442335376L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RegisterResultDto [user=" + user + ", getResult()="
				+ getResult() + ", getMseeage()=" + getMseeage() + "]";
	}
	
	
}
