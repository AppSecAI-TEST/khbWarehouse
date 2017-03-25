package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;
/**
 * 事件实体类
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:05:37
 */
public class ActivityAction implements EntityVersion<Long> {
	
	private static final long serialVersionUID = 4770476841177580591L;

	private Long id;

    private Long version;
    /** 事件编码 **/
    private String actionCode;
    /** 事件名称 **/
    private String actionName;
    /** 创建时间 **/
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

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode == null ? null : actionCode.trim();
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "ActivityAction [id=" + id + ", version=" + version
				+ ", actionCode=" + actionCode + ", actionName=" + actionName
				+ ", createTime=" + createTime + "]";
	}
}