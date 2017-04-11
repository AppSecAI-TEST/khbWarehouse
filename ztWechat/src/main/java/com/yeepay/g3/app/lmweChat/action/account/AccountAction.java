package com.yeepay.g3.app.lmweChat.action.account;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lanmao.consultant.facade.service.UserFacade;
import com.yeepay.g3.app.dto.UserOpeDTO;
import com.yeepay.g3.app.enums.ErrorCodeTypeEnum;
import com.yeepay.g3.app.enums.ResultCodeEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.entity.ReturnUrlParamEntity;
import com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.HttpRequestUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.app.lmweChat.utils.SecretUtils;
import com.yeepay.g3.app.lmweChat.utils.StringProcessorUtils;
import com.yeepay.g3.facade.activity.dto.ActivityShareRecordsDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum;
import com.yeepay.g3.facade.activity.facade.ActivityShareRecordsFacade;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderParamDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultPageDto;
import com.yeepay.g3.facade.lmlc.trust.enumtype.TradeOrderStatusEnum;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.ChannelDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberMobileRegParamDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPasswordValidationResultDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPwdConstraintDto;
import com.yeepay.g3.facade.lmportal.dto.MemberRegResultDto;
import com.yeepay.g3.facade.lmportal.dto.MemberRelevanceDto;
import com.yeepay.g3.facade.lmportal.enumtype.DeviceEnum;
import com.yeepay.g3.facade.lmportal.enumtype.DynamicPwdTypeEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRegModelEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRelevanceStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordValidationTypeEnum;
import com.yeepay.g3.facade.lmportal.enumtype.RecommendedBizTypeEnum;
import com.yeepay.g3.facade.lmportal.service.ChannelFacade;
import com.yeepay.g3.facade.lmportal.service.LMUtilFacde;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoTransactionFacade;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.facade.lmportal.service.MemberPasswordFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.IpUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * @Title: 个人信息
 * @Description: 登录，注册以及个人信息管理
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-4-14 上午10:13:46
 * @version 2016-4-14
 */
@Controller
public class AccountAction extends BaseAction {

	protected MemberPasswordFacade memberPasswordFacade = RemoteServiceFactory
			.getService(MemberPasswordFacade.class);
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory
			.getService(MemberManagementFacade.class);
	protected LMUtilFacde lMUtilFacde = RemoteServiceFactory
			.getService(LMUtilFacde.class);
	protected LanmaoTransactionFacade lanmaoTransactionFacade = RemoteServiceFactory
			.getService(LanmaoTransactionFacade.class);
	protected LanmaoDemandFacade lanmaoDemandFacade = RemoteServiceFactory
			.getService(LanmaoDemandFacade.class);
	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory
			.getService(TrustQueryFacade.class);
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);
	protected ActivityShareRecordsFacade activityShareRecordsFacade = RemoteServiceFactory.getService(ActivityShareRecordsFacade.class);
	protected ChannelFacade channelFacade = RemoteServiceFactory.getService(ChannelFacade.class);
	protected UserFacade userFacade = RemoteServiceFactory.getService(UserFacade.class);
	protected ActivityWXSendMessageFacade activityWXSendMessageFacade = RemoteServiceFactory
			.getService(ActivityWXSendMessageFacade.class);
//	protected ActivityWXSendMessageFacade activityWXSendMessageFacade = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityWXSendMessageFacade.class);
	@Autowired
	protected RequestParamBuilderService requestParamBuilderService;

	private static final Logger logger = LoggerFactory
			.getLogger(AccountAction.class);

	
	
	/**
	 * 
	 * 跳转登录页面
	 * 
	 */
	public String toLogin(@Param("code") String code,@Param("returnUrl") String interceptUrl,@Param("returnFlag") String returnFlag,@Param("productId") Integer productId,@Param("returnProject") String returnProject) {
		HttpServletResponse  response = getMvcFacade().getResponse();
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		// 1.登录需要获取openId，用来增加关联
		if(!StringUtils.isEmpty(code)){
			String openId = null;
			String result = HttpRequestUtils.sendHttpRequest(WX_OAUTH_REUQEST_URL,WX_OAUTH_REUQEST_METHOD,requestParamBuilderService.buildGetWebAccessTokenParam(code));
			if(!StringUtils.isEmpty(result)){ 
				JSONObject resultJson = JSONObject.fromObject(result); 
				if(resultJson.get("openid") != null){ 
					openId = resultJson.get("openid").toString(); 
					Cookie cookie = new Cookie(MLANMAO_OPEN_ID, openId);
					cookie.setMaxAge(-1); 
					response.addCookie(cookie);
				} 
			} 
			session.setAttribute("openId", openId);
		}
		 
		logger.debug("[toLogin] openId={}", session.getAttribute("openId"));
		//2.是否有返回url
		if(returnFlag != null && !returnFlag.equals("")){
			Map<String,Object> map = (Map<String, Object>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmwx_return_url_config").getValue();
			String url = (String) map.get(returnFlag);
			String returnUrl = url+"?productId="+productId;
			logger.info("[toLogin] returnUrl={}",returnUrl);
			addModelObject("returnUrl",returnUrl);
		}
		//判断是否是其他工程过来的登陆请求
		String projectAction = null;
		if(null != returnProject) {
			//获取登陆成功后的跳转action
			Map<String,Object> map = (Map<String, Object>) ConfigurationUtils.getConfigParam("config_type_text_resources", "LM_loginSuccessByProject").getValue();
			if(null != map && map.get(returnProject) != null) {
				projectAction = map.get(returnProject).toString();
				addModelObject("projectAction",projectAction);
			}
			try {
				interceptUrl = URLEncoder.encode(interceptUrl, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("[toLogin] info={},ERROR={}","加密returnUrl时异常",e);
//				e.printStackTrace();
			}
			addModelObject("interceptUrl",interceptUrl);
		} else {
			addModelObject("interceptUrl",interceptUrl);
		}
		//3.获取渠道号
		String type = null;
		String source = null;
		try{
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.debug("[toLogin] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
				}
			}
			logger.info("[toLogin] srcNo={}", source);
			session.setAttribute("srcNo", source);
		}catch(Exception e){
			logger.info("[toLogin] info={},ERROR={}","获取渠道号失败",e.getMessage());
		}
		addModelObject("source",source);
		return SUCCESS;
	}
	/**
	 * 未登录返回json
	 */
	public String toLoginJson(){
		setJsonModel("\"noLogin\"");
		return "json";
	}
	/**
	 * 跳转个人中心
	 */
	public String toAccount() {
		try {
			HttpServletRequest request = getMvcFacade().getRequest();
			// 待加判断是否绑卡接口--待
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			HttpSession session = getMvcFacade().getHttpSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			if(memberDto != null) {
				BankCardInfoDto bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto
						.getMemberNo());
				if (bankCardInfoDto != null) {
					addModelObject("tradePwd", "********");
					addModelObject("credentialsNo",StringProcessorUtils.desensitizedIdNumber(bankCardInfoDto.getIdentityNo()));
					addModelObject("realName",bankCardInfoDto.getBankUserName());
					addModelObject("bindMobileNo",StringProcessorUtils.desensitizedMobileNo(bankCardInfoDto.getBindMobile()));
					addModelObject("bindCard", true);
				} else {
					if(memberDto.getCredentialsNo()!=null){//老系统用户注册时，有交易密码和身份证；
						addModelObject("tradePwd", "********");
						addModelObject("credentialsNo",StringProcessorUtils.desensitizedIdNumber(memberDto.getCredentialsNo()));
						addModelObject("realName",memberDto.getRealName());
					}else{
						addModelObject("tradePwd", "暂未设置交易密码");
						addModelObject("credentialsNo", "暂未设置身份证号码");
					}
					addModelObject("bindCard", false);
				}
				addModelObject("loginName", StringProcessorUtils.desensitizedMobileNo(memberDto.getLoginName()));
//				addModelObject("bindMobileNo",StringProcessorUtils.desensitizedMobileNo(memberDto.getBindMobileNo()));
				addModelObject("createDate",dateFormat.format(memberDto.getCreateDate()));
			}
			// 获取cookie中渠道号
			String type = null;
			String source = null;

			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toAccount] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
				}
			}
			addModelObject("source", source);
		} catch(Exception e) {
			logger.error("[toAccount] info={},error={}","跳转个人中心时异常",e);
//			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	/*public static void main(String[] args) {
		Object member = null;
		MemberDto memberDto = (MemberDto) member;
		System.out.println(memberDto);
		Object str = null;
		String s = (String)str;
		System.out.println(s == null);
		System.out.println(("null").equals(s));
		String sq = "null" ;
		System.out.println(sq);
	}*/

	/**
	 * 跳转验证手机号
	 */
	public String toVerifyMobile() {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		addModelObject("mobileNo",
				StringProcessorUtils.desensitizedMobileNo(memberDto
						.getBindMobileNo()));
		return SUCCESS;
	}

	/**
	 * 跳转修改手机号
	 */
	public String toModifyMobile() {
		return SUCCESS;
	}

	/**
	 * 跳转修改登录密码
	 */
	public String toModifyLoginPwd() {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		addModelObject("mobileNo",
				StringProcessorUtils.desensitizedMobileNo(memberDto
						.getBindMobileNo()));
		return SUCCESS;
	}

	/**
	 * 跳转修改交易密码
	 */
	public String toModifyTradePwd() {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		addModelObject("mobileNo",
				StringProcessorUtils.desensitizedMobileNo(memberDto
						.getBindMobileNo()));
		return SUCCESS;
	}
	
	/**
	 * 跳转更新用户交易密码或者重置密码
	 */
	public String toModifyOrResetTradePwd() {
		
		return SUCCESS;
	}

	/**
	 * 跳转找回交易密码
	 */
	public String toResetTradePwd(@Param("ret") String ret) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		addModelObject("mobileNo",
				StringProcessorUtils.desensitizedMobileNo(memberDto
						.getBindMobileNo()));
		addModelObject("RET", ret);
		return SUCCESS;
	}
	/**
	 * 验证图片验证码
	 */
	public String verPicCode(@Param("errorCount") Integer errorCount,@Param("verifyCode") String verifyCode){
		HttpSession session = getMvcFacade().getHttpSession();
		logger.debug("[verPicCode] errorCount={},verifyCode={}",errorCount,verifyCode);
		// 有验证码，验证输入是否正确
		if (errorCount != null && errorCount > 0) {
			logger.info("[verPicCode] errorCount={},verifyCode={},session验证码={}",errorCount,verifyCode,session.getAttribute("REMOTE_KAPTCHA_SESSION_KEY"));
			if (StringUtils.isEmpty(verifyCode)|| !verifyCode.equals(session.getAttribute("REMOTE_KAPTCHA_SESSION_KEY"))) {
				session.removeAttribute("REMOTE_KAPTCHA_SESSION_KEY");
				logger.error("[verPicCode] ERROR={}", "验证码验证失败");
				setJsonModel(VERIFY_FAILED);
				return "json";
			}
			setJsonModel("SUCCESS");
		}
		return "json";
	}
	/**
	 * 
	 * 登录并绑定公众号
	 * 
	 */
	public String login(@Param("loginName") String loginName,
			@Param("pwd") String pwd, 
			@Param("errorCount") Integer errorCount,
			@Param("verifyCode") String verifyCode) {

		// 1.获取session和request 不能设置为全局
		HttpSession session = getMvcFacade().getHttpSession();
//		HttpServletResponse response = getMvcFacade().getResponse();
		String aesMemberNo = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("[login] loginName={},errorCount={},verifyCode={}",
				loginName, errorCount, verifyCode);
		MemberDto oldMember = (MemberDto)session.getAttribute("member");
		if(oldMember != null){
			session.removeAttribute("member");
		}
		// 2.获取openId
		String openId = (String) session.getAttribute("openId");
		logger.info("[login] openId={},loginName={},errorCount={},verifyCode={}", openId, loginName, errorCount, verifyCode);
		logger.debug("[login] 验证码={}",session.getAttribute("REMOTE_KAPTCHA_SESSION_KEY"));
		try {
			// 3.有验证码，验证输入是否正确
			if (errorCount != null && errorCount > 0) {
				if (StringUtils.isEmpty(verifyCode)
						|| !verifyCode.equals(session
								.getAttribute("REMOTE_KAPTCHA_SESSION_KEY"))) {
					session.removeAttribute("REMOTE_KAPTCHA_SESSION_KEY");
					logger.info("[login] ERROR={}", "验证码输入错误或为空");
					map.put("code", "VERIFY_FAILED");
					setJsonModel(map);
					return "json";
				}
				session.removeAttribute("REMOTE_KAPTCHA_SESSION_KEY");
			}
			if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(pwd)) {
				logger.info("[login] ERROR={}", "用户名或密码为空");
				map.put("code", "FAILED");
				setJsonModel(map);
				return "json";
			}
			//4.验证登录
			MemberDto memberDto = memberManagementFacade.queryByLoginName(loginName);
			if(memberDto == null || !(memberDto.getStatus().equals(MemberStatusEnum.ACTIVATED) || memberDto.getStatus().equals(MemberStatusEnum.AVAILABLY))){
				logger.info("[login] ERROR={},memberDto={}","该会员不存在",memberDto);
				map.put("code", "USER_NOTEXIST");
				setJsonModel(map);
				return "json";
			}
			MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade.checkLoginPassword(memberDto.getMemberNo(), pwd);
			logger.debug("[login] memberPwdConstraintDto={}",memberPwdConstraintDto);
			if(!memberPwdConstraintDto.getResultFlag()){
				logger.info("[login] ERROR={},memberDto={},memberPwdConstraintDto={}","登录密码不正确",memberDto,memberPwdConstraintDto);
				map.put("code", "WRONG_PWD");
				setJsonModel(map);
				return "json";
			}
			MemberDto member = memberManagementFacade.obtainMember(memberDto.getMemberNo());
			session.setAttribute("member", member);
			//作为推荐人的用户编号
			session.setAttribute("recommendMemberNo", member.getMemberNo());
			//5.查询是否是新手
			TrustOrderParamDto trustOrderParamDto = new TrustOrderParamDto();
			trustOrderParamDto.setMemberNo(member.getMemberNo());
			trustOrderParamDto.setStatus(TradeOrderStatusEnum.SUCCESS);
			TrustOrderResultPageDto trustOrderResultPageDTO = trustQueryFacade.queryTrustOrder(trustOrderParamDto);
			List<TrustOrderResultDto> list = trustOrderResultPageDTO.getTrustOrderResult();
			if(list.size() == 0){
				session.setAttribute("newcomerFlag", "YES");
			}else{
				session.setAttribute("newcomerFlag", "NO");
			}
			String newcomerFlag = (String) session.getAttribute("newcomerFlag");
			if(newcomerFlag == null || newcomerFlag == ""){
				session.removeAttribute("newcomerFlag");
			}
			try{
				// openId不为空，则绑定公众号
				if (!StringUtils.isEmpty(openId)) {
					// 根据openId查询绑定表，获得openId与会员编号关联实体
					MemberRelevanceDto memberRelevanceDto = memberManagementFacade
							.obtainMemberRelevance(openId);
					logger.info("[login] memberRelevanceDto={}",memberRelevanceDto);
					// 如果实体为空，插入一条新的关联，如果实体不为空，并且关联表里关联的会员id与session中存储的不一致，或者关联实体的状态不为“on”,则更新关联
					if (memberRelevanceDto == null) {
						// 开关状态是否会默认填为“ON”
						memberManagementFacade
								.addMemberRelevance(new MemberRelevanceDto(openId,
										memberDto.getMemberNo()));
						//更新用户最后登录时间
						memberManagementFacade.updateMemberRelevanceLogDate(openId);
					} else if (!memberRelevanceDto.getMemberNo().equals(memberDto.getMemberNo()) || !(MemberRelevanceStatusEnum.ON).equals(memberRelevanceDto.getStatus())) {
						memberManagementFacade.updateMemberRelevance(memberDto.getMemberNo(), openId,MemberRelevanceStatusEnum.ON);
						//更新用户最后登录时间
						memberManagementFacade.updateMemberRelevanceLogDate(openId);
					}
				}
			}catch(Exception e){
				logger.error("[login] info={},openId={},ERROR={}","openid关联操作失败",openId,e);
			}
			aesMemberNo = SecretUtils.secretData(member.getMemberNo());
			
		} catch (Exception e) {
			// 打印日志，输出错误信息
			logger.error("[login] info={},loginName={},ERROR={}", "登录时异常",loginName, e.getMessage());
			map.put("code", "SYSTEM_EXCEPTION");
			setJsonModel(map);
			return "json";
		}
		
		map.put("code", "SUCCESS");
		map.put("aesMemberNo", aesMemberNo);
		setJsonModel(map);
		return "json";
	}

	/**
	 * 注册页面跳转
	 */
	public String toRegister(@Param("code") String code,@Param("returnFlag") String returnFlag,@Param("productId") String productId,@Param("fundCode") String fundCode) {
		logger.debug("[toRegister] code={},returnFlag={},productId={}",code,returnFlag,productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		try {
			// 1.注册需要获取openId，用来增加关联
			if(!StringUtils.isEmpty(code)){
				String openId = null;
				String result = HttpRequestUtils.sendHttpRequest(WX_OAUTH_REUQEST_URL,WX_OAUTH_REUQEST_METHOD,requestParamBuilderService.buildGetWebAccessTokenParam(code));
				if(!StringUtils.isEmpty(result)){ 
					JSONObject resultJson = JSONObject.fromObject(result); 
					if(resultJson.get("openid") != null){ 
						openId = resultJson.get("openid").toString(); 
						//session.setAttribute("openId", openId); 
						//cookie存openid 
						Cookie cookie = new Cookie(MLANMAO_OPEN_ID, openId);
						cookie.setMaxAge(-1); 
						response.addCookie(cookie);
					} 
				} 
				session.setAttribute("openId", openId);
				logger.info("[toRegister] openId={}", openId);
			}
			
			// 2.获取渠道号
			String type = null;
			String source = null;
			//先获取cookie中的渠道号
			if(StringUtils.isEmpty(source)){
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
						type = URLDecoder.decode(cookie.getValue(), "utf-8");
					} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
						source = cookie.getValue();
						logger.info("[toRegister] srcNo={}", source);
					}
				}
			}
			//cookie中无渠道信息，是否存在推荐人渠道号
			if(StringUtils.isEmpty(source)){
				source = (String) session.getAttribute("_srcNo");
				if(StringUtils.isEmpty(source) || "null".equals(source)){
					source = null;
				}
			}
			logger.debug("[toRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("returnFlag",returnFlag);
		addModelObject("productId",productId);
		addModelObject("fundCode",fundCode);
		return SUCCESS;
	}
	
	/**
	 * 跳转不同的注册成功中间页
	 */
	public String toSwitchRegistSuc(@Param("returnFlag") String returnFlag , @Param("activityCode") String activityCode) {
		logger.info("[toSwitchRegistSuc] returnFlag={}",returnFlag);
		HttpServletResponse response = getMvcFacade().getResponse();
		HttpServletRequest request = getMvcFacade().getRequest();
		String toAction = "toRegistSuc";
		if (null != activityCode && !"".equals(activityCode)
				&& !"null".equalsIgnoreCase(activityCode)) {
			Map<String, Object> map = (Map<String, Object>) ConfigurationUtils
					.getConfigParam("config_type_text_resources",
							"register_success_activity_code").getValue();
			if (null != map.get(activityCode)) {
				toAction = map.get(activityCode).toString();
			}
		}
		try {
//			response.sendRedirect(toAction+"?returnFlag="+returnFlag+"&activityCode="+activityCode);
			RequestDispatcher dispatcher = request.getRequestDispatcher(toAction+"?returnFlag="+returnFlag+"&activityCode="+activityCode);
			dispatcher.forward(request, response);
			return SUCCESS;
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("[toSwitchRegistSuc] returnFlag={},activityCode={},error={}",returnFlag,activityCode,e);
			return SUCCESS;
		}
	}
	
	/**
	 * 多啦宝送流量活动注册成功页面跳转
	 */
	public String toDLBaoFluxRegistSuc(@Param("returnFlag") String returnFlag , @Param("activityCode") String activityCode) {
		logger.info("[toDLBaoFluxRegistSuc] returnFlag={}",returnFlag);
		addModelObject("returnFlag",returnFlag);
		addModelObject("activityCode",activityCode);
		return SUCCESS;
	}
	
	
	/**
	 * 天翼送流量活动注册成功页面跳转
	 */
	public String toTYiFluxRegistSuc(@Param("returnFlag") String returnFlag , @Param("activityCode") String activityCode) {
		logger.info("[toTYiFluxRegistSuc] returnFlag={}",returnFlag);
		addModelObject("returnFlag",returnFlag);
		addModelObject("activityCode",activityCode);
		return SUCCESS;
	}
	
	/**
	 * 送流量活动注册成功页面跳转
	 */
	public String toFluxRegistSuc(@Param("returnFlag") String returnFlag , @Param("activityCode") String activityCode) {
		logger.info("[toFluxRegistSuc] returnFlag={}",returnFlag);
		addModelObject("returnFlag",returnFlag);
		addModelObject("activityCode",activityCode);
		return SUCCESS;
	}
	
	/**
	 * 抽奖活动注册成功页面跳转
	 */
	public String toRaffleRegistSuc(@Param("returnFlag") String returnFlag , @Param("activityCode") String activityCode) {
		logger.info("[toRaffleRegistSuc] returnFlag={}",returnFlag);
		addModelObject("returnFlag",returnFlag);
		addModelObject("activityCode",activityCode);
		return SUCCESS;
	}
	
	

	/**
	 * 注册成功页面跳转
	 */
	public String toRegistSuc(@Param("returnFlag") String returnFlag , @Param("activityCode") String activityCode,@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,@Param("userSessionKey")String userSessionKey) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		ReturnUrlParamEntity returnUrlParamEntity = (ReturnUrlParamEntity) urlMap.get(memberDto.getMemberNo()); 
		addModelObject("returnFlag",returnFlag);
		if(null != returnUrlParamEntity){
			addModelObject("productId", returnUrlParamEntity.getProductId());
		}
		return SUCCESS;
	}

	/**
	 * 验证手机号是否已经注册
	 */
	public String verifyMobile(@Param("mobileNo") String mobileNo) {
		logger.info("[verifyMobile] mobileNo={}", mobileNo);
		try {
			if (StringUtils.isEmpty(mobileNo)) {
				setJsonModel(FAILED);
				return "json";
			}
			MemberRegResultDto memberRegResultDto = lanmaoDemandFacade
					.queryMemberByMobile(mobileNo);
			if (memberRegResultDto != null) {
				logger.info("[verifyMobile] mobileNo={},info={}",mobileNo, "该手机号已经在平台注册");
				setJsonModel(REGISTERED);
				return "json";
			} else {
				logger.info("[verifyMobile] info={}", "手机号未注册过");
				setJsonModel("SUCCESS");
				return "json";
			}
		} catch (Exception e) {
			logger.error("[verifyMobile] info={},ERROR={}", "验证手机号异常",
					e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}

	}

	
	/*public static void main(String[] args) { 
		String openId = null;
		String openId1 = String.valueOf(openId);
		System.out.println(openId1);
		System.out.println(StringUtils.isEmpty(openId1)); 
		System.out.println("null".equals(openId1));
	}*/
	 

	/**
	 * 注册
	 */
	public String register(@Param("tel") String tel,
			@Param("loginPwd") String loginPwd,
			@Param("identifyCode") String identifyCode,
			@Param("verifyCode") String verifyCode,
			@Param("returnFlag") String returnFlag,
			@Param("productId") Long productId,
			@Param("fundCode") String fundCode) {
		logger.info(
				"[register] tel={},identifyCode={},verifyCode={},returnFlag={},productId={}",
				tel, identifyCode, verifyCode,returnFlag,productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		//判空
		if (CheckUtils.isEmpty(tel) || CheckUtils.isEmpty(loginPwd)) {
			setJsonModel(FAILED);
			return "json";
		}
		try {
			//1.获取渠道号
			String srcNo = session.getAttribute("srcNo") != null ? (String)session.getAttribute("srcNo") : "";
			if (StringUtils.isEmpty(tel) || StringUtils.isEmpty(loginPwd)
					|| StringUtils.isEmpty(identifyCode)) {
				setJsonModel(FAILED);
				return "json";
			}
			if(StringUtils.isEmpty(srcNo)){
				String source = (String)session.getAttribute("_srcNo");
				if(!StringUtils.isEmpty(source) && !"null".equals(source)){
					srcNo = source;
				}
			}
			// 2.渠道编号不为空，验证图片验证码是否输入正确
			logger.debug("[register] 验证码={}",session.getAttribute("REMOTE_KAPTCHA_SESSION_KEY"));
			if (!StringUtils.isEmpty(srcNo)) {
				if (StringUtils.isEmpty(verifyCode)
						|| !verifyCode.equals(session
								.getAttribute("REMOTE_KAPTCHA_SESSION_KEY"))) {
					session.removeAttribute("REMOTE_KAPTCHA_SESSION_KEY");
					logger.info("[register] ERROR={}", "验证码验证失败");
					setJsonModel(VERIFY_FAILED);
					return "json";
				}
				session.removeAttribute("REMOTE_KAPTCHA_SESSION_KEY");
			} else {
				// 渠道为空时，设置为默认渠道号，微信公众号的渠道号
				srcNo = (String)ConfigurationUtils.getConfigParam("config_type_text_resources", "sourceNo").getValue();
			}
			// 3.验证手机号是否已经注册 
			MemberRegResultDto memberRegResultdto = lanmaoDemandFacade
					.queryMemberByMobile(tel);
			if (memberRegResultdto != null) {
				logger.info("[register] tel={},ERROR={}",tel, "该手机号已经在平台注册");
				setJsonModel(REGISTERED);
				return "json";
			}
			// 4.验证手机验证码
			try {
				if (GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(
								DynamicPwdTypeEnum.REGISTER, tel, identifyCode)) {
					setJsonModel(DYNAMIC_FAILED);
					return "json";
				}
			} catch (Exception e) {
				logger.error("[register] info={},ERROR={}", "验证手机验证码异常",
						e.getMessage());
				setJsonModel(SYSTEM_EXCEPTION);
				return "json";
			}
			// 5.手机号注册,注册渠道号不能为空
			MemberMobileRegParamDto memberMobileRegParamDto = new MemberMobileRegParamDto();
			memberMobileRegParamDto.setBindMobileNo(tel);
			memberMobileRegParamDto.setPassword(loginPwd);
			memberMobileRegParamDto.setRegModel(MemberRegModelEnum.COMMON);
			memberMobileRegParamDto.setSourceNo(srcNo);
			memberMobileRegParamDto.setDevice(DeviceEnum.WX);
			//获取用户ip，mac，user_agent
//			String macAddress = HttpRequestUtils.getMacAddress(IpUtils.getIpAddr(request));
//			String userAgent = request.getHeader("User-Agent");
			String macAddress = null;
			String userAgent = null;
			String imei = null;
			try{
				macAddress = HttpRequestUtils.getMacAddress(IpUtils.getIpAddr(request));
				userAgent = request.getHeader("User-Agent");
			}catch(Exception e){
				logger.error("[register] info={},error={}","获取mac地址时异常",e);
			}
			logger.debug("[register] macAddress={},userAgent={}",macAddress,userAgent);
			//判断cookie里面是否有imei这个字段，没有则生成该字段
			Cookie[] cookies = request.getCookies();
	        if(null!=cookies){
	            for(Cookie cookie : cookies){
	            	if("IMEI_ID".equals(cookie.getName())) {
	            		imei = cookie.getValue();
	            	}
	            }
	        }
	        if(null == imei) {
	        	imei = UUID.randomUUID().toString();
	        	Cookie imeiCookie = new Cookie("IMEI_ID", imei);
	        	imeiCookie.setMaxAge(-1);
	        	response.addCookie(imeiCookie);
	        }
			Object obj = JSONObjectUtils.userAddrToJSONStr(IpUtils.getIpAddr(request),macAddress, userAgent, imei);
			if(obj != null){
				memberMobileRegParamDto.setIp(obj.toString());
			}
			String _recommendMemberNo = (String)session.getAttribute("_recommendMemberNo");
			Object _bizType = session.getAttribute("_bizType");
			
			//注册增两个接口参数bizType、recommendMemberNo
			if(!StringUtils.isEmpty(_recommendMemberNo) && !"null".equals(_recommendMemberNo)){
				memberMobileRegParamDto.setRecommendMemberNo(_recommendMemberNo);
			}
			if(_bizType!= null && !"null".equals(_bizType)){
				ShareBizTypeEnum bizType = (ShareBizTypeEnum)_bizType;
				if(bizType == ShareBizTypeEnum.ALL) {
					memberMobileRegParamDto.setBizType(RecommendedBizTypeEnum.ONLINE);
				} else if(bizType == ShareBizTypeEnum.ON_LINE) {
					memberMobileRegParamDto.setBizType(RecommendedBizTypeEnum.ONLINE);
				} else {
					memberMobileRegParamDto.setBizType(RecommendedBizTypeEnum.OFFLINE);
				}
				
			}
			MemberRegResultDto memberRegResultDto = lanmaoTransactionFacade
					.regMemberByMoblie(memberMobileRegParamDto);
			if (memberRegResultDto.getStatus().equals(MemberStatusEnum.FAIL)) {
				logger.info("[register] memberRegResultDto={}", memberRegResultDto);
				setJsonModel(FAILED);
				return "json";
			}
			MemberDto memberDto = memberManagementFacade
					.obtainMember(memberRegResultDto.getMemberNo());
			if (memberDto == null) {
				setJsonModel(FAILED);
				return "json";
			}
			session.setAttribute("member", memberDto);
			//作为推荐人的用户编号
			session.setAttribute("recommendMemberNo", memberDto.getMemberNo());
			//6.增加openId与会员的关联
			String openId = (String) session.getAttribute("openId");
			// openId不为空，则绑定公众号
			try{
				if (!StringUtils.isEmpty(openId)) {
					// 获取cookie中的openId后面什么时候用？openId存在cookie中的原因？为了存储用户的信息？
					Cookie cookie = new Cookie(MLANMAO_OPEN_ID, openId);
					cookie.setMaxAge(-1);
					response.addCookie(cookie);

					// 根据openId查询绑定表，获得openId与会员编号关联实体
					MemberRelevanceDto memberRelevanceDto = memberManagementFacade
							.obtainMemberRelevance(openId);
					// 如果实体为空，插入一条新的关联，如果实体不为空，并且关联表里关联的会员id与session中存储的不一致，或者关联实体的状态不为“on”,则更新关联
					if (memberRelevanceDto == null) {
						// 开关状态是否会默认填为“ON”
						memberManagementFacade
								.addMemberRelevance(new MemberRelevanceDto(openId,
										memberDto.getMemberNo()));
						//更新用户最后登录时间
						memberManagementFacade.updateMemberRelevanceLogDate(openId);
					} else if (!memberRelevanceDto.getMemberNo().equals(
							memberDto.getMemberNo())
							|| !(MemberRelevanceStatusEnum.ON).equals(memberRelevanceDto.getStatus())) {
						memberManagementFacade.updateMemberRelevance(
								memberDto.getMemberNo(), openId,
								MemberRelevanceStatusEnum.ON);
						//更新用户最后登录时间
						memberManagementFacade.updateMemberRelevanceLogDate(openId);
					}
				}
			}catch(Exception e){
				logger.error("[register] info={},ERROR={}","openid与会员编号关联失败",e.getMessage());
			}
			//7.添加分享记录
			try{
//				String _recommendMemberNo = (String)session.getAttribute("_recommendMemberNo");
//				Object _bizType = session.getAttribute("_bizType");
				String _srcNo = (String)session.getAttribute("_srcNo");
				logger.info("[register] _recommendMemberNo={},_bizType={},_srcNo={}",_recommendMemberNo,_bizType,_srcNo);
				if(!StringUtils.isEmpty(_recommendMemberNo) && !"null".equals(_recommendMemberNo)){
					ChannelDto channelDto = null;
					String _srcType = null;
					ActivityShareRecordsDTO activityShareRecordsDto = new ActivityShareRecordsDTO();
					if(!StringUtils.isEmpty(_srcNo) && !"null".equals(_srcNo)){
						//查询渠道类型
						channelDto = channelFacade.queryChannelByChannelNo(_srcNo);
						_srcType = channelDto.getChannelName();
						activityShareRecordsDto.setSrcNo(_srcNo);
						activityShareRecordsDto.setSrcType(_srcType);
					}
					if(_bizType!= null && !"null".equals(_bizType)){
						ShareBizTypeEnum bizType = (ShareBizTypeEnum)_bizType;
						activityShareRecordsDto.setBizType(bizType);
					}
					
				}
			}catch(Exception e){
				logger.error("[register] info={},ERROR={}","添加分享记录时失败",e);
			}
			//8.是否存在需要调回的url
			if(returnFlag != null){
				ReturnUrlParamEntity returnUrlParamEntity = new ReturnUrlParamEntity();
				returnUrlParamEntity.setReturnFlag(returnFlag);
				returnUrlParamEntity.setProductId(productId);
				returnUrlParamEntity.setFundCode(fundCode);
				urlMap.put(memberDto.getMemberNo(), returnUrlParamEntity);
			}
			//9.推送微信注册成功消息
			try {
				if(!StringUtils.isEmpty(openId)){
					Map<String,String> modelWx = LmConstants.getRegisterSuccessWxMessageModel();
					ActivityWXSendMessageDTO dataDto = new ActivityWXSendMessageDTO();
					dataDto.setFirst(modelWx.get("first"));
					dataDto.setRemark(modelWx.get("remark"));
					dataDto.setOpenId(openId);
					dataDto.setUrl(modelWx.get("url"));
					dataDto.setKeyword1(tel);
					dataDto.setKeyword2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					activityWXSendMessageFacade.sendWxMessage(ActivityWXSendMessageEnum.REGISTER_SUCCESS, dataDto);
				}
			} catch (Exception e) {
				logger.error("[register] info={},ERROR={}", "注册成功推送微信消息异常",
						e.getMessage());
			}
		} catch (Exception e) {
			logger.error("[register] info={},ERROR={}", "注册异常", e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 下发注册手机短信验证码
	 */
	public String sendRegisterCode(@Param("mobileNo") String mobileNo) {
		try {
			if (StringUtils.isEmpty(mobileNo)) {
				logger.info("[sendRegisterCode] ERROR={}", "手机号为空");
				setJsonModel(FAILED);
				return "json";
			}
			lMUtilFacde.sendDynamicPwd(DynamicPwdTypeEnum.REGISTER, mobileNo,mobileNo);
			setJsonModel("SUCCESS");
			return "json";
		} catch (Exception e) {
			logger.error("[sendRegisterCode] info={},mobileNo={},ERROR={}", "下发短验时异常",mobileNo,e);
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
	}

	/**
	 * 验证旧手机号的短信验证码和交易码
	 */
	public String verifyOldMobileNoCode(@Param("verifyCode") String verifyCode,
			@Param("tradePwd") String tradePwd) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(tradePwd)) {
			setJsonModel(FAILED);
			return "json";
		}
		try {
			MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
					.checkTradePassword(memberDto.getMemberNo(), tradePwd);
			if (!memberPwdConstraintDto.getResultFlag()) {
				setJsonModel(WRONG_PWD);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[verifyOldMobileNoCode] info={},ERROR={}",
					"交易密码受限异常", e.getMessage());
			setJsonModel(TRADE_PWD_VERIFY_LIMIT);
			return "json";
		}
		try {
			if (GetParamUtils.getIsVerifyCode()
					&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.MOLD,
							memberDto.getBindMobileNo(), verifyCode)) {
				setJsonModel(DYNAMIC_FAILED);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[verifyOldMobileNoCode] info={},ERROR={}", "验证短信异常",
					e.getMessage());
			setJsonModel(DYNAMIC_FAILED);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}

	/**
	 * 下发旧手机号的短信验证码
	 */
	public String sendOldMobileNoCode() {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try {
			lMUtilFacde.sendDynamicPwd(DynamicPwdTypeEnum.MOLD,
					memberDto.getBindMobileNo(), memberDto.getRealName());
			setJsonModel("SUCCESS");
			return "json";
		} catch (Exception e) {
			logger.error("[sendOldMobileNoCode] info={},ERROR={}",
					"下发旧手机号的短信验证码异常", e.getMessage());
			setJsonModel(FAILED);
			return "json";
		}
	}

	/**
	 * 下发新手机号的短信验证码
	 */
	public String sendNewMobileNoCode(@Param("newMobileNo") String newMobileNo) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (StringUtils.isEmpty(newMobileNo)) {
			setJsonModel(FAILED);
			return "json";
		}
		try {
			lMUtilFacde.sendDynamicPwd(DynamicPwdTypeEnum.MNEW, newMobileNo,
					memberDto.getRealName());
			setJsonModel("SUCCESS");
			return "json";
		} catch (Exception e) {
			logger.error("[sendNewMobileNoCode] info={},ERROR={}",
					"下发新手机号的短信验证码异常", e.getMessage());
			setJsonModel(FAILED);
			return "json";
		}
	}

	/**
	 * 修改手机号
	 */
	public String modifyMobileNo(@Param("verifyCode") String verifyCode,
			@Param("newMobileNo") String newMobileNo) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(newMobileNo)) {
			setJsonModel(FAILED);
			return "json";
		}
		try {
			if (GetParamUtils.getIsVerifyCode()
					&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.MNEW,
							newMobileNo, verifyCode)) {
				setJsonModel(DYNAMIC_FAILED);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[modifyMobileNo] info={},ERROR={}", "验证短信验证码异常",
					e.getMessage());
			setJsonModel(DYNAMIC_FAILED);
			return "json";
		}

		try {
			if (!memberManagementFacade.updateMemberLoginName(
					memberDto.getMemberNo(), newMobileNo)) {
				setJsonModel(FAILED);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[modifyMobileNo] info={},ERROR={}", "修改手机号码异常",
					e.getMessage());
			setJsonModel(FAILED);
			return "json";
		}
		try {
			String openId = (String) session.getAttribute("openId");
			if (!StringUtils.isEmpty(openId)) {
				memberManagementFacade.updateMemberRelevance(
						memberDto.getMemberNo(), openId,
						MemberRelevanceStatusEnum.OFF);
			}
		
			session.removeAttribute("member");
		} catch (Exception e) { 
			logger.error("[modifyMobileNo] info={},ERROR={}",
					"解绑OPENID与会员账户的关系时异常", e.getMessage());
			/*setJsonModel(FAILED);
			return "json";*/
		}
		setJsonModel("SUCCESS");
		return "json";
	}

	/**
	 * 登录密码修改
	 */
	public String modifyLoginPwd(@Param("loginPwd") String loginPwd,
			@Param("newLoginPwd") String newLoginPwd,
			@Param("newLoginPwdConfirm") String newLoginPwdConfirm) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (newLoginPwd == null || !newLoginPwd.equals(newLoginPwdConfirm)) {
			setJsonModel(PWD_INCONSISTENT);
			return "json";
		}
		try {
				// 检查原登录密码是否正确
				MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
						.checkLoginPassword(memberDto.getMemberNo(), loginPwd);
				if (!memberPwdConstraintDto.getResultFlag()) {
					setJsonModel(VERIFY_FAILED);
					return "json";
				}
				// 检查新登录密码与交易密码是否一致
				MemberPasswordValidationResultDto result = lanmaoDemandFacade
						.queryMemberPwdValidation(memberDto.getMemberNo(),
								newLoginPwd,
								PasswordValidationTypeEnum.LOGIN_COMPARE_TRAD);
				if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
					setJsonModel(result.getDescription());
					return "json";
				}
				// 修改登录密码
				memberPasswordFacade.resetLoginPassword(
						memberDto.getMemberNo(), newLoginPwd);

			String openId = (String) session.getAttribute("openId");
			if (!StringUtils.isEmpty(openId)) {
			/*	setJsonModel(FAILED);
				return "json";*/
				memberManagementFacade.updateMemberRelevance(
						memberDto.getMemberNo(), openId,
						MemberRelevanceStatusEnum.OFF);
			}
			

		} catch (Exception e) {
			logger.error("[modifyLoginPwd] info={},ERROR={}", "登录密码修改异常",
					e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		session.removeAttribute("member");
		setJsonModel("SUCCESS");
		return "json";
	}

	/**
	 * 修改交易密码
	 */
	public String modifyTradePwd(@Param("tradePwd") String tradePwd,
			@Param("newTradePwd") String newTradePwd,
			@Param("newTradePwdConfirm") String newTradePwdConfirm) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (newTradePwd == null || !newTradePwd.equals(newTradePwdConfirm)) {
			setJsonModel(PWD_INCONSISTENT);
			return "json";
		}
		try {

			// 检查原交易密码是否正确
			MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
					.checkTradePassword(memberDto.getMemberNo(), tradePwd);
			if (!memberPwdConstraintDto.getResultFlag()) {
				setJsonModel(VERIFY_FAILED);
				return "json";
			}
			// 检查新交易密码与登录密码是否一致
			MemberPasswordValidationResultDto result = lanmaoDemandFacade
					.queryMemberPwdValidation(memberDto.getMemberNo(),
							newTradePwd,
							PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
			if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
				setJsonModel(result.getDescription());
				return "json";
			}
			// 修改交易密码
			memberPasswordFacade.resetTradePassword(
					memberDto.getMemberNo(), newTradePwd);

		} catch (Exception e) {
			logger.error("[modifyTradePwd] info={},ERROR={}", "修改交易密码异常",
					e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}

	/**
	 * 下发重置交易密码的短信验证码
	 */
	public String sendResetTradePwdCode() {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try {
			lMUtilFacde.sendDynamicPwd(DynamicPwdTypeEnum.RPAYPWD,
					memberDto.getBindMobileNo(), memberDto.getRealName());
		} catch (Exception e) {
			logger.error("[sendResetTradePwdCode] info={},ERROR={}",
					"下发重置交易密码的短信验证码异常", e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}

	/**
	 * 重置交易密码
	 */
	public String resetTradePwd(@Param("credentialsNo") String credentialsNo,
			@Param("newTradePwd") String newTradePwd,
			@Param("newTradePwdConfirm") String newTradePwdConfirm,
			@Param("verifyCode") String verifyCode) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (newTradePwd == null || !newTradePwd.equals(newTradePwdConfirm)) {
			setJsonModel(PWD_INCONSISTENT);
			return "json";
		}
		try {
			// 验证身份证是否正确
			if (credentialsNo == null
					|| !credentialsNo.toUpperCase().equals(
							memberDto.getCredentialsNo().toUpperCase())) {
				setJsonModel("ID_VERIFY_FAILED");
				return "json";
			}
			// 验证验证码是否为空
			if (StringUtils.isEmpty(verifyCode)) {
				setJsonModel(FAILED);
				return "json";
			}
			// 验证验证码
			if (GetParamUtils.getIsVerifyCode()
					&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.RPAYPWD,
							memberDto.getBindMobileNo(), verifyCode)) {
				setJsonModel(VERIFY_FAILED);
				return "json";
			}
			// 验证新交易密码是否与登录密码一致
			MemberPasswordValidationResultDto result = lanmaoDemandFacade
					.queryMemberPwdValidation(memberDto.getMemberNo(),
							newTradePwd,
							PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
			if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
				setJsonModel(result.getDescription());
				return "json";
			}
			// 重新设置交易密码
				memberPasswordFacade.resetTradePassword(
						memberDto.getMemberNo(), newTradePwd);
		} catch (Exception e) {
			logger.error("[resetTradePwd] info={},ERROR={}", " 重置交易密码异常",
					e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 跳转重置登录密码
	 */
	public String toResetLoginPwd(){
		logger.info("[toResetLoginPwd] info={}","重置登录密码...");
		return SUCCESS;
	}
	/**
	 * 下发重置登录密码的短信验证码
	 */
	public String sendResetLoginPwdCode(@Param("mobileNo") String tel) {
		HttpSession session = getMvcFacade().getHttpSession();
		try {
			if(tel == null || "".equals(tel)){
				setJsonModel(FAILED);
				return "json";
			}
			lMUtilFacde.sendDynamicPwd(DynamicPwdTypeEnum.RLOGINPWD,tel, tel);
		} catch (Exception e) {
			logger.error("[sendResetTradePwdCode] info={},ERROR={}",
					"下发重置登录密码的短信验证码异常", e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 重置登录密码
	 */
	public String resetLoginPwd(@Param("tel") String tel,@Param("loginPwd") String loginPwd,@Param("loginRePwd") String loginRePwd,@Param("verifyCode") String verifyCode){
		logger.info("[resetLoginPwd] tel={},verifyCode={}",tel,verifyCode);
		HttpSession session = getMvcFacade().getHttpSession();
		try{
			MemberDto memberDto = memberManagementFacade.queryByBindMobileNo(tel);
			if(memberDto == null){
				setJsonModel(USER_NOTEXIST);
				return "json";
			}
			if(!loginPwd.equals(loginRePwd)){
				setJsonModel(PWD_INCONSISTENT);
				return "json";
			}
			//验证手机验证码
			if (StringUtils.isEmpty(verifyCode)) {
				setJsonModel(FAILED);
				return "json";
			}
			if (GetParamUtils.getIsVerifyCode()
					&& !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.RLOGINPWD,
							memberDto.getBindMobileNo(), verifyCode)) {
				setJsonModel(DYNAMIC_FAILED);
				return "json";
			}
			//验证新登录密码是否和交易密码一致
			MemberPasswordValidationResultDto result = lanmaoDemandFacade.queryMemberPwdValidation(memberDto.getMemberNo(), loginPwd, PasswordValidationTypeEnum.LOGIN_COMPARE_TRAD);
			logger.info("[resetLoginPwd] result={}",result);
			if (!PasswordStatusEnum.SUCCESS.equals(result.getStatus())) {
				setJsonModel(result.getDescription());
				return "json";
			}
			//重置登录密码
			memberPasswordFacade.resetLoginPassword(memberDto.getMemberNo(), loginPwd);
			setJsonModel("SUCCESS");
			return "json";
		}catch(Exception e){
			logger.info("[resetLoginPwd] info={},ERROR={}","重置登录密码时异常",e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		
	}
	/**
	 * 退出并解绑公众号
	 */
	public String unBindAccount(){
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletResponse response=getMvcFacade().getResponse();
		Map<String,Object> map = new HashMap<String,Object>();
		logger.info("[unBindAccount] info={}","解绑公众号...");
		try{
			//有openId表示从微信进入
			String openId = (String)session.getAttribute("openId");
			MemberDto member = (MemberDto) session.getAttribute("member");
			if(!StringUtils.isEmpty(openId)){
				//解除openId与会员关系的绑定
				memberManagementFacade.updateMemberRelevance(member.getMemberNo(), openId,MemberRelevanceStatusEnum.OFF);
			}
			//清除登录信息
			session.removeAttribute("member");
			//清除推荐人信息
			session.removeAttribute("_recommendMemberNo");
			//清除来源信息
			session.removeAttribute("_bizType");
			//清除渠道号信息
			session.removeAttribute("_srcNo");
			//清除新手标志
			session.removeAttribute("newcomerFlag");
			//TODO      cookie暂时没有用到
			//清除信托蒙层记录
			Cookie c=getMvcFacade().getCookie(MLANMAO_IS_FIRST_FIXED);
			if(null!=c){
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
			
			//如果isFirstPopularize不存在 说明没有和购买理财首页交互过
			Cookie firstPopularize=new Cookie(MLANMAO_IS_FIRST_Popularize,null);
			firstPopularize.setMaxAge(0);
			firstPopularize.setPath("/");
			response.addCookie(firstPopularize);
			
			Cookie firstIn=new Cookie(MLANMAO_IS_FIRST_IN,null);
			firstIn.setMaxAge(0);
			firstIn.setPath("/");
			response.addCookie(firstIn);
			
			map.put("status", "SUCCESS");
			map.put("openId", openId);
			setJsonModel(map);
			return "json";
		}catch(Exception e){
			logger.error("[unBindAccount] info={},ERROR={}","解绑时出现异常",e.getMessage());
			setJsonModel("\"FAILED\"");
			return "json";
		}
	}
	/**
	 * 跳转用户协议（懒猫金服服务协议）
	 */
	public String toLmProtocol(){
		logger.info("[toLmProtocol] info={}","跳转查看用户协议");
		return SUCCESS;
	}
	
	/**
	 * 跳转成为理财顾问
	 */
	public String toBecomeAdvisor(){
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletResponse response=getMvcFacade().getResponse();
		HttpServletRequest request = getMvcFacade().getRequest();
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		BankCardInfoDto bankCardInfoDto = null;
		//判断用户是否已经绑卡
		try {
			bankCardInfoDto = lPQueryFacade
					.obtainDefaultBankCardInfo(member.getMemberNo());
		} catch (Exception e) {
			logger.error("[toPopularize] info={},ERROR={}", "购买理财首页查询用户绑卡信息时异常",
					e.getMessage());
			bankCardInfoDto=null;
		}
		//用户未绑卡跳转绑卡页面
		if( null == bankCardInfoDto) {
			try {
				//返回的url
				ReturnUrlParamEntity returnUrlParamEntity = new ReturnUrlParamEntity();
				returnUrlParamEntity.setReturnFlag("toBecomeAdvisor");
				urlMap.put(member.getMemberNo(), returnUrlParamEntity);
				response.sendRedirect("card/toGOBindCard");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("card/toGOBindCard");
//				dispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//绑卡则跳转到理财顾问
		String actUrl = "quickRegisterConsultant";
		String returnUrl = requestParamBuilderService.getConsultantUserUrlParam(actUrl, member.getMemberNo());
		try {
			response.sendRedirect(returnUrl);
//			RequestDispatcher dispatcher = request.getRequestDispatcher(returnUrl);
//			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 跳转安全保障页面
	 * @param in -
	 * @return 
	 */
	public String toInsurance(){
		logger.info("[toInsurance] info={}","跳转安全保障页面");
		return SUCCESS;
	}
	
}
