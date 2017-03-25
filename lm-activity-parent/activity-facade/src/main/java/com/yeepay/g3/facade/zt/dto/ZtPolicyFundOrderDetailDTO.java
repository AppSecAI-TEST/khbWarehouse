package com.yeepay.g3.facade.zt.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户基金订单详情信息
 * @author hongbin.kang
 * @date 2016年10月21日下午4:44:46
 */
public class ZtPolicyFundOrderDetailDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;

    private Long version;

    /*会员编号*/
    private String memberNo;
    
    /*策略交易记录ID*/
    private Long policyOrderDetailId;

    /*商户编号*/
    private String merchantNo;

    /*交易流水号*/
    private String tradeFlowId;

    /*请求类型*/
    private String operationType;

    /*基金代码*/
    private String fundCode;

    /*申购金额*/
    private BigDecimal balance;

    /*赎回份额*/
    private BigDecimal shares;

    /*份额分类*/
    private String shareType;

    /*成功标识*/
    private String status;

    /*失败信息*/
    private String errorInfo;

    /*申请编号*/
    private String allotNo;

    /*申请时间*/
    private Date applyTime;

    /*是否已确认*/
    private String isConfirmation;

    /*创建日期*/
    private Date createTime;

    /*确认日期*/
    private Date affirmTime;

    /*失败原因*/
    private String failCause;

    /*手续费*/
    private BigDecimal fareSx;

    /*净值*/
    private BigDecimal netValue;

    /*交易确认金额*/
    private BigDecimal tradeConfirmBalance;

    /*交易确认份额	*/
    private BigDecimal tradeConfirmType;

    /*更新时间*/
    private Date updateTime;

    /*撤销申请单号	*/
    private String cancelAllotNo;

    /*撤销时间*/
    private Date cancelTime;

    /*撤销状态*/
    private String cancelSuccessType;

    /*撤销失败原因	*/
    private String cancelErrorInfo;

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

    public Long getPolicyOrderDetailId() {
        return policyOrderDetailId;
    }

    public void setPolicyOrderDetailId(Long policyOrderDetailId) {
        this.policyOrderDetailId = policyOrderDetailId;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getTradeFlowId() {
        return tradeFlowId;
    }

    public void setTradeFlowId(String tradeFlowId) {
        this.tradeFlowId = tradeFlowId == null ? null : tradeFlowId.trim();
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode == null ? null : fundCode.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getShares() {
        return shares;
    }

    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo == null ? null : errorInfo.trim();
    }

    public String getAllotNo() {
        return allotNo;
    }

    public void setAllotNo(String allotNo) {
        this.allotNo = allotNo == null ? null : allotNo.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getIsConfirmation() {
        return isConfirmation;
    }

    public void setIsConfirmation(String isConfirmation) {
        this.isConfirmation = isConfirmation == null ? null : isConfirmation.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAffirmTime() {
        return affirmTime;
    }

    public void setAffirmTime(Date affirmTime) {
        this.affirmTime = affirmTime;
    }

    public String getFailCause() {
        return failCause;
    }

    public void setFailCause(String failCause) {
        this.failCause = failCause == null ? null : failCause.trim();
    }

    public BigDecimal getFareSx() {
        return fareSx;
    }

    public void setFareSx(BigDecimal fareSx) {
        this.fareSx = fareSx;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getTradeConfirmBalance() {
        return tradeConfirmBalance;
    }

    public void setTradeConfirmBalance(BigDecimal tradeConfirmBalance) {
        this.tradeConfirmBalance = tradeConfirmBalance;
    }

    public BigDecimal getTradeConfirmType() {
        return tradeConfirmType;
    }

    public void setTradeConfirmType(BigDecimal tradeConfirmType) {
        this.tradeConfirmType = tradeConfirmType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCancelAllotNo() {
        return cancelAllotNo;
    }

    public void setCancelAllotNo(String cancelAllotNo) {
        this.cancelAllotNo = cancelAllotNo == null ? null : cancelAllotNo.trim();
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelSuccessType() {
        return cancelSuccessType;
    }

    public void setCancelSuccessType(String cancelSuccessType) {
        this.cancelSuccessType = cancelSuccessType == null ? null : cancelSuccessType.trim();
    }

    public String getCancelErrorInfo() {
        return cancelErrorInfo;
    }

    public void setCancelErrorInfo(String cancelErrorInfo) {
        this.cancelErrorInfo = cancelErrorInfo == null ? null : cancelErrorInfo.trim();
    }
}