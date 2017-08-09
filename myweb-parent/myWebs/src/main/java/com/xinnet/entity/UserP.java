package com.xinnet.entity;

public class UserP extends User {
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getResult(){
		return "这是在P里面";
	}

	@Override
	public String toString() {
		return "UserP [sex=" + sex + ", getId()=" + getId()
				+ ", getUserName()=" + getUserName() + ", getPassword()="
				+ getPassWord()+"]";
	}
	
	
	
}