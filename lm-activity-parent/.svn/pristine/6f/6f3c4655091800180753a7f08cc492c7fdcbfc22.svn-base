package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

public class ActivityActionRela implements EntityVersion<Long> {

	private static final long serialVersionUID = 8466836478175417769L;

	private Long id;

    private Long version;

    private Long activityId;

    private Long actionId;

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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
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
		return "ActivityActionRela [id=" + id + ", version=" + version
				+ ", activityId=" + activityId + ", actionId=" + actionId
				+ ", createTime=" + createTime + ", creator=" + creator + "]";
	}
}