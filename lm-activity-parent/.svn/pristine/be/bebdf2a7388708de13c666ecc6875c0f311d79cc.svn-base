package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.facade.activity.enums.RaffleTicketStatusEnum;
import com.yeepay.g3.utils.persistence.EntityVersion;

public class ActivityRaffleTicket implements EntityVersion<Long> {

	private static final long serialVersionUID = 5259420087142117274L;

	private Long id;

    private Long version;

    private String raffleTicketCode;

    private String raffleTicketName;

    private Integer totalCount;

    private Integer grantCount;

    private String validityType;

    private Integer validityDays;

    private Date validityDate;

    private RaffleTicketStatusEnum couponStatus;

    private Date createTime;

    private String creator;

    private Date checkedTime;

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

    public void setCouponStatus(RaffleTicketStatusEnum checking) {
        this.couponStatus = checking;
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
		return "ActivityRaffleTicket [id=" + id + ", version=" + version
				+ ", raffleTicketCode=" + raffleTicketCode
				+ ", raffleTicketName=" + raffleTicketName + ", totalCount="
				+ totalCount + ", grantCount=" + grantCount + ", validityType="
				+ validityType + ", validityDays=" + validityDays
				+ ", validityDate=" + validityDate + ", couponStatus="
				+ couponStatus + ", createTime=" + createTime + ", creator="
				+ creator + ", checkedTime=" + checkedTime + ", checkor="
				+ checkor + "]";
	}
}