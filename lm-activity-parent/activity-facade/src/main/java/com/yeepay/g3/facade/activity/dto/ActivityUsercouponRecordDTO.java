package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.BizTypeEnum;
import com.yeepay.g3.facade.activity.enums.CouponUseStatusEnum;

/**
 * 
 * @Description 用户优惠券使用记录实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:53:14
 * @version 1.0
 */
public class ActivityUsercouponRecordDTO implements Serializable {
	
	private static final long serialVersionUID = -8774094743381676218L;

	private Long id;

    private Long version;

    private ActivityUsercouponDTO usercoupon;

    private ActivityCouponDTO coupon;

    private String memberNo;

    private Date useTime;

    private Long tradeId;

    private CouponUseStatusEnum useStatus;
    
    private BizTypeEnum bizType;

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

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public CouponUseStatusEnum getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(CouponUseStatusEnum useStatus) {
        this.useStatus = useStatus;
    }

	public BizTypeEnum getBizType() {
		return bizType;
	}

	public void setBizType(BizTypeEnum bizType) {
		this.bizType = bizType;
	}

	@Override
	public String toString() {
		return "ActivityUsercouponRecordDTO:[id="+id+";version="+version+
				";coupon="+coupon+";usercoupon="+usercoupon+";memberNo="+memberNo+";useTime="+useTime+";tradeId="+tradeId+"]";
	}

	public ActivityCouponDTO getCoupon() {
		return coupon;
	}

	public void setCoupon(ActivityCouponDTO coupon) {
		this.coupon = coupon;
	}

	public ActivityUsercouponDTO getUsercoupon() {
		return usercoupon;
	}

	public void setUsercoupon(ActivityUsercouponDTO usercoupon) {
		this.usercoupon = usercoupon;
	}
	
}