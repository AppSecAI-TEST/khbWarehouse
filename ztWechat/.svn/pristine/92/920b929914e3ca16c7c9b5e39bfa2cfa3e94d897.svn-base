/**
 * 
 */
package com.yeepay.g3.app.lmweChat.biz.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yeepay.g3.app.async.LMAsyncSendUtil;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.enums.UserLoginTypeEnum;
import com.yeepay.g3.app.lmweChat.biz.ChannelOpeBiz;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.facade.activity.async.event.AppMemberOperecord;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.ChannelDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberMobileRegParamDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPwdConstraintDto;
import com.yeepay.g3.facade.lmportal.dto.MemberRegResultDto;
import com.yeepay.g3.facade.lmportal.enumtype.DeviceEnum;
import com.yeepay.g3.facade.lmportal.enumtype.DynamicPwdTypeEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRegModelEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberStatusEnum;
import com.yeepay.g3.facade.lmportal.service.ChannelFacade;
import com.yeepay.g3.facade.lmportal.service.LMUtilFacde;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoTransactionFacade;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.facade.lmportal.service.MemberPasswordFacade;
import com.yeepay.g3.utils.common.encrypt.Digest;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;

/**
 * @Description 会员操作逻辑处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年7月28日 下午5:16:11
 * @version 1.0
 */
@Component
public class UserBizImpl implements UserBiz {
	
	protected MemberPasswordFacade memberPasswordFacade = RemoteServiceFactory
			.getService(MemberPasswordFacade.class);
	protected ChannelFacade channelFacade = RemoteServiceFactory.getService(ChannelFacade.class);
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory
			.getService(MemberManagementFacade.class);
	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory
			.getService(TrustQueryFacade.class);
	protected LMUtilFacde lMUtilFacde = RemoteServiceFactory.getService(LMUtilFacde.class);
	protected LanmaoDemandFacade lanmaoDemandFacade = RemoteServiceFactory.getService(LanmaoDemandFacade.class);
	
	protected LanmaoTransactionFacade lanmaoTransactionFacade = RemoteServiceFactory.getService(LanmaoTransactionFacade.class);
	@Autowired
	private ChannelOpeBiz channelOpeBizImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

	@Override
	public UserOpeDTO login(final String loginName, String pwd,
			final String srcNo, final String deviceType,
			final String macAddress, final String ip, final String userAgent,
			final String imei) {
		
		UserOpeDTO userOpeDto = null;
		
		//先判断渠道是否合法，渠道信息存入redis，每日更新一次
		ChannelDto channelDto = null;
		if (StringUtils.isNotEmpty(srcNo)) {
			userOpeDto = new UserOpeDTO();
			channelDto = channelOpeBizImpl.getChannelInfo(srcNo);
			if(channelDto == null){
				logger.info("[login] ERROR={}","渠道号不存在或已过期");
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);	
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
//				String errorCode = "10001";
				return userOpeDto;
			}
			if(channelDto != null) {
				//当前会员存入redis
				//由srcNo+loginName+sign计算signStr作为存入redis的member信息，类似于session
				String userSessionKey = Digest.md5Digest(srcNo + loginName + channelDto.getSign());
				MemberDto oldMember = (MemberDto) SmartCacheUtils.get(userSessionKey);
				if(oldMember != null){
					SmartCacheUtils.remove(userSessionKey);
				}
				if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(pwd)) {
					logger.info("[login] ERROR={}", "用户名或密码为空");
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10002"; //用户名或密码为空
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NAMEORPWD_NULL_ERROR);	
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				//4.验证登录
				final MemberDto memberDto = memberManagementFacade.queryByLoginName(loginName);
				if(memberDto == null){
					logger.info("[login] ERROR={}","该会员不存在");
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10003"; //该会员不存在
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MEMBER_NOT_EXIST_ERROR);	
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				logger.debug("[login] memberDto={}", memberDto);
				MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade.checkLoginPassword(memberDto.getMemberNo(), pwd);
				logger.debug("[login] memberPwdConstraintDto={}",memberPwdConstraintDto);
				if(!memberPwdConstraintDto.getResultFlag()){
					logger.info("[login] ERROR={}","登录密码不正确");
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10004"; //登录密码不正确
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_LOGINPWD_ERROR);	
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					return userOpeDto;
				}
				MemberDto member = memberManagementFacade.obtainMember(memberDto.getMemberNo());
				SmartCacheUtils.set(userSessionKey, member, LmConstants.REDIS_EXPIRE_TIME); //设置有效期
				userOpeDto.setMemberDto(member);
				userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				
				userOpeDto.setMemberNo(member != null ? member.getMemberNo() : "");
				userOpeDto.setMemberTel(member != null ? member.getBindMobileNo() : "");
				userOpeDto.setUserSessionKey(userSessionKey);
				//TODO
				sendAsyncLoginMsg(loginName, srcNo, deviceType, macAddress, ip, userAgent, imei, memberDto.getMemberNo());
			}
		}
		return userOpeDto;
	}
	
	/**
	 * 发送登录消息到mq
	 * @author ping.zhu
     * @CreateTime 2016年9月21日 下午2:17:41
	 */
	private void sendAsyncLoginMsg(final String loginName, final String srcNo,
			final String deviceType, final String macAddress, final String ip,
			final String userAgent, final String imei,final String memberNo) {
		try {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					AppMemberOperecord event = new AppMemberOperecord();
					event.setClientIp(ip);
					event.setImei(imei);
					event.setMacAddress(macAddress);
					event.setMemberNo(memberNo);
					event.setMemberTel(loginName);
					event.setOperateTime(new Date());
					event.setSrcNo(srcNo);
					if(StringUtils.isEmpty(deviceType))
						event.setLoginType(UserLoginTypeEnum.APP.toString());
					else if (UserLoginTypeEnum.ANDROID.toString().equals(
							deviceType))
						event.setLoginType(UserLoginTypeEnum.ANDROID.toString());
					else if (UserLoginTypeEnum.IOS.toString()
							.equals(deviceType))
						event.setLoginType(UserLoginTypeEnum.IOS.toString());
					event.setUserAgent(userAgent);
					LMAsyncSendUtil.sendAsyncMsg(event, LMAsyncSendUtil.EXCHANGE_APP_MEMBER_OPERECORD);
				}
			});
			thread.run();
		} catch (Throwable e) {
			logger.error("[sendAsyncLoginMsg] error, cause=" + e.getMessage(), e);
		}
	}

	@Override
	public LoginResultDTO obtainLogin(String loginName, String srcNo,String userSessionKey) {
		ChannelDto channelDto = null;
		LoginResultDTO loginResultDto=new LoginResultDTO();
		MemberDto memberDto=null;
		UserOpeDTO userOpeDto = new UserOpeDTO();
		if (StringUtils.isNotEmpty(srcNo)&&StringUtils.isNotEmpty(loginName)&&StringUtils.isNotEmpty(userSessionKey)) {
			channelDto = channelOpeBizImpl.getChannelInfo(srcNo);
			if (null != channelDto) {
				// 当前会员存入redis
				// 由srcNo+loginName+sign计算signStr作为存入redis的member信息，类似于session
				String newUserSessionKey = Digest.md5Digest(srcNo + loginName
						+ channelDto.getSign());
				logger.debug("[obtainLogin] newUserSessionKey={}",newUserSessionKey);
				//身份验证通过
				if(null!=userSessionKey&&newUserSessionKey.equals(userSessionKey)){
					memberDto = (MemberDto) SmartCacheUtils
							.get(newUserSessionKey);
				}else{
					logger.info("[obtainLogin] info={},loginName={},srcNo={},userSessionKry={}","userSessionKey不合法",loginName,srcNo,userSessionKey);
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10037"; // userSessionKey不合法
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_USERSESSIONEY_ERROR);
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					loginResultDto.setResultMsg(LoginResultEnum.SESSION_ERROR);
					loginResultDto.setUserOpeDTO(userOpeDto);
					return loginResultDto;
				}
				// 已登录
				if (null != memberDto) {
					loginResultDto.setMemberDto(memberDto);
					loginResultDto.setResultMsg(LoginResultEnum.SUCCESS);
					loginResultDto.setUserSessionKey(userSessionKey);
					return loginResultDto;
				}else {
					// 未登录
					logger.info("[obtainLogin] info={},loginName={},srcNo={},userSessionKey={}","用户未登录或已失效",loginName,srcNo,userSessionKey);
					userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//					String errorCode = "10020"; // 用户未登录或已失效
					String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_LOGIN_INVALID_ERROR);
					userOpeDto.setErrorCode(error[0]);
					userOpeDto.setErrorMsg(error[1]);
					loginResultDto.setResultMsg(LoginResultEnum.FAILED);
					loginResultDto.setUserOpeDTO(userOpeDto);
					return loginResultDto;
				}
				//渠道号过期
			} else {
				logger.info("[obtainLogin] info={},loginName={},srcNo={},userSessionKey={}","渠道号过期",loginName,srcNo,userSessionKey);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10001"; // 渠道请求不合法
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				loginResultDto.setResultMsg(LoginResultEnum.SRC_NO_EXPRIED);
				loginResultDto.setUserOpeDTO(userOpeDto);
				return loginResultDto;
			}
		}
		logger.info("[obtainLogin] info={},loginName={},srcNo={},userSessionKey={}","用户未登录或已失效",loginName,srcNo,userSessionKey);
		userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//		String errorCode = "10020"; // 用户未登录或已失效
		String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_LOGIN_INVALID_ERROR);
		userOpeDto.setErrorCode(error[0]);
		userOpeDto.setErrorMsg(error[1]);
		loginResultDto.setResultMsg(LoginResultEnum.FAILED);
		loginResultDto.setUserOpeDTO(userOpeDto);
		return loginResultDto;
	}
	@Override
	public UserOpeDTO mobileIsRegister(String mobileNo) {
		logger.info("[mobileIsRegister] info={},mobileNo={}","开始验证手机号是否注册...",mobileNo);
		UserOpeDTO userOpeDto = null;
		if(StringUtils.isNotEmpty(mobileNo)){
			userOpeDto = new UserOpeDTO();
			MemberRegResultDto memberRegResultDto = lanmaoDemandFacade
					.queryMemberByMobile(mobileNo);
			if (memberRegResultDto != null) {
				logger.info("[mobileIsRegister] ERROR={},mobileNo={}", "该手机号已经在平台注册",mobileNo);
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10006";
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MOBILENO_EXIST_ERROR);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
			userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
		}
		return userOpeDto;
	}

	@Override
	public UserOpeDTO register(String srcNo, String loginName, String loginPwd,
			String msgCode,String deviceType,String macAddress,String ip,String userAgent,String imei) {
		logger.info("[register] srcNo={},loginName={},msgCode={},deviceType={},macAddress={},ip={},userAgent={},imei={}",srcNo,loginName,msgCode,deviceType,macAddress,ip,userAgent,imei);
		UserOpeDTO userOpeDto = new UserOpeDTO();
		//1.验证渠道号
		if(StringUtils.isEmpty(srcNo)){
			logger.info("[register] ERROR={}","渠道号为空");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10001";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		ChannelDto channelDto = channelOpeBizImpl.getChannelInfo(srcNo);
		if(channelDto == null){
			logger.info("[register] ERROR={}","渠道号不存在或已过期");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10001";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_SRC_REQUEST_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		logger.info("[register] channelDto={}",channelDto);
		if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPwd)){
			logger.info("[register] ERROR={}","登录名或密码为空");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10002";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NAMEORPWD_NULL_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		if(StringUtils.isEmpty(msgCode)){
			logger.info("[register] ERROR={}","短信验证码为空");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "10013";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		if(msgCode.length()!=6){
			logger.info("[register] ERROR={}","短信验证码不为6位");
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_SHORT);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		// 2.验证手机号是否已经注册过
		userOpeDto = mobileIsRegister(loginName);
		logger.info("[register] info={},userOpeDto={},loginName={}","验证手机号是否注册结果",userOpeDto,loginName);
		if(userOpeDto.getResultCode().equals(ResultCodeEnum.FAILED)){
			return userOpeDto;
		}
		// 3.验证手机验证码
		try {
			if (GetParamUtils.getIsVerifyCode()
					&& !lMUtilFacde.verfyDynamicPwd(
							DynamicPwdTypeEnum.REGISTER, loginName, msgCode)) {
				userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//				String errorCode = "10013";
				String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_MSGCODE_ERROR);
				userOpeDto.setErrorCode(error[0]);
				userOpeDto.setErrorMsg(error[1]);
				return userOpeDto;
			}
		} catch (Exception e) {
			logger.error("[register] info={},ERROR={}", "验证手机验证码异常",e);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
//			String errorCode = "11111";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		// 4.封装json串
		Object obj = JSONObjectUtils.userAddrToJSONStr(ip, macAddress, userAgent, imei);
		// 5.注册
		MemberMobileRegParamDto memberMobileRegParamDto = new MemberMobileRegParamDto();
		memberMobileRegParamDto.setBindMobileNo(loginName);
		memberMobileRegParamDto.setPassword(loginPwd);
		memberMobileRegParamDto.setRegModel(MemberRegModelEnum.COMMON);
		memberMobileRegParamDto.setSourceNo(srcNo);
		memberMobileRegParamDto.setDevice(DeviceEnum.valueOf(deviceType));
		if(obj != null){
			memberMobileRegParamDto.setIp(obj.toString());
		}
		MemberRegResultDto memberRegResultDto = lanmaoTransactionFacade
				.regMemberByMoblie(memberMobileRegParamDto);
		if (memberRegResultDto.getStatus().equals(MemberStatusEnum.FAIL)) {
			logger.info("[register] error={},memberMobileRegParamDto={},memberRegResultDto={}","注册失败",memberRegResultDto,memberMobileRegParamDto);
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			//注册失败
//			String errorCode = "10019";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REGISTER_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		MemberDto memberDto = memberManagementFacade
				.obtainMember(memberRegResultDto.getMemberNo());
		logger.info("[register] memberDto={}",memberDto);
		if (memberDto == null) {
			userOpeDto.setResultCode(ResultCodeEnum.FAILED);
			//未知错误
//			String errorCode = "11111";
			String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_NOT_KNOWN_ERROR);
			userOpeDto.setErrorCode(error[0]);
			userOpeDto.setErrorMsg(error[1]);
			return userOpeDto;
		}
		//将userSessionKey存入redis
		String userSessionKey = Digest.md5Digest(srcNo + loginName + channelDto.getSign());
		SmartCacheUtils.set(userSessionKey, memberDto, LmConstants.REDIS_EXPIRE_TIME);
		userOpeDto.setResultCode(ResultCodeEnum.SUCCESS);
//		String errorCode = "00000";
		String[] error = GetParamUtils.readErrorCodeConfig(ErrorCodeTypeEnum.LMAPP_REQUEST_SUCCESS);
		userOpeDto.setErrorCode(error[0]);
		userOpeDto.setErrorMsg(error[1]);
		userOpeDto.setMemberDto(memberDto);
		userOpeDto.setMemberNo(memberDto.getMemberNo());
		userOpeDto.setMemberTel(memberDto.getBindMobileNo());
		userOpeDto.setUserSessionKey(userSessionKey);
		return userOpeDto;
	}
	
}
