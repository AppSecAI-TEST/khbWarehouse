package com.yeepay.g3.core.activity.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.facade.lmaccs.validation.anotation.Decimal;
import com.yeepay.g3.utils.persistence.EntityVersion;

public class ActivityInvForProRuleXT implements EntityVersion<Long>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3474019579834835444L;

	private Long id;
	/**投资规则名称 **/
    private String ruleName;
    /**产品id **/
    private Long productId;
    /**期限（天） **/
    private Integer trem;
    /**价格 **/
    private BigDecimal price;
    /**创建人员 **/
    private String creater;
    /** 创建时间**/
    private Date createTime;
    /** **/
    private Long version;
    /**年化率 **/
    private BigDecimal rate;

    public BigDecimal getRate() {
		return rate;
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

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getTrem() {
        return trem;
    }

    public void setTrem(Integer trem) {
        this.trem = trem;
    }

    

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ActivityInvForProRuleXT [id=" + id + ", ruleName=" + ruleName
				+ ", productId=" + productId + ", trem=" + trem + ", price="
				+ price + ", creater=" + creater + ", createTime=" + createTime
				+ ", version=" + version + ", rate=" + rate + "]";
	}
}