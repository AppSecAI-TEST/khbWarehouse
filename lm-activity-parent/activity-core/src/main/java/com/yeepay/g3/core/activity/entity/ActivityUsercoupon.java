package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.facade.activity.enums.RuleTypeEnum;
import com.yeepay.g3.facade.activity.enums.UsercouponStatusEnum;
import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description 用户优惠券关联实体
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:52:38
 * @version 1.0
 */
public class ActivityUsercoupon implements EntityVersion<Long> {
	private static final long serialVersionUID = -577057301466576297L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 版本
	 */
    private Long version;
    /**
     * 优惠券id
     */
	private ActivityCoupon coupon;
    /**
     * 优惠券数量
     */
    private Integer couponCount;
    /**
     * 优惠券已使用数
     */
    private Integer couponUsedCount;
    /**
     * 会员编号
     */
    private String memberNo;
    /**
     * 有效期起始时间
     */
    private Date validityTimeStart;
    /**
     * 有效期截止时间
     */
    private Date validityTimeEnd;
    /**
     * 领取日期
     */
    private Date receiveTime;
    /**
     * 发放规则id（批量发放时无规则id）
     */
    private Long ruleId;
    /**
     * 发放规则名称
     */
    private String ruleName;
    /**
     * 发放规则类型
     */
    private RuleTypeEnum ruleType;
    /**
     * 状态（有效，无效）
     */
    private UsercouponStatusEnum status;
    /**
     * 批量发放批次号
     */
    private String batchId;

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

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getCouponUsedCount() {
        return couponUsedCount;
    }

    public void setCouponUsedCount(Integer couponUsedCount) {
        this.couponUsedCount = couponUsedCount;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public Date getValidityTimeStart() {
        return validityTimeStart;
    }

    public void setValidityTimeStart(Date validityTimeStart) {
        this.validityTimeStart = validityTimeStart;
    }

    public Date getValidityTimeEnd() {
        return validityTimeEnd;
    }

    public void setValidityTimeEnd(Date validityTimeEnd) {
        this.validityTimeEnd = validityTimeEnd;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

	public UsercouponStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UsercouponStatusEnum status) {
		this.status = status;
	}

	public ActivityCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(ActivityCoupon coupon) {
		this.coupon = coupon;
	}

	public RuleTypeEnum getRuleType() {
		return ruleType;
	}

	public void setRuleType(RuleTypeEnum ruleType) {
		this.ruleType = ruleType;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "ActivityUsercoupon [id=" + id + ", version=" + version
				+ ", coupon=" + coupon + ", couponCount=" + couponCount
				+ ", couponUsedCount=" + couponUsedCount + ", memberNo="
				+ memberNo + ", validityTimeStart=" + validityTimeStart
				+ ", validityTimeEnd=" + validityTimeEnd + ", receiveTime="
				+ receiveTime + ", ruleId=" + ruleId + ", ruleName=" + ruleName
				+ ", ruleType=" + ruleType + ", status=" + status
				+ ", batchId=" + batchId + "]";
	}

    
}