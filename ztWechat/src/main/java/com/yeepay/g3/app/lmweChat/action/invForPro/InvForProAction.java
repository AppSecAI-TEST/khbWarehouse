package com.yeepay.g3.app.lmweChat.action.invForPro;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInsertOrderResultDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProOrderAndProInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProOrderDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProRuleXTDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserMessageDTO;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderInsertOrderResultEnum;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderStatusEnum;
import com.yeepay.g3.facade.activity.enums.InvForProInfoStatusEnum;
import com.yeepay.g3.facade.activity.enums.TripSecondDiscountSurplusNumResultCode;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProInfoFacade;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProOrderFacade;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProRuleFacade;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProTripSecondDiscountFacade;
import com.yeepay.g3.facade.activity.facade.ActivityUserMessageFacade;
import com.yeepay.g3.facade.lmlc.trust.dto.api.ProductDetailForWXResultDto;
import com.yeepay.g3.facade.lmlc.trust.service.FiQueryFacade;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.AccountInfoQueryResultDto;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;
import com.yeepay.g3.utils.smartcache.utils.SmartCacheUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * @Title: 投资换产品活动
 * @Description: 活动相关管理
 * @Copyright: 懒猫金服
 * @author hongbin.kang
 * @createTime 2016-7-29 下午1:57:33

 */
@Controller
public class InvForProAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(InvForProAction.class);
//	@Resource
	private UserBiz userBizImpl;
	
	
	public UserBiz getUserBizImpl() {
		return userBizImpl;
	}

	public void setUserBizImpl(UserBiz userBizImpl) {
		this.userBizImpl = userBizImpl;
	}

//	protected ActivityInvForProInfoFacade invForProInfoFacade = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityInvForProInfoFacade.class);
//	protected ActivityInvForProOrderFacade invForProOrderFacade = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityInvForProOrderFacade.class);
//	protected ActivityInvForProRuleFacade invForProRuleFacade = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityInvForProRuleFacade.class);
//	protected ActivityInvForProTripSecondDiscountFacade invForProTripSecondDiscountFacade = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityInvForProTripSecondDiscountFacade.class);
//	protected ActivityUserMessageFacade activityUserMessageFacade = RemoteServiceFactory
//			.getService("http://localhost:8080/activity-hessian/hessian", RemotingProtocol.HESSIAN, ActivityUserMessageFacade.class);
	
	protected ActivityInvForProInfoFacade invForProInfoFacade = RemoteServiceFactory
			.getService(ActivityInvForProInfoFacade.class);
	protected ActivityInvForProOrderFacade invForProOrderFacade = RemoteServiceFactory
			.getService(ActivityInvForProOrderFacade.class);
	protected ActivityInvForProRuleFacade invForProRuleFacade = RemoteServiceFactory
			.getService(ActivityInvForProRuleFacade.class);
	protected ActivityInvForProTripSecondDiscountFacade invForProTripSecondDiscountFacade = RemoteServiceFactory
			.getService(ActivityInvForProTripSecondDiscountFacade.class);
	protected ActivityUserMessageFacade activityUserMessageFacade = RemoteServiceFactory
	        .getService(ActivityUserMessageFacade.class);
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);

	
	protected LanmaoDemandFacade lanmaoDemandFacade = RemoteServiceFactory
			.getService(LanmaoDemandFacade.class);
	protected TrustQueryFacade trustQueryFacade = RemoteServiceFactory
			.getService(TrustQueryFacade.class);
	protected FiQueryFacade fiQueryFacade = RemoteServiceFactory.getService(FiQueryFacade.class);
	
	/**
	 * 投资换旅游的首页
	 * @author hongbin.kang
	 * @param 
	 * @return
	 */
	public String toInvestForTravelList(@Param("loginName") String loginName,@Param("srcNo") String srcNo,@Param("userSessionKey")String userSessionKey) {
		logger.info("[toInvestForTravelList] loginName={},srcNo={},userSessionKey={}",loginName,srcNo,userSessionKey);
		HttpSession session = getMvcFacade().getHttpSession();
//		session.setAttribute("platform", "APP");
		//如果是app平台，先获取用户登录信息
		String platform = (String) session.getAttribute("platform");
		logger.info("[toInvestForTravelList] platform={}",platform);
		addModelObject("platform",platform);
		if("APP".equals(platform)){
			LoginResultDTO loginResultDto=null;
			try{
				logger.info("[toInvestForTravelList] userBizImpl={}",userBizImpl);
				loginResultDto=userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);
			}catch(Exception e){
				logger.error("[toInvestForTravelList] info={},ERROR={}", "投资换产品页面验证用户是否登录时异常",
						e.getMessage());
			}
			logger.info("[toInvestForTravelList] loginResultDto={}",loginResultDto);
 			if(null!=loginResultDto&&loginResultDto.getResultMsg()==LoginResultEnum.SUCCESS) {
				//将memberDto存入session 方便h5页面中获取
				MemberDto member=loginResultDto.getMemberDto();
				session.setAttribute("member", member);
			}
		}
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		logger.info("[toInvestForTravelList] memberDto={}",memberDto);
		if(null != memberDto) {
			addModelObject("login","YES");
			ActivityInvForProOrderDTO param = new ActivityInvForProOrderDTO();
			param.setMemberNo(memberDto.getMemberNo());
			param.setStatus(ActivityInvForProOrderStatusEnum.NO_PAID);
			List<ActivityInvForProOrderAndProInfoDTO> orderDtoList = invForProOrderFacade
					.queryOrderAndProInfoListByMemberNoAndStatus(param);
			if(orderDtoList.size() > 0) {
				addModelObject("haveOrder","YES");
			}else {
				addModelObject("haveOrder","NO");
			}
		}else {
			addModelObject("login","NO");
		}
		//查询有效的投资换产品的列表
		ActivityInvForProInfoDTO invForProDto = new ActivityInvForProInfoDTO();
		invForProDto.setStatus(InvForProInfoStatusEnum.EFFECTIVE);
		List<ActivityInvForProInfoDTO> invForProDtoList = invForProInfoFacade.selectEffInvForProInfoList(invForProDto);
		if(null != invForProDtoList) {
			addModelObject("invForProDtoList",invForProDtoList);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 查询产品的详细信息
	 * @author hongbin.kang
	 * @param id
	 * @return
	 */
	public String toFreeTraveInfo(@Param("productId") String productId,
			@Param("productCode") String productCode,
			@Param("loginName") String loginName, @Param("srcNo") String srcNo,
			@Param("userSessionKey") String userSessionKey) {
		logger.info("[toFreeTraveInfo] 投资换产品的productId={}",productId);
		HttpServletRequest request = getMvcFacade().getRequest();
		HttpSession session=getMvcFacade().getHttpSession();
		String platform = (String) session.getAttribute("platform");
		addModelObject("platform",platform);
		if("APP".equals(platform)){
			LoginResultDTO loginResultDto=null;
			try{
				loginResultDto=userBizImpl.obtainLogin(loginName,srcNo,userSessionKey);
			}catch(Exception e){
				logger.error("[toFreeTraveInfo] info={},ERROR={}", "投资换产品页面验证APP用户是否登录时异常",
						e.getMessage());
			}
			logger.info("[toInvestForTravelList] loginResultMsg={}",loginResultDto.getResultMsg());
 			if(null!=loginResultDto&&loginResultDto.getResultMsg()==LoginResultEnum.SUCCESS) {
				//将memberDto存入session 方便h5页面中获取
				MemberDto member=loginResultDto.getMemberDto();
				session.setAttribute("member", member);
			}
		}
		if(null != productId) {
			try {
				ActivityInvForProInfoDTO invForProInfoDto = invForProInfoFacade.selectInvForProInfoById(Long.valueOf(productId));
				logger.info("[toFreeTraveInfo] 产品详情id={}",invForProInfoDto.getId());
				addModelObject("invForProInfoDto", invForProInfoDto);
				if(null != invForProInfoDto){
					List<ActivityInvForProRuleXTDTO> ruleXTDtoList = invForProRuleFacade
							.selectRuleByProductId(invForProInfoDto.getId());
					if(ruleXTDtoList.size() > 0) {
						addModelObject("ruleXTDtoFirst", ruleXTDtoList.get(0));
					}
					addModelObject("ruleXTDtoList", ruleXTDtoList);
				}
				addModelObject("productId", productId);
				addModelObject("productCode", productCode);
				String lastAccessUrl = request.getRequestURL() + "?" + request.getQueryString();
				addModelObject("unLoginReturnUrlParam", request.getQueryString());
				URLEncoder.encode(lastAccessUrl, "UTF-8");
				addModelObject("lastAccessUrl", URLEncoder.encode(lastAccessUrl, "UTF-8"));
				//查询第二次投资打折限额
				try {
					String surplusNum = invForProTripSecondDiscountFacade.
					queryTripSecondDiscountSurplusNum(LmConstants.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION, 
							LmConstants.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY);
					if(TripSecondDiscountSurplusNumResultCode.NO_ACITITY.toString().equals(surplusNum)){
						logger.error("[toFreeTraveInfo] 查询第二次投资打折限额 ,没有活动或活动失效");
					}else if(TripSecondDiscountSurplusNumResultCode.NO_PRIZE.toString().equals(surplusNum)){
						logger.error("[toFreeTraveInfo] 查询第二次投资打折限额 ,没有奖品");
					}else if(TripSecondDiscountSurplusNumResultCode.ERROR_PRIZE.toString().equals(surplusNum)){
						logger.error("[toFreeTraveInfo] 查询第二次投资打折限额,奖品配置错误");
					}else{
						addModelObject("surplusNum", surplusNum);
					}
				} catch (Exception e) {
					logger.error("[toFreeTraveInfo] 查询第二次投资打折限额异常 ",e);
				}
				
				return productCode;
			} catch (Exception e) {
				logger.info("[toFreeTraveInfo] 查询产品详情信息有误");
				// TODO: handle exception
			}
			
		}
		return null;
	}
	
	/**
	 * 生成订单
	 * @author hongbin.kang
	 * @param productId
	 * @param number
	 * @param ruleId
	 * @return
	 */
	public String toCreateOrder(@Param("productId") String productId,
			@Param("number") String number,
			@Param("ruleId") String ruleId
			) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		logger.info("[toCreateOrder] 创建订单productId={},number={},trem={},ruleId={}",
				                            productId, number,ruleId);
		ActivityInvForProInsertOrderResultDTO  result = invForProOrderFacade.insertInvForProOrder(productId, number, ruleId, memberDto.getMemberNo());
		if(result.getCode()==ActivityInvForProOrderInsertOrderResultEnum.ERROR_PARAM){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "产品productId为空");
			setJsonModel(map);
			return "json";
		}else if(result.getCode()==ActivityInvForProOrderInsertOrderResultEnum.NO_RULE){
			logger.info("[toCreateOrder] 投资规则不存在");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "投资规则不存在");
			setJsonModel(map);
			return "json";
		}
		else if(result.getCode()==ActivityInvForProOrderInsertOrderResultEnum.NO_LCPRODUCT){
			logger.info("[toCreateOrder] 理财产品不存在");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "理财产品不存在");
			setJsonModel(map);
			return "json";
		}else if(result.getCode()==ActivityInvForProOrderInsertOrderResultEnum.NO_PRODUCT){
			logger.info("[toCreateOrder] 旅游线路余额不足");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "旅游线路余额不足");
			setJsonModel(map);
			return "json";
		}else if(result.getCode()==ActivityInvForProOrderInsertOrderResultEnum.SUCCESS){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "SUCCESS");
			map.put("orderNum", result.getOrderCode());
			setJsonModel(map);
			return "json";
		}else{
			setJsonModel("FAIL");
			return "json";
		}
		
		/*//1.判断参数的合法性
		if(StringUtils.isEmpty(productId)) {
			logger.info("[toCreateOrder] 产品productId为空");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "产品productId为空");
			setJsonModel(map);
			return "json";
		}
		if(StringUtils.isEmpty(number)) {
			logger.info("[toCreateOrder] 产品购买数量number为空");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "产品购买数量number为空");
			setJsonModel(map);
			return "json";
		}
		if(StringUtils.isEmpty(ruleId)) {
			logger.info("[toCreateOrder] 产品期限ruleId为空");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "产品期限ruleId为空");
			setJsonModel(map);
			return "json";
		}
		//2.规则详情
		ActivityInvForProRuleXTDTO ruleXTDto = invForProRuleFacade
				.selectInvForProRuleById(Long.valueOf(ruleId));
		if(null == ruleXTDto) {
			logger.info("[toCreateOrder] 投资规则不存在");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "投资规则不存在");
			setJsonModel(map);
			return "json";
		}
		
		//3.根据产品期限查询理财产品
		//		FIXED、MONY、30D   一个月
		//		FIXED、QUAY、180D  半年
		//		FIXED、YEAY、365D  一年
		String productTerm = ruleXTDto.getTrem()+"D";
		String typeTime = null;
		if(ruleXTDto.getTrem() >= 365) {
			typeTime = "YEAY";
		} else if(ruleXTDto.getTrem() >= 90){
			typeTime = "QUAY";
		} else {
			typeTime = "MONY";
		}
		ProductDetailResultDto productDetailResultDto = fiQueryFacade.obtainProductDetail("FIXED", typeTime, productTerm);
		if(null == productDetailResultDto) {
			logger.info("[toCreateOrder] 理财产品不存在");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "fail");
			map.put("message", "理财产品不存在");
			setJsonModel(map);
			return "json";
		}
		Long lcProductId = productDetailResultDto.getProductId();
		
		String orderNum = null;
		
		//4.查询产品的详情
		if(null != productId && null != ruleXTDto) {
			ActivityInvForProOrderDTO orderDto = new ActivityInvForProOrderDTO();
			ActivityInvForProInfoDTO invForProInfoDto = new ActivityInvForProInfoDTO();
			try {
				invForProInfoDto = invForProInfoFacade.selectInvForProInfoById(Long.valueOf(productId));
				logger.info("[toCreateOrder] 产品详情id={}",invForProInfoDto.getId());
			} catch (Exception e) {
				logger.info("[toCreateOrder] 查询产品详情信息有误");
				// TODO: handle exception
			}
			if(null != invForProInfoDto) {
				//5.判断数量余额
				if(Integer.valueOf(number) > (invForProInfoDto.getStockNum()-invForProInfoDto.getUsedNum())) {
					logger.info("[toCreateOrder] 旅游线路余额不足");
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", "fail");
					map.put("message", "旅游线路余额不足");
					setJsonModel(map);
					return "json";
				}
				//6.根据人数，打折限额剩余数计算总金额
				//查询第二次投资打折限额
				Integer surplus=0;
				try {
					String surplusNum = invForProTripSecondDiscountFacade.
					queryTripSecondDiscountSurplusNum(LmConstants.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION, 
							LmConstants.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY);
					if(TripSecondDiscountSurplusNumResultCode.NO_ACITITY.toString().equals(surplusNum)){
						logger.error("[toCreateOrder] 查询第二次投资打折限额 ,没有活动或活动失效");
					}else if(TripSecondDiscountSurplusNumResultCode.NO_PRIZE.toString().equals(surplusNum)){
						logger.error("[toCreateOrder] 查询第二次投资打折限额 ,没有奖品");
					}else if(TripSecondDiscountSurplusNumResultCode.ERROR_PRIZE.toString().equals(surplusNum)){
						logger.error("[toCreateOrder] 查询第二次投资打折限额,奖品配置错误");
					}else{
						surplus=Integer.valueOf(surplusNum);
					}
				} catch (Exception e) {
					logger.error("[toCreateOrder] 查询第二次投资打折限额异常 ",e);
				}
				//计算打折之后的总金额
				Integer buyNum=Integer.valueOf(number);
				BigDecimal total = ruleXTDto.getPrice().multiply(new BigDecimal(number));
				logger.info("[toCreateOrder] 计算打折之后的总金额buyNum={},price={},surplus={}",buyNum,ruleXTDto.getPrice(),surplus);
				 if(surplus!=0&&buyNum>1){
					 Float buyNumF=null;
				      if(buyNum/2>surplus){//超出限额
				    	  buyNumF=(float) (buyNum-surplus*2+surplus*1.5);
				      }else{
				    	  buyNumF=(float)(buyNum/2*1.5+buyNum%2);
				      }
				      total=ruleXTDto.getPrice().multiply(new BigDecimal(buyNumF));
				    }
				
				orderDto.setProductId(invForProInfoDto.getId());
				orderDto.setActivityCode(invForProInfoDto.getActivityCode());
				orderDto.setLcProductId(Long.valueOf(lcProductId));
				orderDto.setMemberNo(memberDto.getMemberNo());
				orderDto.setPrice(ruleXTDto.getPrice());
				orderDto.setTotal(total);
				orderDto.setNum(Integer.valueOf(number));
			}

			//6.插入换产品订单信息
			try {
				logger.info("toCreateOrder 插入换产品订单信息");
				orderNum = invForProOrderFacade.insertInvForProOrder(orderDto);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("toCreateOrder 插入换产品订单信息有误");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "SUCCESS");
			map.put("orderNum", orderNum);
			setJsonModel(map);
			return "json";
			
		}
		setJsonModel("FAIL");
		return "json";*/
	}
	
	/**
	 * 根据流水号查询订单详情 
	 * @author hongbin.kang
	 * @param orderNum
	 * @return
	 */
	public String toFreeProcuctOrderDetail(@Param("orderNum") String orderNum) {
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		BigDecimal accountBalanceResult;// 懒猫账户余额
		if(StringUtils.isEmpty(orderNum)) {
			logger.info("[toBuyFreeProcuct] 订单的流水号orderNum为空");
			return SYSTEM_EXCEPTION;
		}
		if(null != orderNum) {
			String platform = (String) session.getAttribute("platform");
			logger.info("[toBuyFreeProcuct] 订单的流水号orderNum={}",orderNum);
			// 1.查询懒猫账户余额
			AccountInfoQueryResultDto aiqrDto = lanmaoDemandFacade
					.queryAccount(memberDto.getMemberNo(), new Date());
			accountBalanceResult = aiqrDto == null
					|| aiqrDto.getAmount() == null ? new BigDecimal(0)
			: aiqrDto.getAmount();
			if (!"0".equals(aiqrDto.getCode())) {// 异常
				logger.error("[toBuyFreeProcuct] info={},ERROR={}", "查询懒猫账户余额接口异常",
				aiqrDto.getDescription());
			}
			addModelObject("accountBalanceResult", accountBalanceResult);
			
			try {
				//2.查询订单的详细信息
				ActivityInvForProOrderAndProInfoDTO result = invForProOrderFacade.queryInvForProOrderDetailByOrderCode(orderNum);
				if(null == result) {
					logger.info("[toBuyFreeProcuct] 该订单信息不存在");
					return SYSTEM_EXCEPTION;
				}
				//3.订单是否过期
				if(ActivityInvForProOrderStatusEnum.CANCE.equals(result.getActivityInvForProOrderDTO().getStatus())) {
					//已过期
					addModelObject("overDue", "overDue");
				}
				//余额的钱是否可以购买产品，不够进行充值
				if(result.getActivityInvForProOrderDTO().getTotal().compareTo(accountBalanceResult) > -1) {
					//计算差值
					addModelObject("charge", result.getActivityInvForProOrderDTO().getTotal().subtract(accountBalanceResult));
				}
				//用户旅游的投资总额
				addModelObject("marketValue", result.getActivityInvForProInfoDTO().getPrice().multiply(new BigDecimal(result.getActivityInvForProOrderDTO().getNum())));
				// 查询产品信息
				ProductDetailForWXResultDto lcProduct = trustQueryFacade
						.queryProductDetailForWX(result.getActivityInvForProOrderDTO().getLcProductId());
				//4.APP端已登录用户判断是否绑卡
				//1-未绑卡跳去原生绑卡页面，然后去h5充值页面 
				//2-已绑卡的和微信端一样跳转去充值页面
				if(null!=memberDto&&StringUtils.isNotEmpty(platform)&&"APP".equals(platform)){
					try {
						BankCardInfoDto bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
						// 说明未绑卡
						if (bankCardInfoDto == null) {
							addModelObject("isBankCard", "NO");
						}
					} catch (Exception e) {
						logger.error("[toPurchaseProduct] info={},ERROR={}", "去产品信息页查询绑卡信息时异常",
								e.getMessage());
					}
				}
				addModelObject("orderNum", orderNum);
				addModelObject("lcProduct", lcProduct);
				addModelObject("result", result);
				return SUCCESS;
			} catch (Exception e) {
				logger.info("[toBuyFreeProcuct] 查询订单详情信息有误");
				// TODO: handle exception
			}
		}
		return SYSTEM_EXCEPTION;
	}
	
	/**
	 * 去订单列表页面
	 * @author 陈大涛
	 * 2016-8-8下午6:06:27
	 */
	public String toOrderList(@Param("type") String type,Model model,@Param("messageId") Long messageId
			,@Param("version") Long version){
		//消息已读
		if(messageId!=null&&messageId!=0L){
			try {
				ActivityUserMessageDTO activityUserMessageDto = new ActivityUserMessageDTO();
				activityUserMessageDto.setId(messageId);
				activityUserMessageDto.setVersion(version);
				activityUserMessageDto.setReadStatus(UserMessageReadStatusEnum.READED);
				activityUserMessageFacade.updateReadStatus(activityUserMessageDto);
			} catch (Exception e) {
				logger.info("消息更改已读状态异常");
			}
		}
		addModelObject("type", type);
		return "success";
	}
	/**
	 * 我的订单
	 * @author 陈大涛
	 * 2016-7-29下午3:43:46
	 */
	public String myOrderList(@Param("status") String status){
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String memberNo = memberDto.getMemberNo();
		List<ActivityInvForProOrderAndProInfoDTO> result =new ArrayList<ActivityInvForProOrderAndProInfoDTO>();
		List<ActivityInvForProOrderAndProInfoDTO> resultSuccess =new ArrayList<ActivityInvForProOrderAndProInfoDTO>();
		List<ActivityInvForProOrderAndProInfoDTO> resultPaid =new ArrayList<ActivityInvForProOrderAndProInfoDTO>();
		List<ActivityInvForProOrderAndProInfoDTO> resultCance =new ArrayList<ActivityInvForProOrderAndProInfoDTO>();
		if("noPaid".equals(status)){
			ActivityInvForProOrderDTO activityInvForProOrderDTO = new ActivityInvForProOrderDTO();
			activityInvForProOrderDTO.setMemberNo(memberNo);
			activityInvForProOrderDTO.setStatus(ActivityInvForProOrderStatusEnum.NO_PAID);
			result = invForProOrderFacade.queryOrderAndProInfoListByMemberNoAndStatus(activityInvForProOrderDTO);
		}else{
			ActivityInvForProOrderDTO activityInvForProOrderDTOPaided = new ActivityInvForProOrderDTO();
			activityInvForProOrderDTOPaided.setMemberNo(memberNo);
			activityInvForProOrderDTOPaided.setStatus(ActivityInvForProOrderStatusEnum.PAIDED);
			ActivityInvForProOrderDTO activityInvForProOrderDTOSuccess = new ActivityInvForProOrderDTO();
			activityInvForProOrderDTOSuccess.setMemberNo(memberNo);
			activityInvForProOrderDTOSuccess.setStatus(ActivityInvForProOrderStatusEnum.SUCCESS);
			ActivityInvForProOrderDTO activityInvForProOrderDTOCance = new ActivityInvForProOrderDTO();
			activityInvForProOrderDTOCance.setMemberNo(memberNo);
			activityInvForProOrderDTOCance.setStatus(ActivityInvForProOrderStatusEnum.CANCE);
			resultPaid = invForProOrderFacade.queryOrderAndProInfoListByMemberNoAndStatus(activityInvForProOrderDTOPaided);
			resultSuccess = invForProOrderFacade.queryOrderAndProInfoListByMemberNoAndStatus(activityInvForProOrderDTOSuccess);
			resultCance =  invForProOrderFacade.queryOrderAndProInfoListByMemberNoAndStatus(activityInvForProOrderDTOCance);
			result.addAll(0, resultSuccess);
			result.addAll(resultPaid);
			result.addAll(resultCance);
		}
		setJsonModel(result);
		return "json";
	}
	
	/**
	 * 根据产品明细id查询图片
	 * @author 陈大涛
	 * 2016-7-29下午3:45:05
	 * @throws IOException 
	 */
	public String lookProductImg(@Param("id") Long id) throws IOException{
		logger.info("[lookProductImg]  参数： id={}", id);
		InputStream in=null;OutputStream out=null;
		try {
			HttpServletResponse response = getMvcFacade().getResponse();
			ActivityInvForProInfoDTO activityInvForProInfoDTO = invForProInfoFacade
					.selectAllInvForProInfoById(id);
			logger.info("[lookProductImg] activityInfoDto={}",
					activityInvForProInfoDTO);
			byte[] data = activityInvForProInfoDTO.getProductImg();
			logger.info("[lookProductImg] data是否为空={}", data==null);
			 out = response.getOutputStream();
			byte[] buff = new byte[(int) data.length];
			int i = 0;
			 in = new ByteArrayInputStream(data);
			while ((i = in.read(buff)) != -1) {
				out.write(buff);
			}
			out.flush();
			out.close();
			in.close();
			response.flushBuffer();
		} catch (Exception e) {
			// System.out.println(e);
			logger.info("[lookProductImg] ERROR={}", e);
		}finally{
			if(out!=null){
				out.close();
			}
			if(in!=null){
				in.close();
			}
		}
		return "success";
	}
}
