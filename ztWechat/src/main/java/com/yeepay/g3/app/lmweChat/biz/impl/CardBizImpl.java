package com.yeepay.g3.app.lmweChat.biz.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yeepay.g3.app.dto.CardTypeDTO;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.biz.CardBiz;
import com.yeepay.g3.app.lmweChat.biz.ChannelOpeBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.facade.lmaccs.enumtype.CardVerifyStatusEnum;
import com.yeepay.g3.facade.lmnotice.dto.VerifyDynamicPwdParamDto;
import com.yeepay.g3.facade.lmnotice.dto.VerifyDynamicPwdResultDto;
import com.yeepay.g3.facade.lmnotice.service.NoticeFacade;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.CardInfoResultDto;
import com.yeepay.g3.facade.lmportal.dto.CardVerifyAndPasswordParamDto;
import com.yeepay.g3.facade.lmportal.dto.CardVerifyAndPasswordResultDto;
import com.yeepay.g3.facade.lmportal.dto.ChannelDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPasswordValidationResultDto;
import com.yeepay.g3.facade.lmportal.dto.MemberRegResultDto;
import com.yeepay.g3.facade.lmportal.enumtype.DeviceEnum;
import com.yeepay.g3.facade.lmportal.enumtype.DynamicPwdTypeEnum;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordValidationTypeEnum;
import com.yeepay.g3.facade.lmportal.service.LMUtilFacde;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoTransactionFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.Digest;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;
import com.yeepay.g3.utils.web.IpUtils;

/**
 * @Description 用户绑卡等请求
 * @author hongbin.kang
 * @CreateTime 2016年8月1日  15:43:41
 * @version 1.0
 */
@Component
public class CardBizImpl implements CardBiz {
	
	private static final Logger logger = LoggerFactory.getLogger(CardBizImpl.class);
	
	@Autowired
	private ChannelOpeBiz channelOpeBizImpl;
	@Autowired
	private UserBiz userBizImpl;
	
	protected LanmaoDemandFacade lanmaoDemandfFacade = RemoteServiceFactory.getService(LanmaoDemandFacade.class);
	protected LanmaoTransactionFacade lanmaoTransactionFacade = RemoteServiceFactory.getService(LanmaoTransactionFacade.class);
	protected LMUtilFacde lMUtilFacde = RemoteServiceFactory
			.getService(LMUtilFacde.class);
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);

	protected NoticeFacade noticeFacade = RemoteServiceFactory.getService(NoticeFacade.class);
	@Override
	public UserOpeDTO addBankCard(String realName, String idCard,
			String cardNo, String tel, String identifyCode, String tradePwd,
			String srcNo, String loginName, String macAddress, String ip,
			String userAgent, String imei, String deviceType,
			String userSessionKey) {
		UserOpeDTO userOpeDto = null;
		MemberDto memberDto=null;
		//1.请求参数不能为空
		if (CheckUtils.isEmpty(realName) || CheckUtils.isEmpty(idCard)
				|| CheckUtils.isEmpty(cardNo) || CheckUtils.isEmpty(tel)
				|| CheckUtils.isEmpty(tradePwd)
				|| CheckUtils.isEmpty(identifyCode)
				|| CheckUtils.isEmpty(loginName)
				|| CheckUtils.isEmpty(srcNo)
				|| CheckUtils.isEmpty(userSessionKey)) {
			userOpeDto = new UserOpeDTO();
			logger.info("[addBankCard] error={}","有参数为空");
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);	
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		if(identifyCode.length()!=6){
			userOpeDto = new UserOpeDTO();
			logger.info("[addBankCard] ERROR={}","短信验证码不为6位");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_SHORT);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		//2.验证是否登录
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		logger.info("[addBankCard] loginResultDTO={}",loginResultDTO);
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			memberDto = loginResultDTO.getMemberDto();
			//3.验证交易密码和登录密码
			try {
				MemberPasswordValidationResultDto memberPasswordValidationResultDto = lanmaoDemandfFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),tradePwd,PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
				if(null!=memberPasswordValidationResultDto&&!memberPasswordValidationResultDto.getStatus().equals(PasswordStatusEnum.SUCCESS)){
					logger.info("[addBankCard] info={},memberPasswordValidationResultDto={}","交易密码和登录密码相同",memberPasswordValidationResultDto);
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[addBankCard] info={},ERROR={}","验证交易密码和登录密码相同时异常",e);
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			//4.验证身份证是否已经注册
			try {
				MemberRegResultDto memberRegResultDto = lanmaoDemandfFacade.queryMemberByIdCard(idCard);
				logger.info("[addBankCard] memberRegResultDto={}",memberRegResultDto);
				//不为空，就说明身份证注册过，或者验证失败了
				if(memberRegResultDto != null){
					//已注册过的老用户
					if(memberRegResultDto.getMemberNo() != null){
						//绑卡时使用的是自己的身份证
						if(memberDto.getMemberNo().equals(memberRegResultDto.getMemberNo())){
							//判断是否绑过卡
							BankCardInfoDto bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
							//不为空则有银行卡信息，有银行卡信息，不可以绑卡
							if(bankCardInfoDto != null){
								userOpeDto = new UserOpeDTO();
								String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_BANKCARD_EXIST_ERROR);	
								userOpeDto.setResultCode(ResultCodeEnum.FAILED);
								userOpeDto.setErrorCode(error[0]);
								userOpeDto.setErrorMsg(error[1]);
								return userOpeDto;
							}
						}else{
							//用户输入的身份证已经在平台注册过，并且不是自己，不允许绑卡
							logger.info("[addBankCard] info={},memberRegResultDto={},memberDto={}","身份证已经在平台注册",memberRegResultDto,memberDto);
							userOpeDto = new UserOpeDTO();
							String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_IDCARD_ERROR);	
							userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//							String errorCode = "10011";
							userOpeDto.setErrorCode(error[0]);
							userOpeDto.setErrorMsg(error[1]);
							return userOpeDto;
						}
					}else{
						//验证身份证信息返回了dto，但是却没有会员编号，可能验证中出现问题
						userOpeDto = new UserOpeDTO();
						String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_IDCARD_EXIST_ERROR);	
						userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//						String errorCode = "10012";
						userOpeDto.setErrorCode(error[0]);
						userOpeDto.setErrorMsg(error[1]);
						return userOpeDto;
					}
				}else {
					//输入的身份证没有在平台注册
					//如果当前用户的身份证信息在数据库存在，说明该用户之前绑过卡。那不允许该用户更换身份证，绑定他人的卡
					if( StringUtils.isNotEmpty(memberDto.getCredentialsNo()) && !idCard.equals(memberDto.getCredentialsNo())) {
						logger.error("[addBankCard] ERROR={}","不能更换成别人的身份证");
						userOpeDto = new UserOpeDTO();
						String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_BIND_OTHER_CARD_ERROR);	
						userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//						String errorCode = "10047";绑卡失败，请绑定本人银行卡
						userOpeDto.setErrorCode(error[0]);
						userOpeDto.setErrorMsg(error[1]);
						return userOpeDto;
					}
				}
			} catch (Exception e) {
				logger.error("[addBankCard] info={},error={}","验证身份证时出现异常",e);
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_IDCARD_ISREGISTER_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10043"; //验证身份证是否已经注册时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			//5.验证手机验证码
			try {
				/*if (GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(
								DynamicPwdTypeEnum.BINDBANKCARD, tel, identifyCode)) {
					logger.info("[addBankCard] ERROR={}","短信验证码输入错误");*/
				VerifyDynamicPwdParamDto param = new VerifyDynamicPwdParamDto();
				param.setMobileNo(tel);
				param.setTemplateNo("bindcard");
				param.setValidateCode(identifyCode);
				VerifyDynamicPwdResultDto verifyDynamicPwdResultDto = noticeFacade.verifyDynamicPwd(param);
				if (!verifyDynamicPwdResultDto.isResultFlag()) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//						String errorCode = "10013";
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
//				}
			} catch (Exception e) {
				logger.error("[addBankCard] info={},ERROR={}", "验证手机验证码异常",
						e);
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_MSGCODE_EXCEPTION);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10014";
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			
			//6.绑卡
			CardVerifyAndPasswordResultDto cardVerifyAndPasswordResultDto=null;
			try {
				Object obj = JSONObjectUtils.userAddrToJSONStr(ip, macAddress, userAgent, imei);
				//绑卡的信息
				CardVerifyAndPasswordParamDto cardVerifyAndPasswordParamDto = new CardVerifyAndPasswordParamDto();
				cardVerifyAndPasswordParamDto.setDevice(DeviceEnum.valueOf(deviceType));
				cardVerifyAndPasswordParamDto.setMemberNo(memberDto.getMemberNo());
				cardVerifyAndPasswordParamDto.setBankAccountName(realName);
				cardVerifyAndPasswordParamDto.setBankAccountNo(cardNo);
				cardVerifyAndPasswordParamDto.setTradingPassword(tradePwd);
				cardVerifyAndPasswordParamDto.setBindMobile(tel);
				cardVerifyAndPasswordParamDto.setIdNumber(idCard);
				cardVerifyAndPasswordParamDto.setValidateCode(identifyCode);
				if (obj != null) {
					cardVerifyAndPasswordParamDto.setIp(obj.toString());
				}
				logger.info("[addBankCard] requestNo={}",SmartCacheUtils.get(memberDto.getMemberNo()));
				String requestNo = null;
				if(null != SmartCacheUtils.get(memberDto.getMemberNo()) && !"".equals(SmartCacheUtils.get(memberDto.getMemberNo()))){
					requestNo = SmartCacheUtils.get(memberDto.getMemberNo()).toString();
				}
				cardVerifyAndPasswordParamDto.setRequestNo(requestNo);
				logger.info("[addBankCard] cardVerifyAndPasswordParamDto={}",cardVerifyAndPasswordParamDto);
				cardVerifyAndPasswordResultDto = lanmaoTransactionFacade.cardVerifyAndSavePassword(cardVerifyAndPasswordParamDto);
				logger.info("[addBankCard] cardVerifyAndPasswordResultDto={}",cardVerifyAndPasswordResultDto);
				//状态码不是0，说明失败
				if(!CardVerifyStatusEnum.SUCCESS.equals(cardVerifyAndPasswordResultDto.getStatus())){
					logger.info("[addBankCard] info={},cardVerifyAndPasswordParamDto={},cardVerifyAndPasswordResultDto={}","绑卡状态失败",cardVerifyAndPasswordParamDto,cardVerifyAndPasswordResultDto);
					userOpeDto = new UserOpeDTO();
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
					userOpeDto.setErrorCode(cardVerifyAndPasswordResultDto.getCode());
					userOpeDto.setErrorMsg(cardVerifyAndPasswordResultDto.getDescription());
					return userOpeDto;
				}
				/*if(!"0".equals(cardVerifyAndPasswordResultDto.getCode())){
					logger.info("[addBankCard] info={},cardVerifyAndPasswordParamDto={},cardVerifyAndPasswordResultDto={}","绑卡状态失败",cardVerifyAndPasswordParamDto,cardVerifyAndPasswordResultDto);
					userOpeDto = new UserOpeDTO();
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
					//底层封装的明确的错误信息
					userOpeDto.setErrorCode(cardVerifyAndPasswordResultDto.getCode());
					userOpeDto.setErrorMsg(cardVerifyAndPasswordResultDto.getDescription());
					return userOpeDto;
				}*/
				SmartCacheUtils.remove(LmConstants.SESSION_BANCARD_REQUESTNO);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("[addBankCard] info={},error={}","绑卡时出现异常",e);
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_BIND_CARD_EXCEPTION);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10044";
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//			String errorCode = "00000";
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			if(memberDto != null){
				userOpeDto.setMemberNo(memberDto.getMemberNo());
				userOpeDto.setMemberTel(memberDto.getBindMobileNo());
				userOpeDto.setUserSessionKey(userSessionKey);
			}
		}else{
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}

	@Override
	public CardTypeDTO verifyCardInfo(String cardNo) {
		CardTypeDTO cardTypeDto = new CardTypeDTO();
		//卡号非空校验
		if(CheckUtils.isEmpty(cardNo)){
			logger.info("[verifyCardInfo] info={}", "卡号参数为空");
			cardTypeDto = new CardTypeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);
			cardTypeDto.setResultCode(ResultCodeEnum.FAILED);
			cardTypeDto.setErrorCode(error[0]);
			cardTypeDto.setErrorMsg(error[1]);
			return cardTypeDto;
		}
		//验证卡类型
		try {
			Map<String,Object> resultMap=new HashMap<String, Object>();
			CardInfoResultDto cirDto=lanmaoDemandfFacade.cardBin(cardNo);
			//验证卡类型时出现异常 
			if(!cirDto.getStatus().equals("SUCCESS")){
				logger.info("[verifyCardInfo] info={},cardNo={},cirDto={}","验证卡类型失败",cardNo,cirDto);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_CARD_TYPE_EXCEPTION);
				cardTypeDto.setResultCode(ResultCodeEnum.FAILED);
				cardTypeDto.setErrorCode(error[0]);
				cardTypeDto.setErrorMsg(error[1]);
				return cardTypeDto;
			}
			cardTypeDto.setResultCode(ResultCodeEnum.SUCCESS);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);
			cardTypeDto.setErrorCode(error[0]);
			cardTypeDto.setErrorMsg(error[1]);
			cardTypeDto.setBankCode(cirDto.getBankCode());
			cardTypeDto.setBankId(cirDto.getBankId());
			cardTypeDto.setBankName(cirDto.getBankName());
			cardTypeDto.setCardName(cirDto.getCardName());
			cardTypeDto.setCardType(cirDto.getCardType());
			return cardTypeDto;
		} catch (Exception e) {
			logger.info("[verifyCardInfo] info={},ERROR={}","判断银行卡类型时失败",e.getMessage());
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			cardTypeDto.setResultCode(ResultCodeEnum.FAILED);
			cardTypeDto.setErrorCode(error[0]);
			cardTypeDto.setErrorMsg(error[1]);
			return cardTypeDto;
		}
	}

	@Override
	public UserOpeDTO verTradePwd(String loginName, String srcNo,
			String userSessionKey, String tradePwd) {
		UserOpeDTO userOpeDto = null;
		MemberDto memberDto=null;
		//1.请求参数不能为空
		if (CheckUtils.isEmpty(loginName) || CheckUtils.isEmpty(srcNo)
				|| CheckUtils.isEmpty(userSessionKey) || CheckUtils.isEmpty(tradePwd)) {
			userOpeDto = new UserOpeDTO();
			logger.info("[verTradePwd] error={}","参数为空");
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);	
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		//2.验证是否登录
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			memberDto = loginResultDTO.getMemberDto();
			//3.验证交易密码和登录密码
			try {
				MemberPasswordValidationResultDto memberPasswordValidationResultDto = lanmaoDemandfFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),tradePwd,PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
				logger.info("[verTradePwd] memberPasswordValidationResultDto={}",memberPasswordValidationResultDto);
				if(memberPasswordValidationResultDto == null){
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				if(!memberPasswordValidationResultDto.getStatus().equals(PasswordStatusEnum.SUCCESS)){
					logger.info("[verTradePwd] info={},memberPasswordValidationResultDto={}","验证交易密码和登录密码",memberPasswordValidationResultDto);
					userOpeDto = new UserOpeDTO();
//					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
					userOpeDto.setErrorCode(memberPasswordValidationResultDto.getCode());
					userOpeDto.setErrorMsg(memberPasswordValidationResultDto.getDescription());
					return userOpeDto;
				}
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
				userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//						String errorCode = "00000";
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				if(memberDto != null){
					userOpeDto.setMemberNo(memberDto.getMemberNo());
					userOpeDto.setMemberTel(memberDto.getBindMobileNo());
					userOpeDto.setUserSessionKey(userSessionKey);
					userOpeDto.setMemberDto(memberDto);
				}
			} catch (Exception e) {
				logger.info("[verTradePwd] ERROR={}","验证交易密码和登录密码相同时异常");
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
		}else{
			logger.info("[verTradePwd] info={},loginResultDTO={}","验证登录状态失败",loginResultDTO);
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}
	

}
