package com.yeepay.g3.app.lmweChat.action.scb;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.components.Bean;
import org.apache.struts2.views.freemarker.tags.SetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.lanmao.fund.facade.fundbiz.dto.FundbizRateInfo;
import com.yeepay.g3.app.dto.LoginResultDTO;
import com.yeepay.g3.app.enums.LoginResultEnum;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.biz.UserBiz;
import com.yeepay.g3.app.lmweChat.biz.impl.UserBizImpl;
import com.yeepay.g3.app.lmweChat.entity.ReturnUrlParamEntity;
import com.yeepay.g3.app.lmweChat.utils.GetParamUtils;
import com.yeepay.g3.app.lmweChat.utils.StringProcessorUtils;
import com.yeepay.g3.facade.fundbiz.dto.BalanceInfoResultDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundAccountParamDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundAccountResultDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundIncomeDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundIncomePageDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundPurchaseRedeemPageDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundbizPurchaseParamDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundbizPurchaseResultDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundbizRedeemParamDTO;
import com.yeepay.g3.facade.fundbiz.dto.FundbizRedeemResultDTO;
import com.yeepay.g3.facade.fundbiz.enumtype.PurchaseRedeemStatusEnum;
import com.yeepay.g3.facade.fundbiz.enumtype.RedeemEnumType;
import com.yeepay.g3.facade.fundbiz.enumtype.UserStatusEnumType;
import com.yeepay.g3.facade.fundbiz.service.FundBizTranscationFacade;
import com.yeepay.g3.facade.fundbiz.service.FundQueryFacade;
import com.yeepay.g3.facade.fundbiz.service.FundTranLimitFacade;
import com.yeepay.g3.facade.lmportal.dto.AccountInfoQueryResultDto;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPwdConstraintDto;
import com.yeepay.g3.facade.lmportal.enumtype.PasswordTypeEnum;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.LanmaoDemandFacade;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.facade.lmportal.service.MemberPasswordFacade;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.IpUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * 生财宝
 * 
 * @Copyright: Copyright (c)2011
 * @company 懒猫金服
 * @author ping.zhu
 * @since 2016-3-14
 * @version 1.0
 */
@Controller
public class ScbAction extends BaseAction {

	protected FundQueryFacade fundQueryFacade = RemoteServiceFactory
			.getService(FundQueryFacade.class);
	protected FundBizTranscationFacade fundBizTranscationFacade = RemoteServiceFactory
			.getService(FundBizTranscationFacade.class);
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);
	protected MemberManagementFacade memberManagementFacade = RemoteServiceFactory
			.getService(MemberManagementFacade.class);
	protected MemberPasswordFacade memberPasswordFacade = RemoteServiceFactory
			.getService(MemberPasswordFacade.class);
	protected LanmaoDemandFacade lanmaoDemandFacade = RemoteServiceFactory
			.getService(LanmaoDemandFacade.class);
	protected FundTranLimitFacade fundTranLimitFacade = RemoteServiceFactory
			.getService(FundTranLimitFacade.class);
	protected LPQueryFacade lpQueryFacade = RemoteServiceFactory
			.getService(LPQueryFacade.class);
	private UserBiz userBizImpl;

	public void setUserBizImpl(UserBizImpl userBizImpl) {
		this.userBizImpl = userBizImpl;
	}
	/**
	 * 签约生财宝协议
	 * 
	 * @return
	 */
	public String toRegistScb() {
		HttpServletResponse response=getMvcFacade().getResponse();
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		FundAccountParamDTO fundAccountParamDTO = new FundAccountParamDTO();
		fundAccountParamDTO.setPlatNo(GetParamUtils.getScbPlatNo());
		fundAccountParamDTO.setUserNo(memberDto.getMemberNo());
		fundAccountParamDTO.setSourceSystem(SOURCE_SYSTEM);
		fundAccountParamDTO.setBuyerName(memberDto.getRealName());
		fundAccountParamDTO.setBuyerCertNo(memberDto.getCredentialsNo());
		fundAccountParamDTO.setMobileNumber(memberDto.getBindMobileNo());
		fundAccountParamDTO.setUserCreateIp(IpUtils.getIpAddr(request));
		FundAccountResultDTO fundAccountResultDTO = null;
		try {
			fundAccountResultDTO = fundBizTranscationFacade
					.register(fundAccountParamDTO);
			if (fundAccountResultDTO.getStatus() != UserStatusEnumType.ACTIVE) {
				return "registScb";
			}
		} catch (Exception e) {
			logger.error("[transferIn] info={},ERROR={}", "签约生财宝时异常",
					e.getMessage());
			return "SYSTEM_EXCEPTION";
		}
		Cookie cookie=getMvcFacade().getCookie("MLANMAO_IS_FIRST_SCB");
		if(cookie==null){
			addModelObject("FLAG","ISFIRST");
			Cookie c=new Cookie("MLANMAO_IS_FIRST_SCB","NO");
			c.setMaxAge(-1);
			response.addCookie(c);
		}
		return "scb";
	}
	
	/**
	 * 去生财宝签约页面
	 * @return
	 */
	public String toSignUpScb(){
		return SUCCESS;
	}
	/**
	 * 根据是否登录跳转签约生财宝页面和未签约生财宝页面
	 * 
	 * @return
	 */
	public String toScb(@Param("loginName") String loginName,
			@Param("srcNo") String srcNo,
			@Param("userSessionKey") String userSessionKey) {

		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		// HttpServletRequest request=getMvcFacade().getRequest();
		HttpServletResponse response = getMvcFacade().getResponse();
		String platform = null;
		// 如果session里没有就从redis里取
		if (null == memberDto && !StringUtils.isEmpty(loginName)
				&& !StringUtils.isEmpty(srcNo)
				&& !StringUtils.isEmpty(userSessionKey)) {
			LoginResultDTO loginResultDto = userBizImpl.obtainLogin(loginName,
					srcNo, userSessionKey);
			if (null != loginResultDto&& LoginResultEnum.SUCCESS.equals(loginResultDto.getResultMsg())) {
				memberDto = loginResultDto.getMemberDto();
				session.setAttribute("member", memberDto);
			}
		}
		/**
		 * 最新七日年化收益率
		 */
		String lastestRateForSevenDay = null;
		/**
		 * 最新万分收益
		 */
		String lastestIncomeOfDay = null;
		/**
		 * 懒猫账户余额
		 */
		String accountBalanceResult=null;
		try {
			List<FundbizRateInfo> fundbizRateInfoList = fundQueryFacade
					.queryRateInfos(1);
			if (fundbizRateInfoList != null && fundbizRateInfoList.size() != 0) {
				FundbizRateInfo fundbizRateInfo = fundbizRateInfoList.get(0);
				lastestRateForSevenDay = fundbizRateInfo.getRate() == null ? "0"
						: fundbizRateInfo.getRate();
				lastestIncomeOfDay = fundbizRateInfo.getIncome() == null ? "0"
						: fundbizRateInfo.getIncome().toString();
			}
		} catch (Exception e) {
			logger.error("[toScb] info={},ERROR={}", "查询七日年华收益时异常",
					e.getMessage());
			lastestRateForSevenDay = "0";
			lastestIncomeOfDay = "0";
		}
		addModelObject("lastestRateForSevenDay", lastestRateForSevenDay);
		addModelObject("lastestIncomeOfDay", lastestIncomeOfDay);
		//生财宝开关统一配置开始
		Map<String,String> scbSwitchMap = new LinkedHashMap<String,String>();
		try{
			scbSwitchMap = GetParamUtils.readScbSwitchConfig();
		}catch(Exception e){
			logger.info("[toScb] info={},ERROR={}","公告开关统一配置获取失败",e);
		}
		addModelObject("scbSwitchMap", scbSwitchMap);
		//生财宝开关统一配置结束
		if (null == memberDto) {
			addModelObject("userStatus", "unLogin");
			return "unSignScb";
		} else {
			// 查询懒猫账户余额
			try {
				AccountInfoQueryResultDto accountInfoQueryResultDto = lanmaoDemandFacade
						.queryAccount(memberDto.getMemberNo(), new Date());
				accountBalanceResult = accountInfoQueryResultDto == null
						|| accountInfoQueryResultDto.getAmount() == null ? "0"
						: accountInfoQueryResultDto.getAmount().toString();
			} catch (Exception e) {
				accountBalanceResult="0";
				logger.error("[toScb] info={},ERROR={}", "查询懒猫账户余额时异常",
						e.getMessage());
			}
			addModelObject("accountBalanceResult", accountBalanceResult);
			try {
				// 判断是否绑卡
				BankCardInfoDto bankCardInfoDto = null;
				bankCardInfoDto = lpQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
				// 说明未绑卡
				if (null == bankCardInfoDto) {
					addModelObject("userStatus", "unBindCard");
					platform = (String) session.getAttribute("platform");
					return "unSignScb";
				}
			} catch (Exception e) {
				logger.error("[toScb] info={},ERROR={}", "查询是否绑卡时异常",
						e.getMessage());
			}	
			try {
				// 登录状态，判断是否签约
				if (fundQueryFacade.forSignQuery(GetParamUtils.getScbPlatNo(),memberDto.getMemberNo())) {
					platform = (String) session.getAttribute("platform");
					// 判断是否是首次与生财宝页面交互
					Cookie cookie = getMvcFacade().getCookie("MLANMAO_IS_FIRST_SCB");
					if (cookie == null) {
						addModelObject("FLAG", "ISFIRST");
						Cookie c = new Cookie("MLANMAO_IS_FIRST_SCB", "NO");
						c.setMaxAge(-1);
						response.addCookie(c);
					}
					// 增加标识，不弹窗
					if (null == platform || !"APP".equals(platform)) {
						addModelObject("platform", "WX");
					}
					return "scb";
				} else {
					addModelObject("userStatus", "unSignUp");
					return "unSignScb";
				}
			} catch (Exception e) {
				logger.error("[toScb] info={},ERROR={}", "查询是否签约生财宝时异常",
						e.getMessage());
			}
			return "unSignScb";
		}
	}

	/**
	 * 查询已签约生财宝 持有资产，昨日收益，最新累计收益
	 * 
	 * @return
	 */
	public String queryScb() {
		/**
		 * 持有资产
		 */
		String totalBalance;
		/**
		 * 昨日收益
		 */
		String lastestIncome;
		/**
		 * 最新累计收益
		 */
		String accumulativeIncome;
		/**
		 * 懒猫账户余额
		 */
		String accountBalanceResult;
		/**
		 * 收益到账时间
		 */
		String incomingDay;
		/**
		 * 产生收益时间
		 */
		String firstIncomeDay;
		HttpSession session = getMvcFacade().getHttpSession();

		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String memberNo = memberDto.getMemberNo();
			BalanceInfoResultDTO balanceInfoResultDTO = fundBizTranscationFacade
					.queryBalanceInfo(GetParamUtils.getScbPlatNo(), memberNo);
			totalBalance = balanceInfoResultDTO == null
					|| balanceInfoResultDTO.getBalance() == null ? "0"
					: balanceInfoResultDTO.getBalance().toString();
			lastestIncome = balanceInfoResultDTO == null
					|| balanceInfoResultDTO.getLatestIncome() == null ? "0"
					: balanceInfoResultDTO.getLatestIncome().toString();
			BigDecimal sumAmount = fundQueryFacade.sumAmount(
					GetParamUtils.getScbPlatNo(), memberNo);
			accumulativeIncome = sumAmount == null ? "0" : sumAmount.toString();
			result.put("totalBalance", totalBalance);
			result.put("lastestIncome", lastestIncome);
			result.put("accumulativeIncome", accumulativeIncome);

		} catch (Exception e) {
		}
		// 查询懒猫账户余额
		try {
			AccountInfoQueryResultDto accountInfoQueryResultDto = lanmaoDemandFacade
					.queryAccount(memberDto.getMemberNo(), new Date());
			accountBalanceResult = accountInfoQueryResultDto == null
					|| accountInfoQueryResultDto.getAmount() == null ? "0"
					: accountInfoQueryResultDto.getAmount().toString();
		} catch (Exception e) {
			accountBalanceResult = "0";
		}
		result.put("accountBalanceResult", accountBalanceResult);

		// 查询起息日
		try {
			Date firstIncomeDayResult = fundQueryFacade.queryFirstIncomeDay(new Date());
			firstIncomeDay = firstIncomeDayResult == null ? "0"
					: firstIncomeDayResult
							.toString();
			// 收益到账日期
			incomingDay = StringProcessorUtils.getNextDay(firstIncomeDay);
		} catch (Exception e) {
			incomingDay = null;
			firstIncomeDay = null;
		}
		result.put("incomingDay", incomingDay);
		result.put("firstIncomeDay", firstIncomeDay);

		setJsonModel(result);
		return "json";
	}

	/**
	 * 查询最新累计收益，近一个月收益记录
	 * 
	 * @return
	 */
	public String toScbAccumulate() {
		String accumulativeIncome=null;
		double max=0;
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		 try {
		 BigDecimal sumAmount = fundQueryFacade.sumAmount(
		 GetParamUtils.getScbPlatNo(), memberDto.getMemberNo());
		 accumulativeIncome = sumAmount == null ? "0" : sumAmount.toString();
		 } catch (Exception e) {
		 accumulativeIncome = "0";
		 }
		 addModelObject("accumulativeIncome", accumulativeIncome);
//		 查询近一个月收益记录
		try {
			FundIncomePageDTO fundIncomePageDto = fundQueryFacade
					.queryLstIncomes(GetParamUtils.getScbPlatNo(),
							memberDto.getMemberNo(), 1, 31,
							DateUtils.addDay(new Date(), -30), new Date());
			if (fundIncomePageDto != null
					&& fundIncomePageDto.getFundIncomeDTOs() != null
					&& fundIncomePageDto.getFundIncomeDTOs().size() != 0) {
				for (FundIncomeDTO fundIncomeDTO : fundIncomePageDto
						.getFundIncomeDTOs()) {
					if (fundIncomeDTO.getLatestIncome() != null
							&& !fundIncomeDTO.getLatestIncome().equals(new BigDecimal(0))) {
						Map<String, Object> item = new HashMap<String, Object>();
						double value=Double.parseDouble(fundIncomeDTO.getLatestIncome().toString());
						max = value >= max ? value : max;
						item.put("date",
								dateFormat.format(fundIncomeDTO.getCreateTime()));
						item.put("value", value);
						list.add(item);
					}
				}

			} else {
				list = null;
			}
		} catch (Exception e) {
			list = null;
		}
		addModelObject("list",list);
		addModelObject("max", max);
		return "success";
	}

	/**
	 * 生财宝转入
	 * 
	 * @return
	 */
	public String transferIn(@Param("amount") String amount,
			@Param("tradePwd") String tradePwd) {
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getRequest();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (StringUtils.isEmpty(amount) || StringUtils.isEmpty(tradePwd)) {
			setJsonModel(FAILED);
			return "json";
		}
		// 验证交易密码
				try {
					MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
							.checkTradePassword(memberDto.getMemberNo(), tradePwd);
					if (memberPwdConstraintDto != null
							&& memberPwdConstraintDto.getResultFlag()) {
						
					} else if (memberPwdConstraintDto != null
							&& (!memberPwdConstraintDto.getResultFlag())) {
						System.out.println(memberPwdConstraintDto.getOverPlusCount());
				       int count=memberPwdConstraintDto.getOverPlusCount();
						System.out.println(count);
						if(count==0){
							Date date=memberPwdConstraintDto.getLockedEndTime();
							String lockTime=StringProcessorUtils.getTimeDefference(new Date(),date);
							System.out.println(lockTime);
							setJsonModel("LOCKED_PWD"+lockTime);
						}else{
							setJsonModel(String.valueOf(count));
						}
						//返回密码错误次数
						return "json";
					}
				} catch (Exception e) {
					logger.error("[transferOut] info={},ERROR={}", "生财宝转入时验证交易密码异常",
							e.getMessage());
				}
				
				try {
					BalanceInfoResultDTO balanceInfoResultDto = fundBizTranscationFacade.queryBalanceInfo(SOURCE_SYSTEM,memberDto.getMemberNo());
					//为空说明用户没有购买过生财宝
					if(null==balanceInfoResultDto){
						if(!fundQueryFacade.forSignQuery(GetParamUtils.getScbPlatNo(),memberDto.getMemberNo())){
							FundAccountParamDTO fundAccountParamDTO = new FundAccountParamDTO();
							fundAccountParamDTO.setPlatNo(GetParamUtils.getScbPlatNo());
							fundAccountParamDTO.setUserNo(memberDto.getMemberNo());
							fundAccountParamDTO.setSourceSystem(SOURCE_SYSTEM);
							fundAccountParamDTO.setBuyerName(memberDto.getRealName());
							fundAccountParamDTO.setBuyerCertNo(memberDto.getCredentialsNo());
							fundAccountParamDTO.setMobileNumber(memberDto.getBindMobileNo());
							fundAccountParamDTO.setUserCreateIp(IpUtils.getIpAddr(request));
							FundAccountResultDTO fundAccountResultDTO = null;
							try {
								fundAccountResultDTO = fundBizTranscationFacade
										.register(fundAccountParamDTO);
								if (fundAccountResultDTO.getStatus() != UserStatusEnumType.ACTIVE) {
									logger.info("[transferIn] info={},ERROR={}", "签约生财宝失败");
									setJsonModel(FAILED);
									return "json";
								}
							} catch (Exception e) {
								logger.error("[transferIn] info={},ERROR={}", "签约生财宝时异常",
										e.getMessage());
								setJsonModel(FAILED);
								return "json";
							}
						}
					}
				} catch (Exception e) {
					logger.error("[transferIn] info={},ERROR={}", "查询用户是否购买过生财宝时异常",
							e.getMessage());
					setJsonModel(FAILED);
					return "json";
				}

		try {
			FundbizPurchaseParamDTO fundbizPurchaseParamDTO = new FundbizPurchaseParamDTO();
			fundbizPurchaseParamDTO.setPlatNo(GetParamUtils.getScbPlatNo());
			fundbizPurchaseParamDTO.setPlatUserNo(memberDto.getMemberNo());
			BigDecimal amountBD = new BigDecimal(amount);
			amountBD = amountBD.setScale(2, BigDecimal.ROUND_HALF_UP);
			fundbizPurchaseParamDTO.setAmount(amountBD);
			fundbizPurchaseParamDTO.setRequestIp(IpUtils.getIpAddr(request));
			fundbizPurchaseParamDTO.setFlowNo(UUID.randomUUID().toString()
					.replace("-", "").substring(0, 16));
			fundbizPurchaseParamDTO.setSourceSystem(SOURCE_SYSTEM);
			fundbizPurchaseParamDTO.setTradePassword(tradePwd);
			FundbizPurchaseResultDTO fundbizPurchaseResultDTO = fundBizTranscationFacade
					.purchase(fundbizPurchaseParamDTO);
			if (fundbizPurchaseResultDTO.getStatus() != PurchaseRedeemStatusEnum.SUCCESS) {
				setJsonModel(FAILED);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[transferOut] info={},ERROR={}", "生财宝转入时异常",
					e.getMessage());
			setJsonModel(FAILED);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";
	}

	/**
	 * 生财宝转出
	 * 
	 * @return
	 */
	public String transferOut(@Param("amount") String amount,
			@Param("tradePwd") String tradePwd) {
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getRequest();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if (StringUtils.isEmpty(amount) || StringUtils.isEmpty(tradePwd)) {
			setJsonModel(FAILED);
			return "json";
		}
		// 验证交易密码
		try {
			MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade
					.checkTradePassword(memberDto.getMemberNo(), tradePwd);
			if (memberPwdConstraintDto != null
					&& memberPwdConstraintDto.getResultFlag()) {

			} else if (memberPwdConstraintDto != null
					&& (!memberPwdConstraintDto.getResultFlag())) {
				System.out.println(memberPwdConstraintDto.getOverPlusCount());
		       int count=memberPwdConstraintDto.getOverPlusCount();
				System.out.println(count);
				if(count==0){
					Date date=memberPwdConstraintDto.getLockedEndTime();
					String lockTime=StringProcessorUtils.getTimeDefference(new Date(),date);
					System.out.println(lockTime);
					setJsonModel("LOCKED_PWD"+lockTime);
				}else{
					setJsonModel(String.valueOf(count));
				}
				//返回密码错误次数
				return "json";
			}
		} catch (Exception e) {
			logger.error("[transferOut] info={},ERROR={}", "生财宝转出时验证交易密码异常",
					e.getMessage());
		}
		try {
			FundbizRedeemParamDTO fundbizRedeemParamDTO = new FundbizRedeemParamDTO();
			fundbizRedeemParamDTO.setPlatNo(GetParamUtils.getScbPlatNo());
			fundbizRedeemParamDTO.setPlatUserNo(memberDto.getMemberNo());
			BigDecimal amountBD = new BigDecimal(amount);
			amountBD = amountBD.setScale(2, BigDecimal.ROUND_HALF_UP);
			fundbizRedeemParamDTO.setAmount(amountBD);
			fundbizRedeemParamDTO.setRequestIp(IpUtils.getIpAddr(request));
			fundbizRedeemParamDTO.setFlowNo(UUID.randomUUID().toString()
					.replace("-", "").substring(0, 16));
			fundbizRedeemParamDTO.setSourceSystem(SOURCE_SYSTEM);
			fundbizRedeemParamDTO.setTradePassword(tradePwd);
			fundbizRedeemParamDTO.setRequestTime(new Date());
			FundbizRedeemResultDTO fundbizRedeemResultDTO = fundBizTranscationFacade
					.redeem(fundbizRedeemParamDTO);
			if (fundbizRedeemResultDTO.getStatus() != PurchaseRedeemStatusEnum.SUCCESS) {
				setJsonModel(FAILED);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[transferOut] info={},ERROR={}", "生财宝转出时异常",
					e.getMessage());
			setJsonModel(FAILED);
			return "json";
		}
		setJsonModel("SUCCESS");
		return "json";

	}
	/**
	 * 跳转生财宝交易明细
	 * @return
	 */
	public String toScbBill(){
		return "success";
	}

	/**
	 * 查询生财宝交易记录 -- js待修改 system_exception
	 */
	public String queryScbRecord(@Param("tradeType") Integer tradeType,
			@Param("tradeStatus") Integer tradeStatus,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime,
			@Param("pageIndex") Integer pageIndex) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try {
			Date startDate = null;
			Date endDate = null;
			if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
				startDate = DateUtils.addDay(new Date(), -365);
				endDate = new Date();
			} else {
				startDate = dateFormat.parse(startTime);
				endDate = dateFormat.parse(endTime);
			}

			String realTradeType;
			switch (tradeType == null ? 0 : tradeType) {
			case 1:
				realTradeType = "转入";
				break;
			case 2:
				realTradeType = "转出";
				break;
			default:
				realTradeType = null;
			}

			String realTradeStatus;
			switch (tradeStatus == null ? 0 : tradeStatus) {
			case 1:
				realTradeStatus = "成功";
				break;
			case 2:
				realTradeStatus = "失败";
				break;
			case 3:
				realTradeStatus = "进行中";
				break;
			case 4:
				realTradeStatus = "资金在途";
				break;
			default:
				realTradeStatus = null;
			}

			FundPurchaseRedeemPageDTO fundPurchaseRedeemPageDTO = fundQueryFacade
					.queryTransactionRecord(GetParamUtils.getScbPlatNo(),
							memberDto.getMemberNo(), realTradeType,
							realTradeStatus, pageIndex == null
									|| pageIndex == 0 ? 1 : pageIndex,
							PAGE_SIZE, startDate, endDate);
			if (fundPurchaseRedeemPageDTO != null) {
				setJsonModel(fundPurchaseRedeemPageDTO);
				return "json";
			} else {
				setJsonModel(NULL_RESULT);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[queryScbRecord] info={},ERROR={}", "查询生财宝交易记录异常",
					e.getMessage());
			setJsonModel(SYSTEM_EXCEPTION);
			return "json";
		}
	}

	/**
	 * 查询二个月的万分收益和7日年化
	 * 
	 * @return map<String,List<String>>
	 */
	public String queryIncome() {

		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		try {
			List<String> timeList = new ArrayList<String>();
			List<String> incomeList = new ArrayList<String>();
			List<String> rateList = new ArrayList<String>();
			List<FundbizRateInfo> fundbizRateInfoList = fundQueryFacade
					.queryRateInfos(60);
			if (fundbizRateInfoList != null && fundbizRateInfoList.size() != 0) {
				for (FundbizRateInfo fundbizRateInfo : fundbizRateInfoList) {
					timeList.add(dataFormat.format(
							fundbizRateInfo.getIncomeDate())
							.replaceAll("-", ""));
					incomeList.add(String.valueOf((int) ((Double
							.parseDouble((fundbizRateInfo.getIncome()
									.toString())) * 10000))));
					rateList.add(String.valueOf((int) (Double
							.parseDouble((fundbizRateInfo.getRate())) * 1000000)));
				}
				result.put("profit_rate_date", timeList);
				result.put("day1_profit_rate", incomeList);
				result.put("day7_profit_rate", rateList);
				setJsonModel(result);
				return "json";
			} else {
				setJsonModel("");
				return "json";
			}

		} catch (Exception e) {
			return "json";
		}
	}

	/**
	 * 跳转确认转入页面
	 * @param totalBalance 转入金额
	 * @return
	 */
	public String toConfirmTransferIn(@Param("totalBalance") String totalBalance) {
		String firstIncomeDay;
		String incomingDay;
		Date tempFirstIncomeDay;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		if(StringUtils.isEmpty(totalBalance)||Float.valueOf(totalBalance.toString())==0){
			return "SYSTEM_EXCEPTION";
		}
		try {
			tempFirstIncomeDay = fundQueryFacade.queryFirstIncomeDay(new Date());
			firstIncomeDay=dateFormat.format(tempFirstIncomeDay);
			// 收益到账日期
			incomingDay = StringProcessorUtils.getNextDays(tempFirstIncomeDay.toString());
		} catch (Exception e) {
			incomingDay = null;
			firstIncomeDay = null;
		}
		addModelObject("incomingDay", incomingDay);
		addModelObject("firstIncomeDay", firstIncomeDay);
		addModelObject("totalBalance", totalBalance);
		return "success";
	}
	/**
	 * 跳转确认转出页面
	 * @param totalBalance
	 * @return
	 */
	public String toConfirmTransferOut(
			@Param("totalBalance") BigDecimal totalBalance) {
		String type = null;
		String date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		if(StringUtils.isEmpty(totalBalance)||Float.valueOf(totalBalance.toString())==0){
			return "SYSTEM_EXCEPTION";
		}
		try {
			RedeemEnumType redeemEnumType = fundTranLimitFacade
					.getRedeemTypeByPlatUserNo(GetParamUtils.getScbPlatNo(),
							memberDto.getMemberNo(), new Date(), totalBalance);
			if ("COMMON".equals(redeemEnumType.toString())) {
				date = fundQueryFacade.queryFirstIncomeDay(new Date()) == null ? "0"
						: dateFormat.format(fundQueryFacade.queryFirstIncomeDay(new Date()));
				type = "普通转出";
			} else {
				date=dateFormat.format(new Date());
				type = "快速转出";
			}
		} catch (Exception e) {
			type = null;
			date = null;
		}
		addModelObject("redeemTime", date);
		addModelObject("reedemType", type);
		addModelObject("totalBalance", totalBalance);
		return "success";
	}
	/**
	 * 获取当前用户的赎回类型
	 * @param redeemAmount
	 * @return
	 */
	public String getRedeemType(@Param("redeemAmount") BigDecimal redeemAmount){
		String type=null;
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		try {
			if(redeemAmount==null)
				type=null;
			RedeemEnumType redeemEnumType = fundTranLimitFacade
					.getRedeemTypeByPlatUserNo(GetParamUtils.getScbPlatNo(),
							memberDto.getMemberNo(), new Date(), redeemAmount);

			if("COMMON".equals(redeemEnumType)){
				type="普通转出";
			}else{
				type="快速转出";
				}
			System.out.print("查询转出类型:"+type);
		} catch (Exception e) {
		}
		setJsonModel(type);
		return "json";
	}
	/**
	 * 跳转生财宝产品介绍页面
	 * @return
	 */
	public String toScbDetail(){
		return "success";
	}
	/**
	 * 跳转转出成功页面
	 * @return
	 */
	public String toTransferOutSuccess(){
		return "success";
	}
	/**
	 * 跳转转入成功页面
	 * @return
	 */
	public String toTransferInSuccess(@Param("totalBalance") String totalBalance){
		HttpSession session = getMvcFacade().getHttpSession();
		//判断cookie是否存在 存在说明已经与公众号交互过，将其改为NO
//		String openId = (String)session.getAttribute("openId");
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String source = memberDto.getRegisterSrcNo();
		String alreadyConcerned = (String)session.getAttribute("alreadyConcerned");
		String app = (String)session.getAttribute("platform");
		if("APP".equals(app)) {
			addModelObject("app", app);
		}
		if (StringUtils.isEmpty(totalBalance)
				|| Float.valueOf(totalBalance.toString()) == 0) {
			return "SYSTEM_EXCEPTION";
		}
		String tempFirstIncomeDay=null;
		String tempIncomingDay=null;
		String thisDay=null;
		StringBuffer firstIncomeDay=new StringBuffer();
		StringBuffer incomingDay=new StringBuffer();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		try {
			thisDay=dateFormat.format(new Date());
			tempFirstIncomeDay = fundQueryFacade
					.queryFirstIncomeDay(new Date()).toString();
			if (tempFirstIncomeDay != null && tempFirstIncomeDay.length() > 5) {
				// 收益到账日期
				tempIncomingDay = StringProcessorUtils.getNextDay(tempFirstIncomeDay);
				firstIncomeDay
						.append(tempFirstIncomeDay.substring(5))
						.append(" 星期")
						.append(StringProcessorUtils
								.getChineseNumber(StringProcessorUtils
										.dayForWeek(tempFirstIncomeDay)));
			}
			if (tempIncomingDay != null && tempIncomingDay.length() > 5) {
				incomingDay
						.append(tempIncomingDay.substring(5))
						.append(" 星期")
						.append(StringProcessorUtils
								.getChineseNumber(StringProcessorUtils
										.dayForWeek(tempIncomingDay)));
			}
		} catch (Exception e) {
			incomingDay = null;
			firstIncomeDay = null;
		}
		addModelObject("totalBalance",totalBalance);
//		addModelObject("openId",openId);
		addModelObject("source", source);
		addModelObject("alreadyConcerned", alreadyConcerned);//已经关注公众号
		addModelObject("incomingDay", incomingDay);
		addModelObject("firstIncomeDay", firstIncomeDay);
		addModelObject("thisDay", thisDay);
		return "success";
	}
	/**
	 * 跳转生财宝协议
	 * @return
	 */
	public String toScbProtocol(){
		return "success";
	}
	/**
	 * 跳转华夏协议页面
	 * @return
	 */
	public String tohxProtocol(){
		return "success";
	}
	/**
	 * 测试微信jssdk接口
	 * @return
	 */
	public String toTestWXJSSDK(){
		return "success";
	}
}
