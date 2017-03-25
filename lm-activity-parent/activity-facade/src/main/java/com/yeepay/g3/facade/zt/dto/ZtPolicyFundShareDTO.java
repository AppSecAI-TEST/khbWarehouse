package com.yeepay.g3.facade.zt.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户基金的汇总信息
 * @author hongbin.kang
 * @date 2016年10月21日下午4:41:40
 */
public class ZtPolicyFundShareDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long version;

    /*场景ID*/
    private Long sceneId;

    /*策略ID*/
    private Long policyId;

    /*会员策略投资计划ID*/
    private Long memberPolicyPlanId;

    /*会员策略交易汇总ID*/
    private Long memberPolicyOrderId;

    /*会员编号*/
    private String memberNo;

    /*基金交易账号*/
    private String fundAccountNo;

    /*基金代码*/
    private String fundCode;

    /*基金类型*/
    private String fundType;

    /*TA账号*/
    private String taAccount;

    /*当前份额*/
    private BigDecimal currentShare;

    /*可用份额*/
    private BigDecimal enableShare;

    /*基金冻结数量*/
    private String frozenShare;

    /*分红方式*/
    private String autoBuy;

    /*交易冻结金额*/
    private BigDecimal businessFrozenAmount;

    /*未付收益金额*/
    private BigDecimal unpaidIncome;

    /*可用到期份额*/
    private BigDecimal availableDueShare;

    /*快速赎回可用份额*/
    private BigDecimal quickExceedEnableShare;

    /*市值*/
    private BigDecimal worthValue;

    /*累计收益*/
    private BigDecimal accumIncome;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

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

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public Long getMemberPolicyPlanId() {
        return memberPolicyPlanId;
    }

    public void setMemberPolicyPlanId(Long memberPolicyPlanId) {
        this.memberPolicyPlanId = memberPolicyPlanId;
    }

    public Long getMemberPolicyOrderId() {
        return memberPolicyOrderId;
    }

    public void setMemberPolicyOrderId(Long memberPolicyOrderId) {
        this.memberPolicyOrderId = memberPolicyOrderId;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getFundAccountNo() {
        return fundAccountNo;
    }

    public void setFundAccountNo(String fundAccountNo) {
        this.fundAccountNo = fundAccountNo == null ? null : fundAccountNo.trim();
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType == null ? null : fundType.trim();
    }

    public String getTaAccount() {
        return taAccount;
    }

    public void setTaAccount(String taAccount) {
        this.taAccount = taAccount == null ? null : taAccount.trim();
    }

    public BigDecimal getCurrentShare() {
        return currentShare;
    }

    public void setCurrentShare(BigDecimal currentShare) {
        this.currentShare = currentShare;
    }

    public BigDecimal getEnableShare() {
        return enableShare;
    }

    public void setEnableShare(BigDecimal enableShare) {
        this.enableShare = enableShare;
    }

    public String getFrozenShare() {
        return frozenShare;
    }

    public void setFrozenShare(String frozenShare) {
        this.frozenShare = frozenShare == null ? null : frozenShare.trim();
    }

    public String getAutoBuy() {
        return autoBuy;
    }

    public void setAutoBuy(String autoBuy) {
        this.autoBuy = autoBuy == null ? null : autoBuy.trim();
    }

    public BigDecimal getBusinessFrozenAmount() {
        return businessFrozenAmount;
    }

    public void setBusinessFrozenAmount(BigDecimal businessFrozenAmount) {
        this.businessFrozenAmount = businessFrozenAmount;
    }

    public BigDecimal getUnpaidIncome() {
        return unpaidIncome;
    }

    public void setUnpaidIncome(BigDecimal unpaidIncome) {
        this.unpaidIncome = unpaidIncome;
    }

    public BigDecimal getAvailableDueShare() {
        return availableDueShare;
    }

    public void setAvailableDueShare(BigDecimal availableDueShare) {
        this.availableDueShare = availableDueShare;
    }

    public BigDecimal getQuickExceedEnableShare() {
        return quickExceedEnableShare;
    }

    public void setQuickExceedEnableShare(BigDecimal quickExceedEnableShare) {
        this.quickExceedEnableShare = quickExceedEnableShare;
    }

    public BigDecimal getWorthValue() {
        return worthValue;
    }

    public void setWorthValue(BigDecimal worthValue) {
        this.worthValue = worthValue;
    }

    public BigDecimal getAccumIncome() {
        return accumIncome;
    }

    public void setAccumIncome(BigDecimal accumIncome) {
        this.accumIncome = accumIncome;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}