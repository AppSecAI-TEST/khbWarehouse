package com.yeepay.g3.core.zt.entity;
import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;
/**
 * 智投策略收益率
 * @Description
 * @author 陈大涛
 * 2016-10-21下午3:32:01
 */
public class ZtYieldRate  implements EntityVersion<Long>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -986346000704723109L;

	private Long id;

    private Long version;
    /**策略ID **/
    private Long policyId;
    /** 计算方式**/
    private String calculateType;
    /**回溯期限 **/
    private Integer lastTerm;
    /** 年月**/
    private String curMonth;
    /**收益率 **/
    private BigDecimal yieldRate;
    /**	批次号	 **/
    private String batchNo;
    /**创建时间 **/
    private Date createTime;

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

    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType == null ? null : calculateType.trim();
    }

    public Integer getLastTerm() {
        return lastTerm;
    }

    public void setLastTerm(Integer lastTerm) {
        this.lastTerm = lastTerm;
    }

    public String getCurMonth() {
        return curMonth;
    }

    public void setCurMonth(String curMonth) {
        this.curMonth = curMonth == null ? null : curMonth.trim();
    }

    public BigDecimal getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}