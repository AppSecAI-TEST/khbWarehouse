/**
 * @author 陈大涛
 * 2016-6-1下午4:06:12
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserRaffleticket;
import com.yeepay.g3.core.activity.service.ActivityDrawPrizeService;
import com.yeepay.g3.core.activity.service.ActivityMemberOperecordService;
import com.yeepay.g3.core.activity.service.ActivityUserRaffleTicketService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;
import com.yeepay.g3.facade.activity.facade.ActivityDrawPrizeFacade;
import com.yeepay.g3.facade.lmact.enumtype.ArenaDetailTypeEnum;
import com.yeepay.g3.facade.lmact.service.LMACTFluxFacade;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.rmi.RemotingProtocol;

/**
 * @author 陈大涛
 * 2016-6-1下午4:06:12
 */
@Service
public class ActivityDrawPrizeFacadeImpl implements ActivityDrawPrizeFacade {

	@Resource
	private ActivityDrawPrizeService activityDrawPrizeServiceImpl;
	
	@Resource
	private ActivityUserRaffleTicketService activityUserRaffleTicketServiceImpl;
	
	@Autowired
	private ActivityMemberOperecordService activityMemberOperecordServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityDrawPrizeFacadeImpl.class);
	@Override
	public ActivityPrizeDTO updateUserPrize(String memberNo, String actionCode,
			String activityCode) throws Exception  {
		ActivityPrizeDTO resultDto = new ActivityPrizeDTO ();
		try {
			ActivityPrize result = activityDrawPrizeServiceImpl.updateUserPrize(memberNo, actionCode, activityCode);
			resultDto = EntityDTOConvert.toTarget(result, ActivityPrizeDTO.class);
			//如果是流量，调用发送流量接口
			Map<String,String> otherSrcNo = (Map<String,String>)ConfigurationUtils.getConfigParam("config_type_text_resources", "LM_otherSrcNo_By_Key").getValue();
			if(null != otherSrcNo && null !=  otherSrcNo.get("RAFFLE_"+resultDto.getPrizeLevel())) {
				activityMemberOperecordServiceImpl.sendFlowByOtherWays(memberNo, ArenaDetailTypeEnum.DRAWPRIZE, otherSrcNo.get("RAFFLE_"+resultDto.getPrizeLevel()));
			}
				
		} catch (Exception e) {
			Map<String, String> exes = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "activity_error_message").getValue();
			if("NOCHANGE".equals(e.getMessage())){
				resultDto.setCode("1002");
				resultDto.setMessage(exes.get("1002"));
			}else if("NOACTIVITY".equals(e.getMessage())){
				resultDto.setCode("1003");
				resultDto.setMessage(exes.get("1003"));
			}else if("NOPRIZE".equals(e.getMessage())){
				resultDto.setCode("1004");
				resultDto.setMessage(exes.get("1004"));
			}else if("NOMEMBER".equals(e.getMessage())){
				resultDto.setCode("1005");
				resultDto.setMessage(exes.get("1005"));
			}else{
				resultDto.setCode("1006");
				resultDto.setMessage(exes.get("1006"));
				logger.error("[updateUserPrize] 抽奖算法 错误日志：",e);
			}
		}
		logger.info("[updateUserPrize] 抽奖算法 code={},message={}",resultDto.getCode(),resultDto.getMessage());
		return resultDto;
	}
	@Override
	public ActivityPrizeDTO updateUserPrizeSpecial(String memberNo,
			String actionCode, String activityCode, Integer prizeId)
			 {
		ActivityPrizeDTO resultDto = new ActivityPrizeDTO ();
		try {
			ActivityPrize result = activityDrawPrizeServiceImpl.updateUserPrizeSpecial(memberNo, actionCode, activityCode, prizeId);
			resultDto = EntityDTOConvert.toTarget(result, ActivityPrizeDTO.class);
			//如果是流量，调用发送流量接口
			Map<String,String> otherSrcNo = (Map<String,String>)ConfigurationUtils.getConfigParam("config_type_text_resources", "LM_otherSrcNo_By_Key").getValue();
			if(null != otherSrcNo && null !=  otherSrcNo.get("RAFFLE_"+resultDto.getPrizeLevel())) {
				activityMemberOperecordServiceImpl.sendFlowByOtherWays(memberNo, ArenaDetailTypeEnum.DRAWPRIZE, otherSrcNo.get("RAFFLE_"+resultDto.getPrizeLevel()));
			}
				
		} catch (Exception e) {
			Map<String, String> exes = (Map<String, String>) ConfigurationUtils.getConfigParam("config_type_text_resources", "activity_error_message").getValue();
			if("NOCHANGE".equals(e.getMessage())){
				resultDto.setCode("1002");
				resultDto.setMessage(exes.get("1002"));
			}else if("NOACTIVITY".equals(e.getMessage())){
				resultDto.setCode("1003");
				resultDto.setMessage(exes.get("1003"));
			}else if("NOPRIZE".equals(e.getMessage())){
				resultDto.setCode("1004");
				resultDto.setMessage(exes.get("1004"));
			}else if("NOMEMBER".equals(e.getMessage())){
				resultDto.setCode("1005");
				resultDto.setMessage(exes.get("1005"));
			}else{
				resultDto.setCode("1006");
				resultDto.setMessage(exes.get("1006"));
				logger.error("[updateUserPrizeSpecial] 特殊抽奖算法 错误日志：",e);
			}
		}
		logger.info("[updateUserPrizeSpecial] 特殊抽奖算法 code={},message={}",resultDto.getCode(),resultDto.getMessage());
		return resultDto;
	}
	
	
	
}
