package com.xinnet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5127853981202454040L;
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "telephone")
	private String telephone;

	public UserInfo() {
	}

	public UserInfo(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserInfo(int id, String name, Integer age, String telephone) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.telephone = telephone;
	}

	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


}
