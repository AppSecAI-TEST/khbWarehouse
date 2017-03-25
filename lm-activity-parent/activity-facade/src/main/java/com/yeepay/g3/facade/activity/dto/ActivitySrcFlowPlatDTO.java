package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 送流量活动选择流量通道数据实体类
 * @author ping.zhu
 * @CreateTime 2016年7月20日 下午17:23:29
 * @version 1.0
 */
public class ActivitySrcFlowPlatDTO implements Serializable{
	private static final long serialVersionUID = -9135771583205324581L;

	private Long id;

    private Long version;
    /** 渠道号 **/
    private String srcNo;
    /** 手机号号段 **/
    private String mobileSection;
    /** 流量平台号 **/
    private String fluxPlatCode;
    /** 创建时间 **/
    private Date createTime;
    /** 操作人 **/
    private String operator;
    /** 最后修改时间 **/
    private Date lastUpdateTime;

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

    public String getSrcNo() {
        return srcNo;
    }

    public void setSrcNo(String srcNo) {
        this.srcNo = srcNo == null ? null : srcNo.trim();
    }

    public String getMobileSection() {
        return mobileSection;
    }

    public void setMobileSection(String mobileSection) {
        this.mobileSection = mobileSection == null ? null : mobileSection.trim();
    }

    public String getFluxPlatCode() {
        return fluxPlatCode;
    }

    public void setFluxPlatCode(String fluxPlatCode) {
        this.fluxPlatCode = fluxPlatCode == null ? null : fluxPlatCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	@Override
	public String toString() {
		return "ActivitySrcFlowPlatDTO [id=" + id + ", version=" + version
				+ ", srcNo=" + srcNo + ", mobileSection=" + mobileSection
				+ ", fluxPlatCode=" + fluxPlatCode + ", createTime="
				+ createTime + ", operator=" + operator + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
}
