package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.CouponTypeEnum;
import com.yeepay.g3.facade.activity.enums.DiscountTypeEnum;
import com.yeepay.g3.facade.activity.enums.ValidityTypeEnum;

/**
 * 
 * @Description 优惠券信息实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:47:21
 * @version 1.0
 */
public class ActivityCouponDTO implements Serializable {
	
	private static final long serialVersionUID = -7112079220749694039L;

	private Long id;

    private Long version;

    private String couponName;
    
    /** 优惠券类别 **/
    private CouponTypeEnum couponType;

    /** 优惠类型 **/
    private DiscountTypeEnum discountType;

    /** 可使用金额 **/
    private BigDecimal minInvestAmount;

    /** 优惠券金额 **/
    private BigDecimal couponAmount;

    /** 加息利息 **/
    private BigDecimal increaseInterest;

    /** 规则说明 **/
    private String ruleDesc;

    /** 总数 **/
    private Integer totalCount;

    /** 已发数 **/
    private Integer grantCount;

    /** 有效期类型 **/
    private ValidityTypeEnum validityType;

    /** 有效期天数 **/
    private Integer validityDays;

    /** 固定日期有效期截止日期 **/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validityDate;

    /** 优惠券状态 **/
    private CouponStatusEnum couponStatus;

    /** 创建日期 **/
    private Date createTime;
    
    /** 创建日期-起始 **/
    private Date createTimeStart;
    
    /** 创建日期-截止 **/
    private Date createTimeEnd;

    /** 创建人 **/
    private String creator;

    /** 审核操作日期- **/
    private Date checkedTime;
    
    /** 审核操作日期-起始 **/
    private Date checkedTimeStart;
    
    /** 审核操作日期-截止 **/
    private Date checkedTimeEnd;

    /** 审核操作人 **/
    private String checkor;

    /** 优惠券简介 **/
    private String couponRemark;

    /** 是否红包 **/
    private Integer isRedPacket = 0;
    
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

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public CouponTypeEnum getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponTypeEnum couponType) {
        this.couponType = couponType;
    }

    public DiscountTypeEnum getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountTypeEnum discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(BigDecimal minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getIncreaseInterest() {
        return increaseInterest;
    }

    public void setIncreaseInterest(BigDecimal increaseInterest) {
        this.increaseInterest = increaseInterest;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getGrantCount() {
        return grantCount;
    }

    public void setGrantCount(Integer grantCount) {
        this.grantCount = grantCount;
    }

    public ValidityTypeEnum getValidityType() {
        return validityType;
    }

    public void setValidityType(ValidityTypeEnum validityType) {
        this.validityType = validityType;
    }

    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public CouponStatusEnum getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(CouponStatusEnum couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCheckedTime() {
        return checkedTime;
    }

    public void setCheckedTime(Date checkedTime) {
        this.checkedTime = checkedTime;
    }

    public String getCheckor() {
        return checkor;
    }

    public void setCheckor(String checkor) {
        this.checkor = checkor == null ? null : checkor.trim();
    }

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getCheckedTimeStart() {
		return checkedTimeStart;
	}

	public void setCheckedTimeStart(Date checkedTimeStart) {
		this.checkedTimeStart = checkedTimeStart;
	}

	public Date getCheckedTimeEnd() {
		return checkedTimeEnd;
	}

	public void setCheckedTimeEnd(Date checkedTimeEnd) {
		this.checkedTimeEnd = checkedTimeEnd;
	}

	@Override
	public String toString() {
		return "ActivityCouponDTO:[id="+id+";version="+version+";couponName="+couponName+";couponType="+couponType+";couponStatus="+couponStatus+"]";
	}

	public String getCouponRemark() {
		return couponRemark;
	}

	public void setCouponRemark(String couponRemark) {
		this.couponRemark = couponRemark;
	}

	public Integer getIsRedPacket() {
		return isRedPacket;
	}

	public void setIsRedPacket(Integer isRedPacket) {
		this.isRedPacket = isRedPacket;
	}
	
}