package com.yeepay.g3.app.lmweChat.biz;

import com.yeepay.g3.app.dto.UserOpeDTO;

/**
 * @Title: 发送短信验证码逻辑处理接口
 * @Description: 发送短信验证码
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-29 下午4:11:26
 * @version 2016-7-29
 */
public interface SendMsgCodeBiz {
	/**
	 * 发送短信验证码
	 * @param in - 手机号，操作类型，真实姓名
	 * @return 读取到的固定长度数据
	 */
	public UserOpeDTO sendMsgCode(String srcNo,String loginName,String mobileNo,String opeType);
	
	/**
	 * 发送绑卡短信验证码
	 * @param in - 交易密码、银行四要素、渠道号、登录名
	 * @return 读取到的固定长度数据
	 */
	public UserOpeDTO sendBankMsgCode(String srcNo,String loginName,String mobileNo,String realName,String idCard,String cardNo,String tradePwd);
	
}
