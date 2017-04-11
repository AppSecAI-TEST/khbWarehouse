package com.yeepay.g3.app.lmweChat.biz;

import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * 
 * @Description 用户操作业务逻辑处理接口
 * @author zhenping.zhou
 * @CreateTime 2016年7月28日 下午5:15:03
 * @version 1.0
 */
public interface UserBiz {
	/**
	 * APP客户端用户登录
	 * @param in -
	 * @return 
	 */
	public UserOpeDTO login(String loginName, String pwd, String srcNo,
			String deviceType, String macAddress, String ip, String userAgent,
			String imei);
	/**
	 * 验证用户在APP客户端是否登录
     * @author ping.zhu
	 * @param loginName
	 * @param pwd
	 * @param srcNo
	 * @return
	 */
	public LoginResultDTO obtainLogin(String loginName, String srcNo,String userSessionKey);
	
	
	/**
	 * 验证手机号是否已经注册过
	 * @param in - 手机号 mobileNo
	 * @return UserOpeDTO
	 */
	public UserOpeDTO mobileIsRegister(String mobileNo);
	
	/**
	 * APP客户端用户注册
	 * @param in - srcNo 渠道号
	 * @param in - loginName 登录名
	 * @param in - loginPwd 登录密码
	 * @param in - msgCode 短信验证码
	 * @param in - deviceType 设备类型：安卓,IOS
	 * @param in - macAddress MAC地址
	 * @param in - ip IP地址
	 * @param in - userAgent 用户代理
	 * @param in - imei 手机唯一标识
	 * @return 
	 */
	public UserOpeDTO register(String srcNo,String loginName,String loginPwd,String msgCode,String deviceType,String macAddress,String ip,String userAgent,String imei);
}
