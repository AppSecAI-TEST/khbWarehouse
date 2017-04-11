package com.yeepay.g3.app.dto;

import java.io.Serializable;

import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;

public class UserOpeDTO implements Serializable {
	/**
	 * 请求处理结果
	 */
	protected ResultCodeEnum resultCode;
	
	/**
	 * 请求结果码
	 */
	protected String errorCode;
	
	/**
	 * 请求结果提示信息
	 */
	protected String errorMsg;
	
	/**
	 * 用户登录唯一标识
	 */
	protected String userSessionKey;
	
	/**
	 * 会员编号
	 */
	protected String memberNo;
	
	/**
	 * 会员手机号
	 */
	protected String memberTel;
	
	/**
	 * 会员信息
	 */
	protected MemberDto memberDto;
	
	
	/**用户身份证号**/
	protected String identityNo;
	
	/**用户真实姓名**/
	protected String realName;
	
	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public ResultCodeEnum getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCodeEnum resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getUserSessionKey() {
		return userSessionKey;
	}

	public void setUserSessionKey(String userSessionKey) {
		this.userSessionKey = userSessionKey;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}

	@Override
	public String toString() {
		return "UserOpeDTO [resultCode=" + resultCode + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg + ", userSessionKey="
				+ userSessionKey + ", memberNo=" + memberNo + ", memberTel="
				+ memberTel + ", memberDto=" + memberDto + ", identityNo="
				+ identityNo + ", realName=" + realName + "]";
	}
	
}