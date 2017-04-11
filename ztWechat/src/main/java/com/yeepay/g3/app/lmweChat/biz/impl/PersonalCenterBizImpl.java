
package com.yeepay.g3.app.lmweChat.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lanmao.consultant.facade.service.UserFacade;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.dto.PersonalDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.BindCardStatusEnum;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.biz.ChannelOpeBiz;
import com.yeepay.g3.app.lmweChat.biz.PersonalCenterBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.StringProcessorUtils;
import com.yeepay.g3.facade.activity.dto.ActivityAppSuggestionDTO;
import com.yeepay.g3.facade.activity.facade.ActivityAppSuggestionFacade;
import com.yeepay.g3.facade.activity.facade.ActivityShareRecordsFacade;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPwdConstraintDto;
import com.yeepay.g3.facade.lmportal.enumtype.DynamicPwdTypeEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRelevanceStatusEnum;
import com.yeepay.g3.facade.lmportal.service.ChannelFacade;
import com.yeepay.g3.facade.lmportal.service.LMUtilFacde;
import com.yeepay.g3.facade.lmportal.dto.MemberPasswordValidationResultDto;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordValidationTypeEnum;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoTransactionFacade;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.facade.lmportal.service.MemberPasswordFacade;
import com.yeepay.g3.utils.common.BeanUtils;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;

@Component
public class PersonalCenterBizImpl implements PersonalCenterBiz {
	
	
	@Autowired
	private UserBiz userBizImpl;
	@Autowired
	private ChannelOpeBiz channelOpeBizImpl;
	
	protected MemberPasswordFacade memberPasswordFacade = RemoteServiceFactory.getService(MemberPasswordFacade.class);
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory.getService(MemberManagementFacade.class);
	protected LMUtilFacde lMUtilFacde = RemoteServiceFactory.getService(LMUtilFacde.class);
	protected LanmaoTransactionFacade lanmaoTransactionFacade = RemoteServiceFactory.getService(LanmaoTransactionFacade.class);
	protected LanmaoDemandFacade lanmaoDemandFacade = RemoteServiceFactory.getService(LanmaoDemandFacade.class);
	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory.getService(TrustQueryFacade.class);
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory.getService(LPQueryFacade.class);
	protected ActivityShareRecordsFacade activityShareRecordsFacade = RemoteServiceFactory.getService(ActivityShareRecordsFacade.class);
	protected ChannelFacade channelFacade = RemoteServiceFactory.getService(ChannelFacade.class);
	protected UserFacade userFacade = RemoteServiceFactory.getService(UserFacade.class);
	protected ActivityWXSendMessageFacade activityWXSendMessageFacade = RemoteServiceFactory.getService(ActivityWXSendMessageFacade.class);
	protected ActivityAppSuggestionFacade activityAppSuggestionFacade=RemoteServiceFactory.getService(ActivityAppSuggestionFacade.class);	
	protected Logger logger = LoggerFactory.getLogger(PersonalCenterBizImpl.class);
	
	@Override
	public PersonalDTO intoPersonal(String loginName, String srcNo,String userSessionKey) {
		
		PersonalDTO personalDto = new PersonalDTO();
		
		//判断请求是否合法
		LoginResultDTO loginResultDto = userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);
		//用户已登录
		if(LoginResultEnum.SUCCESS.equals(loginResultDto.getResultMsg())) {
			// 待加判断是否绑卡接口--待s
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			MemberDto memberDto = loginResultDto.getMemberDto();
			BankCardInfoDto bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto
					.getMemberNo());
			if (bankCardInfoDto != null) {
				personalDto.setTradePwd("********");
				personalDto.setCredentialsNo(StringProcessorUtils.desensitizedIdNumber(bankCardInfoDto.getIdentityNo()));
				personalDto.setRealName("*********".substring(0,bankCardInfoDto.getBankUserName().length()-1)+bankCardInfoDto.getBankUserName().substring(bankCardInfoDto.getBankUserName().length()-1));
				personalDto.setStatus(BindCardStatusEnum.BIND);
			} else {
				if(memberDto.getCredentialsNo()!=null){//老系统用户注册时，有交易密码和身份证；
					personalDto.setTradePwd("********");
					personalDto.setCredentialsNo(StringProcessorUtils.desensitizedIdNumber(memberDto
							.getCredentialsNo()));
					personalDto.setRealName("*********".substring(0,memberDto.getRealName().length()-1)+memberDto.getRealName().substring(memberDto.getRealName().length()-1));
				}else{
					personalDto.setTradePwd("暂未设置交易密码");
					personalDto.setCredentialsNo("暂未设置身份证号码");
				}
				personalDto.setStatus(BindCardStatusEnum.UNBIND);
			}
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
//			String errorCode = "00000";
			personalDto.setResultCode(ResultCodeEnum.SUCCESS);
			personalDto.setErrorCode(error[0]);
			personalDto.setErrorMsg(error[1]);
			personalDto.setLoginName(memberDto.getLoginName());
			personalDto.setBindMobileNo(StringProcessorUtils.desensitizedMobileNo(memberDto.getBindMobileNo()));
			personalDto.setCreateDate(dateFormat.format(memberDto.getCreateDate()));
			return personalDto;
			
		} else {
			personalDto.setResultCode(ResultCodeEnum.FAILED);
			personalDto.setErrorCode(loginResultDto.getUserOpeDTO().getErrorCode());
//			personalDto.setErrorMsg(config.getValue().get(loginResultDto.getUserOpeDTO().getErrorCode()));
			personalDto.setErrorMsg(loginResultDto.getUserOpeDTO().getErrorMsg());
			return personalDto;
		}
	}
	
	@Override
	public UserOpeDTO modifyTradePwd(String loginName,String tradePwd, String newTradePwd,
			String newTradePwdConfirm,String srcNo,String userSessionKey) {
		// TODO Auto-generated method stub
		UserOpeDTO userOpeDto=null;
		//请求参数不能为空
		if (CheckUtils.isEmpty(tradePwd) || CheckUtils.isEmpty(newTradePwd) || CheckUtils.isEmpty(newTradePwdConfirm)) {
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);	
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10024"; //请求不合理，有请求参数为空
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		//验证登录是否失效
		LoginResultDTO loginResultDTO=userBizImpl.obtainLogin(loginName, srcNo,userSessionKey);
		//说明已登录
		if(LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())){
			//两次交易密码不相同
			if(!newTradePwdConfirm.equals(newTradePwd)){
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_CONFIRM_TRADEPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10025"; //两次交易密码不相同
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
				//重置的交易密码与原密码相同
			}else if(tradePwd.equals(newTradePwd)){
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_RESET_TRADEPWD_EQUALS_PERPWD);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10026"; //重置的交易密码与原密码相同
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			
			MemberDto memberDto=loginResultDTO.getMemberDto();
			try {
				// 1.检查原交易密码是否正确
				MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
						.checkTradePassword(memberDto.getMemberNo(), tradePwd);
				//验证原交易密码错误
				if (!memberPwdConstraintDto.getResultFlag()) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10017"; //交易密码验证错误
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[modifyTradePwd] info={},ERROR={}",
						"验证交易密码异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_EXCEPTION);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10018"; //验证交易密码异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			try {
				// 2.检查新交易密码与登录密码是否一致
				MemberPasswordValidationResultDto result = lanmaoDemandFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),
								newTradePwd,
								PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
				if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10008"; //交易密码与登录密码一致
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[modifyTradePwd] info={},ERROR={}",
						"验证新交易密码与登录密码是否一致时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10029"; //验证新交易密码与登录密码是否一致时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			// 3.修改交易密码
			try {
				memberPasswordFacade.resetTradePassword(
						memberDto.getMemberNo(), newTradePwd);
			} catch (Exception e) {
				logger.error("[modifyTradePwd] info={},ERROR={}",
						"修改交易密码时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MODIFY_TRADEPWD_EXCEPTION);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10030"; //修改交易密码时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//			String errorCode = "00000"; //请求成功
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
		}else{
			//渠道无效或未知异常
			return loginResultDTO.getUserOpeDTO();
		}		
		return userOpeDto;
	}
	
	@Override
	public UserOpeDTO verifyOldMobileNoCode(String loginName, String srcNo,
			String verifyCode, String tradePwd,String userSessionKey) {
		
		UserOpeDTO userOpeDTO = new UserOpeDTO();
		if (StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(tradePwd)) {
			//验证码或交易密码为空
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_CODE_OR_TRADEPWD_NULL_ERROR);	
//			String errorCode = "10016";
			userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			return userOpeDTO;
		}
		if(verifyCode.length()!=6){
			logger.info("[register] ERROR={}","短信验证码不为6位");
			userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_SHORT);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			return userOpeDTO;
		}
		//判断请求是否合法
		LoginResultDTO loginResultDto = userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);
		//用户已登录
		if(LoginResultEnum.SUCCESS.equals(loginResultDto.getResultMsg())) {
			MemberDto memberDto = loginResultDto.getMemberDto();
			try {
				MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
						.checkTradePassword(memberDto.getMemberNo(), tradePwd);
				if (!memberPwdConstraintDto.getResultFlag()) {
					//交易密码验证不通过
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_ERROR);	
//					String errorCode = "10017";
					userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
					userOpeDTO.setErrorCode(error[0]);
					userOpeDTO.setErrorMsg(error[1]);
					return userOpeDTO;
				}
			} catch (Exception e) {
				logger.error("[verifyOldMobileNoCode] info={},ERROR={}",
						"交易密码受限异常", e.getMessage());
				//交易密码受限异常
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_EXCEPTION);	
//				String errorCode = "10018";
				userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
				userOpeDTO.setErrorCode(error[0]);
				userOpeDTO.setErrorMsg(error[1]);
				return userOpeDTO;
			}
			try {
				if (GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.MOLD,
								memberDto.getBindMobileNo(), verifyCode)) {
					//验证短信不通过
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);	
//					String errorCode = "10013";
					userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
					userOpeDTO.setErrorCode(error[0]);
					userOpeDTO.setErrorMsg(error[1]);
					return userOpeDTO;
				}
			} catch (Exception e) {
				logger.error("[verifyOldMobileNoCode] info={},ERROR={}", "验证短信异常",
						e.getMessage());
				//验证短信异常
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_MSGCODE_EXCEPTION);	
//				String errorCode = "10014";
				userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
				userOpeDTO.setErrorCode(error[0]);
				userOpeDTO.setErrorMsg(error[1]);
				return userOpeDTO;
			}
			//验证通过可以修改
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
//			String errorCode = "00000";
			userOpeDTO.setResultCode(ResultCodeEnum.SUCCESS);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			return userOpeDTO;
		}else {
			return loginResultDto.getUserOpeDTO();
		}
	}
	
	@Override
	public UserOpeDTO modifyMobileNo(String loginName, String srcNo,
			String verifyCode, String newMobileNo,String userSessionKey) {
		
		
		UserOpeDTO userOpeDTO = new UserOpeDTO();
		
		//判断请求是否合法
		LoginResultDTO loginResultDto = userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);		
		//用户已登录
		if(LoginResultEnum.SUCCESS.equals(loginResultDto.getResultMsg())) {
			MemberDto memberDto = loginResultDto.getMemberDto();
			try {
				if (GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.MNEW,
								newMobileNo, verifyCode)) {
					//验证短信不通过
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);	
//					String errorCode = "10013";
					userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
					userOpeDTO.setErrorCode(error[0]);
					userOpeDTO.setErrorMsg(error[1]);
					return userOpeDTO;
				}
			} catch (Exception e) {
				logger.error("[modifyMobileNo] info={},ERROR={}", "验证短信验证码异常",
						e.getMessage());
				//验证短信异常
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_MSGCODE_EXCEPTION);	
//				String errorCode = "10014";
				userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
				userOpeDTO.setErrorCode(error[0]);
				userOpeDTO.setErrorMsg(error[1]);
				return userOpeDTO;
			}
			
			try {
				if (!memberManagementFacade.updateMemberLoginName(
						memberDto.getMemberNo(), newMobileNo)) {
					//修改手机号失败
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MODIFY_TEL_FAILED_ERROR);	
//					String errorCode = "10021";
					userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
					userOpeDTO.setErrorCode(error[0]);
					userOpeDTO.setErrorMsg(error[1]);
					return userOpeDTO;
				}
			} catch (Exception e) {
				logger.error("[modifyMobileNo] info={},ERROR={}", "修改手机号码异常",
						e.getMessage());
				//修改手机号异常
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MODIFY_TEL_EXCEPTION);	
//				String errorCode = "10022";
				userOpeDTO.setResultCode(ResultCodeEnum.FAILED);
				userOpeDTO.setErrorCode(error[0]);
				userOpeDTO.setErrorMsg(error[1]);
				return userOpeDTO;
			}
			//TODO
			try {
				memberManagementFacade.downMemberRelevance(memberDto.getMemberNo(), MemberRelevanceStatusEnum.OFF);
				//清空redis
				SmartCacheUtils.remove(userSessionKey);
			} catch (Exception e) { 
				logger.error("[modifyMobileNo] info={},ERROR={}",
						"解绑OPENID与会员账户的关系时异常", e.getMessage());
				//TODO 解绑 与修改手机应该在同一事物?
			}
			//修改手机号失败
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
//			String errorCode = "00000";
			userOpeDTO.setResultCode(ResultCodeEnum.SUCCESS);
			userOpeDTO.setErrorCode(error[0]);
			userOpeDTO.setErrorMsg(error[1]);
			return userOpeDTO;
		} else {
			return loginResultDto.getUserOpeDTO();
		}
	}
	
	@Override
	public UserOpeDTO resetTradePwd(String loginName, String srcNo,
			String credentialsNo, String newTradePwd,
			String newTradePwdConfirm, String verifyCode,String userSessionKey) {
		// TODO Auto-generated method stub
		UserOpeDTO userOpeDto = null;
		MemberDto memberDto = null;
		// 统一配置取错误码
		ConfigParam<Map<String, String>> config = ConfigurationUtils
				.getConfigParam("config_type_text_resources",
						"lmapp_error_code");
		// 1.请求参数不能为空
		if (CheckUtils.isEmpty(credentialsNo)
				|| CheckUtils.isEmpty(newTradePwd)
				|| CheckUtils.isEmpty(newTradePwdConfirm)
				|| CheckUtils.isEmpty(verifyCode)) {
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);	
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10024"; // 请求不合理，有请求参数为空
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		if(verifyCode.length()!=6){
			userOpeDto = new UserOpeDTO();
			logger.info("[register] ERROR={}","短信验证码不为6位");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_SHORT);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		// 2.验证登录是否失效
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		// 说明已登录
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			memberDto = loginResultDTO.getMemberDto();
			//验证用户是否绑卡 即是否实名认证过
			try{
				BankCardInfoDto bankCardInfoDto = lPQueryFacade
						.obtainDefaultBankCardInfo(memberDto.getMemberNo());
				if (null == bankCardInfoDto) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_USER_NO_BANKCARD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10041"; // 用户尚未绑卡
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[resetTradePwd] info={},ERROR={}",
						"验证用户是否绑卡时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_ISBINDCARD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10042"; // 查询用户是否绑卡时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			// 3.验证验证码
			try {
				if (CheckUtils.isEmpty(verifyCode)||GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.RPAYPWD,
								memberDto.getBindMobileNo(), verifyCode)) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10013"; // 验证码不正确
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[resetTradePwd] info={},ERROR={}",
						"验证手机验证码异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_MSGCODE_EXCEPTION);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10014"; // 验证手机验证码异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			// 4.验证身份证是否正确
			if (credentialsNo == null
					|| !credentialsNo.toUpperCase().equals(
							memberDto.getCredentialsNo().toUpperCase())) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_IDCARD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10028"; // 身份证验证错误
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
				
			}
			// 5.验证两次交易密码是否相同
			if (!newTradePwdConfirm.equals(newTradePwd)) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_CONFIRM_TRADEPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10025"; // 两次交易密码不相同
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			// 6.检查新交易密码与登录密码是否一致
			try {
				MemberPasswordValidationResultDto result = lanmaoDemandFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),
								newTradePwd,
								PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
				if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10008"; // 交易密码与登录密码一致
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[resetTradePwd] info={},ERROR={}",
						"验证新交易密码与登录密码是否一致时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10029"; // 验证新交易密码与登录密码是否一致时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			// 7.重新设置交易密码
			try {
				memberPasswordFacade.resetTradePassword(
						memberDto.getMemberNo(), newTradePwd);
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
				userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//				String errorCode = "00000"; // 请求成功
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			} catch (Exception e) {
				logger.error("[resetTradePwd] info={},ERROR={}",
						"重置交易密码时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_RESET_TRADEPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10031"; // 重置交易密码时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			
			// 说明未登录或其它错误
		}else {
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}
	
	@Override
	public UserOpeDTO modifyLoginPwd(String loginName, String srcNo,
			String loginPwd, String newLoginPwd, String newLoginPwdConfirm,String userSessionKey) {
		UserOpeDTO userOpeDto = null;
		MemberDto memberDto = null;
		// 统一配置取错误码
		ConfigParam<Map<String, String>> config = ConfigurationUtils
				.getConfigParam("config_type_text_resources",
						"lmapp_error_code");
		// 1.请求参数不能为空
		if (CheckUtils.isEmpty(loginPwd) || CheckUtils.isEmpty(newLoginPwd) || CheckUtils.isEmpty(newLoginPwdConfirm)) {
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);	
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10024"; // 请求不合理，有请求参数为空
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		// 2.验证登录是否失效
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		// 说明已登录
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			memberDto = loginResultDTO.getMemberDto();
			// 3.验证两次登录密码是否相同
			if (!newLoginPwd.equals(newLoginPwdConfirm)) {
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_CONFIRM_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10034"; // 两次新登录密码不相同
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			try {
				// 检查原登录密码是否正确
				MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
						.checkLoginPassword(memberDto.getMemberNo(), loginPwd);
				if (!memberPwdConstraintDto.getResultFlag()) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10004"; // 登录密码不正确
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[modifyLoginPwd] info={},ERROR={}",
						"验证原登录密码时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_PER_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10032"; // 验证原登录密码时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			try {
				// 检查新登录密码与交易密码是否一致
				MemberPasswordValidationResultDto result = lanmaoDemandFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),
								newLoginPwd,
								PasswordValidationTypeEnum.LOGIN_COMPARE_TRAD);
				if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10008"; // 登录密码与交易密码相同
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[modifyLoginPwd] info={},ERROR={}",
						"验证交易密码与登录密码是否一致时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_TRADEPWD_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10029"; //验证交易密码与登录密码是否一致时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			try {
				// 修改登录密码
				memberPasswordFacade.resetLoginPassword(
						memberDto.getMemberNo(), newLoginPwd);
			} catch (Exception e) {
				userOpeDto = new UserOpeDTO();
				logger.error("[modifyLoginPwd] info={},ERROR={}",
						"修改登录密码时异常", e.getMessage());
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MODIFY_LOGINPWD_EXCEPTION);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10035"; //修改登录密码时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//			String errorCode = "00000"; //请求成功
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
		}else {
			// 渠道无效或未知异常
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}
	
	@Override
	public UserOpeDTO resetLoginPwd(String loginName, String srcNo,
			String loginPwd, String loginRePwd, String verifyCode) {
		
		UserOpeDTO userOpeDto = null;
		MemberDto memberDto = null;
		// 1.请求参数不能为空
		if (CheckUtils.isEmpty(loginPwd)|| CheckUtils.isEmpty(loginRePwd)|| CheckUtils.isEmpty(verifyCode)) {
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_PARAM_NULL_ERROR);	
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10024"; // 请求不合理，有请求参数为空
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		if(verifyCode.length()!=6){
			userOpeDto = new UserOpeDTO();
			logger.info("[register] ERROR={}","短信验证码不为6位");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10013";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_SHORT);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		// 2.验证渠道号是否合法
		if (null != channelOpeBizImpl.getChannelInfo(srcNo)) {
			try {
				memberDto = memberManagementFacade
						.queryByBindMobileNo(loginName);
				if (memberDto == null) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MEMBER_NOT_EXIST_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10003"; // 该会员不存在
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				//3.验证两次登录密码是否相同
				if (!loginPwd.equals(loginRePwd)) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_CONFIRM_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10034"; // 两次登录密码不同
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				// 4.验证手机验证码
				if (CheckUtils.isEmpty(verifyCode)
						|| GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(
								DynamicPwdTypeEnum.RLOGINPWD,
								memberDto.getBindMobileNo(), verifyCode)) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10013"; // 短信验证码错误
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				// 5.验证新登录密码是否和交易密码一致
				MemberPasswordValidationResultDto result = lanmaoDemandFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),
								loginPwd,
								PasswordValidationTypeEnum.LOGIN_COMPARE_TRAD);
				if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_TRADEPWD_EQUALS_LOGINPWD_ERROR);	
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10008"; // 登录密码与交易密码一致
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				// 重置登录密码
				memberPasswordFacade.resetLoginPassword(
						memberDto.getMemberNo(), loginPwd);
				userOpeDto = new UserOpeDTO();
				userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	
//				String errorCode = "00000"; // 请求成功
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
			} catch (Exception e) {
				logger.error("[resetLoginPwd] info={},ERROR={}",
						"重置登录密码时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_RESET_LOGINPWD_ERROR);	
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10036"; // 重置登录密码时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
		} else {
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);	

			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10001"; // 渠道请求不合法
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		return userOpeDto;
	}
	
	@Override
	public UserOpeDTO unBindAccount(String loginName, String srcNo,String userSessionKey) {
		UserOpeDTO userOpeDto = null;
		// 1.验证登录是否失效
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		// 说明已登录
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			userOpeDto=new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	

			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//			String errorCode = "00000"; // 请求成功
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			SmartCacheUtils.remove(userSessionKey);
		}else {
			// 渠道无效或未知异常
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}
	@Override
	public UserOpeDTO addUserSuggestion(String loginName, String srcNo,
			String userSessionKey, String suggestion, String reservedNo,byte[] suggestionImg) {
		UserOpeDTO userOpeDto=null;
		MemberDto memberDto=null;
		ActivityAppSuggestionDTO activityAppSuggestionDto=null;
		if (CheckUtils.isEmpty(suggestion)) {
			userOpeDto=new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_USERSUGGESTION_NULL_ERROR);	

			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10040"; // 反馈意见为空，请正确输入反馈意见
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		// 2.验证登录是否失效
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		// 说明已登录
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			userOpeDto=new UserOpeDTO();
			memberDto=loginResultDTO.getMemberDto();
			try {
				activityAppSuggestionDto=new ActivityAppSuggestionDTO();
				activityAppSuggestionDto.setCreateTime(new Date());
				activityAppSuggestionDto.setMemberNo(memberDto.getMemberNo());
				activityAppSuggestionDto.setReservedNo(reservedNo);
				activityAppSuggestionDto.setSuggestion(suggestion);
				activityAppSuggestionDto.setSuggestionImg(suggestionImg);
				activityAppSuggestionFacade.addUserSuggerstion(activityAppSuggestionDto);
			} catch (Exception e) {
				logger.error("[addUserSuggestion] info={},ERROR={}",
						"添加用户反馈时异常", e.getMessage());
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_ADD_USERSUGGESTION_ERROR);	

				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10039"; // 添加用户反馈时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	

			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//			String errorCode = "00000"; // 请求成功
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
		}else {
			// 渠道无效或未知异常
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}

	@Override
	public UserOpeDTO isAuthenticated(String loginName, String srcNo,
			String userSessionKey) {
		UserOpeDTO userOpeDto=null;
		MemberDto memberDto=null;
		BankCardInfoDto bankCardInfoDto=null;
		// 2.验证登录是否失效
		LoginResultDTO loginResultDTO = userBizImpl.obtainLogin(loginName,
				srcNo,userSessionKey);
		// 说明已登录
		if (LoginResultEnum.SUCCESS.equals(loginResultDTO.getResultMsg())) {
			memberDto=loginResultDTO.getMemberDto();
			//验证用户是否绑卡 即是否实名认证过
			try{
				bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
				if (null == bankCardInfoDto) {
					userOpeDto = new UserOpeDTO();
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_USER_NO_BANKCARD_ERROR);	

					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10041"; // 用户尚未绑卡
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
			} catch (Exception e) {
				logger.error("[resetTradePwd] info={},ERROR={}",
						"验证用户是否绑卡时异常", e.getMessage());
				userOpeDto = new UserOpeDTO();
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_VERIFY_ISBINDCARD_ERROR);	

				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10042"; // 查询用户是否绑卡时异常
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			userOpeDto = new UserOpeDTO();
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);	

			userOpeDto.setRealName(bankCardInfoDto.getBankUserName());
			userOpeDto.setIdentityNo(bankCardInfoDto.getIdentityNo());
			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//			String errorCode = "00000"; // 请求成功
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
		}else {
			// 渠道无效或未知异常
			return loginResultDTO.getUserOpeDTO();
		}
		return userOpeDto;
	}

}