package com.yeepay.g3.app.lmweChat.action.activity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lanmao.consultant.facade.dto.TblLmUser;
import com.lanmao.consultant.facade.enumtype.Enums.UserType;
import com.lanmao.consultant.facade.service.UserFacade;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.service.RequestParamBuilderService;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.HttpRequestUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.app.lmweChat.utils.StringProcessorUtils;
import com.yeepay.g3.facade.activity.dto.ActivityInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserMessageDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserRaffleticketDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageResultDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.activity.enums.UserRaffleticketStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityCouponTradeFacade;
import com.yeepay.g3.facade.activity.facade.ActivityDrawPrizeFacade;
import com.yeepay.g3.facade.activity.facade.ActivityFacade;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProTripSecondDiscountFacade;
import com.yeepay.g3.facade.activity.facade.ActivityPrizeFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserInfoFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserMessageFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserPrizeFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserRaffleTicketFacade;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.facade.lmact.dto.FluxCloudCallBackDto;
import com.yeepay.g3.facade.lmact.service.NewLMACTFluxCloudFacade;
import com.yeepay.g3.facade.lmlc.trust.dto.TrustRankingDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderParamDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultPageDto;
import com.yeepay.g3.facade.lmlc.trust.dto.product.ProductDetailResultDto;
import com.yeepay.g3.facade.lmlc.trust.enumtype.TradeOrderStatusEnum;
import com.yeepay.g3.facade.lmlc.trust.service.FiQueryFacade;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.ChannelDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberRelevanceDto;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRelevanceStatusEnum;
import com.yeepay.g3.facade.lmportal.enumtype.MemberStatusEnum;
import com.yeepay.g3.facade.lmportal.service.ChannelFacade;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import com.yeepay.utils.lock.Lock;
import com.yeepay.utils.lock.impl.RedisLock;

/**
 * @Title: 推广活动
 * @Description: 活动相关管理
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-4-13 下午1:57:33
 * @version 2016-4-13
 */
@Controller
public class ActivityAction extends BaseAction {

	Logger logger = LoggerFactory.getLogger(ActivityAction.class);

	/*
	 * protected ActivityUserRaffleTicketFacade activityUserRaffleTicketFacade =
	 * RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityUserRaffleTicketFacade.class);
	 * protected ActivityUserInfoFacade activityUserInfoFacade =
	 * RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityUserInfoFacade.class); protected
	 * ActivityUserPrizeFacade activityUserPrizeFacade = RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityUserPrizeFacade.class); protected
	 * ActivityPrizeFacade activityPrizeFacade = RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityPrizeFacade.class); protected
	 * ActivityFacade activityFacade = RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityFacade.class); protected
	 * ActivityDrawPrizeFacade activityDrawPrizeFacade = RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityDrawPrizeFacade.class); protected
	 * ActivityUserMessageFacade activityUserMessageFacade =
	 * RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityUserMessageFacade.class); protected
	 * ActivityWXSendMessageFacade activityWXSendMessageFacade =
	 * RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityWXSendMessageFacade.class); protected
	 * ActivityCouponTradeFacade activityCouponTradeFacade =
	 * RemoteServiceFactory
	 * .getService("http://localhost:8080/activity-hessian/hessian",
	 * RemotingProtocol.HESSIAN, ActivityCouponTradeFacade.class);
	 */

	// protected ActivityInvForProTripSecondDiscountFacade invForProTripFacade =
	// RemoteServiceFactory
	// .getService("http://localhost:8004/activity-hessian/hessian",
	// RemotingProtocol.HESSIAN,
	// ActivityInvForProTripSecondDiscountFacade.class);

	// protected ActivityUserMessageFacade activityUserMessageFacade =
	// RemoteServiceFactory
	// .getService("http://localhost:8004/activity-hessian/hessian",
	// RemotingProtocol.HESSIAN, ActivityUserMessageFacade.class);

	protected UserFacade userFacade = RemoteServiceFactory
			.getService(UserFacade.class);
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory
			.getService(MemberManagementFacade.class);
	protected ChannelFacade channelFacade = RemoteServiceFactory
			.getService(ChannelFacade.class);

	protected ActivityUserRaffleTicketFacade activityUserRaffleTicketFacade = RemoteServiceFactory
			.getService(ActivityUserRaffleTicketFacade.class);
	protected ActivityUserInfoFacade activityUserInfoFacade = RemoteServiceFactory
			.getService(ActivityUserInfoFacade.class);
	protected ActivityUserPrizeFacade activityUserPrizeFacade = RemoteServiceFactory
			.getService(ActivityUserPrizeFacade.class);
	protected ActivityPrizeFacade activityPrizeFacade = RemoteServiceFactory
			.getService(ActivityPrizeFacade.class);
	protected ActivityFacade activityFacade = RemoteServiceFactory
			.getService(ActivityFacade.class);
	protected ActivityDrawPrizeFacade activityDrawPrizeFacade = RemoteServiceFactory
			.getService(ActivityDrawPrizeFacade.class);
	protected ActivityUserMessageFacade activityUserMessageFacade = RemoteServiceFactory
			.getService(ActivityUserMessageFacade.class);
	protected ActivityWXSendMessageFacade activityWXSendMessageFacade = RemoteServiceFactory
			.getService(ActivityWXSendMessageFacade.class);
	protected ActivityCouponTradeFacade activityCouponTradeFacade = RemoteServiceFactory
			.getService(ActivityCouponTradeFacade.class);

	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory
			.getService(TrustQueryFacade.class);
	protected NewLMACTFluxCloudFacade fluxCloudFacade = RemoteServiceFactory
			.getService(NewLMACTFluxCloudFacade.class);

	protected FiQueryFacade fiQueryFacade = RemoteServiceFactory
			.getService(FiQueryFacade.class);

	@Autowired
	protected RequestParamBuilderService requestParamBuilderService;

	protected ActivityInvForProTripSecondDiscountFacade invForProTripFacade = RemoteServiceFactory
			.getService(ActivityInvForProTripSecondDiscountFacade.class);

	public String promo(@Param("source") String source) {
		logger.info("[promo] source={}", source);
		HttpServletResponse response = getMvcFacade().getResponse();
		HttpServletRequest request = getMvcFacade().getRequest();
		ChannelDto channelDto = null;
		// 根据渠道号获取渠道信息
		if (source != null) {
			channelDto = channelFacade.queryChannelByChannelNo(source);
		}
		try {
			// 渠道来源类型
			String type = channelDto.getChannelName();
			if (type != null) {
				try {
					type = URLEncoder.encode(type, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					logger.error("[promo] info={}, ERROR={}", "Cookie编码异常", e1);
				}
				// 来源类型存入cookie
				Cookie cookieType = new Cookie(LmConstants.FROM_TYPE, type);
				// 渠道编号存入cookie
				Cookie cookieSource = new Cookie(LmConstants.FROM_NO, source);
				cookieType.setMaxAge(24 * 60 * 60);
				cookieType.setPath("/");
				cookieSource.setMaxAge(24 * 60 * 60);
				cookieSource.setPath("/");
				try {
					response.addCookie(cookieType);
					response.addCookie(cookieSource);
				} catch (Exception e) {
					logger.error("[promo] info={}, ERROR={}", "Cookie添加异常", e);
				}
			}

			// 要跳转的页面
			String url = channelDto.getSkipPageUrl();
			logger.info("[promo] url={}", url);
			if (url == null) {
				return ERROR;
			}
			try {
				logger.info("[promo] url={}", url);
				addModelObject("url", url);
				/*
				 * RequestDispatcher dispatcher = request.getRequestDispatcher(
				 * "/WEB-INF/pages/activity/toPopularize.jsp");
				 * dispatcher.forward(request, response);
				 */
				// response.sendRedirect("/lmweChat"+url);

			} catch (Exception e) {
				logger.error("[promo] info={},ERROR={}", "跳转页面时出错",
						e.getMessage());
				return SYSTEM_EXCEPTION;
			}
		} catch (NullPointerException e) {
			logger.error("[promo] ERROR={},source={}", "该平台用户编号不存在", source);
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 注册活动页面跳转,根据不同的渠道号
	 */
	public String toChannelRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toRegister] toRegister={}", "去注册...");
		logger.info("[toRegister] code={},returnFlag={},productId={}", code,
				returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */

		// 获取cookie中渠道号
		String source = null;
		try {
			// 获取cookie中渠道号
			String type = null;

			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[register] srcNo={}", source);
				}
			}
			logger.info("[toRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);

		return source;
	}

	/**
	 * 注册活动页面跳转
	 */
	public String toActivityRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toActivityRegister] toRegister={}", "去注册...");
		logger.info("[toActivityRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		try {
			// 获取cookie中渠道号
			String type = null;
			String source = null;
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toActivityRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toActivityRegister] srcNo={}", source);
				}
			}
			logger.info("[toActivityRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toActivityRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 天翼的注册送流量
	 * 
	 * @time 2016-5-24
	 */
	public String toTYiRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toTYiRegister] toRegister={}", "去注册...");
		logger.info("[toTYiRegister] code={},returnFlag={},productId={}", code,
				returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {

			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toTYiRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toTYiRegister] srcNo={}", source);
				}
			}
			logger.info("[toTYiRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toTYiRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	// 根据渠道号获取返回的returnFlag
	private String getReturnFlagBySource(String source) {
		Map<String, Object> map = (Map<String, Object>) ConfigurationUtils
				.getConfigParam("config_type_text_resources",
						"lmwx_returnFlag_by_source").getValue();
		if (null != map && null != map.get(source)) {
			String returnFlg = (String) map.get(source);
			return returnFlg;
		} else {
			return null;
		}
	}

	/**
	 * 易钱包的注册送流量
	 * 
	 * @time 2016-5-24
	 */
	public String toYQBaoRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toYQBaoRegister] toRegister={}", "去注册...");
		logger.info("[toYQBaoRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toYQBaoRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toYQBaoRegister] srcNo={}", source);
				}
			}
			logger.info("[toYQBaoRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 洗爱车的注册送流量
	 * 
	 * @time 2016-5-24
	 */
	public String toXACheRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toXACheRegister] toRegister={}", "去注册...");
		logger.info("[toXACheRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[register] srcNo={}", source);
				}
			}
			logger.info("[toXACheRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toXACheRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 多啦宝的注册送流量
	 * 
	 * @time 2016-5-24
	 */
	public String toDLBaoRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toDLBaoRegister] toRegister={}", "去注册...");
		logger.info("[toDLBaoRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toDLBaoRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toDLBaoRegister] srcNo={}", source);
				}
			}
			logger.info("[toDLBaoRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 懒猫金服的注册送流量
	 * 
	 * @time 2016-5-24
	 */
	public String toLMJFuRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toLMJFuRegister] toRegister={}", "去注册...");
		logger.info("[toLMJFuRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toLMJFuRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toLMJFuRegister] srcNo={}", source);
				}
			}
			logger.info("[toLMJFuRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toLMJFuRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 不同渠道不同活动注册页面 cookie取渠道号
	 * 
	 * @time 2016-5-24
	 */
	public String toRegisterByScrNo() {
		logger.info("[toRegisterByScrNo] toRegisterByScrNo={}", "去注册...");
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取cookie中渠道号
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toRegisterByScrNo] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toRegisterByScrNo] srcNo={}", source);
				}
			}
			logger.info("[toRegisterByScrNo] srcNo={}", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toRegisterByScrNo] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		// 根据渠道号获取返回的returnFlag 注册绑卡成功跳转页面编号
		String returnFlag = null;
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		// 根据渠道号查询跳转页面name
		Map<String, Object> map = (Map<String, Object>) ConfigurationUtils
				.getConfigParam("config_type_text_resources",
						"lmwx_source_code_return_name").getValue();
		String returnName = null;
		// String
		// returnName="../WEB-INF/pages/account/our_ten_year_movie_regist_suc.jsp";
		if (map != null) {
			returnName = (String) map.get(source);
			// 跳转注册页面
			try {
				// response.sendRedirect(returnName);
				request.setAttribute("source", source);
				request.setAttribute("returnFlag", returnFlag);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(returnName);
				dispatcher.forward(request, response);
			} catch (Exception e) {
				logger.error("[toRegisterByScrNo] 跳转注册页面异常e={}", e);
			}

		}
		return SUCCESS;
	}

	/**
	 * 被邀请人点击链接跳转注册页
	 */
	public String toInvitedRegister(
			@Param("recommendMemberNo") String recommendMemberNo,
			@Param("bizType") ShareBizTypeEnum bizType,
			@Param("srcNo") String srcNo,
			@Param("activityCode") String activityCode) {
		HttpSession session = getMvcFacade().getHttpSession();
		// 中奖列表
		try {
			List<ActivityUserPrizeDTO> prizeDtoNewList = new ArrayList<ActivityUserPrizeDTO>();
			// 查询中奖的最新的5条记录
			prizeDtoNewList = activityUserPrizeFacade.selectUserPrizeNewList(5);

			addModelObject("prizeDtoNewList", prizeDtoNewList);
		} catch (Exception e) {
			logger.info("最新的5条抽奖记录查询有问题");
		}
		// 存储推荐人的会员编号，推荐的业务类型，来源编号
		if (!StringUtils.isEmpty(recommendMemberNo)
				&& !"null".equals(recommendMemberNo)) {
			session.setAttribute("_recommendMemberNo", recommendMemberNo);
			session.setAttribute("_bizType", bizType);
			session.setAttribute("_srcNo", srcNo);
			MemberDto recommendMemberDto = memberManagementFacade
					.obtainMember(recommendMemberNo);
			// 查询推荐人的昵称与头像等信息
			if (recommendMemberDto != null) {
				ActivityUserInfoDTO activityUserInfo = activityUserInfoFacade
						.selectUserInfoByMemberNo(recommendMemberNo);
				addModelObject("activityUserInfo", activityUserInfo);
			}
		}

		// 根据activityCode跳转不同的注册成功
		addModelObject("activityCode", activityCode);

		return SUCCESS;
	}

	/**
	 * 用户跳转抽奖大转盘页面
	 */
	public String toRaffleActivity(@Param("actionCode") String actionCode,
			@Param("activityCode") String activityCode,
			@Param("messageId") Long messageId, @Param("version") Long version) {
		logger.info(
				"[toRaffleActivity] actionCode={},activityCode={},messageId={},version={}",
				actionCode, activityCode, messageId, version);
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		// String platform = (String) session.getAttribute("platform");
		// 未登录,app的
		/*
		 * if(memberDto == null || "APP".equals(platform)){
		 * 
		 * }
		 */
		// 消息已读
		if (messageId != null && messageId != 0L) {
			try {
				ActivityUserMessageDTO activityUserMessageDto = new ActivityUserMessageDTO();
				activityUserMessageDto.setId(messageId);
				activityUserMessageDto.setVersion(version);
				activityUserMessageDto
						.setReadStatus(UserMessageReadStatusEnum.READED);
				activityUserMessageFacade
						.updateReadStatus(activityUserMessageDto);
			} catch (Exception e) {
				logger.info("消息更改已读状态异常");
			}
		}
		// 中奖列表
		try {
			List<ActivityUserPrizeDTO> prizeDtoNewList = new ArrayList<ActivityUserPrizeDTO>();
			// 查询中奖的最新的5条记录
			prizeDtoNewList = activityUserPrizeFacade.selectUserPrizeNewList(5);

			addModelObject("prizeDtoNewList", prizeDtoNewList);
		} catch (Exception e) {
			logger.info("最新的5条抽奖记录查询有问题");
		}
		// 已经使用的抽奖次数
		List<ActivityUserRaffleticketDTO> usedTicketList = new ArrayList<ActivityUserRaffleticketDTO>();
		// 未使用的抽奖次数
		List<ActivityUserRaffleticketDTO> canuseTicketList = new ArrayList<ActivityUserRaffleticketDTO>();
		// 用的幸运值
		Integer luckyScore = 0;
		if (memberDto != null) {
			ActivityUserRaffleticketDTO activityUserRaffleticketDto = new ActivityUserRaffleticketDTO();
			activityUserRaffleticketDto.setMemberNo(memberDto.getMemberNo());
			// 用户已使用的抽奖券
			activityUserRaffleticketDto
					.setStatus(UserRaffleticketStatusEnum.USED);// 已使用
			usedTicketList = activityUserRaffleTicketFacade
					.selectListByParams(activityUserRaffleticketDto);
			// 用户未使用的抽奖券
			activityUserRaffleticketDto
					.setStatus(UserRaffleticketStatusEnum.UN_USE);// 未使用
			canuseTicketList = activityUserRaffleTicketFacade
					.selectListByParams(activityUserRaffleticketDto);

			// 用户的幸运值
			ActivityUserInfoDTO activityUserInfo = activityUserInfoFacade
					.selectUserInfoByMemberNo(memberDto.getMemberNo());
			luckyScore = activityUserInfo.getTotalScore();
			if (luckyScore > 88) {
				luckyScore = 88;
			}
			// 查询用户的类型
			Boolean commonUser = queryUserType(memberDto);
			addModelObject("commonUser", commonUser);
			addModelObject("activityUserInfo", activityUserInfo);
		}
		addModelObject("actionCode", actionCode);
		addModelObject("activityCode", activityCode);

		addModelObject("usedTicketCount", usedTicketList.size());
		addModelObject("canuseTicketCount", canuseTicketList.size());
		addModelObject("luckyScore", luckyScore);

		// 判断绑卡是否送抽奖券，根据时间
		Boolean isFlag = false;
		Map<String, Object> map = (Map<String, Object>) ConfigurationUtils
				.getConfigParam("config_type_sys",
						"LMACTIVITY_BINDCARD_INVEST_RAFFLE").getValue();
		if (null != map && null != map.get("everyBindCardTimeEnd")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date bindCardEndTime = sdf.parse(map
						.get("everyBindCardTimeEnd").toString());
				Date nowTime = new Date();
				if (nowTime.before(bindCardEndTime)) {
					isFlag = true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		addModelObject("isFlag", isFlag);

		return SUCCESS;
	}

	/**
	 * 用户跳转抽奖记录列表
	 */
	public String toRafflePrizeList(@Param("messageId") Long messageId,
			@Param("version") Long version) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		// 消息已读
		if (messageId != null && messageId != 0L) {
			try {
				ActivityUserMessageDTO activityUserMessageDto = new ActivityUserMessageDTO();
				activityUserMessageDto.setId(messageId);
				activityUserMessageDto.setVersion(version);
				activityUserMessageDto
						.setReadStatus(UserMessageReadStatusEnum.READED);
				activityUserMessageFacade
						.updateReadStatus(activityUserMessageDto);
			} catch (Exception e) {
				logger.info("消息更改已读状态异常");
			}
		}
		// 中奖列表
		List<ActivityUserPrizeDTO> prizeDtoList = new ArrayList<ActivityUserPrizeDTO>();

		if (memberDto != null) {
			prizeDtoList = activityUserPrizeFacade
					.selectUserPrizeByMemberNo(memberDto.getMemberNo());
		}
		addModelObject("prizeDtoList", prizeDtoList);

		return SUCCESS;
	}

	/**
	 * 查询用户的类型
	 * 
	 * @return true普通用户 false不是普通用户
	 */
	private boolean queryUserType(MemberDto memberDto) {
		Boolean commonUser = false;
		try {
			if (null != memberDto) {
				TblLmUser userBasic = userFacade.getUserBasic(memberDto
						.getMemberNo());
				// -1 userBasic null 成为推荐人
				// 0 - 未知 - 成为推荐人
				// 1 - 普通用户 - 成为推荐人
				// 2 - 推荐人 - 我的推荐
				// 3 - 顾问 - 我的客户
				// 4 - 销售 - 我的顾问
				if (userBasic != null) {
					Short userType = userBasic.getUserType();
					if (UserType.NONE.getValue() == userType
							|| UserType.USER.getValue() == userType) {
						commonUser = true;
					}
				} else {
					commonUser = true;
				}
			}
		} catch (Exception e) {
			logger.info("查询用户类型出错", e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
		}
		return commonUser;
	}

	/**
	 * 去邀请朋友页面（首页进入，账户中心进入）
	 * 
	 * @return
	 */
	public String toInviteFriend(@Param("code") String code) {

		logger.info("微信的code：code={}", code);
		HttpServletResponse response = getMvcFacade().getResponse();
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		// session.setAttribute("platform", "APP");
		String platform = (String) session.getAttribute("platform");
		logger.info("[toInviteFriend] platform={}", platform);
		// 未登录,app的
		addModelObject("platform", platform);
		String openId = null;
		if (!StringUtils.isEmpty(code)) {
			try {
				String result = HttpRequestUtils.sendHttpRequest(
						WX_OAUTH_REUQEST_URL, WX_OAUTH_REUQEST_METHOD,
						requestParamBuilderService
								.buildGetWebAccessTokenParam(code));
				if (!StringUtils.isEmpty(result)) {
					JSONObject resultJson = JSONObject.fromObject(result);
					if (resultJson.get("openid") != null) {
						openId = resultJson.get("openid").toString();
						Cookie cookie = new Cookie(MLANMAO_OPEN_ID, openId);
						cookie.setMaxAge(-1);
						response.addCookie(cookie);
					}
				}
				session.setAttribute("openId", openId);
				logger.info("通过code获取的openid={}", openId);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("通过code获取的openid异常code={},openid={}", code, openId);
			}

		} else {
			openId = (String) session.getAttribute("openId");
			logger.info("通过session获取的openid={}", openId);
		}

		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		// 未使用的抽奖次数
		List<ActivityUserRaffleticketDTO> canuseTicketList = new ArrayList<ActivityUserRaffleticketDTO>();
		// 中奖列表
		try {
			List<ActivityUserPrizeDTO> prizeDtoNewList = new ArrayList<ActivityUserPrizeDTO>();
			// 查询中奖的最新的5条记录
			prizeDtoNewList = activityUserPrizeFacade.selectUserPrizeNewList(5);

			addModelObject("prizeDtoNewList", prizeDtoNewList);
		} catch (Exception e) {
			logger.info("最新的5条抽奖记录查询有问题");
		}
		if (null != memberDto) {
			// 说明用户已经登录
			addModelObject("status", "login");
			// 查询用户的类型
			Boolean commonUser = queryUserType(memberDto);
			addModelObject("commonUser", commonUser);

			ActivityUserInfoDTO userInfo = activityUserInfoFacade
					.selectUserInfoByMemberNo(memberDto.getMemberNo());
			ActivityUserRaffleticketDTO activityUserRaffleticketDto = new ActivityUserRaffleticketDTO();
			activityUserRaffleticketDto.setMemberNo(memberDto.getMemberNo());
			// 用户未使用的抽奖券
			activityUserRaffleticketDto
					.setStatus(UserRaffleticketStatusEnum.UN_USE);// 未使用
			canuseTicketList = activityUserRaffleTicketFacade
					.selectListByParams(activityUserRaffleticketDto);
			addModelObject("canuseTicketCount", canuseTicketList.size());
			// openid不为空则查询用户的信息，放入用户信息
			if (openId != null) {
				activityUserInfoFacade.modifyUserInfoByWX(userInfo, openId);
				userInfo = activityUserInfoFacade
						.selectUserInfoByMemberNo(memberDto.getMemberNo());
			}
			addModelObject("userInfo", userInfo);
		}

		Map<String, Object> map = (Map<String, Object>) ConfigurationUtils
				.getConfigParam("config_type_sys",
						"LMACTIVITY_BINDCARD_INVEST_RAFFLE").getValue();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != map && null != map.get("everyBindCardTimeEnd")) {
			try {
				Date bindCardEndTime = sdf.parse(map
						.get("everyBindCardTimeEnd").toString());
				Date nowTime = new Date();
				if (nowTime.before(bindCardEndTime)) {
					return "bindRaffle";
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 活动专区的活动的列表
	 * 
	 * @return
	 */
	public String toActivityList() {
		List<ActivityInfoDTO> activityList = null;
		try {
			activityList = activityFacade.selectEffActivityList();
		} catch (Exception e) {
			logger.error("[toActivityList] error={}", e);
		}
		addModelObject("activityList", activityList);
		return SUCCESS;

	}

	/**
	 * 查看活动图片
	 */
	public String lookPicture(@Param("id") long id) {
		HttpServletResponse response = getMvcFacade().getResponse();
		logger.info("[lookActivityImg]  参数： id={}", id);
		// 查询活动详情
		ActivityInfoDTO activityInfoDto = activityFacade.queryActivityById(id);
		logger.info("[lookActivityImg] activityInfoDto={}", activityInfoDto);
		try {
			byte[] data = activityInfoDto.getImg();
			OutputStream out = response.getOutputStream();
			byte[] buff = new byte[(int) data.length];
			int i = 0;
			InputStream in = new ByteArrayInputStream(data);
			while ((i = in.read(buff)) != -1) {
				out.write(buff);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return SUCCESS;

	}

	/**
	 * 消息公告列表
	 * 
	 * @return
	 */
	public String toNewsList() {

		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		List<ActivityUserMessageDTO> userMessageList = activityUserMessageFacade
				.selectByMemberNo(memberDto.getMemberNo());
		addModelObject("userMessageList", userMessageList);
		return SUCCESS;

	}

	/**
	 * 用户抽奖
	 * 
	 * @param actionCode
	 * @param activityCode
	 * @return
	 */
	public String toRafflePrize(@Param("actionCode") String actionCode) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		ActivityPrizeDTO prizeDTO = new ActivityPrizeDTO();

		String activityCode = activityFacade
				.queryActivityCodeByActionCode(actionCode);
		logger.info("根据事件编码查询活动的编码：actionCode={},activityCode={}", actionCode,
				activityCode);
		try {
			prizeDTO = activityDrawPrizeFacade.updateUserPrize(
					memberDto.getMemberNo(), actionCode, activityCode);
			logger.info("获取的奖品的等级prizeDTO.prizeLevel={}",
					prizeDTO.getPrizeLevel());
			if ("0".equals(prizeDTO.getCode())) {// 抽奖成功
				if (!"谢谢参与".equals(prizeDTO.getPrizeName()))// 谢谢参与 不推送消息
				{
					// 发送微信推送消息
					if (null != session.getAttribute("openId")) {
						Map<String, String> modelInfo = LmConstants
								.getActivityDrawPrizeWxMessageModel();
						ActivityWXSendMessageDTO dataDto = new ActivityWXSendMessageDTO();
						dataDto.setOpenId(session.getAttribute("openId")
								.toString());
						dataDto.setUrl(modelInfo.get("url"));
						dataDto.setFirst(modelInfo.get("first"));
						dataDto.setRemark(modelInfo.get("remark"));
						dataDto.setKeyword1(modelInfo.get("activityName"));
						dataDto.setKeyword2(prizeDTO.getPrizeName());
						ActivityWXSendMessageResultDTO resultMessage = activityWXSendMessageFacade
								.sendWxMessage(
										ActivityWXSendMessageEnum.GET_PRIZE,
										dataDto);
						if (resultMessage.getCode() != 0) {
							logger.debug("[toRafflePrize] 推送微信消息失败，info={}",
									resultMessage.getMessage());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("[toRafflePrize] error={}", e);
			e.printStackTrace();
		}
		// 已经使用的抽奖次数
		List<ActivityUserRaffleticketDTO> usedTicketList = new ArrayList<ActivityUserRaffleticketDTO>();
		// 未使用的抽奖次数
		List<ActivityUserRaffleticketDTO> canuseTicketList = new ArrayList<ActivityUserRaffleticketDTO>();
		// 用的幸运值
		Integer luckyScore = 0;
		ActivityUserRaffleticketDTO activityUserRaffleticketDto = new ActivityUserRaffleticketDTO();
		activityUserRaffleticketDto.setMemberNo(memberDto.getMemberNo());
		// 用户已使用的抽奖券
		activityUserRaffleticketDto.setStatus(UserRaffleticketStatusEnum.USED);// 已使用
		usedTicketList = activityUserRaffleTicketFacade
				.selectListByParams(activityUserRaffleticketDto);
		// 用户未使用的抽奖券
		activityUserRaffleticketDto
				.setStatus(UserRaffleticketStatusEnum.UN_USE);// 未使用
		canuseTicketList = activityUserRaffleTicketFacade
				.selectListByParams(activityUserRaffleticketDto);

		// 用户的幸运值
		ActivityUserInfoDTO activityUserInfo = activityUserInfoFacade
				.selectUserInfoByMemberNo(memberDto.getMemberNo());
		luckyScore = activityUserInfo.getTotalScore();
		if (luckyScore > 88) {
			luckyScore = 88;
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("prizeDTO", prizeDTO);
		data.put("usedTicketCount", usedTicketList.size());
		data.put("canuseTicketCount", canuseTicketList.size());
		data.put("luckyScore", luckyScore);

		setJsonModel(data);

		return "json";
	}

	/**
	 * 跳转绑卡成功后的页面
	 * 
	 * @return
	 */
	public String toInvestOrRaffle() {
		return SUCCESS;

	}

	/**
	 * 跳转天翼绑卡成功后的页面
	 * 
	 * @return
	 */
	public String toTYiInvestOrRaffle() {
		return SUCCESS;

	}

	/**
	 * 根据openId快速免登录，跳转活动页面
	 * 
	 * @param code
	 * @param response
	 * @return
	 */
	public String fastToInviteFriend(@Param("code") String code) {
		logger.info("[fastToPopularize] code={}", code);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 判断cookie是否存在 存在说明已经与公众号交互过，将其改为NO
		String openId = null;
		try {
			if (!StringUtils.isEmpty(code)) {
				String result = HttpRequestUtils.sendHttpRequest(
						WX_OAUTH_REUQEST_URL, WX_OAUTH_REUQEST_METHOD,
						requestParamBuilderService
								.buildGetWebAccessTokenParam(code));
				logger.info("[fastToPopularize] result={}", result);
				if (!StringUtils.isEmpty(result)) {
					JSONObject resultJson = JSONObject.fromObject(result);
					if (resultJson.get("openid") != null) {
						openId = resultJson.get("openid").toString();
						logger.info("[fastToPopularize] openId={}", openId);
						session.setAttribute("openId", openId);
						Cookie cookie = new Cookie(MLANMAO_OPEN_ID, openId);
						cookie.setMaxAge(-1);
						response.addCookie(cookie);
						MemberRelevanceDto memberRelevanceDto = memberManagementFacade
								.obtainMemberRelevance(openId);
						if (memberRelevanceDto != null
								&& (MemberRelevanceStatusEnum.ON)
										.equals(memberRelevanceDto.getStatus())
								&& memberRelevanceDto.getMemberNo() != null) {
							MemberDto memberDto = memberManagementFacade
									.obtainMember(memberRelevanceDto
											.getMemberNo());
							if (memberDto != null
									&& (memberDto.getStatus().equals(
											MemberStatusEnum.ACTIVATED) || memberDto
											.getStatus().equals(
													MemberStatusEnum.AVAILABLY))) {
								session.setAttribute("member", memberDto);
								session.setAttribute("recommendMemberNo",
										memberDto.getMemberNo());
								// 查询是否是新手
								TrustOrderParamDto trustOrderParamDto = new TrustOrderParamDto();
								trustOrderParamDto.setMemberNo(memberDto
										.getMemberNo());
								trustOrderParamDto
										.setStatus(TradeOrderStatusEnum.SUCCESS);
								TrustOrderResultPageDto trustOrderResultPageDTO = trustQueryFacade
										.queryTrustOrder(trustOrderParamDto);
								logger.info(
										"[login] trustOrderResultPageDTO={}",
										trustOrderResultPageDTO);
								List<TrustOrderResultDto> list = trustOrderResultPageDTO
										.getTrustOrderResult();
								logger.info("[login] list={}", list);
								if (list.size() == 0) {
									session.setAttribute("newcomerFlag", "YES");
								} else {
									session.setAttribute("newcomerFlag", "NO");
								}
							}
						}
					}

				}
			}
		} catch (Exception e) {
			logger.info("[fastToPopularize] info={},ERROR={}", "免登录时发生异常",
					e.getMessage());
		}
		try {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("toInviteFriend");
			dispatcher.forward(request, response);
			// response.sendRedirect("toInviteFriend");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * banner跳转抽奖页面的中间页
	 * 
	 * @param in
	 *            -
	 * @return
	 */
	public String toRedirectInviteFriend() {
		return SUCCESS;

	}

	/**
	 * banner跳转新人专享页面的中间页
	 * 
	 * @param in
	 *            -
	 * @return
	 */
	public String toNewMan(@Param("productId") String productId) {
		logger.info("[toNewMan] productId={}", productId);
		addModelObject("productId", productId);
		return SUCCESS;

	}

	/**
	 * 流量云回调
	 */
	public String toFluxCloudCallBack(@Param("partnerId") String partnerId,
			@Param("tradeId") String tradeId,
			@Param("tradeCode") String tradeCode,
			@Param("partnerOrderId") String partnerOrderId,
			@Param("unixTime") String unixTime, @Param("nonce") String nonce,
			@Param("sign") String sign) {
		logger.info("[toNewMan] productId={}");
		FluxCloudCallBackDto fluxCloudCallBackDto = new FluxCloudCallBackDto();
		fluxCloudCallBackDto.setNonce(nonce);
		fluxCloudCallBackDto.setPartnerId(partnerId);
		fluxCloudCallBackDto.setPartnerOrderId(partnerOrderId);
		fluxCloudCallBackDto.setSign(sign);
		fluxCloudCallBackDto.setTradeCode(tradeCode);
		fluxCloudCallBackDto.setTradeId(tradeId);
		fluxCloudCallBackDto.setUnixTime(unixTime);
		String result = fluxCloudFacade
				.updateFluxCloudByCallBack(fluxCloudCallBackDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if ("success".equals(result)) {
			resultMap.put("code", 0);
			resultMap.put("message", "success");
			setJsonModel(resultMap);
		} else {
			resultMap.put("code", 2);
			resultMap.put("message", "fail");
			setJsonModel(resultMap);
		}
		return "json";

	}

	/**
	 * 流量来移动
	 * 
	 * @param code
	 * @param returnFlag
	 * @param productId
	 * @return
	 */
	public String toFCCmccRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toFCCmccRegister] toRegister={}", "去注册...");
		logger.info("[toFCCmccRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toFCCmccRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toFCCmccRegister] srcNo={}", source);
				}
			}
			logger.info("[toFCCmccRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toFCCmccRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 流量来了联通
	 * 
	 * @param code
	 * @param returnFlag
	 * @param productId
	 * @return
	 */
	public String toFCCuccRegister(@Param("code") String code,
			@Param("returnFlag") String returnFlag,
			@Param("productId") String productId) {
		logger.info("[toFCCuccRegister] toRegister={}", "去注册...");
		logger.info("[toFCCuccRegister] code={},returnFlag={},productId={}",
				code, returnFlag, productId);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 获取前一个页面的url
		/*
		 * String returnUrl = HttpRequestUtils.buildReturnUrl(request);
		 * logger.info("[toRegister] returnUrl={}",returnUrl);
		 * addModelObject("returnUrl", returnUrl);
		 */
		// 获取cookie中渠道号
		String type = null;
		String source = null;
		try {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				logger.info("[toFCCuccRegister] cookie={}", cookie.getName());
				if (cookie.getName().equals(LmConstants.FROM_TYPE)) {
					type = URLDecoder.decode(cookie.getValue(), "utf-8");
				} else if (cookie.getName().equals(LmConstants.FROM_NO)) {
					source = cookie.getValue();
					logger.info("[toFCCuccRegister] srcNo={}", source);
				}
			}
			logger.info("[toFCCuccRegister] srcNo={}", source);
			addModelObject("srcNo", source);
			session.setAttribute("srcNo", source);
		} catch (Exception e) {
			logger.info("[toFCCuccRegister] info={},ERROR={}", "注册时异常",
					e.getMessage());
		}
		addModelObject("source", source);
		// 根据渠道号获取返回的returnFlag
		if (null != source) {
			returnFlag = getReturnFlagBySource(source);
		}
		addModelObject("returnFlag", returnFlag);
		addModelObject("productId", productId);
		return SUCCESS;
	}

	/**
	 * 跳转奥运活动页
	 * 
	 * @param in
	 *            -
	 * @return
	 */
	public String toOlympicActivity() {
		logger.info("[toOlympicActivity] info={}", "跳转活动页");
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletResponse response = getMvcFacade().getResponse();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		logger.info("[toOlympicActivity] memberDto={}", memberDto);
		// 获取统一配置
		Map<String, String> map = (Map<String, String>) GetParamUtils
				.readOlympic();
		logger.info("[toOlympicActivity] map={}", map);
		addModelObject("olympicMap", map);
		// flag为false时，表示是14点前显示的是敬请期待
		boolean flag = false;
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		// flag为true时，表示是14点后，显示的是已抢完
		if (hour >= 14) {
			flag = true;
		}
		logger.info("[toOlympicActivity] flag={}", flag);
		addModelObject("flag", flag);
		ProductDetailResultDto productDetailResultDto = fiQueryFacade
				.obtainProductDetail("FIXED", "SHORT", "SHORT");
		logger.info("[toOlympicActivity] productDetailResultDto={}",
				productDetailResultDto);
		if (productDetailResultDto != null) {
			BigDecimal rate = productDetailResultDto.getRate();
			BigDecimal baseRate = new BigDecimal(5);
			BigDecimal increaseRate = rate.subtract(baseRate);
			logger.info("[toOlympicActivity] increaseRate={}", increaseRate);
			addModelObject("increaseRate", increaseRate);
		}
		// productDetailResultDto = null;
		addModelObject("productDetailResultDto", productDetailResultDto);
		return SUCCESS;
	}

	/**
	 * 助威奥运拿奖活动
	 * 
	 * @param in
	 *            -
	 * @return
	 */
	public String toCheerOlympic() {
		logger.info("[toCheerOlympic] info={}", "跳转助威奥运活动页");
		// 查询活动期间总投资金额,元
		BigDecimal sumAmount = fiQueryFacade.queryTradeOrderSumAmount();
		logger.info("[toCheerOlympic] sumAmount={}", sumAmount);
		// 进度条比例
		BigDecimal proportion = sumAmount.divide(new BigDecimal(8880000), 2,
				BigDecimal.ROUND_HALF_UP);
		logger.info("[toCheerOlympic] proportion={}", proportion);
		BigDecimal percent = proportion.multiply(new BigDecimal(100));
		logger.info("[toCheerOlympic] percent={}", percent);
		// 查询列表
		List<TrustRankingDto> list = fiQueryFacade.queryTrustRankingList();
		// 总交易额，万元
		addModelObject("sumAmount", sumAmount.divide(new BigDecimal(10000)));
		addModelObject("percent", percent);
		addModelObject("list", list);

		return SUCCESS;
	}

	/**
	 * 跳转查看榜单页面
	 * 
	 * @param in
	 *            -
	 * @return 读取到的固定长度数据
	 */
	public String toLookAwardList() {
		logger.info("[toLookAwardList] info={}", "跳转查看获奖榜单");
		// 查询活动期间投资榜单20条
		List<TrustRankingDto> list = fiQueryFacade.queryTrustRankingList();
		logger.info("[toLookAwardList] list={}", list);
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				TrustRankingDto trustRankingDto = list.get(i);
				trustRankingDto.setPhoneNo(StringProcessorUtils
						.desensitizedMobileNo(trustRankingDto.getPhoneNo()));
				// list.set(i, trustRankingDto);
			}
			logger.info("[toLookAwardList] list={}", list);

		}
		addModelObject("list", list);
		return SUCCESS;
	}

	/**
	 * 免费去旅游送京东卡活动
	 * 
	 * @param in
	 *            -
	 * @return 读取到的固定长度数据
	 */
	public String toTourHalfActivity() {
		logger.info("[toTourHalfActivity] info={}", "旅游第二份半价");

		// 统一配置取总金额
		String total_count = GetParamUtils.readTotalCount();
		// 查询发送京东卡的名单
		List<ActivityUserPrizeDTO> activityUserPrizeList = invForProTripFacade
				.queryTourSendJDCard();
		logger.info("[toTourHalfActivity] activityUserPrizeList={}",
				activityUserPrizeList);
		// 剩余数量
		int leftCount = 0;
		if (activityUserPrizeList != null) {
			addModelObject("count", activityUserPrizeList.size());
			/*
			 * if(activityUserPrizeList.size() != 0){ for(int i = 0; i <
			 * activityUserPrizeList.size(); i++){ ActivityUserPrizeDTO
			 * activityUserPrizeDTO = activityUserPrizeList.get(i);
			 * activityUserPrizeDTO
			 * .setMemberTel(StringProcessorUtils.desensitizedMobileNo
			 * (activityUserPrizeDTO.getMemberTel())); } }
			 */
			if (Integer.valueOf(total_count) - activityUserPrizeList.size() > 0) {
				leftCount = Integer.valueOf(total_count)
						- activityUserPrizeList.size();
			}
		} else {
			// 无京东卡发出
			leftCount = Integer.valueOf(total_count);
			addModelObject("count", 0);
		}
		addModelObject("leftCount", leftCount);
		addModelObject("list", activityUserPrizeList);
		return SUCCESS;
	}

	/**
	 * 跳转兑换码链接文章
	 * 
	 * @param in
	 *            -
	 * @return 读取到的固定长度数据
	 */
	public String toRedeemCodeMsg(@Param("messageId") Long messageId,
			@Param("version") Long version) {
		logger.info("[toRedeemCodeMsg] messageId={},version={}", messageId,
				version);

		HttpServletRequest request = getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		// 消息已读
		if (messageId != null && messageId != 0L) {
			try {
				ActivityUserMessageDTO activityUserMessageDto = new ActivityUserMessageDTO();
				activityUserMessageDto.setId(messageId);
				activityUserMessageDto.setVersion(version);
				activityUserMessageDto
						.setReadStatus(UserMessageReadStatusEnum.READED);
				activityUserMessageFacade
						.updateReadStatus(activityUserMessageDto);
			} catch (Exception e) {
				logger.error("[toRedeemCodeMsg] info={},ERROR={}",
						"消息更改已读状态异常", e);
			}
		}

		// 跳转链接
		Map<String, Object> map = (Map<String, Object>) GetParamUtils
				.readRedeemCodeLink();
		logger.info("[toRedeemCodeMsg] map={}", map);
		// 转发map.get("link").toString()
		/*
		 * try { RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("../"
		 * +"m.lanmao.com/lmweChat/account/toRegister");
		 * dispatcher.forward(request, response); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		// addModelObject("link", map.get("link"));
		setJsonModel(map);
		return "json";
	}

	/**
	 * 跳转中秋活动页
	 * 
	 * @param in
	 *            -
	 * @return
	 */
	public String toMidAutumnActivity() {
		logger.info("[toMidAutumnActivity] info={}", "跳转中秋活动页...");

		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletResponse response = getMvcFacade().getResponse();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		logger.info("[toMidAutumnActivity] memberDto={}", memberDto);

		Calendar c = Calendar.getInstance();

		// 日期判断，觉得显示的下一场的日期
		int day = c.get(Calendar.DAY_OF_MONTH);
		// 时间判断，决定显示按钮
		int hour = c.get(Calendar.HOUR_OF_DAY);
		// 显示的下一场日期
		int showDay = 0;
		// flag为false时，表示是显示的是敬请期待
		boolean flag = false;
		int showSeason = 0; // 显示“下一场”的标志，为1时不显示
		if (day < 12) {
			// 显示开场时间为9月12日
			showSeason = 1;
			showDay = 12;
		} else if (day == 12) {
			if (hour < 14) {
				showSeason = 1;// 12日14：00之前，不显示"下一场"
				showDay = day;
			} else {
				flag = true;
				showDay = day + 1;
			}
		} else if (day == 15) { // 15日上午显示敬请期待，下一场开场时间15日14点,下午显示上一场已抢完
			if (hour < 14) {
				showDay = day;
			} else {
				flag = true;
			}
		} else if (day > 15) { // 15日以后，只显示已抢完
			flag = true;
			showDay = 0; // 不显示下一场时间
		} else { // 活动期间，14点前显示敬请期待，下一场时间为今天14点。14点后显示已抢完，下一场时间为明天14点
			if (hour < 14) {
				flag = false;
				showDay = day;
			} else {
				flag = true;
				showDay = day + 1;
			}
		}
		logger.info("[toMidAutumnActivity] flag={},showDay={},showSeason={}",
				flag, showDay, showSeason);
		addModelObject("flag", flag);
		addModelObject("showDay", showDay);
		addModelObject("showSeason", showSeason);
		ProductDetailResultDto productDetailResultDto = fiQueryFacade
				.obtainProductDetail("FIXED", "SHORT", "SHORT");
		logger.info("[toOlympicActivity] productDetailResultDto={}",
				productDetailResultDto);
		if (productDetailResultDto != null) {
			BigDecimal rate = productDetailResultDto.getRate();
			addModelObject("rate", rate);
		}
		// productDetailResultDto = null;
		addModelObject("productDetailResultDto", productDetailResultDto);

		return SUCCESS;
	}

	/**
	 * 去app推广活动
	 * 
	 * @author 陈大涛 2016-9-12下午5:44:49
	 */
	public String toAppMarketingPromotion(@Param("productId") Long productId) {
		logger.info("[toAppMarketingPromotion] info={},productId={}",
				"跳转app推广活动页...", productId);
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		logger.info("[toAppMarketingPromotion] memberDto={}", memberDto);
		if ("APP".equals(session.getAttribute("platform"))) {// 如果是app则显示，不是则返回异常页面
			if (null == memberDto) {
				// 用户未登录
				addModelObject("loginFlag", "noLogin");
			} else {
				addModelObject("loginFlag", "login");
			}
			addModelObject("productId", productId);
			return SUCCESS;
		} else {
			return "errorScrNo";
		}
	}

	/**
	 * app推广活动，领取10元投资券
	 * 
	 * @author 陈大涛 2016-9-12下午6:05:10
	 */
	public String appMarketingPromotion(@Param("productId") Long productId) {
		// logger.info("[appMarketingPromotion] productId={}",productId);
		// logger.info("[appMarketingPromotion] info={},eventCode={}","app推广活动，领取10元投资券",eventCode);
		String eventCode = LmConstants.APP_MARKETING_PROMOTION_ACTION;
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (memberDto != null) {

			logger.info("[appMarketingPromotion] memberNo={},eventCode={}",
					memberDto.getMemberNo(), eventCode);
			Lock lock = new RedisLock(memberDto.getMemberNo(), 10);
			try {
				if (lock.tryLock(6)) {// 等待一段时间，尝试多次去获取锁

					if ("APP".equals(session.getAttribute("platform"))) {// 如果渠道是app正常，否则返回异常
						// 1.查询用户是否已经领取投资券
						List<ActivityUsercouponDTO> result = new ArrayList<ActivityUsercouponDTO>();
						result = activityCouponTradeFacade
								.queryTradeUserCouponList(eventCode,
										memberDto.getMemberNo());
						// 判断是否已经领取投资券
						if (result != null && result.size() != 0) {// 已领取
							addModelObject("type", false);
							addModelObject("productId", productId);
							return SUCCESS;
						} else {
							// 2.调用发放投资券接口
							List<ActivityUsercouponDTO> activityUsercouponDTOList = activityCouponTradeFacade
									.receiveUserCoupon(eventCode,
											memberDto.getMemberNo());
							// 判断是否发放投资券
							if (activityUsercouponDTOList != null
									&& activityUsercouponDTOList.size() != 0) {// 正确发放
								addModelObject("type", true);
								addModelObject("productId", productId);
								return SUCCESS;
							} else {// 没有发放
								logger.error("[appMarketingPromotion] info={}",
										"没有正确发放投资券");
								return SYSTEM_EXCEPTION;
							}
						}
					} else {
						logger.error("[appMarketingPromotion] info={}",
								"非app调用此接口");
						return "errorScrNo";
					}
				}

			} finally {
				lock.unlock();
			}
		}

		return SUCCESS;

	}

	/**
	 * 去app推广活动渠道页
	 * 
	 * @author 陈大涛 2016-9-12下午5:44:49
	 */
	public String toAppMarketingCoupon() {
		logger.info("[toAppMarketingCoupon] info={}",
				"跳转app推广活动页...");
		return SUCCESS;
	}

	/**
	 * 不同渠道的app推广下载页面
	 * 
	 * @author 陈大涛 2016-9-19下午3:31:45
	 */
	public String toAppMarketingForScrNo() {
		// 获取请求的user-Agent
		HttpServletRequest request = getMvcFacade().getRequest();
		String userAgent = request.getHeader("User-Agent");
		// 判断Android字样还是ios
		if (userAgent.contains("Android")||userAgent.contains("windows phone")) {
			addModelObject("type", "ANDROID");
			logger.info("[toAppMarketingForScrNo] type=ANDROID");
		} else if (userAgent.contains("iPhone")) {
			addModelObject("type", "IOS");
			logger.info("[toAppMarketingForScrNo] type=IOS");
		} else {
			addModelObject("type", "OTHER");
			logger.info("[toAppMarketingForScrNo] type=OTHER");
		}
		
		return SUCCESS;
	}
}
