/**
 * 
 */
package com.yeepay.g3.app.action;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderParamDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultPageDto;
import com.yeepay.g3.facade.lmlc.trust.enumtype.TradeOrderStatusEnum;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * @Description 会员登录、注册等请求
 * @author zhenping.zhou
 * @CreateTime 2016年7月28日 上午11:43:41
 * @version 1.0
 */
@Controller
public class UserAction extends BaseAction {
	
	@Autowired
	private UserBiz userBizImpl;
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserAction.class);

	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory
			.getService(TrustQueryFacade.class);
	/**
	 * app用户登录
	 * 会员信息存入redis
	 * @param loginName
	 * @param pwd
	 * @param srcNo
	 * @param deviceType
	 * @param macAddress
	 * @param ip
	 * @param userAgent
	 * @param imei
	 * @return
	 */
	public String login(@Param("loginName") String loginName,
			@Param("pwd") String pwd, 
			@Param("srcNo") String srcNo,
			@Param("deviceType") String deviceType,
			@Param("macAddress") String macAddress,
			@Param("ip") String ip,
			@Param("userAgent") String userAgent,
			@Param("imei") String imei) {
		logger.info("[login] loginName={},deviceType={},macAddress={},ip={},userAgent={},imei={}",
				loginName,deviceType,macAddress,ip,userAgent,imei);
		// 1.获取session和request 不能设置为全局
		HttpServletRequest request = getMvcFacade().getRequest();
		
		logger.info(request.getMethod());
		
		//调用biz方法，返回bean后，将该bean转化为json字符串
		UserOpeDTO userOpeDto = null;
		MemberDto memberDto=null;
		try {
			
			userOpeDto = userBizImpl.login(loginName, pwd, srcNo, deviceType,
					macAddress, ip, userAgent, imei);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "11111"; //未知错误
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			if(ResultCodeEnum.SUCCESS.equals(userOpeDto.getResultCode())){
				//获取用户信息 并存在服务器中与redis同步
				memberDto=userOpeDto.getMemberDto();
				/*session.setAttribute("member", memberDto);
				session.setAttribute("source", srcNo);*/
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		} catch(Exception e) {
			logger.error("登录出现异常：", e);
			userOpeDto = new UserOpeDTO();
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}

		return SUCCESS;
	
	}
	
	/**
	 * app用户注册
	 * @param srcNo 渠道号
	 * @param loginName 登录名
	 * @param loginPwd 登录密码
	 * @param msgCode 短信验证码
	 * @param deviceType 设备类型：安卓、IOS
	 * @param macAddress MAC地址
	 * @param ip ip
	 * @param userAgent 用户代理
	 * @param imei 手机唯一标识
	 * @return 
	 */
	public String register(
			@Param("srcNo") String srcNo,
			@Param("loginName") String loginName,
			@Param("loginPwd") String loginPwd,
			@Param("msgCode") String msgCode,
			@Param("deviceType") String deviceType,
			@Param("macAddress") String macAddress,
			@Param("ip") String ip,
			@Param("userAgent") String userAgent,
			@Param("imei") String imei) {
		logger.info("[register] srcNo={},loginName={},msgCode={},deviceType={}，macAddress={},ip={},userAgent={},imei={}",srcNo,loginName,msgCode,deviceType,macAddress,ip,userAgent,imei);
//		HttpSession session = getMvcFacade().getHttpSession();
		UserOpeDTO userOpeDto = null;
		try{
			userOpeDto = userBizImpl.register(srcNo, loginName, loginPwd, msgCode, deviceType, macAddress, ip, userAgent, imei);
			if(userOpeDto == null){
				userOpeDto = new UserOpeDTO();
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			/*if(ResultCodeEnum.SUCCESS.equals(userOpeDto.getResultCode())){
				session.setAttribute("member", userOpeDto.getMemberDto());
			}*/
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			userOpeDto = new UserOpeDTO();
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
		
	}
	/**
	 * app验证手机号是否已经注册
	 * @param in - 手机号
	 * @return json
	 */
	public String mobileIsRegister(@Param("mobileNo") String mobileNo) {
		logger.info("[mobileIsRegister] mobileNo={}",mobileNo);
		
		UserOpeDTO userOpeDto = null;
		try{
			userOpeDto = userBizImpl.mobileIsRegister(mobileNo);
			if(userOpeDto == null){
				userOpeDto = new UserOpeDTO();
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("[mobileIsRegister] error={}",e);
			userOpeDto = new UserOpeDTO();
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
}
