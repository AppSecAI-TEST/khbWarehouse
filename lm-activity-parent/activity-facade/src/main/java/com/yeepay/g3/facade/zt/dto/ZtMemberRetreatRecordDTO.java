package com.yeepay.g3.facade.zt.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员最大回撤率测评结果
 * @author hongbin.kang
 * @date 2016年10月21日下午4:26:20
 */
public class ZtMemberRetreatRecordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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