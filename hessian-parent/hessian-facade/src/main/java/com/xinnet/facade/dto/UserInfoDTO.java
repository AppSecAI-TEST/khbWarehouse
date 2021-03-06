package com.xinnet.facade.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author hongbin.kang
 * @date 2017年3月25日下午3:57:29
 */
public class UserInfoDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5032099682351966171L;

	private Integer id;
    
    private String userName;
    
    private String passWord;
    
    private String email;

    private Date creatTime;

    private byte[] headPortrait;

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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public byte[] getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(byte[] headPortrait) {
        this.headPortrait = headPortrait;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord="
				+ passWord + ", email=" + email + ", creatTime=" + creatTime
				+ "]";
	}
    
}