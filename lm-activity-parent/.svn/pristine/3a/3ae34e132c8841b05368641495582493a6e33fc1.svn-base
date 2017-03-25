package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description 事件优惠券关联实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:48:28
 * @version 1.0
 */
public class ActivityCouponEventRelation implements EntityVersion<Long> {
	private static final long serialVersionUID = -4316488070533087979L;

	private Long id;

    private Long version;

    private Long eventId;

    private String eventCode;

    private Long couponId;

    private Date createTime;

    private String creator;

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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

	@Override
	public String toString() {
		return "ActivityCouponEventRelation [id=" + id + ", version=" + version
				+ ", eventId=" + eventId + ", eventCode=" + eventCode
				+ ", couponId=" + couponId + ", createTime=" + createTime
				+ ", creator=" + creator + "]";
	}
}