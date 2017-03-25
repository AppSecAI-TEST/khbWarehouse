package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityMemberOperecordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 版本号
	 */
    private Long version;
    /**
     * 会员编号
     */
    private String memberNo;
    /**
     * 会员手机号
     */
    private String memberTel;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 操作时间
     */
    private Date operateTime;
    /**
     * 操作金额
     */
    private BigDecimal operateAmount;

    private Date createTime;
    
    /**
     * 操作时的ip
     */
    private String clientIp;
    /**
     * mac地址
     */
    private String clientMac;
    /**
     * 手机唯一标识
     */
    private String clientImei;
    /**
     * 用户代理信息
     */
    private String userAgent;
    /**
     * 扩展字段
     */
    private String clientIpStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getMemberTel() {
        return memberTel;
    }

    public void setMemberTel(String memberTel) {
        this.memberTel = memberTel == null ? null : memberTel.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public BigDecimal getOperateAmount() {
        return operateAmount;
    }

    public void setOperateAmount(BigDecimal operateAmount) {
        this.operateAmount = operateAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	
	public String getClientMac() {
		return clientMac;
	}

	public void setClientMac(String clientMac) {
		this.clientMac = clientMac;
	}

	public String getClientImei() {
		return clientImei;
	}

	public void setClientImei(String clientImei) {
		this.clientImei = clientImei;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public String getClientIpStr() {
		return clientIpStr;
	}

	public void setClientIpStr(String clientIpStr) {
		this.clientIpStr = clientIpStr;
	}

	@Override
	public String toString() {
		return "ActivityMemberOperecordDTO [id=" + id + ", version=" + version
				+ ", memberNo=" + memberNo + ", memberTel=" + memberTel
				+ ", operateType=" + operateType + ", operateTime="
				+ operateTime + ", operateAmount=" + operateAmount
				+ ", createTime=" + createTime + ", clientIp=" + clientIp
				+ ", clientMac=" + clientMac + ", clientImei=" + clientImei
				+ ", userAgent=" + userAgent + ", clientIpStr=" + clientIpStr
				+ "]";
	}

	
    
}
