package com.yeepay.g3.core.activity.entity;

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
public class ActivityGoodsDetail implements EntityVersion<Long>{
	
	private static final long serialVersionUID = -5898641087197282690L;

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
     * 使用时间
     */
    private Date usedTime;

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

	@Override
	public String toString() {
		return "ActivityGoodsDetail [id=" + id + ", version=" + version
				+ ", redeemCode=" + redeemCode + ", goodId=" + goodId
				+ ", usedStatus=" + usedStatus + ", createTime=" + createTime
				+ ", usedTime=" + usedTime + "]";
	}
    
}