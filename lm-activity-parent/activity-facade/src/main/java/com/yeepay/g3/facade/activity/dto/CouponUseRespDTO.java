/**
 * 
 */
package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;

import com.yeepay.g3.facade.activity.enums.CouponUseResultCodeEnum;
import com.yeepay.g3.facade.activity.enums.CouponUseStatusEnum;

/**
 * @Description 用户优惠券请求返回实体类
 * @author zhenping.zhou
 * @CreateTime 2016年4月3日 下午3:05:32
 * @version 1.0
 */
public class CouponUseRespDTO implements Serializable{

	private static final long serialVersionUID = -2699890592474780973L;
	
	/** 返回码 **/
	private CouponUseResultCodeEnum resultCode;
	
	private Long usercouponId;
	
	private Long tradeId;
	
	private String memberNo;
	
	private CouponUseStatusEnum reqType;

	public CouponUseResultCodeEnum getResultCode() {
		return resultCode;
	}

	public void setResultCode(CouponUseResultCodeEnum resultCode) {
		this.resultCode = resultCode;
	}

	public Long getUsercouponId() {
		return usercouponId;
	}

	public void setUsercouponId(Long usercouponId) {
		this.usercouponId = usercouponId;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public CouponUseStatusEnum getReqType() {
		return reqType;
	}

	public void setReqType(CouponUseStatusEnum reqType) {
		this.reqType = reqType;
	}

	@Override
	public String toString() {
		return "CouponUseRespDTO:[resultCode="+resultCode+";usercouponId="+usercouponId+";tradeId="+tradeId+";memberNo="+memberNo+";reqType="+reqType+"]";
	}
}
