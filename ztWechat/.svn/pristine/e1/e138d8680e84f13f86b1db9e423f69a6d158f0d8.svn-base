package com.yeepay.g3.app.dto;

import java.io.Serializable;

import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;

public class CardTypeDTO implements Serializable {
	private static final long serialVersionUID = -6568625141611239452L;

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
	 * 银行英文简称
	 */
	private String bankId;
	/**
	 * 银行卡编号
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 卡名称
	 */
	private String cardName;
	/**
	 * 卡的类型（信用卡/借记卡）credit/debit
	 */
	private String cardType;
	
	
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


	public String getBankId() {
		return bankId;
	}


	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getCardName() {
		return cardName;
	}


	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	@Override
	public String toString() {
		return "CardTypeDTO [resultCode=" + resultCode + ", errorCode="
				+ errorCode + ", errorMsg=" + errorMsg  + ",bankId=" + bankId
				+",bankCode=" + bankCode + ",bankName=" + bankName + ",cardName=" 
				+ cardName + "]";
	}
	
}