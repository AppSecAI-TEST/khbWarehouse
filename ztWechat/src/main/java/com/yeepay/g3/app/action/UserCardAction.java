/**
 * 
 */
package com.yeepay.g3.app.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.dto.CardTypeDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.CardBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
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
public class UserCardAction extends BaseAction {
	
	@Autowired
	private CardBiz cardBizImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(UserCardAction.class);

	/**
	 * 
	 * @param realName
	 * @param idCard
	 * @param cardNo
	 * @param tel
	 * @param identifyCode
	 * @param tradePwd
	 * @param srcNo
	 * @param macAddress
	 * @param ip
	 * @param userAgent
	 * @param imei
	 * @return
	 */
	public String addBankCard(
			@Param("realName") String realName,
			@Param("idCard") String idCard,
			@Param("cardNo") String cardNo,
			@Param("tel") String tel,
			@Param("identifyCode") String identifyCode,
			@Param("tradePwd") String tradePwd,
			@Param("srcNo") String srcNo,
			@Param("loginName") String loginName,
			@Param("macAddress") String macAddress,
			@Param("ip") String ip,
			@Param("userAgent") String userAgent,
			@Param("imei") String imei,@Param("deviceType") String deviceType,@Param("userSessionKey") String userSessionKey) {
		logger.info(
				"[addBankCard] realName={},idCard={},cardNo={},tel={},identifyCode={},tradePwd={},srcNo={},loginName={},macAddress={},ip={},userAgent={},imei={},deviceType={},userSessionKey={}",
				realName, idCard, cardNo, tel, identifyCode, tradePwd,
				srcNo,loginName,macAddress,ip,userAgent,imei,deviceType,userSessionKey);
		
		
		//调用biz方法，返回bean后，将该bean转化为json字符串
		UserOpeDTO userOpeDto = null;
		try {
			userOpeDto = cardBizImpl.addBankCard(realName, idCard, cardNo, tel, identifyCode, tradePwd,
					srcNo,loginName,macAddress,ip,userAgent,imei,deviceType,userSessionKey);
			if(userOpeDto == null) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				logger.info("[addBankCard] info={}","绑卡时未知错误");
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		} catch(Exception e) {
			logger.error("[addBankCard] 绑卡时出现异常={}", e);
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}

		return "json";
	
	}
	
	/**
	 * 验证卡的类型和银行名称
	 * @param in - cardNo
	 * @return 
	 */
	public String verifyCardInfo(@Param("cardNo") String cardNo){
		logger.info("[verifyCardInfo] cardNo={}",cardNo);
		CardTypeDTO cardTypeDto = null;
		try{
			cardTypeDto = cardBizImpl.verifyCardInfo(cardNo);
			if(cardTypeDto == null){
				logger.info("[verifyCardInfo] ERROR={}","判断银行卡类型时失败");
				cardTypeDto=new CardTypeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				cardTypeDto.setResultCode(ResultCodeEnum.FAILED);
				cardTypeDto.setErrorCode(error[0]);
				cardTypeDto.setErrorMsg(error[1]);
			}
			String reqResult = JSONObjectUtils.entityToJSON(cardTypeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.error("[verifyCardInfo] info={},ERROR={}","验证卡的类型时失败",e);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			cardTypeDto.setResultCode(ResultCodeEnum.FAILED);
			cardTypeDto.setErrorCode(error[0]);
			cardTypeDto.setErrorMsg(error[1]);
			String reqResult = JSONObjectUtils.entityToJSON(cardTypeDto);
			setJsonModel(reqResult);
		}
		return SUCCESS;
	}
	
	/**
	 * 验证交易密码和登录密码是否相同
	 * @param loginName
	 * @param srcNo
	 * @param userSessionKey
	 * @param tradePwd
	 * @return 
	 */
	public String verTradePwd(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,
			@Param("userSessionKey") String userSessionKey,
			@Param("tradePwd") String tradePwd){
		logger.info("[verTradePwd] loginName={},srcNo={},userSessionKey={},tradePwd={}",loginName,srcNo,userSessionKey,tradePwd);
		UserOpeDTO userOpeDto = null;
		try{
			userOpeDto = cardBizImpl.verTradePwd(loginName, srcNo, userSessionKey, tradePwd);
			if(userOpeDto == null){
				logger.info("[verTradePwd] userOpeDto={}","验证交易密码和登录密码是否相同时出现错误");
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				logger.info("[verTradePwd] info={}","验证交易密码和登录密码是否相同时未知错误");
			}
			String reqResult = JSONObjectUtils.userOpeDtoToJsonStr(userOpeDto);
			setJsonModel(reqResult);
		}catch(Exception e){
			logger.info("[verTradePwd] info={},ERROR={}","验证交易密码和登录密码是否相同时异常",e);
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
