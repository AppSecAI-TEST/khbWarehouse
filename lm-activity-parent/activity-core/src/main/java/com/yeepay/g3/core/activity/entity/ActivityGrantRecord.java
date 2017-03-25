package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.facade.activity.enums.GrantStatusEnum;
import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description 人工发放记录实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:51:03
 * @version 1.0
 */
public class ActivityGrantRecord implements EntityVersion<Long> {
	private static final long serialVersionUID = -94438442008369339L;

	/**
	 * id
	 */
    private Long id;

    /**
     * 版本号
     */
    private Long version;
    /**
     * 批量发放名称（唯一）
     */
    private String batchGrantName;
    /**
     * 批次
     */
    private String batchId;
    /**
     * 用户数量
     */
    private Integer memberCount;
    /**
     * 每个人发放数量
     */
    private Integer perGrantCount;
    /**
     * 优惠券id
     */
    private Long couponId;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 发放状态
     */
    private GrantStatusEnum grantStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 审核时间
     */
    private Date checkedTime;
    /**
     * 审核人员
     */
    private String checkor;
    /**
     * 用户列表
     */
    private byte[] memberNoList;

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

    public String getBatchGrantName() {
        return batchGrantName;
    }

    public void setBatchGrantName(String batchGrantName) {
        this.batchGrantName = batchGrantName == null ? null : batchGrantName.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getPerGrantCount() {
        return perGrantCount;
    }

    public void setPerGrantCount(Integer perGrantCount) {
        this.perGrantCount = perGrantCount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }


    public GrantStatusEnum getGrantStatus() {
		return grantStatus;
	}

	public void setGrantStatus(GrantStatusEnum grantStatus) {
		this.grantStatus = grantStatus;
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

    public byte[] getMemberNoList() {
        return memberNoList;
    }

    public void setMemberNoList(byte[] memberNoList) {
        this.memberNoList = memberNoList;
    }
    @Override
	public String toString() {
		return "[ActivityGrantRecordDto:"+"id="+id
				+",version="+version
				+",batchGrantName="+batchGrantName
				+",batchId="+batchId
				+",memberCount="+memberCount
				+",perGrantCount="+perGrantCount
				+",couponId="+couponId
				+",couponName="+couponName
				+",grantStatus="+grantStatus
				+",createTime="+createTime
				+",creator="+creator
				+",checkedTime="+checkedTime
				+",checkor="+checkor
				+",memberNoList="+memberNoList
				+"]";
	}
}