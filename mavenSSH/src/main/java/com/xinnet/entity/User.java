package com.xinnet.entity;

import java.io.Serializable;  

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.Id;  
  
@Entity(name = "user")  
public class User implements Serializable {  
    private static final long serialVersionUID = 3834833794454172304L;  
    private int id;  
    private String userName;  
    private String password;  
    private int gender;  
      
    @Id  
    @Column(name = "id", unique = true,  nullable = false, insertable = false, updatable = false)  
    public int getId() {  
        return id;  
    }  
      
    public void setId(int id) {  
        this.id = id;  
    }  
      
    @Column(name = "userName", nullable = false)  
    public String getUserName() {  
        return userName;  
    }  
      
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    @Column(name = "password", nullable = false)  
    public String getPassword() {  
        return password;  
    }  
      
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    @Column(name = "gender", nullable = false)  
    public int getGender() {  
        return gender;  
    }  
      
    public void setGender(int gender) {  
        this.gender = gender;  
    }  
}