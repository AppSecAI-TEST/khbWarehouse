package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: 渠道发放流量规则实体类
 * @Description: 描述
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-20 下午4:00:44
 * @version 2016-7-20
 */
public class ActivitySrcFlowRuleDTO implements Serializable{

	private static final long serialVersionUID = -5698391886799435525L;

	/**
     * 主键id
     */
	private Long id;

	/**
	 * 版本号
	 */
    private Long version;

    /**
     * 渠道编号
     */
    private String srcNo;

    /**
     * 操作类型（注册、绑卡、投资信托、投资新手标）
     */
    private String opeType;

    /**
     * 最小投资金额
     */
    private BigDecimal minOpeAmount;

    /**
     * 最大投资金额
     */
    private BigDecimal maxOpeAmount;

    /**
     * 移动编码
     */
    private String cmccCode;

    /**
     * 电信编码
     */
    private String ctccCode;

    /**
     * 联通编码
     */
    private String cuccCode;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 结束日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 发送标志
     */
    private Short sendFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作人员
     */
    private String operator;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;
    
    @Override
	public String toString() {
		return "ActivitySrcFlowRule:[id="+id+
				",version="+version+
				",srcNo="+srcNo+
				",opeType="+opeType+
				",minOpeAmount="+minOpeAmount+
				",maxOpeAmount="+maxOpeAmount+
				",cmccCode="+cmccCode+
				",ctccCode="+ctccCode+
				",cuccCode="+cuccCode+
				",startDate="+startDate+
				",endDate="+endDate+
				",sendFlag="+sendFlag+
				",createTime="+createTime+
				",operator="+operator+
				",lastUpdateTime="+lastUpdateTime
				+"]";
	}

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

    public String getSrcNo() {
        return srcNo;
    }

    public void setSrcNo(String srcNo) {
        this.srcNo = srcNo == null ? null : srcNo.trim();
    }

    public String getOpeType() {
        return opeType;
    }

    public void setOpeType(String opeType) {
        this.opeType = opeType == null ? null : opeType.trim();
    }

    public BigDecimal getMinOpeAmount() {
        return minOpeAmount;
    }

    public void setMinOpeAmount(BigDecimal minOpeAmount) {
        this.minOpeAmount = minOpeAmount;
    }

    public BigDecimal getMaxOpeAmount() {
        return maxOpeAmount;
    }

    public void setMaxOpeAmount(BigDecimal maxOpeAmount) {
        this.maxOpeAmount = maxOpeAmount;
    }

    public String getCmccCode() {
        return cmccCode;
    }

    public void setCmccCode(String cmccCode) {
        this.cmccCode = cmccCode == null ? null : cmccCode.trim();
    }

    public String getCtccCode() {
        return ctccCode;
    }

    public void setCtccCode(String ctccCode) {
        this.ctccCode = ctccCode == null ? null : ctccCode.trim();
    }

    public String getCuccCode() {
        return cuccCode;
    }

    public void setCuccCode(String cuccCode) {
        this.cuccCode = cuccCode == null ? null : cuccCode.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Short getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Short sendFlag) {
        this.sendFlag = sendFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
