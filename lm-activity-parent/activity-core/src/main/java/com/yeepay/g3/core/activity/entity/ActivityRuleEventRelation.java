package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description 事件规则关联实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:52:01
 * @version 1.0
 */
public class ActivityRuleEventRelation implements EntityVersion<Long> {
	
	private static final long serialVersionUID = -3977607851455958780L;

	private Long id;

    private Long version;

    private Long ruleId;

    private String ruleCode;

    private Long eventId;

    private String eventCode;

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

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
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
		return "ActivityRuleEventRelation [id=" + id + ", version=" + version
				+ ", ruleId=" + ruleId + ", ruleCode=" + ruleCode
				+ ", eventId=" + eventId + ", eventCode=" + eventCode
				+ ", createTime=" + createTime + ", creator=" + creator + "]";
	}
}