package com.yeepay.g3.core.zt.entity;
import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;
/**
 * 智投策略产品组合
 * @Description
 * @author 陈大涛
 * 2016-10-21下午3:32:01
 */
public class ZtPolicyProduct  implements EntityVersion<Long>{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4052171042344877563L;

	private Long id;

    private Long version;
    /**策略ID **/
    private Long policyId;
    /**产品配比 **/
    private BigDecimal productProportion;
    /**产品名称 **/
    private String productName;
    /**产品编号 **/
    private String productCode;
    /**产品类型 **/
    private String productType;
    /**创建时间 **/
    private Date createTime;

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

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public BigDecimal getProductProportion() {
        return productProportion;
    }

    public void setProductProportion(BigDecimal productProportion) {
        this.productProportion = productProportion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}