/**
 * @author 陈大涛
 * 2016-10-26下午3:45:23
 */
package com.yeepay.g3.app.lmweChat.action.zt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.lanmao.fund.facade.fundsales.dto.FundTradeLimitResultDto;
import com.lanmao.fund.facade.fundsales.service.FundCalendarFacade;
import com.lanmao.fund.facade.fundsales.service.LMFundInfoQueryServiceFacade;
import com.yeepay.g3.app.lmweChat.action.BaseAction;
import com.yeepay.g3.app.lmweChat.utils.DateUtils;
import com.yeepay.g3.app.lmweChat.utils.LmConstants;
import com.yeepay.g3.facade.lmportal.dto.BankCardInfoDto;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberPwdConstraintDto;
import com.yeepay.g3.facade.lmportal.service.LPQueryFacade;
import com.yeepay.g3.facade.lmportal.service.MemberPasswordFacade;
import com.yeepay.g3.facade.zt.dto.ZtPolicyDTO;
import com.yeepay.g3.facade.zt.dto.ZtPolicyFundShareAndPolicyProductDTO;
import com.yeepay.g3.facade.zt.dto.ZtPolicyFundShareDTO;
import com.yeepay.g3.facade.zt.dto.ZtPolicyInvestNewPlanDTO;
import com.yeepay.g3.facade.zt.dto.ZtPolicyOrderDTO;
import com.yeepay.g3.facade.zt.dto.ZtPolicyOrderDetailDTO;
import com.yeepay.g3.facade.zt.dto.ZtRedeemParamDTO;
import com.yeepay.g3.facade.zt.dto.ZtSceneDTO;
import com.yeepay.g3.facade.zt.dto.ZtTotalFundDetailDTO;
import com.yeepay.g3.facade.zt.enums.ZtPolicyOrderStatusEnum;
import com.yeepay.g3.facade.zt.facade.ZtPolicyCalculateFacade;
import com.yeepay.g3.facade.zt.facade.ZtPolicyFacade;
import com.yeepay.g3.facade.zt.facade.ZtPolicyFundShareFacade;
import com.yeepay.g3.facade.zt.facade.ZtPolicyFundTradeQueryFacade;
import com.yeepay.g3.facade.zt.facade.ZtPolicyOrderDetailFacade;
import com.yeepay.g3.facade.zt.facade.ZtPolicyOrderFacade;
import com.yeepay.g3.facade.zt.facade.ZtPurchaseFacade;
import com.yeepay.g3.facade.zt.facade.ZtSceneFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.IpUtils;
import com.yeepay.g3.utils.web.emvc.annotation.Param;

/**
 * 赎回action
 * @Description
 * @author 陈大涛
 * 2016-10-26下午3:45:23
 */
@Controller
public class RedeemAction extends BaseAction{
	
	private Logger logger = LoggerFactory.getLogger(RedeemAction.class);
	
	//本地
//	private ZtPolicyOrderFacade ztPolicyOrderFacadeImpl = RemoteServiceFactory.getService("localhost:8080/activity-hession/", RemotingProtocol.HESSIAN, ZtPolicyOrderFacade.class);
//	
//	private ZtPolicyFacade ztPolicyFacadeImpl = RemoteServiceFactory.getService("localhost:8080/activity-hessian/", RemotingProtocol.HESSIAN, ZtPolicyFacade.class);
//
//	private ZtPolicyFundShareFacade ztPolicyFundShareFacadeImpl = RemoteServiceFactory.getService("http://localhost:8080/activity-hessian/", RemotingProtocol.HESSIAN, ZtPolicyFundShareFacade.class);
//	
//	private ZtSceneFacade ztSceneFacadeImpl = RemoteServiceFactory.getService("http://localhost:8080/activity-hessian/", RemotingProtocol.HESSIAN, ZtSceneFacade.class);

//	private ZtPurchaseFacade ztPurchaseFacadeImpl = RemoteServiceFactory.getService("http://localhost:8080/activity-hessian/", RemotingProtocol.HESSIAN, ZtPurchaseFacade.class);
	
//	private ZtPolicyFundTradeQueryFacade ztPolicyFundTradeQueryFacadeImpl =  RemoteServiceFactory.getService("http://localhost:8080/activity-hessian/", RemotingProtocol.HESSIAN, ZtPolicyFundTradeQueryFacade.class);
	
//	private ZtPolicyCalculateFacade ztPolicyCalculateFacadeImpl = RemoteServiceFactory.getService(http://localhost:8080/activity-hessian/", RemotingProtocol.HESSIAN,ZtPolicyCalculateFacade.class);
	
	
	//线上
	private ZtPolicyOrderFacade ztPolicyOrderFacadeImpl = RemoteServiceFactory.getService(ZtPolicyOrderFacade.class);
	private ZtPolicyFacade ztPolicyFacadeImpl = RemoteServiceFactory.getService(ZtPolicyFacade.class);
	private ZtPolicyFundShareFacade ztPolicyFundShareFacadeImpl = RemoteServiceFactory.getService(ZtPolicyFundShareFacade.class);
	private ZtSceneFacade ztSceneFacadeImpl = RemoteServiceFactory.getService(ZtSceneFacade.class);
	private ZtPurchaseFacade ztPurchaseFacadeImpl = RemoteServiceFactory.getService(ZtPurchaseFacade.class);
	private ZtPolicyFundTradeQueryFacade ztPolicyFundTradeQueryFacadeImpl = RemoteServiceFactory.getService(ZtPolicyFundTradeQueryFacade.class);
	private ZtPolicyCalculateFacade ztPolicyCalculateFacadeImpl = RemoteServiceFactory.getService(ZtPolicyCalculateFacade.class);
	private FundCalendarFacade fundCalendarFacadeImpl = RemoteServiceFactory.getService(FundCalendarFacade.class);
	private ZtPolicyOrderDetailFacade ZtPolicyOrderDetailFacadeImpl = RemoteServiceFactory.getService(ZtPolicyOrderDetailFacade.class);
	
	
	protected LPQueryFacade lPQueryFacade = RemoteServiceFactory.getService(LPQueryFacade.class);
	protected MemberPasswordFacade memberPasswordFacade = RemoteServiceFactory.getService(MemberPasswordFacade.class);
	private LMFundInfoQueryServiceFacade lMFundInfoQueryServiceFacadeImpl = RemoteServiceFactory
			.getService(LMFundInfoQueryServiceFacade.class);
	/**
	 * 跳转到赎回页面
	 * @author 陈大涛
	 * 2016-10-26下午3:47:58
	 */
	public String toRedeem(@Param("policyOrderId") Long policyOrderId){
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		logger.info("[toRedeem] policyOrderId={}",policyOrderId);
		ZtPolicyDTO ztPolicyDto=null;
		List<ZtPolicyFundShareDTO>  fundAndPolicyDtoList=null;
//		List<ZtTotalFundDetailDTO> totalFundDetailListDto =null;
		BigDecimal totalEnableMoney =new BigDecimal(0);
		List<BigDecimal> leastRedeemList = new ArrayList<BigDecimal>();
		try {
			//1.查询会员策略交易汇总表是否有有效数据
			ZtPolicyOrderDTO ztPolicyOrderDTO = ztPolicyOrderFacadeImpl.queryZtPolicyOrderById(policyOrderId);
			if(ztPolicyOrderDTO==null||ztPolicyOrderDTO.getPolicyStatus()==ZtPolicyOrderStatusEnum.END){
				logger.info("[toRedeem] 会员策略交易汇总没有此有效记录");
				return null;
			}
			//2.查询策略信息
			 ztPolicyDto =  ztPolicyFacadeImpl.selectPolicyByPrimaryKey(ztPolicyOrderDTO.getPolicyId());
			//3.查询策略交易记录中基金交易持有汇总
			 fundAndPolicyDtoList = ztPolicyFundShareFacadeImpl.queryFundAndPolicyProductList(policyOrderId,memberDto.getMemberNo());
			//4.可赎回总市值
				for(ZtPolicyFundShareDTO items:fundAndPolicyDtoList){
					totalEnableMoney=totalEnableMoney.add(items.getEnableShare().multiply(items.getNetValue()));
					//查询最低赎回份额 
					try {
						FundTradeLimitResultDto fundTradeLimitResultDto  =lMFundInfoQueryServiceFacadeImpl.queryfundLimit(items.getFundCode());
						logger.info("[toRedeem] fundTradeLimitResultDto={}",fundTradeLimitResultDto);//TODO	
						leastRedeemList.add(fundTradeLimitResultDto.getRedeemLimit()==null?new BigDecimal(0):fundTradeLimitResultDto.getRedeemLimit());
					} catch (Exception e) {
						logger.error("[toRedeem] 调用基金远程接口异常 e={}",e);
					}
					
					
				}
			//5.查询用户策略基金对应的总共基金详情--无效
//				totalFundDetailListDto = ztPolicyFundTradeQueryFacadeImpl.queryTotalFundDetailForPolicy(policyOrderId,memberDto.getMemberNo());
		} catch (Exception e) {
			logger.error("[toRedeem] 调用远程接口异常 e={}",e);
		}
			
		
		addModelObject("ztPolicyDto", ztPolicyDto);//策略信息
		addModelObject("policyOrderId", policyOrderId);//策略订单id
		addModelObject("fundAndPolicyDtoList", fundAndPolicyDtoList);//基金列表
		addModelObject("totalEnableMoney", totalEnableMoney);//可赎回总市值
		addModelObject("leastRedeemList", leastRedeemList);//最低赎回份额集合
//		addModelObject("totalFundDetailListDto", totalFundDetailListDto);//用户策略基金对应的总共基金详情
		return SUCCESS;
	}
	
	/**
	 * 跳转到确认赎回页面
	 * @author 陈大涛
	 * 2016-10-26下午3:47:58
	 */
	public String toConfirmRedeem(@Param("policyOrderId") Long policyOrderId,@Param("redeemMoney") BigDecimal redeemMoney){
		logger.info("[toConfirmRedeem] policyOrderId={},redeemMoney={}",policyOrderId,redeemMoney);
		HttpSession session = getMvcFacade().getHttpSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		List<ZtPolicyFundShareDTO> fundAndPolicyDtoList=null;
		BankCardInfoDto bankCardInfoDto=null;
//		List<ZtTotalFundDetailDTO> totalFundDetailListDto =null;
		BigDecimal totalEnableMoney =new BigDecimal(0);
		List<BigDecimal> leastRedeemList = new ArrayList<BigDecimal>();
		try {
			//1.查询策略交易记录中基金交易持有汇总
			 fundAndPolicyDtoList = ztPolicyFundShareFacadeImpl.queryFundAndPolicyProductList(policyOrderId,memberDto.getMemberNo());
			//2.可赎回总市值
			for(ZtPolicyFundShareDTO items:fundAndPolicyDtoList){
				totalEnableMoney=totalEnableMoney.add(items.getEnableShare().multiply(items.getNetValue()));
				//查询最低赎回份额
				try {
					FundTradeLimitResultDto fundTradeLimitResultDto  =lMFundInfoQueryServiceFacadeImpl.queryfundLimit(items.getFundCode());
					leastRedeemList.add(fundTradeLimitResultDto.getRedeemLimit()==null?new BigDecimal(0):fundTradeLimitResultDto.getRedeemLimit());
				} catch (Exception e) {
					logger.error("[toConfirmRedeem] 调用基金远程接口异常 e={}",e);
				}
			}
			//3.查询用户银行卡信息
			 bankCardInfoDto = lPQueryFacade.obtainDefaultBankCardInfo(memberDto.getMemberNo());
			//5.查询用户策略基金对应的总共基金详情--无效
//				totalFundDetailListDto = ztPolicyFundTradeQueryFacadeImpl.queryTotalFundDetailForPolicy(policyOrderId,memberDto.getMemberNo());
		} catch (Exception e) {
			logger.error("[toConfirmRedeem] 调用远程接口异常e={}",e);
		}
		addModelObject("policyOrderId", policyOrderId);//用户策略唯一标示
		addModelObject("totalEnableMoney", totalEnableMoney);//持仓可赎回总市值
		addModelObject("redeemMoney", redeemMoney);//赎回金额
		addModelObject("fundAndPolicyDtoList", fundAndPolicyDtoList);//基金列表
		addModelObject("leastRedeemList", leastRedeemList);//最低赎回份额集合
		addModelObject("bankCardInfoDto", bankCardInfoDto);//用户银行卡信息
//		addModelObject("totalFundDetailListDto", totalFundDetailListDto);//用户策略基金对应的总共基金详情
		
		return SUCCESS;
	}
	
	/**
	 * 跳转到赎回成功页面
	 * @author 陈大涛
	 * 2016-10-26下午3:47:58
	 */
	public String toRedeemSuccess(@Param("policyOrderId") Long policyOrderId,@Param("policyOrderDetailId") Long policyOrderDetailId){
		logger.info("[toRedeemSuccess] policyOrderId={}",policyOrderId);
		ZtPolicyOrderDTO ztPolicyOrderDTO =null;
		ZtSceneDTO ztSceneDto=null;
		ZtPolicyInvestNewPlanDTO ztPolicyInvestNewPlanDto=null;
		ZtPolicyOrderDetailDTO  ztPolicyOrderDetailDto=null;
		 List<Date> dateList=new ArrayList<Date>();
		 HttpSession session = getMvcFacade().getHttpSession();
			MemberDto memberDto = (MemberDto)session.getAttribute("member");
		try {
			//1.查询会员策略交易汇总表是否有有效数据
			 ztPolicyOrderDTO = ztPolicyOrderFacadeImpl.queryZtPolicyOrderById(policyOrderId);
			if(ztPolicyOrderDTO==null){
				logger.error("[toRedeemSuccess] 会员策略交易汇总没有此条记录 policyOrderId={}",policyOrderId);
				return null;
			}
			//2.查询场景信息
			 ztSceneDto = ztSceneFacadeImpl.querySceneInfoById(ztPolicyOrderDTO.getSceneId());
			 //3.下月需申购金额
			 ztPolicyInvestNewPlanDto = ztPolicyCalculateFacadeImpl.calculatePolicyInvestNewPlan(ztPolicyOrderDTO.getMemberPolicyPlanId(), policyOrderDetailId);
			 //4.基金公司预估确认份额时间
			 dateList = fundCalendarFacadeImpl.queryNextNFundWorKDay(DateUtils.formatFundDate(new Date()) , 0, 2);//预估2个基金工作日
			 //5.查询策略交易订单详情
			  ztPolicyOrderDetailDto = ZtPolicyOrderDetailFacadeImpl.queryPolicyOrderDetailById(policyOrderDetailId, memberDto.getMemberNo());
			 
		} catch (Exception e) {
			logger.info("[toRedeemSuccess] 调用远程接口异常 e={}",e);
		}
		
		addModelObject("ztSceneDto", ztSceneDto);//场景信息
		addModelObject("policyOrderId", policyOrderId);//策略订单id
		addModelObject("buyMoney", ztPolicyOrderDetailDto.getOrderAmount());//购买金额
		addModelObject("newPerInvestAmount", ztPolicyInvestNewPlanDto==null?0:ztPolicyInvestNewPlanDto.getNewPerInvestAmount());//购买金额
		addModelObject("confirmDate", dateList.get(dateList.size()-1));//预估确认份额时间
		return SUCCESS;
	}
	/**
	 * 赎回
	 * @author 陈大涛
	 * 2016-10-27下午2:44:30
	 */
	public String redeem(@Param("policyOrderId") Long policyOrderId,@Param("redeemMoney") BigDecimal redeemMoney,@Param("tradePwd") String tradePwd){
		logger.info("[redeem] policyOrderId={},redeemMoney={}",policyOrderId,redeemMoney);
		HttpSession session = getMvcFacade().getHttpSession();
		HttpServletRequest request = getMvcFacade().getRequest();
		MemberDto memberDto = (MemberDto)session.getAttribute("member");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Long result =null;
		try {
			//验证交易密码
			MemberPwdConstraintDto memberPwdConstraintDto = memberPasswordFacade.checkTradePassword(memberDto.getMemberNo(), tradePwd);
			if (!memberPwdConstraintDto.getResultFlag()) {
				resultMap.put("status", "WRONG_PWD");
				resultMap.put("description",memberPwdConstraintDto );
				setJsonModel(resultMap);
				return "json";
			}
			//赎回接口
			ZtRedeemParamDTO ztRedeemParamDTO = new ZtRedeemParamDTO();
			ztRedeemParamDTO.setRedeemMoney(redeemMoney);
			ztRedeemParamDTO.setPolicyOrderId(policyOrderId);
			ztRedeemParamDTO.setMemberNo(memberDto.getMemberNo());
			ztRedeemParamDTO.setMerchantNo(LmConstants.getZTMerchantNo());
			ztRedeemParamDTO.setClientIp(IpUtils.getIpAddr(request));
			ztRedeemParamDTO.setUserAgent(request.getHeader("User-Agent"));
			result = ztPurchaseFacadeImpl.redeem(ztRedeemParamDTO);
			if(result==null){
				logger.error("[redeem] result={}",result);
				resultMap.put("status", SYSTEM_EXCEPTION);
				setJsonModel(resultMap);
				return "json";
			}
		} catch (Exception e) {
			logger.error("[redeem] 远程接口调用异常e={}",e);
			resultMap.put("status", SYSTEM_EXCEPTION);
			setJsonModel(resultMap);
			return "json";
		}
		resultMap.put("orderDetailId", result);
		resultMap.put("status", "SUCCESS");
		setJsonModel(resultMap);
		return "json";
	}
	
}
