package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderStatusEnum;

public class ActivityInvForProOrderFlowDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7758111859676661839L;

	private Long id;
	/**订单id **/
    private Long orderId;
    /**状态 **/
    private ActivityInvForProOrderStatusEnum status;
    /**更新事件 **/
    private Date updateTime;

    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ActivityInvForProOrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityInvForProOrderStatusEnum status) {
        this.status = status ;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityInvForProOrderFlowDTO [id=");
		builder.append(id);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
}