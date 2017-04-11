package com.yeepay.g3.app.lmweChat.action.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.entity.ReturnUrlParamEntity;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.HttpRequestUtils;
import com.yeepay.g3.app.lmweChat.utils.JSONObjectUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.app.lmweChat.utils.SecretUtils;
import com.yeepay.g3.app.lmweChat.utils.StringProcessorUtils;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.facade.lmaccs.enumtype.CardVerifyStatusEnum;
import com.yeepay.g3.facade.lmlc.trust.dto.api.ProductsForWXResultDto;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmnotice.dto.VerifyDynamicPwdParamDto;
import com.yeepay.g3.facade.lmnotice.dto.VerifyDynamicPwdResultDto;
import com.yeepay.g3.facade.lmnotice.service.NoticeFacade;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.CardInfoResultDto;
import com.yeepay.g3.facade.lmportal.dto.CardVerifyAndPasswordParamDto;
import com.yeepay.g3.facade.lmportal.dto.CardVerifyAndPasswordResultDto;
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
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.facade.lmrouter.dto.result.PlatChannelCanBindCard;
import com.yeepay.g3.facade.lmrouter.dto.result.PlatChannelResultDto;
import com.yeepay.g3.facade.lmrouter.enumtype.PlatChannelStatusEnum;
import com.yeepay.g3.facade.lmrouter.enumtype.RouteTypeEnum;
import com.yeepay.g3.facade.lmrouter.service.PlatChannelFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.IpUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
@Controller 
public class CardAction extends BaseAction {
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);
	protected LMUtilFacde lMUtilFacde = RemoteServiceFactory
			.getService(LMUtilFacde.class);
	protected LanmaoDemandFacade lanmaoDemandfFacade = RemoteServiceFactory.getService(LanmaoDemandFacade.class);
	protected LanmaoTransactionFacade lanmaoTransactionFacade = RemoteServiceFactory.getService(LanmaoTransactionFacade.class);
	protected PlatChannelFacade platChannelFacade = RemoteServiceFactory
			.getService(PlatChannelFacade.class);
	protected ActivityWXSendMessageFacade activityWXSendMessageFacade = RemoteServiceFactory
	.getService(ActivityWXSendMessageFacade.class);
//	protected ActivityWXSendMessageFacade activityWXSendMessageFacade = RemoteServiceFactory
//	.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityWXSendMessageFacade.class);
	private Logger logger = LoggerFactory.getLogger(CardAction.class);
	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory
			.getService(TrustQueryFacade.class);
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory
			.getService(MemberManagementFacade.class);
	protected NoticeFacade noticeFacade = RemoteServiceFactory.getService(NoticeFacade.class);

	/**
	 * 跳转去绑卡页面
	 */
	public String toGOBindCard(){
		return SUCCESS;
	}
	/**
	 * 跳转绑卡页面
	 */
	public String toBindCard(@Param("returnFlag") String returnFlag,
			@Param("activityCode") String activityCode,
			@Param("returnUrlString") String returnUrlString){
		logger.info("[toBindCard] toBindCard={}","去绑卡...");
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletResponse response = getMvcFacade().getResponse();
		HttpServletRequest request = getMvcFacade().getRequest();
		//判断是否绑过卡
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		//已经绑卡的，跳转我的银行卡界面
		BankCardInfoDto bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
		if(bankCardInfoDto != null){
			try {
				request.getRequestDispatcher("toCard").forward(request, response);
//				response.sendRedirect("toCard");
			} catch (Exception e) {
//				e.printStackTrace();
				logger.error("[toBindCard] info={},error={}","转发时出现异常",e);
			} /*catch (ServletException e) {
				e.printStackTrace();
			}*/
			return "failed";
		}
		if(returnUrlString != null){
			ReturnUrlParamEntity returnUrlParamEntity = new ReturnUrlParamEntity();
			returnUrlParamEntity.setReturnUrlString(returnUrlString);
			urlMap.put(memberDto.getMemberNo(), returnUrlParamEntity);
		} else if(returnFlag != null){
			ReturnUrlParamEntity returnUrlParamEntity = new ReturnUrlParamEntity();
			returnUrlParamEntity.setReturnFlag(returnFlag);
			urlMap.put(memberDto.getMemberNo(), returnUrlParamEntity);
		}
		
		if(null != activityCode) {
			addModelObject("activityCode", activityCode);
		}
		addModelObject("memberDto", memberDto);
		return SUCCESS;
	}
	/**
	 * 跳转查看银行限额
	 */
	public String toBankLimit(){
		logger.info("[toBankLimit] info={}","查看银行限额...");
		PlatChannelCanBindCard result=platChannelFacade.queryCanBindCardListlByBankNo();
		if(result==null||!"0".equals(result.getCode())){
			logger.error("[toBankLimit] error={},description={}","查看银行限额列表接口异常",result.getDescription());
			return SYSTEM_EXCEPTION;
		}
		addModelObject("result", result);
		return SUCCESS;
	}
	
	/**
	 * 根据不同的activityCode，跳转绑卡成功页面  
	 */
	public String toSwitchBindCardSuc(@Param("activityCode") String activityCode){
		logger.info("[toSwitchBindCardSuc] toSwitchBindCardSuc={}","跳转绑卡成功页...");
		HttpServletResponse response = getMvcFacade().getResponse();
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		MemberDto member = (MemberDto) session.getAttribute("member");
		//默认的成功中间页
		String toAction = "toBindCardSuc";
		//获取跳转的帮卡成功中间页的action
		Map<String, Object> map = (Map<String, Object>) ConfigurationUtils
				.getConfigParam("config_type_text_resources",
						"bind_success_activity_code").getValue();
		//根据穿参数的activityCode，选择跳转页
		if (null != activityCode && !"".equals(activityCode)
				&& !"null".equalsIgnoreCase(activityCode)) {
			if (null != map && null != map.get(activityCode)) {
				toAction = map.get(activityCode).toString();
			}
		}else {
			//根据用户的来源判断
			if(null != member) {
				String source = member.getRegisterSrcNo();
				if (null != map && null != source && null != map.get(source)) {
					toAction = map.get(source).toString();
				}
			}
		}
		try {
			//根据不同渠道号跳转不同页面；故查询银行卡信息；之前跳转action也有查询；冗余问题待处理
			//查询渠道对应returnUrl
			Map<String, Object> returnFlagMap = (Map<String, Object>) ConfigurationUtils
					.getConfigParam("config_type_text_resources",
							"lmwx_returnFlag_by_source").getValue();
			String returnFlag = returnFlagMap.get(activityCode)==null?"":returnFlagMap.get(activityCode).toString();
			Map<String, Object> returnUrlMap = (Map<String, Object>) ConfigurationUtils
					.getConfigParam("config_type_text_resources",
							"lmwx_return_url_config").getValue();
			String returnUrl = returnUrlMap.get(returnFlag)==null?"":returnUrlMap.get(returnFlag).toString();
			request.setAttribute("returnUrl", returnUrl);
			//显示银行卡信息
			BankCardInfoDto  bankCardInfoDto = null;
			bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(member
					.getMemberNo());
			logger.info("[toSwitchBindCardSuc] bankCardInfoDto={}",bankCardInfoDto);
			if (bankCardInfoDto != null) {
				CardInfoResultDto resultDto=lanmaoDemandfFacade.cardBin(bankCardInfoDto.getCardNo());
				request.setAttribute("BANK_NAME", resultDto.getBankName());
				request.setAttribute("CARD_NO", StringProcessorUtils
						.desensitizedCardNo(bankCardInfoDto.getCardNo()));
			} 
			RequestDispatcher dispatcher = request.getRequestDispatcher(toAction);
			dispatcher.forward(request, response);
//			response.sendRedirect(toAction);
			return SUCCESS;
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("[toSwitchBindCardSuc] ERROR={}",e);
			return SUCCESS;
		}
		
	}
	
	/**
	 * 多啦宝流量活动绑卡成功跳转页面 
	 */
	public String toDLBaoFluxBindCardSuc(){
		logger.info("[toDLBaoFluxBindCardSuc] toDLBaoFluxBindCardSuc={}","跳转绑卡成功页...");
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		logger.info("[toDLBaoFluxBindCardSuc] member={}",member);
		List<ProductsForWXResultDto> list=null;
		try{
			list = trustQueryFacade
					.queryProductsForWX();
			if (list != null && list.size() != 0) {
				for (ProductsForWXResultDto productsForWXResultDto : list) {
					if("FRESHMAN".equals(productsForWXResultDto.getChannelColumn())){
						long freshmanId=productsForWXResultDto.getProductId();
						logger.info("[toDLBaoFluxBindCardSuc] freshmanId={}",freshmanId);
						addModelObject("FRESHMANID",freshmanId);
					}
				}
			}
			return SUCCESS;
		}catch(Exception e){
			logger.error("[toDLBaoFluxBindCardSuc] info={},ERROR={}","跳转绑卡成功页异常",e.getMessage());
			return SUCCESS;
		}
	}
	
	/**
	 * 流量活动绑卡成功跳转页面  
	 */
	public String toFluxBindCardSuc(){
		logger.info("[toFluxBindCardSuc] toFluxBindCardSuc={}","跳转绑卡成功页...");
		HttpSession session = getMvcFacade().getHttpSession();
		StringBuilder urlConfig = null;
		String returnUrl = null;
		MemberDto member = (MemberDto) session.getAttribute("member");
		logger.info("[toFluxBindCardSuc] member={}",member);
		try{
			//显示银行卡信息
			BankCardInfoDto  bankCardInfoDto = null;
			bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(member
					.getMemberNo());
			logger.info("[toFluxBindCardSuc] bankCardInfoDto={}",bankCardInfoDto);
			if (bankCardInfoDto != null) {
				addModelObject("CARD_NO", StringProcessorUtils
						.desensitizedCardNo(bankCardInfoDto.getCardNo()));
				CardInfoResultDto resultDto=lanmaoDemandfFacade.cardBin(bankCardInfoDto.getCardNo());
				/*if(!"0".equals(resultDto.getCode())){
					setJsonModel("\"FAILED\"");
				}*/
				addModelObject("BANK_NAME", resultDto.getBankName());
				addModelObject("BANK_ID", resultDto.getBankId());
			} 
			logger.info("[toFluxBindCardSuc] urlMap={}",urlMap.get(member.getMemberNo()));
			ReturnUrlParamEntity returnParamEntity = (ReturnUrlParamEntity) urlMap.get(member.getMemberNo());
			logger.info("[toBindCardSuc] returnParamEntity={}",returnParamEntity);
			//如果没有返回url，则是正常流程，跳转购买理财首页
			if(returnParamEntity == null || null == returnParamEntity.getReturnFlag() || returnParamEntity.getReturnFlag() == ""){
				returnUrl = "popularize/toPopularize";
			}else{
				Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmwx_return_url_config").getValue();
				//通过标志位取返回的url
				urlConfig = new StringBuilder(map.get(returnParamEntity.getReturnFlag())+"?");
				logger.info("[toFluxBindCardSuc] urlConfig={}",urlConfig);
//				returnUrl = urlConfig+"?productId="+returnParamEntity.getProductId()+"&amount="+returnParamEntity.getAmount()+"&ret="+returnParamEntity.getRet()+"&expectIncome="+returnParamEntity.getExpectIncome()+"&promoNo="+returnParamEntity.getPromoNo();
				returnUrl = (returnParamEntity.getProductId() == null ? urlConfig : urlConfig.append("productId=").append(returnParamEntity.getProductId())).toString();
				returnUrl = (returnParamEntity.getAmount() == null ? urlConfig : urlConfig.append("&amount=").append(returnParamEntity.getAmount())).toString();
				returnUrl = (returnParamEntity.getExpectIncome() == null ? urlConfig : urlConfig.append("&expectIncome=").append(returnParamEntity.getExpectIncome())).toString();
				returnUrl = (returnParamEntity.getPromoNo() == null ? urlConfig : urlConfig.append("&promoNo=").append(returnParamEntity.getPromoNo())).toString();
				returnUrl = (returnParamEntity.getRet() == null ? urlConfig : urlConfig.append("&ret=").append(returnParamEntity.getRet())).toString();
			}
			logger.info("[toFluxBindCardSuc] returnUrl={}",returnUrl);
			addModelObject("returnUrl",returnUrl);
			urlMap.remove(member.getMemberNo());
			return SUCCESS;
		}catch(Exception e){
			logger.error("[toFluxBindCardSuc] info={},ERROR={}","跳转绑卡成功页异常",e);
			return SUCCESS;
		}
		
	}
	
	
	/**
	 * 抽奖活动绑卡成功跳转页面  
	 */
	public String toRaffleBindCardSuc(){
		logger.info("[toRaffleBindCardSuc] toBindCardSuc={}","跳转绑卡成功页...");
		HttpSession session = getMvcFacade().getHttpSession();
		StringBuilder urlConfig = null;
		String returnUrl = null;
		MemberDto member = (MemberDto) session.getAttribute("member");
		logger.info("[toRaffleBindCardSuc] member={}",member);
		try{
			//显示银行卡信息
			BankCardInfoDto  bankCardInfoDto = null;
			bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(member
					.getMemberNo());
			logger.info("[toRaffleBindCardSuc] bankCardInfoDto={}",bankCardInfoDto);
			if (bankCardInfoDto != null) {
				addModelObject("CARD_NO", StringProcessorUtils
						.desensitizedCardNo(bankCardInfoDto.getCardNo()));
				CardInfoResultDto resultDto=lanmaoDemandfFacade.cardBin(bankCardInfoDto.getCardNo());
				/*if(!"0".equals(resultDto.getCode())){
					setJsonModel("\"FAILED\"");
				}*/
				addModelObject("BANK_NAME", resultDto.getBankName());
				addModelObject("BANK_ID", resultDto.getBankId());
			} 
			logger.debug("[toRaffleBindCardSuc] urlMap={}",urlMap.get(member.getMemberNo()));
			ReturnUrlParamEntity returnParamEntity = (ReturnUrlParamEntity) urlMap.get(member.getMemberNo());
			logger.debug("[toRaffleBindCardSuc] returnParamEntity={}",returnParamEntity);
			//如果没有返回url，则是正常流程，跳转购买理财首页
			if(returnParamEntity == null || null == returnParamEntity.getReturnFlag() || returnParamEntity.getReturnFlag() == ""){
				returnUrl = "popularize/toPopularize";
			}else{
				Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmwx_return_url_config").getValue();
				//通过标志位取返回的url
				urlConfig = new StringBuilder(map.get(returnParamEntity.getReturnFlag())+"?");
				logger.debug("[toRaffleBindCardSuc] urlConfig={}",urlConfig);
//				returnUrl = urlConfig+"?productId="+returnParamEntity.getProductId()+"&amount="+returnParamEntity.getAmount()+"&ret="+returnParamEntity.getRet()+"&expectIncome="+returnParamEntity.getExpectIncome()+"&promoNo="+returnParamEntity.getPromoNo();
				returnUrl = (returnParamEntity.getProductId() == null ? urlConfig : urlConfig.append("productId=").append(returnParamEntity.getProductId())).toString();
				returnUrl = (returnParamEntity.getAmount() == null ? urlConfig : urlConfig.append("&amount=").append(returnParamEntity.getAmount())).toString();
				returnUrl = (returnParamEntity.getExpectIncome() == null ? urlConfig : urlConfig.append("&expectIncome=").append(returnParamEntity.getExpectIncome())).toString();
				returnUrl = (returnParamEntity.getPromoNo() == null ? urlConfig : urlConfig.append("&promoNo=").append(returnParamEntity.getPromoNo())).toString();
				returnUrl = (returnParamEntity.getRet() == null ? urlConfig : urlConfig.append("&ret=").append(returnParamEntity.getRet())).toString();
			}
			logger.debug("[toRaffleBindCardSuc] returnUrl={}",returnUrl);
			addModelObject("returnUrl",returnUrl);
			urlMap.remove(member.getMemberNo());
			return SUCCESS;
		}catch(Exception e){
			logger.error("[toRaffleBindCardSuc] info={},ERROR={}","跳转绑卡成功页异常",e.getMessage());
			return SUCCESS;
		}
		
	}
	
	
	
	/**
	 * 跳转绑卡成功页面  
	 */
	public String toBindCardSuc(@Param("activityCode") String activityCode,@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,@Param("userSessionKey")String userSessionKey){
		logger.info("[toBindCardSuc] toBindCardSuc={}","跳转绑卡成功页...");
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletResponse response = getMvcFacade().getResponse();
		StringBuilder urlConfig = null;
		String returnUrl = null;
		MemberDto member = (MemberDto) session.getAttribute("member");
		logger.info("[toBindCardSuc] member={}",member);
		try{
			//显示银行卡信息
			BankCardInfoDto  bankCardInfoDto = null;
			bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(member
					.getMemberNo());
			logger.info("[toBindCardSuc] memberNo={},bankCardInfoDto={}",member.getMemberNo(),bankCardInfoDto);
			if (bankCardInfoDto != null) {
				addModelObject("CARD_NO", StringProcessorUtils
						.desensitizedCardNo(bankCardInfoDto.getCardNo()));
				CardInfoResultDto resultDto=lanmaoDemandfFacade.cardBin(bankCardInfoDto.getCardNo());
				/*if(!"0".equals(resultDto.getCode())){
					setJsonModel("\"FAILED\"");
				}*/
				addModelObject("BANK_NAME", resultDto.getBankName());
				addModelObject("BANK_ID", resultDto.getBankId());
			} 
			logger.debug("[toBindCardSuc] urlMap={}",urlMap.get(member.getMemberNo()));
			ReturnUrlParamEntity returnParamEntity = (ReturnUrlParamEntity) urlMap.get(member.getMemberNo());
			logger.debug("[toBindCardSuc] returnParamEntity={}",returnParamEntity);
			//如果没有返回url，则是正常流程，跳转购买理财首页
			if(!StringUtils.isEmpty(returnParamEntity.getReturnUrlString())){
				returnUrl = returnParamEntity.getReturnUrlString();
			} else {
				if(returnParamEntity == null || null == returnParamEntity.getReturnFlag() || returnParamEntity.getReturnFlag() == ""){
					returnUrl = "popularize/toPopularize";
				}else{
					Map<String,String> map = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "lmwx_return_url_config").getValue();
					//通过标志位取返回的url
					logger.debug("返回的returnFlag={}",returnParamEntity.getReturnFlag());
					/**
					 * 跳转基金的详情页面
					 */
					String aesMemberNo = SecretUtils.secretData(member.getMemberNo());
					if("toBuyFund".equals(returnParamEntity.getReturnFlag())) {
						urlConfig = new StringBuilder(GetParamUtils.getfundSalesUrl());
						if(null != returnParamEntity.getFundCode()) {
							urlConfig.append(map.get(returnParamEntity.getReturnFlag()).toString()).append("/").append(returnParamEntity.getFundCode());
						} else {
							urlConfig.append("show/fundList");
						}
						logger.debug("返回的fundCode={}",returnParamEntity.getFundCode());
						urlConfig.append("?memberNo="+aesMemberNo);
						returnUrl = urlConfig.toString();
						logger.debug("[toBindCardSuc] returnUrl={}",returnUrl);
						response.sendRedirect(returnUrl);
					} else if("toFundRiskTest".equals(returnParamEntity.getReturnFlag())){
						String fundUrl = GetParamUtils.getfundSalesUrl();
						response.sendRedirect(fundUrl+map.get(returnParamEntity.getReturnFlag()).toString()+"?memberNo="+aesMemberNo);
					} else {
						urlConfig = new StringBuilder(map.get(returnParamEntity.getReturnFlag())+"?");
						logger.debug("[toBindCardSuc] urlConfig={}",urlConfig);
						returnUrl = (returnParamEntity.getProductId() == null ? urlConfig : urlConfig.append("productId=").append(returnParamEntity.getProductId())).toString();
						returnUrl = (returnParamEntity.getAmount() == null ? urlConfig : urlConfig.append("&amount=").append(returnParamEntity.getAmount())).toString();
						returnUrl = (returnParamEntity.getExpectIncome() == null ? urlConfig : urlConfig.append("&expectIncome=").append(returnParamEntity.getExpectIncome())).toString();
						returnUrl = (returnParamEntity.getPromoNo() == null ? urlConfig : urlConfig.append("&promoNo=").append(returnParamEntity.getPromoNo())).toString();
						returnUrl = (returnParamEntity.getRet() == null ? urlConfig : urlConfig.append("&ret=").append(returnParamEntity.getRet())).toString();
						returnUrl = (returnParamEntity.getExpectType() == null ? urlConfig : urlConfig.append("&expectType=").append(returnParamEntity.getExpectType())).toString();
						returnUrl = (returnParamEntity.getOrderNum() == null ? urlConfig : urlConfig.append("&orderNum=").append(returnParamEntity.getOrderNum())).toString();
					}
				}
			}
			logger.info("[toBindCardSuc] returnUrl={}",returnUrl);
			addModelObject("returnUrl",returnUrl);
			urlMap.remove(member.getMemberNo());
			return SUCCESS;
		}catch(Exception e){
			logger.error("[toBindCardSuc] info={},ERROR={}","跳转绑卡成功页异常",e);
			return SUCCESS;
		}
		
	}
	/**
	 * 跳转我的银行卡
	 */
	public String toCard(){
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		BankCardInfoDto bankCardInfoDto = null;
		try {
			bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto
					.getMemberNo());
			if (bankCardInfoDto != null) {
				addModelObject("CARD_NO", StringProcessorUtils
						.desensitizedCardNo(bankCardInfoDto.getCardNo()));
				CardInfoResultDto resultDto=lanmaoDemandfFacade.cardBin(bankCardInfoDto.getCardNo());
				logger.info("[toCard] cardNo={},resultDto={}",bankCardInfoDto.getCardNo(),resultDto);
				addModelObject("BANK_NAME", resultDto.getBankName());
				addModelObject("BANK_ID", resultDto.getBankId());
			} 
		} catch (Exception e) {
			logger.error("[toCard] info={},ERROR={}","跳转我的银行卡异常",e.getMessage());
			return SYSTEM_EXCEPTION;
		}
		return SUCCESS;
	}
	/**
	 * 跳转添加银行卡页面
	 */
/*	public String toAddCard() {
		addModelObject("showTip", "0");//是否显示提示
		ReturnUrlParamEntity param=new ReturnUrlParamEntity();
		param.setReturnFlag("account/card/toCard");
		urlMap.put("returnFlag", param.getReturnFlag());
		return SUCCESS;
	}*/
	/**
	 * 验证交易密码和登录密码是否相同
	 */
	public String verTradePwd(@Param("tradePwd") String tradePwd){
		HttpSession session = getMvcFacade().getHttpSession();
		try{
			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			String memberNo = memberDto.getMemberNo();
			if(StringUtils.isEmpty(tradePwd)){
				setJsonModel(FAILED);
				logger.error("[verTradePwd] ERROR={}","交易密码为空");
				return "json";
			}
			MemberPasswordValidationResultDto memberPasswordValidationResultDto = lanmaoDemandfFacade.queryMemberPwdValidation(memberNo, tradePwd, PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
			logger.info("[verTradePwd] memberDto={},memberPasswordValidationResultDto={}",memberDto,memberPasswordValidationResultDto);
			if(!memberPasswordValidationResultDto.getStatus().equals(PasswordStatusEnum.SUCCESS)){
			//	setJsonModel("FAILED");
				setJsonModel(memberPasswordValidationResultDto.getDescription());
				logger.error("[verTradePwd] ERROR={}",memberPasswordValidationResultDto.getDescription());
				return "json";
			}
		}catch(Exception e){
			logger.error("[verTradePwd] ERROR={},={}","验证交易密码时异常",e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 验证身份证是否在平台已经注册  
	 */
	public String verIdCard(@Param("idCard") String idCard){
		logger.info("[verIdCard] idCard={}",idCard);
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try{
			if(StringUtils.isEmpty(idCard)){
				setJsonModel(FAILED);
				logger.error("[verIdCard] ERROR={}","身份证为空");
				return "json";
			}
			MemberRegResultDto memberRegResultDto = lanmaoDemandfFacade.queryMemberByIdCard(idCard);
			logger.info("[verIdCard] memberRegResultDto={}",memberRegResultDto);
			/*if(memberRegResultDto != null){
			//	if(memberRegResultDto.getStatus().equals(MemberStatusEnum.FAIL)){
					setJsonModel(REGISTERED);
					logger.error("[verIdCard] ERROR={}","身份证已经在平台注册");
					return "json";
			//	}
			}*/
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
							setJsonModel(REGISTERED);
							logger.info("[addBankCard] ERROR={}","身份证已经在平台注册");
							return "json";
						}
						
					}else{
						//用户输入的身份证已经在平台注册过，并且不是自己，不允许绑卡
						setJsonModel(REGISTERED);
						logger.info("[addBankCard] ERROR={}","身份证已经在平台注册");
						return "json";
					}
				}else{
					setJsonModel(FAILED);
					logger.info("[addBankCard] ERROR={}","身份证已经在平台注册");
					return "json";
				}
			} else {
				//身份证存在，说明该用户之前绑过卡。那不允许该用户更换身份证，绑定他人的卡
				if( StringUtils.isNotEmpty(memberDto.getCredentialsNo()) && !idCard.equals(memberDto.getCredentialsNo())) {
					setJsonModel(IDCARDERROR);
					logger.info("[addBankCard] ERROR={}","不能使用别人的身份证，绑定别人的卡");
					return "json";
				}
			}
		}catch(Exception e){
			logger.error("[verIdCard] info={},ERROR={}","验证身份证是否存在，抛出异常",e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 验证绑卡手机短信验证码 (现在不用这个方法)
	 */
	public String verDynamicCode(@Param("tel") String tel,@Param("identifyCode") String identifyCode){
		logger.info("[verDynamicCode] tel={},identifyCode={}",tel,identifyCode);
		try{
			if(GetParamUtils.getIsVerifyCode() && !lMUtilFacde.verfyDynamicPwd(DynamicPwdTypeEnum.BINDBANKCARD, tel, identifyCode)){
				setJsonModel(DYNAMIC_FAILED);
				logger.error("[verDynamicCode] ERROR={}","短信验证码不正确");
				return "json";
			}
		}catch(Exception e){
			logger.error("[verDynamicCode] info={},ERROR={}","验证动态密码时异常",e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 绑卡
	 */
	public String addBankCard(
			@Param("realName") String realName,
			@Param("idCard") String idCard,
			@Param("cardNo") String cardNo,
			@Param("tel") String tel,
			@Param("identifyCode") String identifyCode,
			/*@Param("returnFlag") String returnFlag,
			@Param("productId") Integer productId,
			@Param("amount") String amount,
			@Param("ret") String ret,
			@Param("expectIncome") String expectIncome,
			@Param("promoNo") String promoNo,*/
			@Param("tradePwd") String tradePwd){
		logger.info("[addBankCard] realName={},idCard={},cardNo={},tel={}"
				,realName,idCard,cardNo,tel);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request=getMvcFacade().getRequest();
		try{
			//验证交易密码和登录密码是否一致
			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			String memberNo = memberDto.getMemberNo();
			if(StringUtils.isEmpty(tradePwd)){
				setJsonModel(FAILED);
				logger.info("[addBankCard] ERROR={}","交易密码为空");
				return "json";
			}
			MemberPasswordValidationResultDto memberPasswordValidationResultDto = lanmaoDemandfFacade.queryMemberPwdValidation(memberNo, tradePwd, PasswordValidationTypeEnum.TRAD_COMPARE_LOGIN);
			
			if(!PasswordStatusEnum.SUCCESS.equals(memberPasswordValidationResultDto.getStatus())){
				logger.info("[addBankCard] memberPasswordValidationResultDto={}",memberPasswordValidationResultDto);
			//	setJsonModel("FAILED");
				setJsonModel(memberPasswordValidationResultDto.getDescription());
				return "json";
			}
			//验证身份证是否已经注册
			if(StringUtils.isEmpty(idCard)){
				setJsonModel(FAILED);
				logger.info("[addBankCard] ERROR={}","身份证为空");
				return "json";
			}
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
							setJsonModel(REGISTERED);
							logger.error("[addBankCard] ERROR={}","身份证已经在平台注册");
							return "json";
						}
						
					}else{
						//用户输入的身份证已经在平台注册过，并且不是自己，不允许绑卡
						setJsonModel(REGISTERED);
						logger.error("[addBankCard] ERROR={}","身份证已经在平台注册");
						return "json";
					}
				}else{
					setJsonModel(FAILED);
					logger.error("[addBankCard] ERROR={}","身份证已经在平台注册");
					return "json";
				}
			} else {
				//身份证存在，说明该用户之前绑过卡。那不允许该用户更换身份证，绑定他人的卡
				if( StringUtils.isNotEmpty(memberDto.getCredentialsNo()) && !idCard.equals(memberDto.getCredentialsNo())) {
					setJsonModel(IDCARDERROR);
					logger.error("[addBankCard] ERROR={}","不能使用别人的身份证，绑定别人的卡");
					return "json";
				}
			}
			//验证手机验证码
			/*try {
				if (GetParamUtils.getIsVerifyCode()
						&& !lMUtilFacde.verfyDynamicPwd(
								DynamicPwdTypeEnum.BINDBANKCARD, tel, identifyCode)) {
					logger.info("[addBankCard] ERROR={}","短信验证码输入错误");
					setJsonModel(DYNAMIC_FAILED);
					return "json";
				}
			} catch (Exception e) {
				logger.error("[addBankCard] info={},ERROR={}", "验证手机验证码异常",
						e.getMessage());
				setJsonModel(SYSTEM_EXCEPTION);
				return "json";
			}*/
			VerifyDynamicPwdParamDto param = new VerifyDynamicPwdParamDto();
			param.setMobileNo(tel);
			param.setTemplateNo("bindcard");
			param.setValidateCode(identifyCode);
			VerifyDynamicPwdResultDto verifyDynamicPwdResultDto = noticeFacade.verifyDynamicPwd(param);
			if (!verifyDynamicPwdResultDto.isResultFlag()) {
//					result = BaseResult.getBaseResult(false, verifyDynamicPwdResultDto.getCode(), verifyDynamicPwdResultDto.getErrDes());// code
				setJsonModel(verifyDynamicPwdResultDto.getErrDes());
				return "json";
			}
			
			//  TODO
			CardVerifyAndPasswordParamDto cardVerifyAndPasswordParamDto = new CardVerifyAndPasswordParamDto();
			cardVerifyAndPasswordParamDto.setMemberNo(memberDto.getMemberNo());
			cardVerifyAndPasswordParamDto.setBankAccountName(realName);
			cardVerifyAndPasswordParamDto.setBankAccountNo(cardNo);
			cardVerifyAndPasswordParamDto.setTradingPassword(tradePwd);
			cardVerifyAndPasswordParamDto.setBindMobile(tel);
			cardVerifyAndPasswordParamDto.setIdNumber(idCard);
			cardVerifyAndPasswordParamDto.setValidateCode(identifyCode);
			cardVerifyAndPasswordParamDto.setDevice(DeviceEnum.WX);
			logger.debug("[addBankCard] session={}",session.getAttribute(LmConstants.SESSION_BANCARD_REQUESTNO));
			cardVerifyAndPasswordParamDto.setRequestNo(session.getAttribute(LmConstants.SESSION_BANCARD_REQUESTNO)==null?null:session.getAttribute(LmConstants.SESSION_BANCARD_REQUESTNO).toString());
//			cardVerifyAndPasswordParamDto.setIp(IpUtils.getIpAddr(request));
			//获取用户ip，mac，user_agent
//			String macAddress = HttpRequestUtils.getMacAddress(IpUtils.getIpAddr(request));
//			String userAgent = request.getHeader("User-Agent");
			String macAddress = null;
			String userAgent = null;
			try{
				macAddress = HttpRequestUtils.getMacAddress(IpUtils.getIpAddr(request));
				userAgent = request.getHeader("User-Agent");
			}catch(Exception e){
				logger.error("[addBankCard] info={},error={}","获取mac地址时异常",e);
			}
			logger.debug("[addBankCard] macAddress={},userAgent={}",macAddress,userAgent);
			Object obj = JSONObjectUtils.userAddrToJSONStr(IpUtils.getIpAddr(request),macAddress, userAgent, null);
			if(obj != null){
				cardVerifyAndPasswordParamDto.setIp(obj.toString());
			}
			
			//绑卡
			logger.debug(
					"[addBankCard] cardVerifyAndPasswordParamDto={}",
					cardVerifyAndPasswordParamDto);
			CardVerifyAndPasswordResultDto cardVerifyAndPasswordResultDto =lanmaoTransactionFacade.cardVerifyAndSavePassword(cardVerifyAndPasswordParamDto);
			logger.info("[addBankCard] cardVerifyAndPasswordParamDto={},cardVerifyAndPasswordResultDto={}",cardVerifyAndPasswordParamDto,cardVerifyAndPasswordResultDto);
			//状态码不是0，说明失败
			/*if(!cardVerifyAndPasswordResultDto.getCode().equals("0")){
				setJsonModel(cardVerifyAndPasswordResultDto.getDescription());
				logger.error("[addBankCard] ERROR={}",cardVerifyAndPasswordResultDto.getCode());
				logger.error("[addBankCard] ERROR={}",cardVerifyAndPasswordResultDto.getDescription());
				return "json";
			}*/
			if(!CardVerifyStatusEnum.SUCCESS.equals(cardVerifyAndPasswordResultDto.getStatus())){
				setJsonModel(cardVerifyAndPasswordResultDto.getDescription());
				logger.info("[addBankCard] cardVerifyAndPasswordParamDto={},cardVerifyAndPasswordResultDto={}",cardVerifyAndPasswordParamDto,cardVerifyAndPasswordResultDto);
				
				return "json";
			}
			//刷新session信息
			try {
				MemberDto member = memberManagementFacade.obtainMember(memberDto.getMemberNo());
				session.removeAttribute("member");
				session.setAttribute("member", member);
			} catch (Exception e) {
				logger.error("[addBankCard] info={},ERROR={}", "绑卡时刷新session异常",
						e.getMessage());			
				}
			
			// 绑卡成功推送微信消息
			try {
				String openId = (String) session.getAttribute("openId");
				if(!StringUtils.isEmpty(openId)){
					Map<String,String> modelWx = LmConstants.getBindCardSuccessWxMessageModel();
					ActivityWXSendMessageDTO dataDto = new ActivityWXSendMessageDTO();
					dataDto.setFirst(modelWx.get("first"));
					dataDto.setRemark(modelWx.get("remark"));
					dataDto.setOpenId(openId);
					dataDto.setUrl(modelWx.get("url"));
					dataDto.setKeyword1(StringProcessorUtils.desensitizedCardNo(cardNo));
					dataDto.setKeyword2(StringProcessorUtils.desensitizedMobileNo(tel) );
					activityWXSendMessageFacade.sendWxMessage(ActivityWXSendMessageEnum.BINDCARD_SUCCESS, dataDto);
				}
			} catch (Exception e) {
				logger.error("[addBankCard] info={},ERROR={}", "绑卡成功推送微信消息异常",
						e.getMessage());
			}
			//returnUrl实体类
			/*ReturnUrlParamEntity returnUrlParamEntity = new ReturnUrlParamEntity();
			returnUrlParamEntity.setAmount(amount);
			returnUrlParamEntity.setExpectIncome(expectIncome);
			returnUrlParamEntity.setProductId(productId);
			returnUrlParamEntity.setPromoNo(promoNo);
			returnUrlParamEntity.setRet(ret);
			returnUrlParamEntity.setReturnFlag(returnFlag);
			urlMap.put(memberDto.getMemberNo(),returnUrlParamEntity);*/
		}catch(Exception e){
			logger.error("[addBankCard] info={},ERROR={}","绑卡时异常",e);
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		
		setJsonModel("SUCCESS");
		return "json";
	}
	/**
	 * 判断银行卡是否是信用卡及银行类型
	 */
	public String verifyCardInfo(@Param("cardNo") String cardNo){
		try {
			Map<String,Object> resultMap=new HashMap<String, Object>();
			CardInfoResultDto cirDto=lanmaoDemandfFacade.cardBin(cardNo);
			
			//验证时出现异常   
			if(!cirDto.getStatus().equals("SUCCESS")){
				logger.info("[verifyCardInfo] cardNo={},cirDto={}",cardNo,cirDto);
				setJsonModel("\"FAILED\"");
				return "json";
			}
			if(bankMap.containsKey(cirDto.getBankId())) {
				resultMap.put("bankName", bankMap.get(cirDto.getBankId()));
			} else {
				resultMap.put("bankName", cirDto.getBankName());
			}
			resultMap.put("status", cirDto.getCardType());
			setJsonModel(resultMap);
			return "json";
		} catch (Exception e) {
			logger.error("[verifyCardInfo] info={},ERROR={}","判断银行卡类型",e);
			setJsonModel(SYSTEM_EXCEPTION_JSON);
			return "json";
		}
	
	}
	/**
	 * 判断银行卡是否支持鉴权，无卡
	 * @param cardNo
	 * @return
	 */
	public String verifyCard(@Param("cardNo") String cardNo){
		logger.info("[verifyCard] cardNo={}",cardNo);
		try {
			CardInfoResultDto cirDto=lanmaoDemandfFacade.cardBin(cardNo);
			
			//验证时出现异常   
			if(!cirDto.getStatus().equals("SUCCESS")){
				logger.info("[verifyCard] cardNo={},cirDto={}",cardNo,cirDto);
				setJsonModel("\"FAILED\"");
				return "json";
			}
			PlatChannelResultDto result = platChannelFacade.queryPlatChannelByBankNo(cirDto.getBankId(), RouteTypeEnum.AUTH_BANK_CARD);
			if(!"0".equals(result.getCode())){
				logger.info("[verifyCard] info={},ERROR={}",
						"根据银行编号查询限额接口异常或不支持绑卡", result);
				setJsonModel("\"bankCardOFF\"");
				return "json";
			}
			if(result.getStatus()==PlatChannelStatusEnum.ON){
				PlatChannelResultDto resultNoCard = platChannelFacade.queryPlatChannelByBankNo(cirDto.getBankId(), RouteTypeEnum.NO_CARD);
				if(!"0".equals(resultNoCard.getCode())){
					logger.info("[verifyCard] info={},ERROR={}",
							"根据银行编号查询限额接口异常或不支持无卡", resultNoCard.getDescription());
					setJsonModel("\"noCardOFF\"");	
					return "json";
				}
				if(resultNoCard.getStatus()==PlatChannelStatusEnum.ON){
					setJsonModel("\"noCardON\"");
				}else{
					setJsonModel("\"noCardOFF\"");	
				}
			}else{
				setJsonModel("\"bankCardOFF\"");
			}
			return "json";
		} catch (Exception e) {
			logger.error("[verifyCardInfo] info={},ERROR={}","判断银行卡类型",e);
			setJsonModel(SYSTEM_EXCEPTION_JSON);
			return "json";
		}
	}
	
	/**
	 * 下发绑卡的短信验证码  TODO
	 */
	public String sendBindBankCardCode(@Param("realName") String realName,@Param("idCard") String idCard,@Param("cardNo") String cardNo,@Param("mobileNo") String mobileNo,@Param("tradePwd") String tradePwd) {
		logger.info("[sendBindBankCardCode] realName={},idCard={},crdNo={},mobileNo={}",realName,idCard,cardNo,mobileNo);
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if(CheckUtils.isEmpty(realName) || CheckUtils.isEmpty(idCard) || CheckUtils.isEmpty(cardNo) || CheckUtils.isEmpty(mobileNo) || CheckUtils.isEmpty(tradePwd)){
			logger.info("[sendBindBankCardCode] ERROR={}","有参数为空");
			setJsonModel(FAILED);
			return "json";
		}
		try{
			CardVerifyAndPasswordParamDto cardVerifyAndPasswordParamDto = new CardVerifyAndPasswordParamDto();
			cardVerifyAndPasswordParamDto.setMemberNo(memberDto.getMemberNo());
			cardVerifyAndPasswordParamDto.setBankAccountName(realName);
			cardVerifyAndPasswordParamDto.setBankAccountNo(cardNo);
			cardVerifyAndPasswordParamDto.setBindMobile(mobileNo);
			cardVerifyAndPasswordParamDto.setIdNumber(idCard);
			cardVerifyAndPasswordParamDto.setTradingPassword(tradePwd);
			//绑卡发送短验
			logger.info(
					"[sendBindBankCardCode]cardVerifyAndPasswordParamDto={}",
					cardVerifyAndPasswordParamDto);
			CardVerifyAndPasswordResultDto cardVerifyAndPasswordResultDto = lanmaoTransactionFacade.cardVerifyRequest(cardVerifyAndPasswordParamDto);
			logger.info("[sendBindBankCardCode] cardVerifyAndPasswordResultDto={}",cardVerifyAndPasswordResultDto);
			session.setAttribute(LmConstants.SESSION_BANCARD_REQUESTNO, cardVerifyAndPasswordResultDto.getRequestNo());
			//状态码不是0，说明失败
			/*if(!cardVerifyAndPasswordResultDto.getCode().equals("0")){
				setJsonModel(cardVerifyAndPasswordResultDto.getDescription());
				logger.error("[sendBindBankCardCode] code={},error={}",cardVerifyAndPasswordResultDto.getCode(),cardVerifyAndPasswordResultDto.getDescription());
				return "json";
			}*/
			if(!CardVerifyStatusEnum.SUCCESS.equals(cardVerifyAndPasswordResultDto.getStatus())){
				setJsonModel(cardVerifyAndPasswordResultDto.getDescription());
				logger.error("[sendBindBankCardCode] code={},error={}",cardVerifyAndPasswordResultDto.getCode(),cardVerifyAndPasswordResultDto.getDescription());
				return "json";
			}
		}catch(Exception e){
//			e.printStackTrace();
			logger.error("[sendBindBankCardCode] info={},ERROR={}","下发绑卡的短信验证码异常",e);
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
		
		/*logger.info("[sendBindBankCardCode] memberDto={}",memberDto);
		if (StringUtils.isEmpty(mobileNo)) {
			setJsonModel(FAILED);
			logger.error("[sendBindBankCardCode] ERROR={}","手机号为空");
			return "json";
		}
		try {
			lMUtilFacde.sendDynamicPwd(DynamicPwdTypeEnum.BINDBANKCARD,
					mobileNo, mobileNo);
		} catch (Exception e) {
			logger.error("[sendBindBankCardCode] info={},ERROR={}","下发绑卡的短信验证码异常",e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}*/
		setJsonModel("SUCCESS");
		return "json";
	}
}
