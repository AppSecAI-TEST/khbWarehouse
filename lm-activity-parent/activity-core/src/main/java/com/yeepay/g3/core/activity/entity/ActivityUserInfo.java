package com.yeepay.g3.core.activity.entity;

import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description 活动用户基础信息实体类
 * @author zhenping.zhou
 * @CreateTime 2016年5月31日 下午1:44:20
 * @version 1.0
 */
public class ActivityUserInfo implements EntityVersion<Long> {

	private static final long serialVersionUID = 2244342244056391926L;

	private Long id;

    private Long version;
    /**
     * 会员编号
     */
    private String memberNo;
    /**
     * 用户openId
     */
    private String openId;
    /**
     * 用户unionid
     */
    private String unionId;
    /**
     * 订阅该公众号标识
     */
    private Integer wxSubscribe;
    /**
     * 用户关注时间
     */
    private Date wxSubscribeTime;
    /**
     * 用户微信性别
     */
    private Integer wxSex;
    /**
     * 用户微信昵称
     */
    private String wxNickName;
    /**
     * 用户微信头像
     */
    private String wxHeadUrl;
    /**
     * 用户微信城市
     */
    private String wxCity;
    /**
     * 用户微信国家
     */
    private String wxCountry;
    /**
     * 用户微信省份
     */
    private String wxProvince;
    /**
     * 用户微信语言
     */
    private String wxLanguage;
    /**
     * 用户微信备注
     */
    private String wxRemark;
    /**
     * 用户微信所在的分组ID
     */
    private Long wxGroupId;
    /**
     * 用户总幸运分数
     */
    private Integer totalScore;

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

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public Integer getWxSubscribe() {
        return wxSubscribe;
    }

    public void setWxSubscribe(Integer wxSubscribe) {
        this.wxSubscribe = wxSubscribe;
    }

    public Date getWxSubscribeTime() {
        return wxSubscribeTime;
    }

    public void setWxSubscribeTime(Date wxSubscribeTime) {
        this.wxSubscribeTime = wxSubscribeTime;
    }

    public Integer getWxSex() {
        return wxSex;
    }

    public void setWxSex(Integer wxSex) {
        this.wxSex = wxSex;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName == null ? null : wxNickName.trim();
    }

    public String getWxHeadUrl() {
        return wxHeadUrl;
    }

    public void setWxHeadUrl(String wxHeadUrl) {
        this.wxHeadUrl = wxHeadUrl == null ? null : wxHeadUrl.trim();
    }

    public String getWxCity() {
        return wxCity;
    }

    public void setWxCity(String wxCity) {
        this.wxCity = wxCity == null ? null : wxCity.trim();
    }

    public String getWxCountry() {
        return wxCountry;
    }

    public void setWxCountry(String wxCountry) {
        this.wxCountry = wxCountry == null ? null : wxCountry.trim();
    }

    public String getWxProvince() {
        return wxProvince;
    }

    public void setWxProvince(String wxProvince) {
        this.wxProvince = wxProvince == null ? null : wxProvince.trim();
    }

    public String getWxLanguage() {
        return wxLanguage;
    }

    public void setWxLanguage(String wxLanguage) {
        this.wxLanguage = wxLanguage == null ? null : wxLanguage.trim();
    }

    public String getWxRemark() {
        return wxRemark;
    }

    public void setWxRemark(String wxRemark) {
        this.wxRemark = wxRemark == null ? null : wxRemark.trim();
    }

    public Long getWxGroupId() {
        return wxGroupId;
    }

    public void setWxGroupId(Long wxGroupId) {
        this.wxGroupId = wxGroupId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "ActivityUserInfo [id=" + id + ", version=" + version
				+ ", memberNo=" + memberNo + ", openId=" + openId
				+ ", unionId=" + unionId + ", wxSubscribe=" + wxSubscribe
				+ ", wxSubscribeTime=" + wxSubscribeTime + ", wxSex=" + wxSex
				+ ", wxNickName=" + wxNickName + ", wxHeadUrl=" + wxHeadUrl
				+ ", wxCity=" + wxCity + ", wxCountry=" + wxCountry
				+ ", wxProvince=" + wxProvince + ", wxLanguage=" + wxLanguage
				+ ", wxRemark=" + wxRemark + ", wxGroupId=" + wxGroupId
				+ ", totalScore=" + totalScore + ", createTime=" + createTime
				+ "]";
	}
}