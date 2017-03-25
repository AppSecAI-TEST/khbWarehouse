package com.yeepay.g3.core.zt.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * @Description 会员策略交易明细实体类
 * 用于记录每次申购、赎回、追加投资的交易信息
 * @version 1.0
 */
public class ZtPolicyOrderDetail implements EntityVersion<Long>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 144493230133287238L;

	private Long id;

    private Long version;

    /*会员编号*/
    private String memberNo;
    
    /*策略ID*/
    private Long policyId;

    /*策略交易汇总ID*/
    private Long policyOrderId;

    /*交易金额*/
    private BigDecimal orderAmount;

    /*交易类型*/
    private String orderType;

    /*支付方式*/
    private String payMode;

    /*交易时间*/
    private Date orderTime;

    /*交易状态*/
    private String orderStatus;

    /*手续费*/
    private BigDecimal fee;

    /*计息时间*/
    private Date interestTime;

    /*客户端IP*/
    private String clientIp;

    /*用户客户端信息*/
    private String userAgent;

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

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public Long getPolicyOrderId() {
        return policyOrderId;
    }

    public void setPolicyOrderId(Long policyOrderId) {
        this.policyOrderId = policyOrderId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getInterestTime() {
        return interestTime;
    }

    public void setInterestTime(Date interestTime) {
        this.interestTime = interestTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }
}