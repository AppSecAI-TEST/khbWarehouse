/**
 * 
 */
package com.yeepay.g3.app.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yeepay.g3.app.dto.PersonalDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.PersonalCenterBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPwdConstraintDto;
import com.yeepay.g3.facade.lmportal.enumtype.DynamicPwdTypeEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRelevanceStatusEnum;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * @Description 用户绑卡等请求
 * @author hongbin.kang
 * @CreateTime 2016年8月1日  15:43:41
 * @version 1.0
 */
@Controller
public class PersonalCenterAction extends BaseAction {
	
	@Autowired
	private PersonalCenterBiz personBizImpl;
	
	@Autowired
	private UserBiz userBizImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(PersonalCenterAction.class);

    /**
     * 进入用户的账户中心
     * @param loginName
     * @param srcNo
     * @return
     */
	public String toPersonalCenter(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,@Param("userSessionKey") String userSessionKey) {
		logger.info("[account] loginName={}",loginName);
		
		//调用biz方法，返回bean后，将该bean转化为json字符串
		PersonalDTO personalDto = null;
		try {
			personalDto = personBizImpl.intoPersonal(loginName, srcNo,userSessionKey);
			if(personalDto == null) {
				personalDto = new PersonalDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);
				personalDto.setErrorCode(error[0]);
				personalDto.setResultCode(ResultCodeEnum.FAILED);
				personalDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.dtoToJSON(personalDto);
			setJsonModel(reqResult);
		} catch(Exception e) {
			logger.error("进入用户的账户中心：", e);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);
			personalDto = new PersonalDTO();
			personalDto.setErrorCode(error[0]);
			personalDto.setErrorMsg(error[1]);
			personalDto.setResultCode(ResultCodeEnum.FAILED);
			String reqResult = JSONObjectUtils.dtoToJSON(personalDto);
			setJsonModel(reqResult);
		}
		return "json";
	}
	
	/**
	 * 验证旧手机号
	 * @param verifyCode
	 * @param tradePwd
	 * @return
	 */
	public String verifyOldMobileNoCode(@Param("verifyCode") String verifyCode,
			@Param("tradePwd") String tradePwd,@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,@Param("userSessionKey") String userSessionKey) {
		
		UserOpeDTO userOpeDTO = null;
		
		try {
			userOpeDTO = personBizImpl.verifyOldMobileNoCode(loginName, srcNo,verifyCode,tradePwd,userSessionKey);
			if(userOpeDTO == null) {
				userOpeDTO = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
				userOpeDTO.setErrorCode(error[0]);
				userOpeDTO.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.entityToJSON(userOpeDTO);
			setJsonModel(reqResult);
			return "json";
			
		} catch(Exception e) {
			logger.error("验证旧手机号：", e);
			//未知错误
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDTO = new UserOpeDTO();
			userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.entityToJSON(userOpeDTO);
			setJsonModel(reqResult);
			return "json";
		}
	}
	
	
	/**
	 * 修改手机号
	 */
	public String modifyMobileNo(@Param("verifyCode") String verifyCode,
			@Param("newMobileNo") String newMobileNo ,@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,@Param("userSessionKey") String userSessionKey) {
		
		UserOpeDTO userOpeDTO = null;
		
		if (StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(newMobileNo)) {
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TELORCODE_NULL_ERROR);
			userOpeDTO = new UserOpeDTO();
			userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.entityToJSON(userOpeDTO);
			setJsonModel(reqResult);
			return "json";
		}
		if(verifyCode.length()!=6){
			userOpeDTO = new UserOpeDTO();
			logger.info("[register] ERROR={}","短信验证码不为6位");
			userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_SHORT);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.entityToJSON(userOpeDTO);
			setJsonModel(reqResult);
			return "json";
		}
		try {
			userOpeDTO = personBizImpl.modifyMobileNo(loginName, srcNo,verifyCode,newMobileNo,userSessionKey);
			if(userOpeDTO == null) {
				userOpeDTO = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
				userOpeDTO.setErrorCode(error[0]);
				userOpeDTO.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.entityToJSON(userOpeDTO);
			setJsonModel(reqResult);
			return "json";
			
		} catch(Exception e) {
			logger.error("修改手机号异常：", e);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDTO = new UserOpeDTO();
			userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.entityToJSON(userOpeDTO);
			setJsonModel(reqResult);
			return "json";
		}
		
		
		
		
	}
	
	
	/**
	 * 修改交易密码接口
	 * @author ping.zhu
	 * @CreateTime 2016年8月2日 上午09:46:20
	 * @version 1.0
	 */
	public String modifyTradePwd(@Param("loginName") String loginName,
			@Param("tradePwd") String tradePwd,
			@Param("newTradePwd") String newTradePwd,
			@Param("newTradePwdConfirm") String newTradePwdConfirm,
			@Param("srcNo") String srcNo,@Param("userSessionKey") String userSessionKey) {
		logger.info("[modifyTradePwd] loginName={}",loginName);
		UserOpeDTO userOpeDto=null;
		try{
			userOpeDto=personBizImpl.modifyTradePwd(loginName, tradePwd, newTradePwd, newTradePwdConfirm, srcNo,userSessionKey);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("修改交易密码出现异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
	/**
	 * 重置交易密码接口
	 * @author ping.zhu
	 * @CreateTime 2016年8月2日 上午11:13:42
	 * @version 1.0
	 */
	public String resetTradePwd(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,
			@Param("credentialsNo") String credentialsNo,
			@Param("newTradePwd") String newTradePwd,
			@Param("newTradePwdConfirm") String newTradePwdConfirm,
			@Param("verifyCode") String verifyCode,@Param("userSessionKey") String userSessionKey) {
		logger.info("[resetTradePwd] loginName={}",loginName);
		UserOpeDTO userOpeDto=null;
		try{
			userOpeDto=personBizImpl.resetTradePwd(loginName, srcNo, credentialsNo, newTradePwd, newTradePwdConfirm, verifyCode,userSessionKey);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("重置交易密码出现异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 修改登录密码接口
	 * @author ping.zhu
	 * @CreateTime 2016年8月2日 下午16:03:22
	 * @version 1.0
	 */
	public String modifyLoginPwd(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo, @Param("loginPwd") String loginPwd,
			@Param("newLoginPwd") String newLoginPwd,
			@Param("newLoginPwdConfirm") String newLoginPwdConfirm,@Param("userSessionKey") String userSessionKey) {
		logger.info("[modifyLoginPwd] loginName={}",loginName);
		UserOpeDTO userOpeDto=null;
		try{
			userOpeDto=personBizImpl.modifyLoginPwd(loginName, srcNo, loginPwd, newLoginPwd, newLoginPwdConfirm,userSessionKey);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("修改登录密码时出现异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
	/**
	 * 重置登录密码接口
	 * @author ping.zhu
	 * @CreateTime 2016年8月2日 下午16:05:54
	 * @version 1.0
	 */
	public String resetLoginPwd(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,
			@Param("loginPwd") String loginPwd,
			@Param("loginRePwd") String loginRePwd,
			@Param("verifyCode") String verifyCode) {
		logger.info("[resetLoginPwd] loginName={}",loginName);
		UserOpeDTO userOpeDto=null;
		try{
			userOpeDto=personBizImpl.resetLoginPwd(loginName, srcNo, loginPwd, loginRePwd, verifyCode);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("重置登录密码时出现异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
	/**
	 * 退出登录接口
	 * @author ping.zhu
	 * @CreateTime 2016年8月2日 下午17:08:34
	 * @version 1.0
	 */
	public String unBindAccount(@Param("loginName") String loginName,@Param("srcNo") String srcNo,@Param("userSessionKey") String userSessionKey){
		logger.info("[unBindAccount] loginName={}",loginName);
		HttpSession session=getMvcFacade().getHttpSession();
		UserOpeDTO userOpeDto=null;
		try{
			userOpeDto=personBizImpl.unBindAccount(loginName, srcNo,userSessionKey);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("退出登录时出现异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}

	/**
	 * 添加APP用户反馈意见
	 * @author ping.zhu
	 * @CreateTime 2016年8月2日 下午17:08:34
	 * @version 1.0
	 */
	public String addUserSuggestion(@Param("reservedNo") String reservedNo,
			@Param("userSessionKey") String userSessionKey,
			@Param("suggestion") String suggestion,
			@Param("srcNo") String srcNo, @Param("loginName") String loginName) {
		logger.info("[addUserSuggestion] loginName={}", loginName);
		UserOpeDTO userOpeDto = null;
		byte[] suggestionImg = null;
		HttpServletRequest request = getRequest();
		if(request instanceof MultipartHttpServletRequest){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件
		MultipartFile multipartFile = multipartRequest.getFile("suggestionImg");
		try {
			// 输入流
			InputStream is=multipartFile.getInputStream();
			if(is instanceof FileInputStream){
			FileInputStream fis = (FileInputStream) is;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			suggestionImg = bos.toByteArray();
			}
		} catch (Exception e) {
			logger.error("添加APP用户反馈意见图片时异常：", e);
		}
		}
		try{
			userOpeDto=personBizImpl.addUserSuggestion(loginName, srcNo, userSessionKey, suggestion, reservedNo,suggestionImg);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("添加APP用户反馈意见异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
	/**
	 * 验证用户是否鉴权接口
	 * @author ping.zhu
	 * @CreateTime 2016年8月9日 下午14:08:22
	 * @version 1.0
	 */
	public String isAuthenticated(@Param("loginName")String loginName, @Param("srcNo")String srcNo,@Param("userSessionKey")String userSessionKey){
		logger.info("[isAuthenticated] loginName={}",loginName);
		UserOpeDTO userOpeDto=null;
		try{
			userOpeDto=personBizImpl.isAuthenticated(loginName, srcNo, userSessionKey);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("判断用户是否实名认证异常：", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
}
