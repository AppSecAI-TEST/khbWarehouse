package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.yeepay.g3.facade.activity.enums.CouponRuleStatusEnum;

/**
 * 
 * @Description 规则定义实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:51:31
 * @version 1.0
 */
public class ActivityRuleDTO implements Serializable {
	
	private static final long serialVersionUID = -2437866158982827127L;

	private Long id;

    private Long version;
    
    /** 规则编码 **/
    private String ruleCode;

    /** 规则名称 **/
    private String ruleName;

    /** 生效时间 **/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date takeEffectTime;

    /** 失效时间 **/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date invalidTime;

    /** 状态 **/
    private CouponRuleStatusEnum ruleStatus;

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

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Date getTakeEffectTime() {
        return takeEffectTime;
    }

    public void setTakeEffectTime(Date takeEffectTime) {
        this.takeEffectTime = takeEffectTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public CouponRuleStatusEnum getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(CouponRuleStatusEnum ruleStatus) {
        this.ruleStatus = ruleStatus;
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
		return "ActivityRuleDTO:[id="+id+
				",version="+version+
				",ruleCode="+ruleCode+
				",ruleName="+ruleName+
				",takeEffectTime="+takeEffectTime+
				",invalidTime="+invalidTime+
				",ruleStatus="+ruleStatus+
				",createTime="+createTime+
				",creator="+creator+
				",checkedTime="+checkedTime+
				",checkor"+checkor;
		
	}
    
}