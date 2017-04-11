package com.yeepay.g3.app.dto;

import java.io.Serializable;

import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;


/**
 * @Description 验证登录返回信息DTO
 * @author ping.zhu
 * @CreateTime 2016年8月9日  10:13:20
 * @version 1.0
 */
public class LoginResultDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8818084619134035238L;

	private MemberDto memberDto;
	
	private UserOpeDTO userOpeDTO;
	
	private LoginResultEnum resultMsg;
	
	private String userSessionKey;

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	public UserOpeDTO getUserOpeDTO() {
		return userOpeDTO;
	}

	public void setUserOpeDTO(UserOpeDTO userOpeDTO) {
		this.userOpeDTO = userOpeDTO;
	}

	public LoginResultEnum getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(LoginResultEnum resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public String getUserSessionKey() {
		return userSessionKey;
	}

	public void setUserSessionKey(String userSessionKey) {
		this.userSessionKey = userSessionKey;
	}

	@Override
	public String toString() {
		return "LoginResultDTO [memberDto=" + memberDto + ", userOpeDTO="
				+ userOpeDTO + ", resultMsg=" + resultMsg + ", userSessionKey="
				+ userSessionKey + "]";
	}
	

}
