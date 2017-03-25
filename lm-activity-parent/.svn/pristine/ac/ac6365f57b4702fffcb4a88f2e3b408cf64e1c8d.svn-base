package com.yeepay.g3.core.activity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lanmao.consultant.facade.dto.TblLmUser;
import com.lanmao.consultant.facade.enumtype.Enums.UserType;
import com.lanmao.consultant.facade.service.UserFacade;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderParamDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultDto;
import com.yeepay.g3.facade.lmlc.trust.dto.api.TrustOrderResultPageDto;
import com.yeepay.g3.facade.lmlc.trust.enumtype.TradeOrderStatusEnum;
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
 * 
 * @Description 线下理财顾问平台服务类
 * @author zhenping.zhou
 * @CreateTime 2016年5月31日 下午6:01:45
 * @version 1.0
 */
public class LmUserServiceUtil {
	
	private static Logger logger = LoggerFactory.getLogger(LmUserServiceUtil.class);

	/**
	 * 查询当前用户是否为普通用户
	 * @param memberNo
	 * @return
	 */
	public static boolean isCommonUser(String memberNo) {
		
		boolean commonUser = false;
		
		//查询用户身份（线下理财顾问使用）
		UserFacade userFacade = RemoteServiceFactory.getService(UserFacade.class);
		TblLmUser userBasic = userFacade.getUserBasic(memberNo);
		//-1 userBasic null 成为推荐人
		//0 - 未知 - 成为推荐人
		//1 - 普通用户 - 成为推荐人
		//2 - 推荐人 - 我的推荐
		//3 - 顾问 - 我的客户
		//4 - 销售 - 我的顾问
		if(userBasic != null) {
			Short userType = userBasic.getUserType();
			if(UserType.NONE.getValue() == userType || UserType.USER.getValue() == userType) {
				commonUser = true;
			}
		} else {
			commonUser = true;
		}
		logger.info("[isCommonUser] memberNo={},commonUser={}",memberNo,commonUser);
		
		return commonUser;
	}
	
	/**
	 * 查询当前用户是否购买过信托交易
	 * @param memberNo
	 * @return
	 */
	public static boolean isFirstTrustOrder(String memberNo) {
		logger.info("[isFirstTrustOrder] memberNo={}",memberNo);
		boolean result = false;
		TrustQueryFacade trustQueryFacade = RemoteServiceFactory.getService(TrustQueryFacade.class);
		
		TrustOrderParamDto trustOrderParamDto = new TrustOrderParamDto();
		trustOrderParamDto.setMemberNo(memberNo);
		trustOrderParamDto.setStatus(TradeOrderStatusEnum.SUCCESS);
		//查询信托交易
		TrustOrderResultPageDto trustOrderResultPageDTO = trustQueryFacade.queryTrustOrder(trustOrderParamDto);
		logger.info("[LmUserServiceUtil.isFirstTrustOrder] trustOrderResultPageDTO={}",trustOrderResultPageDTO);
		List<TrustOrderResultDto> list = trustOrderResultPageDTO.getTrustOrderResult();
		logger.info("[LmUserServiceUtil.isFirstTrustOrder] list={}",list);
		//当前逻辑为成功一笔则为首投
		if(list != null && list.size() == 1){
			result = true;
		}
		return result;
	}
	
	/**
	 * 查询当前会员正在登陆的微信公众号openid
	 * @param memberNo
	 * @return
	 */
	public static MemberRelevanceDto getMemberRelevance(String memberNo) {
		logger.info("[getMemberRelevance] memberNo={}",memberNo);
		
		MemberRelevanceDto memberRelevanceDto = null;
		
		MemberManagementFacade memberManagementFacade = RemoteServiceFactory.getService(MemberManagementFacade.class);
		List<MemberRelevanceDto> memberRelevanceDtoList = memberManagementFacade.obtainMemberRelevanceByMemberNo(memberNo);
		if(memberRelevanceDtoList != null) {
			for(MemberRelevanceDto relevance : memberRelevanceDtoList) {
				if(MemberRelevanceStatusEnum.ON == relevance.getStatus()) {
					memberRelevanceDto = relevance;
					break;
				}
			}
		}
		return memberRelevanceDto;
	}
	

	/**
	 * 微信公众号消息推送领取成功参数配置
	 * @return
	 */
	public static Map<String,String> getActivityReceiveSuccess(){
		ConfigParam<Map<String,String>> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "wx_send_message_activity_receive_success"); 
		return config.getValue() == null ? new HashMap<String,String>() : config.getValue();
	}
}
