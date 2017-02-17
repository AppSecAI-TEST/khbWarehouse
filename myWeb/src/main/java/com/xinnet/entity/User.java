package com.xinnet.entity;

import java.io.Serializable;

import com.xinnet.annotation.NotEmpty;
import com.xinnet.annotation.NotNull;


public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8983483272576663056L;

	private Integer id;
    
    @NotEmpty(name="用户名")
    private String userName;

    @NotEmpty(name="密码",maxLength=20)
//    @NotNull
    private String password;

//    @NotNull
    private Integer age;
    
    @NotEmpty(name="邮箱")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", age=" + age + ", email=" + email + "]";
	}

    
}