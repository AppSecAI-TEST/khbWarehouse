package com.xinnet.entity;

public class UserS extends User {
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getResult(){
		return "shizheyangde";
	}
}