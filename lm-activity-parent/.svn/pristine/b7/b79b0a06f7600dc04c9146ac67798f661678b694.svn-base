package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.GoodsUsedStatusEnum;
import com.yeepay.g3.utils.persistence.EntityVersion;
/**
 * @Title: 商品详情实体类
 * @Description: 兑换码等
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-31 下午1:59:42
 * @version 2016-8-31
 */
public class ActivityGoodsDetailDTO implements Serializable{

	private static final long serialVersionUID = -3000026939321155078L;

	/**
	 * 主键
	 */
    private Long id;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 兑换码
     */
    private String redeemCode;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 使用状态
     */
    private GoodsUsedStatusEnum usedStatus;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 开始创建时间（用于查询条件）
     */
    private Date createTimeStart;
    
    /**
     * 截止创建时间（用于查询条件）
     */
    private Date createTimeEnd;

    /**
     * 使用时间
     */
    private Date usedTime;
    
    /**
     * 开始使用时间（用于查询条件）
     */
    private Date usedTimeStart;
    
    /**
     * 截止使用时间（用于查询条件）
     */
    private Date UsedTimeEnd;

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

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode == null ? null : redeemCode.trim();
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }


    public GoodsUsedStatusEnum getUsedStatus() {
		return usedStatus;
	}

	public void setUsedStatus(GoodsUsedStatusEnum usedStatus) {
		this.usedStatus = usedStatus;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
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

	public Date getUsedTimeStart() {
		return usedTimeStart;
	}

	public void setUsedTimeStart(Date usedTimeStart) {
		this.usedTimeStart = usedTimeStart;
	}

	public Date getUsedTimeEnd() {
		return UsedTimeEnd;
	}

	public void setUsedTimeEnd(Date usedTimeEnd) {
		UsedTimeEnd = usedTimeEnd;
	}

	@Override
	public String toString() {
		return "ActivityGoodsDetailDTO [id=" + id + ", version=" + version
				+ ", redeemCode=" + redeemCode + ", goodId=" + goodId
				+ ", usedStatus=" + usedStatus + ", createTime=" + createTime
				+ ", createTimeStart=" + createTimeStart + ", createTimeEnd="
				+ createTimeEnd + ", usedTime=" + usedTime + ", usedTimeStart="
				+ usedTimeStart + ", UsedTimeEnd=" + UsedTimeEnd + "]";
	}

	

    
}