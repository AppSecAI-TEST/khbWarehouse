package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description 事件定义实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:50:25
 * @version 1.0
 */
public class ActivityEvent implements EntityVersion<Long> {

	private static final long serialVersionUID = -281000622669238034L;

	private Long id;

	private Long version;

	/** 事件编码 **/
	private String eventCode;

	/** 事件名称 **/
	private String eventName;

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

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode == null ? null : eventCode.trim();
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName == null ? null : eventName.trim();
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
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "ActivityEvent [id=" + id + ", version=" + version
				+ ", eventCode=" + eventCode + ", eventName=" + eventName
				+ ", createTime=" + createTime + ", creator=" + creator + "]";
	}
}