package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.GrantStatusEnum;
/**
 * 
 * @Description 人工发放记录实体类
 * @author ying.liu
 * @CreateTime 2016-3-31
 * @version 1.0
 */
public class ActivityGrantRecordDTO implements Serializable{
	
	private static final long serialVersionUID = -4825077083852429203L;

	private Long id;
	
    private Long version;
    /**
     * 批量发放名称
     */
    private String batchGrantName;
    /**
     * 批次
     */
    private String batchId;
    /**
     * 用户总数
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
     * 状态（待审核，有效，退回）
     */
    private GrantStatusEnum grantStatus;

    private Date createTime;
    
    private Date createTimeStart;
    
    private Date createTimeEnd;

    private String creator;
    /**
     * 审核时间
     */
    private Date checkedTime;
    
    private Date checkedTimeStart;
    
    private Date checkedTimeEnd;
    /**
     * 审核人
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
    
    
    
    public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getCheckedTimeStart() {
		return checkedTimeStart;
	}

	public void setCheckedTimeStart(Date checkedTimeStart) {
		this.checkedTimeStart = checkedTimeStart;
	}

	public Date getCheckedTimeEnd() {
		return checkedTimeEnd;
	}

	public void setCheckedTimeEnd(Date checkedTimeEnd) {
		this.checkedTimeEnd = checkedTimeEnd;
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
