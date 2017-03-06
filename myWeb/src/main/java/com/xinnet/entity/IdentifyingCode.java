package com.xinnet.entity;

import java.io.Serializable;
import java.util.Date;

public class IdentifyingCode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5673837063771708198L;

	private Integer id;
	
	private String mode;
	
	private String email;
	
	private String code;
	
	private Date creatTime;
	
	private String effective = "true";
	
	private Date effectiveTime;
	
	public IdentifyingCode(){
		
	}
	
	public IdentifyingCode(String mode, String email,String code,Date creatTime,Date effectiveTime) {
		this.mode = mode;
		this.email = email;
		this.code = code;
		this.creatTime = creatTime;
		this.effectiveTime = effectiveTime;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode == null ? null : mode.trim();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}
	
	public Date getCreatTime() {
		return creatTime;
	}
	
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	public String getEffective() {
		return effective;
	}
	
	public void setEffective(String effective) {
		this.effective = effective == null ? null : effective.trim();
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
}