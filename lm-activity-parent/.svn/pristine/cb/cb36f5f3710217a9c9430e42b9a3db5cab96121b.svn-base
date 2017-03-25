package com.yeepay.g3.facade.zt.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 智投策略基础信息
 * @Description
 * @author 陈大涛
 * 2016-10-21下午3:32:01
 */
public class ZtPolicyDTO  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6929111471469950835L;


	private Long id;

    private Long version;
    /**策略名称 **/
    private String policyName;
    /**波动性类型 **/
    private String fluctuate;
    /**策略类型 **/
    private String policyType;
    /**动态策略 **/
    private String policyDesc;
    /**相关费用 **/
    private String costDesc;
    /**注意事项**/
    private String attentionDesc;
    /**起购金额 **/
    private BigDecimal minPurchaseAmount;
    /**创建时间 **/
    private Date createTime;
    /**创建人 **/
    private String creator;
    /**审核状态 **/
    private String checkStatus;
    /**审核时间 **/
    private Date checkTime;
    /**审核人	 **/
    private String checker;

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

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName == null ? null : policyName.trim();
    }

    public String getFluctuate() {
        return fluctuate;
    }

    public void setFluctuate(String fluctuate) {
        this.fluctuate = fluctuate == null ? null : fluctuate.trim();
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType == null ? null : policyType.trim();
    }

    public String getPolicyDesc() {
        return policyDesc;
    }

    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc == null ? null : policyDesc.trim();
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc == null ? null : costDesc.trim();
    }

    public String getAttentionDesc() {
        return attentionDesc;
    }

    public void setAttentionDesc(String attentionDesc) {
        this.attentionDesc = attentionDesc == null ? null : attentionDesc.trim();
    }

    public BigDecimal getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
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

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

	@Override
	public String toString() {
		return "ZtPolicyDTO [id=" + id + ", version=" + version
				+ ", policyName=" + policyName + ", fluctuate=" + fluctuate
				+ ", policyType=" + policyType + ", policyDesc=" + policyDesc
				+ ", costDesc=" + costDesc + ", attentionDesc=" + attentionDesc
				+ ", minPurchaseAmount=" + minPurchaseAmount + ", createTime="
				+ createTime + ", creator=" + creator + ", checkStatus="
				+ checkStatus + ", checkTime=" + checkTime + ", checker="
				+ checker + "]";
	}
    
}