package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @Description App用户反馈意见实体类
 * @author ping.zhu
 * @CreateTime 2016年3月24日 下午4:30:21
 * @version 1.0
 */
public class ActivityAppSuggestionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7256449928405619987L;
	
	private Long id;

    private Long version;

    private String memberNo;
    /**反馈预留手机号**/
    private String reservedNo;
    /**反馈意见**/
    private String suggestion;
    /**反馈时间**/
    private Date createTime;
    /**是否回复标志**/
    private Short replayFlag;
    /**回复信息**/
    private String replayMsg;
    /**回复时间**/
    private Date replayTime;
    /**回复人**/
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
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityAppSuggestionDTO [id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
		builder.append(", memberNo=");
		builder.append(memberNo);
		builder.append(", reservedNo=");
		builder.append(reservedNo);
		builder.append(", suggestion=");
		builder.append(suggestion);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", replayFlag=");
		builder.append(replayFlag);
		builder.append(", replayMsg=");
		builder.append(replayMsg);
		builder.append(", replayTime=");
		builder.append(replayTime);
		builder.append(", operator=");
		builder.append(operator);
		builder.append("]");
		return builder.toString();
	}
	
}