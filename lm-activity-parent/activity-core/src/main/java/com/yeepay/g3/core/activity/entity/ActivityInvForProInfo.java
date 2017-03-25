package com.yeepay.g3.core.activity.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.InvForProInfoStatusEnum;
import com.yeepay.g3.utils.persistence.EntityVersion;

public class ActivityInvForProInfo  implements EntityVersion<Long> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1430827523130290760L;
	/** **/
	private Long id;
	/**名称 **/
    private String name;
    /**活动code **/
    private String activityCode;
    /**价格 **/
    private BigDecimal price;
    /**创建人员 **/
    private String createPerson;
    /**创建时间 **/
    private Date createTime;
    /**url **/
    private String url;
    /**最低购买理财价格 **/
    private BigDecimal productPriceLow;
    /**状态 **/
    private InvForProInfoStatusEnum status;
    /**库存量 **/
    private Integer stockNum;
    /**已使用量 **/
    private Integer usedNum;
    /**审批人员 **/
    private String operatorer;
    /**审批时间 **/
    private Date operatorTime;
    /**版本 **/
    private Long version;
    /**产品图片WX **/
    private byte[] productImg;
    /**产品图片PC **/
    private byte[] productImgPc;
    /**年化率 **/
    private BigDecimal rate;

    public BigDecimal getRate() {
		return rate;
	}
    
	public byte[] getProductImgPc() {
		return productImgPc;
	}

	public void setProductImgPc(byte[] productImgPc) {
		this.productImgPc = productImgPc;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

   

    public InvForProInfoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InvForProInfoStatusEnum status) {
        this.status = status;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public String getOperatorer() {
        return operatorer;
    }

    public void setOperatorer(String operatorer) {
        this.operatorer = operatorer == null ? null : operatorer.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public byte[] getProductImg() {
        return productImg;
    }

    public void setProductImg(byte[] productImg) {
        this.productImg = productImg;
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getProductPriceLow() {
		return productPriceLow;
	}

	public void setProductPriceLow(BigDecimal productPriceLow) {
		this.productPriceLow = productPriceLow;
	}

	@Override
	public String toString() {
		return "ActivityInvForProInfo [id=" + id + ", name=" + name
				+ ", activityCode=" + activityCode + ", price=" + price
				+ ", createPerson=" + createPerson + ", createTime="
				+ createTime + ", url=" + url + ", productPriceLow="
				+ productPriceLow + ", status=" + status + ", stockNum="
				+ stockNum + ", usedNum=" + usedNum + ", operatorer="
				+ operatorer + ", operatorTime=" + operatorTime + ", version="
				+ version +", rate="
				+ rate + "]";
	}

	

	
}