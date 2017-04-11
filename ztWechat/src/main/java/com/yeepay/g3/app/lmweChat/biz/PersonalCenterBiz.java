package com.yeepay.g3.app.lmweChat.biz;

import com.yeepay.g3.app.dto.PersonalDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

public interface PersonalCenterBiz {

	/**
	 * 进入个人中心
	 * @param loginName
	 * @param srcNo
	 * @return
	 */
	PersonalDTO intoPersonal(String loginName, String srcNo,String userSessionKey);


	/**
	 * 验证旧的手机号
	 * @param loginName
	 * @param srcNo
	 * @param verifyCode
	 * @param tradePwd
	 * @return
	 */
	UserOpeDTO verifyOldMobileNoCode(String loginName, String srcNo,
			String verifyCode, String tradePwd,String userSessionKey);

	/**
	 * 修改手机号
	 * @param loginName
	 * @param srcNo
	 * @param verifyCode
	 * @param newMobileNo
	 * @return
	 */
	UserOpeDTO modifyMobileNo(String loginName, String srcNo,
			String verifyCode, String newMobileNo,String userSessionKey);


	
	/**
	 * 修改交易密码
	 * @param tradePwd
	 * @param newTradePwd
	 * @param newTradePwdConfirm
	 * @return
	 */
	public UserOpeDTO modifyTradePwd(String loginName,String tradePwd,String newTradePwd,String newTradePwdConfirm,String srcNo,String userSessionKey);
	
	/**
	 * 重置交易密码
	 * @param loginName
	 * @param srcNo
	 * @param credentialsNo
	 * @param newTradePwd
	 * @param newTradePwdConfirm
	 * @param verifyCode
	 * @return
	 */
	public UserOpeDTO resetTradePwd(String loginName,String srcNo,String credentialsNo,String newTradePwd,String newTradePwdConfirm,String verifyCode,String userSessionKey);
	
	/**
	 * 修改登录密码	
	 * @param loginName
	 * @param srcNo
	 * @param credentialsNo
	 * @param loginPwd
	 * @param newLoginPwd
	 * @param newLoginPwdConfirm
	 * @return
	 */
	public UserOpeDTO modifyLoginPwd(String loginName,String srcNo,String loginPwd,String newLoginPwd,String newLoginPwdConfirm,String userSessionKey);
	
	/**
	 * 重置登录密码
	 * @param loginName
	 * @param srcNo
	 * @param loginPwd
	 * @param loginRePwd
	 * @param verifyCode
	 * @return
	 */
	public UserOpeDTO resetLoginPwd(String loginName,String srcNo,String loginPwd,String loginRePwd,String verifyCode);
	
	/**
	 * 退出登录
	 * @param loginName
	 * @param srcNo
	 * @param userSessionKey
	 * @return
	 */
	public UserOpeDTO unBindAccount(String loginName,String srcNo,String userSessionKey);
	
	/**
	 * 添加反馈信息
	 * @param loginName
	 * @param srcNo
	 * @param userSessionKey
	 * @param suggestion
	 * @param reservedNo 反馈人预留手机号
	 * @return
	 */
	public UserOpeDTO addUserSuggestion(String loginName,String srcNo,String userSessionKey,String suggestion,String reservedNo,byte[] suggestionImg);
	
	/**
	 * 判断用户是否实名认证
	 * @param loginName
	 * @param srcNo
	 * @param userSessionKey
	 * @return
	 */
	public UserOpeDTO isAuthenticated(String loginName,String srcNo,String userSessionKey);
}
