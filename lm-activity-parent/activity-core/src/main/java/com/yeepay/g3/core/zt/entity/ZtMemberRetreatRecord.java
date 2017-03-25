package com.yeepay.g3.core.zt.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;


/**
 * @Description 会员最大回撤率测评结果实体类
 * @version 1.0
 */
public class ZtMemberRetreatRecord implements EntityVersion<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -39340664049277894L;

	private Long id;

    private Long version;

    /*会员编号*/
    private String memberNo;
    
    /*回撤率	*/
    private BigDecimal recordRate;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

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

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public BigDecimal getRecordRate() {
        return recordRate;
    }

    public void setRecordRate(BigDecimal recordRate) {
        this.recordRate = recordRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}