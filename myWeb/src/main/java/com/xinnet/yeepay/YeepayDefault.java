package com.xinnet.yeepay;


public class YeepayDefault implements EntityVersion<Long> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 483392172066434349L;

	/**
	 * 
	 */

	private Long id;
	
	private Long version;

    private String name;

    private Integer age;

    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setVersion(Long version){
    	this.version = version;
    }

	public Long getVersion(){
		return version;
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