/**
 * @author 陈大涛
 * 2016-5-31下午4:02:14
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.utils.WxUtil;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageResultDTO;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.facade.ActivityWXSendMessageFacade;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderPromResultDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultDto;
import com.yeepay.g3.facade.lmlc.trust.enumtype.PromotionPaymentTypeEnum;
import com.yeepay.g3.facade.lmlc.trust.enumtype.PromotionTypeEnum;
import com.yeepay.g3.facade.lmlc.trust.service.api.TrustQueryFacade;
import com.yeepay.g3.facade.lmportal.dto.MemberRelevanceDto;
import com.yeepay.g3.facade.lmportal.enumtype.MemberRelevanceStatusEnum;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @author 陈大涛
 * 2016-5-31下午4:02:14
 */
@Service
public class ActivityWXSendMessageFacadeImpl implements
		ActivityWXSendMessageFacade {
	private Logger logger = LoggerFactory.getLogger(ActivityWXSendMessageFacadeImpl.class);
	
	private TrustQueryFacade trustQueryFacadeImpl =RemoteServiceFactory.getService(TrustQueryFacade.class);
	
	@Override
	public ActivityWXSendMessageResultDTO sendWxMessage(
			ActivityWXSendMessageEnum type, ActivityWXSendMessageDTO dataDto) {

		WxUtil wxUtil = new WxUtil();
		return wxUtil.sendWxMessage(type, dataDto);
	}

	@Override
	public Map<String, Object> getWxUserInfo(String openId) {
		WxUtil wxUtil = new WxUtil();
		return wxUtil.getWxUserInfo(openId);
	}

	@Override
	public void sendWxMessageForMemberNo(
			ActivityWXSendMessageEnum type, String memberNo,String orderNo) {
		logger.info("[sendWxMessageForMemberNo] orderNo={},type={},memberNo={}",orderNo,type,memberNo);
		if(type==null||memberNo==null||orderNo==null){
			logger.error("[sendWxMessageForMemberNo] 参数不对");
			return	;
		}
		MemberManagementFacade memberManagementFacade = RemoteServiceFactory
				.getService(MemberManagementFacade.class);
		ConfigParam<Map<String,String>> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "wx_send_message_activity_cash_success"); 
		Map<String,String> map = config.getValue() == null ? new HashMap<String,String>() : config.getValue();
		ActivityWXSendMessageDTO dataDto = new ActivityWXSendMessageDTO();
		try {
			TrustOrderResultDto trustOrderResultDto = trustQueryFacadeImpl.queryOrder(orderNo);
			logger.error("[sendWxMessageForMemberNo] trustOrderResultDto={}",trustOrderResultDto.toString());
			//如果没有此订单
			if(trustOrderResultDto==null){
				logger.error("[sendWxMessageForMemberNo] 没有此订单 orderNo={}",orderNo);
				return ;
			}
			List<TrustOrderPromResultDto> promotionResult = trustOrderResultDto.getPromotionResult();
			dataDto.setKeyword1(trustOrderResultDto.getProductName());//产品名称
			if(promotionResult==null||promotionResult.size()==0){//用户没有使用优惠券
				logger.info("[sendWxMessageForMemberNo] 用户没有使用优惠券");
				dataDto.setKeyword2(trustOrderResultDto.getAmount().setScale(0, BigDecimal.ROUND_DOWN).toString());//投资金额
				dataDto.setKeyword3(trustOrderResultDto.getExpectIncomeAmount().setScale(2, BigDecimal.ROUND_DOWN).toString());//投资收益
				dataDto.setKeyword4(trustOrderResultDto.getYearRate().setScale(2, BigDecimal.ROUND_DOWN).toString()+"%");//年化收益率
				dataDto.setKeyword5(trustOrderResultDto.getAmount().add(trustOrderResultDto.getExpectIncomeAmount()).setScale(2, BigDecimal.ROUND_DOWN).toString());//实际到账金额
			}else{//用户使用优惠券
				PromotionTypeEnum promoType = promotionResult.get(0).getPromoType();
				PromotionPaymentTypeEnum promoPaymentType = promotionResult.get(0).getPromoPaymentType();
				if(promoType==PromotionTypeEnum.SURPASSED_ADD_RATE){//加息券
					dataDto.setKeyword2(trustOrderResultDto.getAmount().setScale(0, BigDecimal.ROUND_DOWN).toString());//投资金额
					dataDto.setKeyword3(trustOrderResultDto.getExpectIncomeAmount().add( promotionResult.get(0).getPromoIncomeAmount()).setScale(2, BigDecimal.ROUND_DOWN).toString());//投资收益 本金收益+优惠券收益
					dataDto.setKeyword4(trustOrderResultDto.getYearRate().add(promotionResult.get(0).getAddIncome()).setScale(2, BigDecimal.ROUND_DOWN).toString()+"%");//年化收益率 
					dataDto.setKeyword5(trustOrderResultDto.getAmount().add(trustOrderResultDto.getExpectIncomeAmount()).add( promotionResult.get(0).getPromoIncomeAmount()).setScale(2, BigDecimal.ROUND_DOWN).toString());//实际到账金额
				}else if(promoType==PromotionTypeEnum.SURPASSED_ADD_PRINCIPAL&&promoPaymentType ==PromotionPaymentTypeEnum.PRINCIPAL_ABLE){//投资券
					dataDto.setKeyword2(trustOrderResultDto.getAmount().add( promotionResult.get(0).getPromoPrincipal()).setScale(0, BigDecimal.ROUND_DOWN).toString());//投资金额
					dataDto.setKeyword3(trustOrderResultDto.getExpectIncomeAmount().add( promotionResult.get(0).getPromoIncomeAmount().add( promotionResult.get(0).getPromoPrincipal())).setScale(2, BigDecimal.ROUND_DOWN).toString());//投资收益 本金收益+优惠券收益
					dataDto.setKeyword4(trustOrderResultDto.getYearRate().setScale(2, BigDecimal.ROUND_DOWN).toString()+"%");//年化收益率
					dataDto.setKeyword5(trustOrderResultDto.getAmount().add(trustOrderResultDto.getExpectIncomeAmount()).add( promotionResult.get(0).getPromoPrincipal()).add( promotionResult.get(0).getPromoIncomeAmount()).setScale(2, BigDecimal.ROUND_DOWN).toString());//实际到账金额
				}else if(promoType==PromotionTypeEnum.SURPASSED_ADD_PRINCIPAL&&promoPaymentType ==PromotionPaymentTypeEnum.PRINCIPAL_ENABLE){//理财金
					dataDto.setKeyword2(trustOrderResultDto.getAmount().add( promotionResult.get(0).getPromoPrincipal()).setScale(0, BigDecimal.ROUND_DOWN).toString());//投资金额
					dataDto.setKeyword3(trustOrderResultDto.getExpectIncomeAmount().add( promotionResult.get(0).getPromoIncomeAmount()).setScale(2, BigDecimal.ROUND_DOWN).toString());//投资收益 本金收益+优惠券收益
					dataDto.setKeyword4(trustOrderResultDto.getYearRate().setScale(2, BigDecimal.ROUND_DOWN).toString()+"%");//年化收益率
					dataDto.setKeyword5(trustOrderResultDto.getAmount().add(trustOrderResultDto.getExpectIncomeAmount()).add( promotionResult.get(0).getPromoIncomeAmount()).setScale(2, BigDecimal.ROUND_DOWN).toString());//实际到账金额
				}
			}
		} catch (Exception e) {
			logger.error("[sendWxMessageForMemberNo] 调用根据订单orderNo查询交易详情错误 e={}",e);
		}
		dataDto.setFirst(map.get("first"));
		dataDto.setRemark(map.get("remark"));
		dataDto.setUrl(map.get("url"));
		WxUtil wxUtil = new WxUtil();
		List<MemberRelevanceDto> result =  memberManagementFacade.obtainMemberRelevanceByMemberNo(memberNo);
		for(MemberRelevanceDto item:result){
			if(item.getStatus()==MemberRelevanceStatusEnum.ON){
				dataDto.setOpenId(item.getOpenId());
				wxUtil.sendWxMessage(type, dataDto);
			}
		}
	}


}
