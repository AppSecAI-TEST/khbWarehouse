/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:02:43
 */
package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:02:43
 */
public class ActivityActionDTO implements Serializable {

	/**
	 * @author cdt
	 * @date 2016-5-16
	 * @time 下午4:03:37
	 */
	private static final long serialVersionUID = 3636613063757942245L;

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
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityActionDTO [id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
		builder.append(", actionCode=");
		builder.append(actionCode);
		builder.append(", actionName=");
		builder.append(actionName);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
    
}
