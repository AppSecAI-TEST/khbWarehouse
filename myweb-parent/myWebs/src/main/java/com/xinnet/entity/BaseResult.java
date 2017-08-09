package com.xinnet.entity;

import java.io.Serializable;

public class BaseResult implements Serializable {  
	/**
	 * 
	 */
	private static final long serialVersionUID = -8217244775440295716L;
	
	private String result;  
	
	private String mseeage;
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getMseeage() {
		return mseeage;
	}
	
	public void setMseeage(String mseeage) {
		this.mseeage = mseeage;
	}  
	
	
}