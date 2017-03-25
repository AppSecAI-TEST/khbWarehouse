package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.yeepay.g3.facade.activity.enums.RaffleTicketStatusEnum;

public class ActivityRaffleTicketDTO implements Serializable {

	private static final long serialVersionUID = 5259420087142117274L;

	private Long id;

    private Long version;

    /**抽奖券编码**/
    private String raffleTicketCode;
    /**抽奖券名称**/
    private String raffleTicketName;

    /**抽奖券总数**/
    private Integer totalCount;

    /**抽奖券已发数量**/
    private Integer grantCount;

    /**有效期类型**/
    private String validityType;

    /**有效期天数**/
    private Integer validityDays;

    /**有效期截止日期**/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date validityDate;

    /**抽奖券的状态**/
    private RaffleTicketStatusEnum couponStatus;

    /**创建时间**/
    private Date createTime;
    /**创建人**/
    private String creator;
    /**审核时间**/
    private Date checkedTime;
    /**审核人**/
    private String checkor;

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

    public String getRaffleTicketCode() {
        return raffleTicketCode;
    }

    public void setRaffleTicketCode(String raffleTicketCode) {
        this.raffleTicketCode = raffleTicketCode == null ? null : raffleTicketCode.trim();
    }

    public String getRaffleTicketName() {
        return raffleTicketName;
    }

    public void setRaffleTicketName(String raffleTicketName) {
        this.raffleTicketName = raffleTicketName == null ? null : raffleTicketName.trim();
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

    public String getValidityType() {
        return validityType;
    }

    public void setValidityType(String validityType) {
        this.validityType = validityType == null ? null : validityType.trim();
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

    public RaffleTicketStatusEnum getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(RaffleTicketStatusEnum couponStatus) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityRaffleTicketDTO [id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
		builder.append(", raffleTicketCode=");
		builder.append(raffleTicketCode);
		builder.append(", raffleTicketName=");
		builder.append(raffleTicketName);
		builder.append(", totalCount=");
		builder.append(totalCount);
		builder.append(", grantCount=");
		builder.append(grantCount);
		builder.append(", validityType=");
		builder.append(validityType);
		builder.append(", validityDays=");
		builder.append(validityDays);
		builder.append(", validityDate=");
		builder.append(validityDate);
		builder.append(", couponStatus=");
		builder.append(couponStatus);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", checkedTime=");
		builder.append(checkedTime);
		builder.append(", checkor=");
		builder.append(checkor);
		builder.append("]");
		return builder.toString();
	}
}