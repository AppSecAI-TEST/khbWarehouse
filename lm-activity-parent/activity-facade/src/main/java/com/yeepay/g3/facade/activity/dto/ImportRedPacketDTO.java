package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @Description 导入红包接口实体类
 * @author zhenping.zhou
 * @CreateTime 2016年8月2日 下午6:04:47
 * @version 1.0
 */
public class ImportRedPacketDTO implements Serializable {
	
	private static final long serialVersionUID = 8603653909733482054L;

	/**
	 * 会员编号
	 */
	private String memberNo;
	/**
	 * 红包名称
	 */
	private String packetName;
	/**
	 * 红包金额
	 */
	private BigDecimal packetAmount;
	/**
	 * 有效期时长
	 */
	private Integer validityDays;
	
	private String creator;

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getPacketName() {
		return packetName;
	}

	public void setPacketName(String packetName) {
		this.packetName = packetName;
	}

	public BigDecimal getPacketAmount() {
		return packetAmount;
	}

	public void setPacketAmount(BigDecimal packetAmount) {
		this.packetAmount = packetAmount;
	}

	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
	}

	@Override
	public String toString() {
		return "ImportRedPacketDTO:[memberNo="+memberNo+";packetName="+packetName
				+";packetAmount="+packetAmount+";validityDays="+validityDays+"]";
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
