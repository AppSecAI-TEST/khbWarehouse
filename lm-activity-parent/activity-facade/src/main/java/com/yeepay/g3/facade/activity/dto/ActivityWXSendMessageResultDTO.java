/**
 * @author 陈大涛
 * 2016-5-31下午5:06:07
 */
package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;

/**
 * @author 陈大涛
 * 2016-5-31下午5:06:07
 */
public class ActivityWXSendMessageResultDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7565383944035549012L;

	public Integer getCode() {
		return Code;
	}

	public void setCode(Integer code) {
		Code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**微信返回码  **/
	private Integer Code;//0:成功，详见https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234&token=&lang=zh_CN
	
	/** 微信返回信息 **/
	private String message;//"ok":成功

	@Override
	public String toString() {
		return "ActivityWXSendMessageResultDTO [Code=" + Code + ", message="
				+ message + "]";
	}
	
}
