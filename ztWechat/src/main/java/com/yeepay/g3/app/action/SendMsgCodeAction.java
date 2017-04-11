/**
 * 
 */
package com.yeepay.g3.app.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.SendMsgCodeBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * @Title: app发送短信验证码接口
 * @Description: 发送短信验证码的请求
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-7-29 下午4:07:51
 * @version 2016-7-29
 */
@Controller
public class SendMsgCodeAction extends BaseAction {
	
	@Autowired
	private SendMsgCodeBiz sendMsgCodeBizImpl; 
	
	private static final Logger logger = LoggerFactory
			.getLogger(SendMsgCodeAction.class);

	/**
	 * app发送短信验证码
	 * @param in - 
	 * @return json
	 */
	public String sendMsgCode(
			@Param("loginName") String loginName,
			@Param("srcNo")String srcNo,
			@Param("mobileNo") String mobileNo,
			@Param("opeType") String opeType
			){
		
		logger.info("[sendMsgCode] loginName={},srcNo={},mobileNo={},opeType={}",loginName,srcNo,mobileNo,opeType);

		UserOpeDTO userOpeDto = new UserOpeDTO();
		try{
			userOpeDto = sendMsgCodeBizImpl.sendMsgCode(srcNo, loginName, mobileNo, opeType);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("[sendMsgCode] ERROR={}", "发送短信异常");
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
	 * app发送短信验证码
	 * @param in - 
	 * @return json
	 */
	public String sendBankMsgCode(
			@Param("loginName") String loginName,
			@Param("srcNo")String srcNo,
			@Param("mobileNo") String mobileNo,
			@Param("realName") String realName,
			@Param("idCard") String idCard,
			@Param("cardNo") String cardNo,
			@Param("tradePwd") String tradePwd
			){
		
		logger.info("[sendBankMsgCode] loginName={},srcNo={},mobileNo={},realName={},idCard={},cardNo={}",loginName,srcNo,mobileNo,realName,idCard,cardNo);

		UserOpeDTO userOpeDto = new UserOpeDTO();
		try{
			userOpeDto = sendMsgCodeBizImpl.sendBankMsgCode(srcNo, loginName, mobileNo, realName,idCard,cardNo,tradePwd);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("[sendBankMsgCode] ERROR={}", "发送短信异常");
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
