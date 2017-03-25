package com.yeepay.g3.core.activity.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.GoodsStatusEnum;
import com.yeepay.g3.facade.activity.enums.GoodsTypeEnum;
import com.yeepay.g3.utils.persistence.EntityVersion;

public class ActivityGoods implements EntityVersion<Long> {

	private static final long serialVersionUID = -8538419248531455354L;

	private Long id;

    private Long version;

    private String goodsCode;

    private String goodsName;

    private GoodsTypeEnum goodsType;

    private String goodsRemark;

    private BigDecimal goodsPrice;

    private Long totalCount;

    private Long grantCount;

    private GoodsStatusEnum goodsStatus;

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

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public GoodsTypeEnum getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsTypeEnum goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark == null ? null : goodsRemark.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getGrantCount() {
        return grantCount;
    }

    public void setGrantCount(Long grantCount) {
        this.grantCount = grantCount;
    }

    public GoodsStatusEnum getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(GoodsStatusEnum goodsStatus) {
        this.goodsStatus = goodsStatus;
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
		return "ActivityGoods [id=" + id + ", version=" + version
				+ ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", goodsType=" + goodsType + ", goodsRemark=" + goodsRemark
				+ ", goodsPrice=" + goodsPrice + ", totalCount=" + totalCount
				+ ", grantCount=" + grantCount + ", goodsStatus=" + goodsStatus
				+ ", createTime=" + createTime + ", creator=" + creator
				+ ", checkedTime=" + checkedTime + ", checkor=" + checkor + "]";
	}
}