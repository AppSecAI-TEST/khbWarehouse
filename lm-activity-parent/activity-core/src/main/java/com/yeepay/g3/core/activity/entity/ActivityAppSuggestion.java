package com.yeepay.g3.core.activity.entity;

import java.util.Arrays;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

/**
 * 
 * @Description app用户反馈意见实体
 * @author ping.zhu
 * @CreateTime 2016年8月08日 下午15:45:21
 * @version 1.0
 */
public class ActivityAppSuggestion implements EntityVersion<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8836434150072489147L;

	private Long id;

	private Long version;

	private String memberNo;
	/** 反馈预留手机号 **/
	private String reservedNo;
	/** 反馈意见 **/
	private String suggestion;
	/** 反馈时间 **/
	private Date createTime;
	/** 是否回复标志 **/
	private Short replayFlag;
	/** 回复信息 **/
	private String replayMsg;
	/** 回复时间 **/
	private Date replayTime;
	/** 回复人 **/
	private String operator;
	/** 建议图片 **/
	private byte[] suggestionImg;

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

	public String getReservedNo() {
		return reservedNo;
	}

	public void setReservedNo(String reservedNo) {
		this.reservedNo = reservedNo == null ? null : reservedNo.trim();
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion == null ? null : suggestion.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getReplayFlag() {
		return replayFlag;
	}

	public void setReplayFlag(Short replayFlag) {
		this.replayFlag = replayFlag;
	}

	public String getReplayMsg() {
		return replayMsg;
	}

	public void setReplayMsg(String replayMsg) {
		this.replayMsg = replayMsg == null ? null : replayMsg.trim();
	}

	public Date getReplayTime() {
		return replayTime;
	}

	public void setReplayTime(Date replayTime) {
		this.replayTime = replayTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public byte[] getSuggestionImg() {
		return suggestionImg;
	}

	public void setSuggestionImg(byte[] img) {
		this.suggestionImg = img;
	}

	@Override
	public String toString() {
		return "ActivityAppSuggestion [id=" + id + ", version=" + version
				+ ", memberNo=" + memberNo + ", reservedNo=" + reservedNo
				+ ", suggestion=" + suggestion + ", createTime=" + createTime
				+ ", replayFlag=" + replayFlag + ", replayMsg=" + replayMsg
				+ ", replayTime=" + replayTime + ", operator=" + operator
				+ ", suggestionImg=" + Arrays.toString(suggestionImg) + "]";
	}
}