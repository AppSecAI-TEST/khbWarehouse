package com.xinnet.yeepay;

import java.io.Serializable;

public class YeepayDefault implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -663599785106227817L;

	private Integer id;

    private String name;

    private Integer age;

    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

	@Override
	public String toString() {
		return "YeepayDefault [id=" + id + ", name=" + name + ", age=" + age
				+ ", city=" + city + "]";
	}
}