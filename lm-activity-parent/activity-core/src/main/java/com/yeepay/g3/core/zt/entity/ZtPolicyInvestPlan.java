package com.yeepay.g3.core.zt.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;


/**
 * @Description 会员策略投资计划实体类
 * @version 1.0
 */
public class ZtPolicyInvestPlan implements EntityVersion<Long>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3406793632813334113L;

	private Long id;

    private Long version;

    /*策略ID*/
    private Long policyId;

    /*用户编号*/
    private String memberNo;

    /*心愿单总额*/
    private BigDecimal wishAmount;

    /*累计总投资额	*/
    private BigDecimal totalInvestAmount;

    /*累计投资期数	*/
    private BigDecimal totalInvestTerm;

    /*每月投资金额	*/
    private BigDecimal perInvestAmount;

    /*未来总市值最小值*/
    private BigDecimal futureAmountMin;

    /*未来总市值最大值*/
    private BigDecimal futureAmountMax;

    /*创建时间*/
    private Date createTime;

    /*最近修改时间	*/
    private Date lastModifyTime;

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

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public BigDecimal getWishAmount() {
        return wishAmount;
    }

    public void setWishAmount(BigDecimal wishAmount) {
        this.wishAmount = wishAmount;
    }

    public BigDecimal getTotalInvestAmount() {
        return totalInvestAmount;
    }

    public void setTotalInvestAmount(BigDecimal totalInvestAmount) {
        this.totalInvestAmount = totalInvestAmount;
    }

    public BigDecimal getTotalInvestTerm() {
        return totalInvestTerm;
    }

    public void setTotalInvestTerm(BigDecimal totalInvestTerm) {
        this.totalInvestTerm = totalInvestTerm;
    }

    public BigDecimal getPerInvestAmount() {
        return perInvestAmount;
    }

    public void setPerInvestAmount(BigDecimal perInvestAmount) {
        this.perInvestAmount = perInvestAmount;
    }

    public BigDecimal getFutureAmountMin() {
        return futureAmountMin;
    }

    public void setFutureAmountMin(BigDecimal futureAmountMin) {
        this.futureAmountMin = futureAmountMin;
    }

    public BigDecimal getFutureAmountMax() {
        return futureAmountMax;
    }

    public void setFutureAmountMax(BigDecimal futureAmountMax) {
        this.futureAmountMax = futureAmountMax;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}