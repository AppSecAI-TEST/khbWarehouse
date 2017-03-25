/**
 * @author 陈大涛
 * 2016-5-31下午4:34:37
 */
package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;

/**
 * @author 陈大涛
 * 2016-5-31下午4:34:37
 */
public class ActivityWXSendMessageDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875079860803454774L;

	/**消息推送的openId **/
	private String openId;
	
	/**消息推送的url **/
	private String url;
	
	/** 公用frist.DATA**/
	private String first;
	
	/**领取成功通知（获取抽奖机会）-领取人 **/
	private String toName;
	
	/**领取成功通知（获取抽奖机会）-赠品 **/
	private String gift;
	
	/**领取成功通知（获取抽奖机会）-领取时间**/
	private String time;
	
	/**中奖结果通知/购买成功通知/注册成功/提现成功/充值成功/绑卡成功/收益到账通知-活动/产品名称/手机号码/银行卡号/充值金额/银行卡号/产品名称 **/
	private String keyword1;
	
	/**中奖结果通知/购买成功通知/注册成功/提现成功/充值成功/绑卡成功/收益到账通知-奖品/购买金额/注册时间/提现金额/充值时间/手机号码/投资金额**/
	private String keyword2;
	
	/**购买成功通知/提现成功/充值成功/收益到账通知-购买时间 /提现日期/账户余额/投资收益**/
	private String keyword3;
	
	/**收益到账通知 -年化收益率**/
	private String keyword4;
	
	/**收益到账通知 -实际到账金额**/
	private String keyword5;
	
	/**公用remark.DATA **/
	private String remark;

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	
	public String getKeyword4() {
		return keyword4;
	}

	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeyword5() {
		return keyword5;
	}

	public void setKeyword5(String keyword5) {
		this.keyword5 = keyword5;
	}

	@Override
	public String toString() {
		return "ActivityWXSendMessageDTO [openId=" + openId + ", url=" + url
				+ ", first=" + first + ", toName=" + toName + ", gift=" + gift
				+ ", time=" + time + ", keyword1=" + keyword1 + ", keyword2="
				+ keyword2 + ", keyword3=" + keyword3 + ", keyword4="
				+ keyword4 + ", remark=" + remark + "]";
	}


}
