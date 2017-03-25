package com.yeepay.g3.core.activity.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityActionDao;
import com.yeepay.g3.core.activity.dao.ActivityInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao;
import com.yeepay.g3.core.activity.dao.ActivityRaffleTicketDao;
import com.yeepay.g3.core.activity.dao.ActivityShareRecordsDao;
import com.yeepay.g3.core.activity.dao.ActivityUserInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.dao.ActivityUserRaffleticketDao;
import com.yeepay.g3.core.activity.dao.ActivityUserScoreRecordDao;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.entity.ActivityInfo;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.core.activity.entity.ActivityRaffleTicket;
import com.yeepay.g3.core.activity.entity.ActivityShareRecords;
import com.yeepay.g3.core.activity.entity.ActivitySrcFlowPlat;
import com.yeepay.g3.core.activity.entity.ActivitySrcFlowRule;
import com.yeepay.g3.core.activity.entity.ActivityUserInfo;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.core.activity.entity.ActivityUserRaffleticket;
import com.yeepay.g3.core.activity.entity.ActivityUserScoreRecord;
import com.yeepay.g3.core.activity.service.ActivityMemberOperecordService;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowPlatService;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowRuleService;
import com.yeepay.g3.core.activity.service.ActivityUserInfoService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.core.activity.utils.DateFormatUtil;
import com.yeepay.g3.core.activity.utils.StringProcessorUtils;
import com.yeepay.g3.core.activity.utils.WxUtil;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageDTO;
import com.yeepay.g3.facade.activity.dto.ActivityWXSendMessageResultDTO;
import com.yeepay.g3.facade.activity.enums.ActivityStatusEnum;
import com.yeepay.g3.facade.activity.enums.ActivityWXSendMessageEnum;
import com.yeepay.g3.facade.activity.enums.RaffleTicketStatusEnum;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.activity.enums.UserRaffleticketStatusEnum;
import com.yeepay.g3.facade.activity.enums.UserScoreRecordTypeEnum;
import com.yeepay.g3.facade.lmact.dto.Esurfing800DetailDto;
import com.yeepay.g3.facade.lmact.dto.FluxCloudDetailDto;
import com.yeepay.g3.facade.lmact.dto.FluxPlatDetailDto;
import com.yeepay.g3.facade.lmact.enumtype.ArenaDetailTypeEnum;
import com.yeepay.g3.facade.lmact.service.LMACTFluxFacade;
import com.yeepay.g3.facade.lmact.service.NewLMACTFluxCloudFacade;
import com.yeepay.g3.facade.lmact.service.NewLMACTFluxEsuring800Facade;
import com.yeepay.g3.facade.lmact.service.NewLMACTFluxPlatFacade;
import com.yeepay.g3.facade.lmlc.async.enumtype.LMTradeTypeEnum;
import com.yeepay.g3.facade.lmlc.async.event.LMTradeMessageEvent;
import com.yeepay.g3.facade.lmportal.async.enumType.LMOperationTypeEnum;
import com.yeepay.g3.facade.lmportal.async.event.ActivityMemberRecords;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.dto.MemberRelevanceDto;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
/**
 * @Title: 会员操作记录业务逻辑实现类
 * @Description: 业务逻辑实现类
 * @Copyright: 懒猫金服
 * @author zhenping.zhou
 * @createTime 2016-5-18 下午1:55:06
 * @version 2016-5-4
 */
@Service
public class ActivityMemberOperecordServiceImpl implements ActivityMemberOperecordService {

	private static Logger logger = LoggerFactory.getLogger(ActivityMemberOperecordServiceImpl.class);

	@Autowired
	private ActivityMemberOperecordDao activityMemberOperecordImpl;
	
	@Autowired
	private ActivityShareRecordsDao activityShareRecordsDaoImpl;
	
	@Autowired
	private ActivityActionDao activityActionDaoImpl;
	
	@Autowired
	private ActivityRaffleTicketDao activityRaffleTicketDaoImpl;
	
	@Autowired
	private ActivityUserRaffleticketDao activityUserRaffleticketDaoImpl;
	
	@Autowired
	private ActivityInfoDao activityInfoDaoImpl;
	
	@Autowired
	private ActivityUserInfoDao activityUserInfoDaoImpl;
	
	@Autowired
	private ActivityUserInfoService activityUserInfoServiceImpl;
	
	@Autowired
	private ActivityUserScoreRecordDao activityUserScoreRecordDaoImpl;
	
	@Autowired
	private ActivityUserMessageDao activityUserMessageDaoImpl;
	
	private SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	protected LMACTFluxFacade lMACTFluxFacade = RemoteServiceFactory.getService(LMACTFluxFacade.class);;
	
	@Autowired
	private ActivitySrcFlowRuleService activitySrcFlowRuleServiceImpl;
	
	@Autowired
	private ActivitySrcFlowPlatService activitySrcFlowPlatServiceImpl;
	
	private MemberManagementFacade memberManagementFacade = RemoteServiceFactory.getService(MemberManagementFacade.class);
	
	private NewLMACTFluxEsuring800Facade newLMACTFluxEsuring800Facade = RemoteServiceFactory.getService(NewLMACTFluxEsuring800Facade.class);
	
	private NewLMACTFluxPlatFacade newLMACTFluxPlatFacade = RemoteServiceFactory.getService(NewLMACTFluxPlatFacade.class);
	
	private NewLMACTFluxCloudFacade newLMACTFluxCloudFacade = RemoteServiceFactory.getService(NewLMACTFluxCloudFacade.class);
	
	@Override
	public void addActivityMemberOperecord(
			ActivityMemberOperecord activityMemberOperecord) {
		// 新增会员操作记录
		if(activityMemberOperecord.getId() == null) {
			activityMemberOperecordImpl.add(activityMemberOperecord);
		}
		
		
	}
	
	/**
	 * 初始化消息公告内容参数
	 * @param shareRecords
	 * @param activityMemberOperecord
	 * @param templateName
	 * @return
	 */
	public ActivityUserMessage initUserMessage(String memberTel, Date occurTime,
			String templateName, String memberNo, Integer score, String wxNickName,String prizeName,String activityName) {
		ActivityUserMessage userMessage = new ActivityUserMessage();
		try {
			userMessage.setMemberNo(memberNo);
			JSONObject msgParam = new JSONObject();
			msgParam.put("createTime",DateFormatUtil.dateFormat("yyyy-MM-dd HH:mm:ss", new Date()));
			msgParam.put("score", score+"");
			msgParam.put("wxNickName", wxNickName);
			msgParam.put("prizeName", prizeName);
			msgParam.put("activityName", activityName);
			msgParam.put("memberTel", memberTel);
			msgParam.put("occurTime", DateFormatUtil.dateFormat("yyyy-MM-dd HH:mm:ss", occurTime));
			userMessage.setMsgContent(msgParam.toString());
			userMessage.setOccurTime(occurTime);
			userMessage.setReadStatus(UserMessageReadStatusEnum.UNREAD);
			userMessage.setTemplateName(templateName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userMessage;
	}
	
	/**
	 * 赠送抽奖券
	 * @param shareRecords
	 * @param activityMemberOperecord
	 * @param action
	 * @param activityInfo
	 */
	private void addMemberRaffleTicket(ActivityShareRecords shareRecords, ActivityMemberOperecord activityMemberOperecord, 
			ActivityAction action, ActivityInfo activityInfo, boolean isCommonUser, boolean isCommonRecommendUser) {
		
		//查询当前事件对应的抽奖券
		Map<String, Object> raffleTicketParam = new HashMap<String, Object>();
		raffleTicketParam.put("couponStatus", RaffleTicketStatusEnum.EFFECTIVE);
		raffleTicketParam.put("actionId", action.getId());
//					actionParam.put("raffleTicketCode", activityMemberOperecord.getOperateType() + Constant.RAFFLE_TICKET_SUFFIX);//BIND_CARD_RAFFLE_TICKET 绑卡抽奖券
		List<ActivityRaffleTicket> raffleTicketList = activityRaffleTicketDaoImpl.query("selectByActionId", raffleTicketParam);
		if(raffleTicketList != null && raffleTicketList.size() > 0) {
			//赠送抽奖券
			for(ActivityRaffleTicket raffleTicket : raffleTicketList) {
				
				//此处判断推荐人关系是否存在，不存在则不赠送推荐人抽奖券
				//有推荐人时 推荐人角色必须是普通会员
				if(isCommonRecommendUser) {
					
					//a 推荐人
					ActivityUserRaffleticket recommendMemberRaffleTicket = new ActivityUserRaffleticket();
					recommendMemberRaffleTicket.setActionId(action.getId());
					recommendMemberRaffleTicket.setActivityId(activityInfo.getId());
					recommendMemberRaffleTicket.setActivityName(activityInfo.getActivityName());
					recommendMemberRaffleTicket.setMemberNo(shareRecords.getRecommendMemberNo()); //推荐人会员编号
					recommendMemberRaffleTicket.setMemberTel(shareRecords.getRecommendMemberTel());
					recommendMemberRaffleTicket.setRaffleTicketCount(1);
					recommendMemberRaffleTicket.setRaffleTicketId(raffleTicket.getId());
					recommendMemberRaffleTicket.setRaffleTicketUsedCount(0);
//							recommendMemberRaffleTicket.setReceiveTime(receiveTime);
					recommendMemberRaffleTicket.setStatus(UserRaffleticketStatusEnum.UN_USE);
//							recommendMemberRaffleTicket.setValidityTimeEnd(validityTimeEnd);
					recommendMemberRaffleTicket.setValidityTimeStart(new Date());
					
					activityUserRaffleticketDaoImpl.add(recommendMemberRaffleTicket);
				}
				
				//如果当前操作会员非普通会员，也不送抽奖券
				if(isCommonUser) {
					
					//b 被推荐人
					ActivityUserRaffleticket memberRaffleTicket = new ActivityUserRaffleticket();
					memberRaffleTicket.setActionId(action.getId());
					memberRaffleTicket.setActivityId(activityInfo.getId());
					memberRaffleTicket.setActivityName(activityInfo.getActivityName());
					memberRaffleTicket.setMemberNo(activityMemberOperecord.getMemberNo());
					memberRaffleTicket.setMemberTel(activityMemberOperecord.getMemberTel());
					memberRaffleTicket.setRaffleTicketCount(1);
					memberRaffleTicket.setRaffleTicketId(raffleTicket.getId());
					memberRaffleTicket.setRaffleTicketUsedCount(0);
//							memberRaffleTicket.setReceiveTime(receiveTime);
					memberRaffleTicket.setStatus(UserRaffleticketStatusEnum.UN_USE);
//							memberRaffleTicket.setValidityTimeEnd(validityTimeEnd);
					memberRaffleTicket.setValidityTimeStart(new Date());
					
					activityUserRaffleticketDaoImpl.add(memberRaffleTicket);
					
					//微信公众号推送消息
					// 根据memberNo查询绑定表，获得openId与会员编号关联实体
					MemberRelevanceDto memberRelevanceDto = LmUserServiceUtil.getMemberRelevance(activityMemberOperecord.getMemberNo());
					if(memberRelevanceDto != null) {
						
						//发送微信推送消息
						Map<String,String> modelInfo = LmUserServiceUtil.getActivityReceiveSuccess();
						ActivityWXSendMessageDTO dataDto = new ActivityWXSendMessageDTO();
						dataDto.setOpenId(memberRelevanceDto.getOpenId());
						dataDto.setToName(StringProcessorUtils.desensitizedMobileNo(activityMemberOperecord.getMemberTel()));
						dataDto.setTime(DateUtils.formatDate(activityMemberOperecord.getOperateTime(), "yyyy-MM-dd HH:mm:ss"));
						dataDto.setGift(raffleTicket.getRaffleTicketName());
						dataDto.setUrl(modelInfo.get("raffle_ticket_url"));
						dataDto.setFirst(modelInfo.get("first"));
						dataDto.setRemark(modelInfo.get("remark"));
						WxUtil wxUtil = new WxUtil();
						
						ActivityWXSendMessageResultDTO resultMessage = wxUtil.sendWxMessage(ActivityWXSendMessageEnum.GET_CHANCE, dataDto);
						if(resultMessage.getCode()!=0){
							logger.debug("[toRafflePrize] 推送微信消息失败，info={}",resultMessage.getMessage());
						}
					}
				}
				
			}
		}
			
		
	}
	
	/**
	 * 用户分值流水记录
	 * @param action
	 * @param shareRecords
	 * @param score
	 */
	private void addUserScoreRecord(ActivityAction action, String memberNo, int score) {
		
		//创建一条幸运值流水记录
		ActivityUserScoreRecord userScoreRecord = new ActivityUserScoreRecord();
		userScoreRecord.setEventId(action.getId());
		userScoreRecord.setEventCode(action.getActionCode());
		userScoreRecord.setMemberNo(memberNo);
		userScoreRecord.setScore(Long.valueOf(score));
		userScoreRecord.setType(UserScoreRecordTypeEnum.ADD);

		activityUserScoreRecordDaoImpl.add(userScoreRecord);
		logger.info("[addUserScoreRecord] userScoreRecord={}",userScoreRecord);
	}
	
	/**
	 * 所有绑卡赠送抽奖券及分值
	 * @param activityMemberOperecord
	 */
	private void everyBindcardRcvRaffle(ActivityMemberOperecord activityMemberOperecord) {

		//1 查询当前用户是否为被推荐人
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberNo", activityMemberOperecord.getMemberNo());
		List<ActivityShareRecords> shareRecordsList = activityShareRecordsDaoImpl.selectByParams(params);
		ActivityShareRecords shareRecords = null;
		if(shareRecordsList != null && shareRecordsList.size() > 0) {
			shareRecords = shareRecordsList.get(0);
		}
		
		boolean isCommonUser = LmUserServiceUtil.isCommonUser(activityMemberOperecord.getMemberNo());
		boolean isCommonRecommendUser = false;
		if(shareRecords != null) {
			isCommonRecommendUser = LmUserServiceUtil.isCommonUser(shareRecords.getRecommendMemberNo());
		}
		
			//2 查线下理财顾问系统，判断当前操作会员的推荐人是什么角色，若是普通用户，则赠送抽奖券、赠送分值，创建对应消息
			//注意 是判断推荐人的角色，而非当前操作会员的角色
//			boolean commonUser = LmUserServiceUtil.isCommonUser(shareRecords.getRecommendMemberNo());
//			if(commonUser) {
//			}
				
		//查询当前事件动作
		Map<String, Object> actionParam = new HashMap<String, Object>();
		actionParam.put("actionCode", activityMemberOperecord.getOperateType() + Constant.ACTION_SUFFIX);//BIND_CARD_ACTION 绑卡动作
		List<ActivityAction> actionList = activityActionDaoImpl.query("selectByParams", actionParam);
		if(actionList != null && actionList.size() > 0) {
			ActivityAction action = actionList.get(0);
			
			//查询当前事件对应的活动
			Map<String, Object> activityParam = new HashMap<String, Object>();
			activityParam.put("activityStatus", ActivityStatusEnum.EFFECTIVE);
			activityParam.put("actionId", action.getId());
			List<ActivityInfo> activityList = activityInfoDaoImpl.query("selectByActionId", activityParam);
			ActivityInfo activityInfo = null;
			//活动不为空
			if(activityList != null && activityList.size() > 0) {
				
				activityInfo = activityList.get(0);
				//TODO 判断当前活动是否有效
				
				//a 赠送抽奖券活动
				this.addMemberRaffleTicket(shareRecords, activityMemberOperecord, action, activityInfo, isCommonUser, isCommonRecommendUser);
				
				//b 赠送分值
				int bindCardScore = Integer.valueOf(ConfigurationUtils.getSysConfigParam(Constant.BIND_CARD_SCORE_CODE).getValue().toString());
				logger.info("[everyBindcardRcvRaffle] bindCardScoreInit={}",bindCardScore);
				List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
				
				//非普通会员投资不送分值
				if(isCommonUser) {
					
					//1 查询当前会员用户基础信息
					ActivityUserInfo userInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", activityMemberOperecord.getMemberNo());
					logger.info("[everyBindcardRcvRaffle] userInfo={}",userInfo);
					if(userInfo != null && activityInfo.getLuckBase() != null) {
						if(userInfo.getTotalScore() >= activityInfo.getLuckBase()) {
							bindCardScore = 0;
						} else if(userInfo.getTotalScore() + bindCardScore >= activityInfo.getLuckBase()) {
							bindCardScore = activityInfo.getLuckBase() - userInfo.getTotalScore();
						}
					}
					logger.info("[everyBindcardRcvRaffle] bindCardScore={}",bindCardScore);
					if(bindCardScore > 0) {
						activityUserInfoServiceImpl.updateOrInitUserScore(userInfo, activityMemberOperecord.getMemberNo(), bindCardScore);
						logger.info("[everyBindcardRcvRaffle] userInfo={}",userInfo);
						//创建一条幸运值流水记录
						this.addUserScoreRecord(action, activityMemberOperecord.getMemberNo(), bindCardScore);
					}
					ActivityUserInfo recommendUserInfo = null;
					if(shareRecords != null) {
						recommendUserInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", shareRecords.getRecommendMemberNo());
					}
					logger.info("[everyBindcardRcvRaffle] info={},recommendUserInfo={}","分享人会员信息",recommendUserInfo);
					
					//判断是否有推荐人关系，消息模板不一样
					if(recommendUserInfo != null) {
						
						//被推荐人得抽奖券 添加消息公告记录
						userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_raffle_ticket_4bindcard.ftl",
								activityMemberOperecord.getMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						//被推荐人得分值 添加消息公告
						if(bindCardScore > 0) {
							userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_score_4bindcard.ftl",
									activityMemberOperecord.getMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						}
					} else {
						
						//普通用户注册并绑卡得抽奖券 添加消息公告记录
						userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "common_receive_raffle_ticket_4bindcard.ftl",
								activityMemberOperecord.getMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						//普通用户注册并绑卡得分值 添加消息公告
						if(bindCardScore > 0) {
							userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "common_receive_score_4bindcard.ftl",
									activityMemberOperecord.getMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						}
					}
					logger.info("[everyBindcardRcvRaffle] isCommonRecommendUser={}",isCommonRecommendUser);
					//推荐人非普通会员，不送分值
					if(isCommonRecommendUser) {
						logger.info("[everyBindcardRcvRaffle] recommendUserInfo={}",recommendUserInfo);
						//2 查询推荐人用户基础信息
						if(recommendUserInfo != null && activityInfo.getLuckBase() != null) {
							if(recommendUserInfo.getTotalScore() >= activityInfo.getLuckBase()) {
								bindCardScore = 0;
							} else if(recommendUserInfo.getTotalScore() + bindCardScore >= activityInfo.getLuckBase()) {
								bindCardScore = activityInfo.getLuckBase() - recommendUserInfo.getTotalScore();
							}
						}
						//推荐人若分值超过最高幸运值时，不添加流水记录、分值通知消息
						if(recommendUserInfo != null) {
							logger.info("[everyBindcardRcvRaffleb] recommendUser is not null! bindCardScore={}",bindCardScore);
							if( bindCardScore > 0) {
								
								activityUserInfoServiceImpl.updateOrInitUserScore(recommendUserInfo, shareRecords.getRecommendMemberNo(), bindCardScore);
								logger.info("[everyBindcardRcvRaffle] recommendUserInfo={}",recommendUserInfo);
								// 创建一条幸运值流水记录
								this.addUserScoreRecord(action, shareRecords.getRecommendMemberNo(), bindCardScore);
								
								//推荐人得分值 添加消息公告
								userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_score_4bindcard.ftl",
										shareRecords.getRecommendMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
							}
							
							//推荐人得抽奖券 添加消息公告
							userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_raffle_ticket_4bindcard.ftl",
									shareRecords.getRecommendMemberNo(),bindCardScore,recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
							//TODO 
							//d 推送用户公众号
						}
					}
				}
				
				activityUserMessageDaoImpl.insertUserMessage(userMessageList);
			}
			
		}
				
	}
	
	/**
	 * 所有投资赠送抽奖券及分值
	 * @param activityMemberOperecord
	 */
	private void everyInvestRcvRaffle(ActivityMemberOperecord activityMemberOperecord) {
		
		//1 查询当前用户是否为被推荐人
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberNo", activityMemberOperecord.getMemberNo());
		List<ActivityShareRecords> shareRecordsList = activityShareRecordsDaoImpl.selectByParams(params);
		ActivityShareRecords shareRecords = null;
		if(shareRecordsList != null && shareRecordsList.size() > 0) {
			shareRecords = shareRecordsList.get(0);
		}
		
		boolean isCommonUser = LmUserServiceUtil.isCommonUser(activityMemberOperecord.getMemberNo());
		boolean isCommonRecommendUser = false;
		if(shareRecords != null) {
			isCommonRecommendUser = LmUserServiceUtil.isCommonUser(shareRecords.getRecommendMemberNo());
		}
		
		//2 查线下理财顾问系统，判断当前操作会员的推荐人是什么角色，若是普通用户，则赠送抽奖券、赠送分值，创建对应消息
		//注意 是判断推荐人的角色，而非当前操作会员的角色
//			boolean commonUser = LmUserServiceUtil.isCommonUser(shareRecords.getRecommendMemberNo());
//			if(commonUser) {
//			}
		
		//查询当前事件动作
		Map<String, Object> actionParam = new HashMap<String, Object>();
		actionParam.put("actionCode", activityMemberOperecord.getOperateType() + Constant.ACTION_SUFFIX);//BIND_CARD_ACTION 绑卡动作
		List<ActivityAction> actionList = activityActionDaoImpl.query("selectByParams", actionParam);
		if(actionList != null && actionList.size() > 0) {
			ActivityAction action = actionList.get(0);
			
			//查询当前事件对应的活动
			Map<String, Object> activityParam = new HashMap<String, Object>();
			activityParam.put("activityStatus", ActivityStatusEnum.EFFECTIVE);
			activityParam.put("actionId", action.getId());
			List<ActivityInfo> activityList = activityInfoDaoImpl.query("selectByActionId", activityParam);
			ActivityInfo activityInfo = null;
			//活动不为空
			if(activityList != null && activityList.size() > 0) {
				
				activityInfo = activityList.get(0);
				//TODO 判断当前活动是否有效
				
				//a 赠送抽奖券活动
				this.addMemberRaffleTicket(shareRecords, activityMemberOperecord, action, activityInfo, isCommonUser, isCommonRecommendUser);
				
				//b 赠送分值
				Map<String, String> investScoreMap = (Map<String, String>)ConfigurationUtils.getSysConfigParam(Constant.FIRST_INVEST_SCORE_CODE).getValue();
				logger.info("firstInvestScoreMap={}" + investScoreMap);
				String scoreKey = "";
				int investScore = 1; //首次投资送分值，如果统一配置未查询到，则默认送1分
				BigDecimal operateAmount = activityMemberOperecord.getOperateAmount(); //当前购买信托金额
				BigDecimal amountMin = BigDecimal.ZERO;
				BigDecimal amountMax = BigDecimal.ZERO;
				//查询当前交易该赠送分值
				if(investScoreMap != null) {
					Set<String> scoreKeyList = investScoreMap.keySet();
					for (String str : scoreKeyList) {
						if(StringUtils.isNotEmpty(str)) {
							String[] amountRegion = str.split("-");
							if(amountRegion.length > 1) {
								amountMin = new BigDecimal(amountRegion[0]);
								amountMax = new BigDecimal(amountRegion[1]);
							} else {
								amountMin = new BigDecimal(amountRegion[0]);
								amountMax = new BigDecimal("1000000000"); //如果没有设置最大值，则默认10亿，谁他妈会投这么多钱放懒猫^_^
							}
							if(operateAmount.compareTo(amountMin) >= 0 && operateAmount.compareTo(amountMax) < 0) {
								scoreKey = str;
								break;
							}
						}
					}
					if(investScoreMap.get(scoreKey) != null) {
						investScore = Integer.valueOf(investScoreMap.get(scoreKey));
					}
				}				
				List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
				
				if(isCommonUser) {
					//1 查询当前会员用户基础信息
					ActivityUserInfo userInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", activityMemberOperecord.getMemberNo());
					if(userInfo != null && activityInfo.getLuckBase() != null) {
						if(userInfo.getTotalScore() >= activityInfo.getLuckBase()) {
							investScore = 0;
						} else if(userInfo.getTotalScore() + investScore >= activityInfo.getLuckBase()) {
							investScore = activityInfo.getLuckBase() - userInfo.getTotalScore();
						}
					}
					if(investScore > 0) {
						
						activityUserInfoServiceImpl.updateOrInitUserScore(userInfo, activityMemberOperecord.getMemberNo(), investScore);
						//创建一条幸运值流水记录
						this.addUserScoreRecord(action, activityMemberOperecord.getMemberNo(), investScore);
					}
					ActivityUserInfo recommendUserInfo = null;
					if(shareRecords != null) {
						recommendUserInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", shareRecords.getRecommendMemberNo());
					}
					
					//判断是否有推荐人关系，消息模板不一样
					if(recommendUserInfo != null) {
						
						//被推荐人得抽奖券 添加消息公告记录
						userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_raffle_ticket_4invest.ftl",
								activityMemberOperecord.getMemberNo(),investScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						//被推荐人得分值 添加消息公告
						if(investScore > 0) {
							userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_score_4invest.ftl",
									activityMemberOperecord.getMemberNo(),investScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						}
					} else {
						
						//普通用户注册并绑卡得抽奖券 添加消息公告记录
						userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "common_receive_raffle_ticket_4invest.ftl",
								activityMemberOperecord.getMemberNo(),investScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						//普通用户注册并绑卡得分值 添加消息公告
						if(investScore > 0) {
							userMessageList.add(this.initUserMessage(activityMemberOperecord.getMemberTel(), activityMemberOperecord.getOperateTime(), "common_receive_score_4invest.ftl",
									activityMemberOperecord.getMemberNo(),investScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
						}
					}
					
					//非普通用户推荐人也不送分值
					if(isCommonRecommendUser) {
						//2 查询推荐人用户基础信息
						if(recommendUserInfo != null && activityInfo.getLuckBase() != null) {
							if(recommendUserInfo.getTotalScore() >= activityInfo.getLuckBase()) {
								investScore = 0;
							} else if(recommendUserInfo.getTotalScore() + investScore >= activityInfo.getLuckBase()) {
								investScore = activityInfo.getLuckBase() - recommendUserInfo.getTotalScore();
							}
						}
						//推荐人若分值超过最高幸运值时，不添加流水记录、分值通知消息
						if(recommendUserInfo != null) {
							
							if( investScore > 0) {
								
								activityUserInfoServiceImpl.updateOrInitUserScore(recommendUserInfo, shareRecords.getRecommendMemberNo(), investScore);
								// 创建一条幸运值流水记录
								this.addUserScoreRecord(action, shareRecords.getRecommendMemberNo(), investScore);
								
								//推荐人得分值 添加消息公告
								userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_score_4invest.ftl",
										shareRecords.getRecommendMemberNo(),investScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
							}
							
							//推荐人得抽奖券 添加消息公告
							userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_raffle_ticket_4invest.ftl",
									shareRecords.getRecommendMemberNo(),investScore,recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
							//TODO 
							//d 推送用户公众号
						}
					}
				}
				activityUserMessageDaoImpl.insertUserMessage(userMessageList);
			}
		}
		
	}

	@Override
	public void updateUserRaffleTicket(Long userMemberOperecordId) {
		ActivityMemberOperecord activityMemberOperecord = (ActivityMemberOperecord)activityMemberOperecordImpl.
				queryOne("selectByPrimaryKey", userMemberOperecordId);
		if(activityMemberOperecord != null) {
			this.addUserRaffleTicket(activityMemberOperecord);
		}
	}

	@Override
	public void addUserRaffleTicket(ActivityMemberOperecord activityMemberOperecord) {
		//修改活动规则——新注册用户、新老用户投资用户都送抽奖券和分值（此规则不影响普通推荐人对应规则），此活动在指定时间范围内有效，统一配置设置 update by zzp 2016-06-28 
		//读取统一配置
		Map<String, String> bindcardInvestRaffleCodeMap = (Map<String, String>)ConfigurationUtils.getSysConfigParam(Constant.BINDCARD_INVEST_RAFFLE_CODE).getValue();

		logger.info("[addUserRaffleTicket] bindcardInvestRaffleCodeMap={}",bindcardInvestRaffleCodeMap);
		//绑卡进行推荐人和被推荐人逻辑处理
		if(LMOperationTypeEnum.BIND_CARD.toString().equals(activityMemberOperecord.getOperateType())) {
			logger.info("[addUserRaffleTicket] BIND_CARD ={}", activityMemberOperecord);

			String bindcardRaffleTimeEndStr = bindcardInvestRaffleCodeMap.get("everyBindCardTimeEnd");
			Date bindcardRaffleTimeEnd = null;
			if(StringUtils.isNotEmpty(bindcardRaffleTimeEndStr)) {
				try {
					bindcardRaffleTimeEnd = dateParser.parse(bindcardRaffleTimeEndStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			Date nowTime = new Date();
			//如果当前时间小于新用户绑卡送抽奖券活动截止时间
			if(nowTime.before(bindcardRaffleTimeEnd)) {
				logger.info("[addUserRaffleTicket] info={}","绑卡要送抽奖券");
				this.everyBindcardRcvRaffle(activityMemberOperecord);
				
			} else {

				//1 查询当前用户是否为被推荐人
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("memberNo", activityMemberOperecord.getMemberNo());
				List<ActivityShareRecords> shareRecordsList = activityShareRecordsDaoImpl.selectByParams(params);
				
				if(shareRecordsList != null && shareRecordsList.size() > 0) {
					ActivityShareRecords shareRecords = shareRecordsList.get(0);

					//2 查线下理财顾问系统，判断当前操作会员的推荐人是什么角色，若是普通用户，则赠送抽奖券、赠送分值，创建对应消息
					//注意 是判断推荐人的角色，而非当前操作会员的角色
					boolean isCommonUser = LmUserServiceUtil.isCommonUser(activityMemberOperecord.getMemberNo());
					boolean isCommonRecommendUser = LmUserServiceUtil.isCommonUser(shareRecords.getRecommendMemberNo());
					
					if(isCommonRecommendUser) {
						
						//查询当前事件动作
						Map<String, Object> actionParam = new HashMap<String, Object>();
						actionParam.put("actionCode", activityMemberOperecord.getOperateType() + Constant.ACTION_SUFFIX);//BIND_CARD_ACTION 绑卡动作
						List<ActivityAction> actionList = activityActionDaoImpl.query("selectByParams", actionParam);
						if(actionList != null && actionList.size() > 0) {
							ActivityAction action = actionList.get(0);
							
							//查询当前事件对应的活动
							Map<String, Object> activityParam = new HashMap<String, Object>();
							activityParam.put("activityStatus", ActivityStatusEnum.EFFECTIVE);
							activityParam.put("actionId", action.getId());
							List<ActivityInfo> activityList = activityInfoDaoImpl.query("selectByActionId", activityParam);
							ActivityInfo activityInfo = null;
							//活动不为空
							if(activityList != null && activityList.size() > 0) {
								
								activityInfo = activityList.get(0);
								//TODO 判断当前活动是否有效
								
								//a 赠送抽奖券活动
								this.addMemberRaffleTicket(shareRecords, activityMemberOperecord, action, activityInfo, isCommonUser, isCommonRecommendUser);
								
								//b 赠送分值
								int bindCardScore = Integer.valueOf(ConfigurationUtils.getSysConfigParam(Constant.BIND_CARD_SCORE_CODE).getValue().toString());
								int tmpScore = bindCardScore;
								List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
								
								//1 查询当前会员用户基础信息
								ActivityUserInfo userInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", shareRecords.getMemberNo());
								ActivityUserInfo recommendUserInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", shareRecords.getRecommendMemberNo());
								if(userInfo != null && activityInfo.getLuckBase() != null) {
									if(userInfo.getTotalScore() >= activityInfo.getLuckBase()) {
										bindCardScore = 0;
									} else if(userInfo.getTotalScore() + bindCardScore >= activityInfo.getLuckBase()) {
										bindCardScore = activityInfo.getLuckBase() - userInfo.getTotalScore();
									}
								}
								//推荐人若分值超过最高幸运值时，不添加流水记录、分值通知消息
								logger.info("[addUserRaffleTicket] bindCardScore={}",bindCardScore);
								if(bindCardScore > 0) {
									logger.info("[addUserRaffleTicket] userInfo={},shareRecords={},bindCardScore={}",userInfo,shareRecords,bindCardScore);
									activityUserInfoServiceImpl.updateOrInitUserScore(userInfo, shareRecords.getMemberNo(), bindCardScore);
									//创建一条幸运值流水记录
									this.addUserScoreRecord(action, shareRecords.getMemberNo(), bindCardScore);
									//被推荐人得分值 添加消息公告
									userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_score_4bindcard.ftl",
											shareRecords.getMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
								}

								//被推荐人得抽奖券 添加消息公告记录
								userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_raffle_ticket_4bindcard.ftl",
										shareRecords.getMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
								
								//2 查询推荐人用户基础信息
								bindCardScore = tmpScore;
								if(recommendUserInfo != null && activityInfo.getLuckBase() != null) {
									
									if(recommendUserInfo.getTotalScore() >= activityInfo.getLuckBase()) {
										bindCardScore = 0;
									} else if(recommendUserInfo.getTotalScore() + bindCardScore >= activityInfo.getLuckBase()) {
										bindCardScore = activityInfo.getLuckBase() - recommendUserInfo.getTotalScore();
									}
								}
								//推荐人若分值超过最高幸运值时，不添加流水记录、分值通知消息
								if(recommendUserInfo != null && bindCardScore > 0) {
									logger.info("[addUserRaffleTicket] recommendUserInfo={},shareRecords={},bindCardScore={}",recommendUserInfo,shareRecords,bindCardScore);
									activityUserInfoServiceImpl.updateOrInitUserScore(recommendUserInfo, shareRecords.getRecommendMemberNo(), bindCardScore);
									// 创建一条幸运值流水记录
									this.addUserScoreRecord(action, shareRecords.getRecommendMemberNo(), bindCardScore);
									
									//推荐人得分值 添加消息公告
									userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_score_4bindcard.ftl",
											shareRecords.getRecommendMemberNo(),bindCardScore, recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
								}
								
								//推荐人得抽奖券 添加消息公告
								userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_raffle_ticket_4bindcard.ftl",
										shareRecords.getRecommendMemberNo(),bindCardScore,recommendUserInfo == null ? "" : recommendUserInfo.getWxNickName(),null,null));
								activityUserMessageDaoImpl.insertUserMessage(userMessageList);
								//TODO 
								//d 推送用户公众号
							}
							
						}
						
					}
					
				}
			}
		} else if(LMTradeTypeEnum.INVEST.toString().equals(activityMemberOperecord.getOperateType())) {
			//信托交易进行推荐人和被推荐人逻辑处理
			
			String everyInvestTimeEndStr = bindcardInvestRaffleCodeMap.get("everyInvestTimeEnd");
			String everyInvestTimeStartStr = bindcardInvestRaffleCodeMap.get("everyInvestTimeStart");
			Date everyInvestTimeEnd = null;
			Date everyInvestTimeStart = null;
			if(StringUtils.isNotEmpty(everyInvestTimeEndStr)) {
				try {
					everyInvestTimeEnd = dateParser.parse(everyInvestTimeEndStr);
					everyInvestTimeStart = dateParser.parse(everyInvestTimeStartStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			Date nowTime = new Date();
			//如果当前时间小于投资送抽奖券活动截止时间
			if(nowTime.before(everyInvestTimeEnd)) {
				
				//每天限制次数、累计限制次数
				int dayCountLimit = Integer.valueOf(bindcardInvestRaffleCodeMap.get("dayCountLimit"));
				int totalCountLimit = Integer.valueOf(bindcardInvestRaffleCodeMap.get("totalCountLimit"));
				
				//查询当天会员是否已投资
				List<ActivityMemberOperecord> dayList = activityMemberOperecordImpl.queryActivityMemberOperecordList(activityMemberOperecord.getMemberNo(), 
						DateFormatUtil.currentDateStart(), DateFormatUtil.currentDateEnd(), activityMemberOperecord.getOperateType());
				
				logger.info("member_no={}, dayList={}" ,activityMemberOperecord.getMemberNo(), dayList);
				
				if(dayList != null && dayList.size() <= dayCountLimit) {
					List<ActivityMemberOperecord> totalList = activityMemberOperecordImpl.queryActivityMemberOperecordList(activityMemberOperecord.getMemberNo(), 
							everyInvestTimeStart, everyInvestTimeEnd, activityMemberOperecord.getOperateType());
					logger.info("member_no={}, totalList={}" ,activityMemberOperecord.getMemberNo(), totalList);

					if(totalList != null && totalList.size() <= totalCountLimit) {
						
						this.everyInvestRcvRaffle(activityMemberOperecord);
					}
					
				}
				
			} else {
				
				//1 查询当前用户是否为被推荐人
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("memberNo", activityMemberOperecord.getMemberNo());
				List<ActivityShareRecords> shareRecordsList = activityShareRecordsDaoImpl.selectByParams(params);
				if(shareRecordsList != null && shareRecordsList.size() > 0) {
					ActivityShareRecords shareRecords = shareRecordsList.get(0);
					
					//2 查询当前投资是否为首次投资，只有首次投资才送抽奖券和分值
					boolean isFirstTrustOrder = LmUserServiceUtil.isFirstTrustOrder(shareRecords.getMemberNo());
					if(isFirstTrustOrder) {
						
						//3 查线下理财顾问系统，判断当前操作会员的推荐人是什么角色，若是普通用户，则赠送抽奖券、赠送分值，创建对应消息
						//注意 是判断推荐人的角色，而非当前操作会员的角色
						boolean isCommonUser = LmUserServiceUtil.isCommonUser(activityMemberOperecord.getMemberNo());
						boolean isCommonRecommendUser = LmUserServiceUtil.isCommonUser(shareRecords.getRecommendMemberNo());

						if(isCommonRecommendUser) {
							
							//查询当前事件动作
							Map<String, Object> actionParam = new HashMap<String, Object>();
							actionParam.put("actionCode", activityMemberOperecord.getOperateType() + Constant.ACTION_SUFFIX);//INVEST_ACTION 投资动作
							List<ActivityAction> actionList = activityActionDaoImpl.query("selectByParams", actionParam);
							if(actionList != null && actionList.size() > 0) {
								ActivityAction action = actionList.get(0);
								
								//查询当前事件对应的活动
								Map<String, Object> activityParam = new HashMap<String, Object>();
								activityParam.put("activityStatus", ActivityStatusEnum.EFFECTIVE);
								activityParam.put("actionId", action.getId());
								List<ActivityInfo> activityList = activityInfoDaoImpl.query("selectByActionId", activityParam);
								ActivityInfo activityInfo = null;
								//活动不为空
								if(activityList != null && activityList.size() > 0) {
									
									activityInfo = activityList.get(0);
									//TODO 判断当前活动是否有效
									
									//a 赠送抽奖券活动
									this.addMemberRaffleTicket(shareRecords, activityMemberOperecord, action, activityInfo, isCommonUser, isCommonRecommendUser);
									
									//b 赠送分值
									Map<String, String> firstInvestScoreMap = (Map<String, String>)ConfigurationUtils.getSysConfigParam(Constant.FIRST_INVEST_SCORE_CODE).getValue();
									logger.info("firstInvestScoreMap={}" + firstInvestScoreMap);
									String scoreKey = "";
									int firstInvestScore = 1; //首次投资送分值，如果统一配置未查询到，则默认送1分
									BigDecimal operateAmount = activityMemberOperecord.getOperateAmount(); //当前购买信托金额
									BigDecimal amountMin = BigDecimal.ZERO;
									BigDecimal amountMax = BigDecimal.ZERO;
									//查询当前交易该赠送分值
									if(firstInvestScoreMap != null) {
										Set<String> scoreKeyList = firstInvestScoreMap.keySet();
										for (String str : scoreKeyList) {
											if(StringUtils.isNotEmpty(str)) {
												String[] amountRegion = str.split("-");
												if(amountRegion.length > 1) {
													amountMin = new BigDecimal(amountRegion[0]);
													amountMax = new BigDecimal(amountRegion[1]);
												} else {
													amountMin = new BigDecimal(amountRegion[0]);
													amountMax = new BigDecimal("1000000000"); //如果没有设置最大值，则默认10亿，谁他妈会投这么多钱放懒猫^_^
												}
												if(operateAmount.compareTo(amountMin) >= 0 && operateAmount.compareTo(amountMax) < 0) {
													scoreKey = str;
													break;
												}
											}
										}
										if(firstInvestScoreMap.get(scoreKey) != null) {
											firstInvestScore = Integer.valueOf(firstInvestScoreMap.get(scoreKey));
										}
									}
									List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
									
									ActivityUserInfo userInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", shareRecords.getMemberNo());
									ActivityUserInfo recommendUserInfo = (ActivityUserInfo)activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", shareRecords.getRecommendMemberNo());
									if(userInfo != null && activityInfo.getLuckBase() != null) {
										if(userInfo.getTotalScore() >= activityInfo.getLuckBase()) {
											firstInvestScore = 0;
										} else if(userInfo.getTotalScore() + firstInvestScore >= activityInfo.getLuckBase()) {
											firstInvestScore = activityInfo.getLuckBase() - userInfo.getTotalScore();
										}
									}
									if(firstInvestScore > 0) {
										
										//1) 查询当前会员用户基础信息
										activityUserInfoServiceImpl.updateOrInitUserScore(userInfo, shareRecords.getMemberNo(), firstInvestScore);
										
										//创建一条幸运值流水记录
										this.addUserScoreRecord(action, shareRecords.getMemberNo(), firstInvestScore);
										//被推荐人得分值 添加消息公告
										userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_score_4invest.ftl",
												shareRecords.getMemberNo(),firstInvestScore, recommendUserInfo.getWxNickName(),null,null));
									}
									//被推荐人得抽奖券 添加消息公告记录
									userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "btjr_receive_raffle_ticket_4invest.ftl",
											shareRecords.getMemberNo(),firstInvestScore, recommendUserInfo.getWxNickName(),null,null));
									
									
									//2) 查询推荐人用户基础信息
									firstInvestScore = 1;
									if(recommendUserInfo != null && activityInfo.getLuckBase() != null) {
										if(recommendUserInfo.getTotalScore() >= activityInfo.getLuckBase()) {
											firstInvestScore = 0;
										} else if(recommendUserInfo.getTotalScore() + firstInvestScore >= activityInfo.getLuckBase()) {
											firstInvestScore = activityInfo.getLuckBase() - recommendUserInfo.getTotalScore();
										}
									}
									if(recommendUserInfo != null && firstInvestScore > 0) {
										activityUserInfoServiceImpl.updateOrInitUserScore(recommendUserInfo, shareRecords.getRecommendMemberNo(), firstInvestScore);
										// 创建一条幸运值流水记录
										this.addUserScoreRecord(action, shareRecords.getRecommendMemberNo(), firstInvestScore);
										
										//推荐人得分值 添加消息公告
										userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_score_4invest.ftl",
												shareRecords.getRecommendMemberNo(),firstInvestScore, recommendUserInfo.getWxNickName(),null,null));
									}
									//推荐人得抽奖券 添加消息公告
									userMessageList.add(this.initUserMessage(shareRecords.getMemberTel(), activityMemberOperecord.getOperateTime(), "tjr_receive_raffle_ticket_4invest.ftl",
											shareRecords.getRecommendMemberNo(),firstInvestScore,recommendUserInfo.getWxNickName(),null,null));
									
									activityUserMessageDaoImpl.insertUserMessage(userMessageList);
									//TODO 
									//d 推送用户公众号
								}
							}
						}
					}
				}
			}
		} else if(LMOperationTypeEnum.REGISTER.toString().equals(activityMemberOperecord.getOperateType())) {
			Map<String, Object> map = (Map<String, Object>) ConfigurationUtils.getConfigParam("config_type_text_resources", "LM_PlatformNo").getValue();
			String platNo = null;
			if(null != map){
				if(null != map.get("PlatformNo")) {
					platNo = map.get("PlatformNo").toString();
				}
			}
			logger.info("[sendFlux] activityMemberOperecord.getMemberNo()={},type={}",activityMemberOperecord.getMemberNo(),ArenaDetailTypeEnum.REGISTER.toString());
			try {
				lMACTFluxFacade.sendFlux(platNo, activityMemberOperecord.getMemberNo(), ArenaDetailTypeEnum.REGISTER, new BigDecimal(0));
				logger.info("调用注册发流量成功");
			} catch (Exception e) {
				logger.error("调用注册发流量失败",e.getMessage());
				// TODO: handle exception
			}
		}
	}

	@Override
	public void sendBindCardFlow(ActivityMemberRecords event,ActivityMemberOperecord memberOperecord) {
		logger.info("[sendBindCardFlow] event={}",event);
		//绑卡送流量
		if(LMOperationTypeEnum.BIND_CARD.toString().equals(event.getType())){
			ArenaDetailTypeEnum opeType = ArenaDetailTypeEnum.BINDCARD;
			
			//1.查询会员信息，获取渠道号
			MemberDto memberDto = memberManagementFacade.obtainMember(event.getMemberNo());
			logger.info("[process] memberDto="+memberDto);
			//2.根据渠道号和操作类型去查询渠道发放流量规则
			List<ActivitySrcFlowRule> list = new ArrayList<ActivitySrcFlowRule>();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("srcNo", memberDto.getRegisterSrcNo());
			map.put("opeType", event.getType());
			List<ActivitySrcFlowRule> ruleList = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
			if(ruleList.size() != 0 && ruleList.get(0).getSendFlag() == 1){
				list = ruleList;
			}else{
				map.put("srcNo", "default");
				map.put("opeType", event.getType());
				List<ActivitySrcFlowRule> rulelist = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
				if(rulelist.size() != 0 && rulelist.get(0).getSendFlag() == 1){
					list = rulelist;
				}
			}
			
			if(list.size() == 0){
				logger.info("[process] info = 不发送流量");
				return;
			}
			Date today = new Date();
			if(list.get(0).getStartDate() != null && today.getTime() < list.get(0).getStartDate().getTime()){
				logger.info("[process] info = 规则未生效或已过期");
				return;
			}
			if(list.get(0).getEndDate() != null && today.getTime() > list.get(0).getEndDate().getTime()){
				logger.info("[process] info = 规则未生效或已过期");
				return;
			}
			logger.info("[process] ruleList ="+list.get(0));
			String cmccCodes = list.get(0).getCmccCode();
//			String[] cmccCode = cmccCodes.split("#"); 
			String cuccCodes = list.get(0).getCuccCode();
//			String[] cuccCode = cuccCodes.split("#");
			String ctccCodes = list.get(0).getCtccCode();
//			String[] ctccCode = ctccCodes.split("#");
			//3.根据渠道号和手机号前三位去查询对应的发放流量通道
			ActivitySrcFlowPlat activitySrcFlowPlat = activitySrcFlowPlatServiceImpl.getSrcPlowPlatBySrcNo(memberDto.getRegisterSrcNo(), memberDto.getBindMobileNo());
			if(activitySrcFlowPlat == null){
				logger.info("[process] info = 该渠道无流量发放通道");
				return;
			}
			logger.info("[process] activitySrcFlowPlat ="+activitySrcFlowPlat.getFluxPlatCode());
			//4.根据手机号判断是移动、联通还是电信(统一配置)
//		String telOpe = "ctcc";
			ConfigParam<Map<String,String>> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "telecom_operators");
			String telOpe= config.getValue().get(memberDto.getBindMobileNo().substring(0, 3));
			logger.info("[process] telOpe = " + telOpe);
			//5.根据流量通道选择调用哪个接口
			//天翼800只充电信
			try{
				if(activitySrcFlowPlat.getFluxPlatCode().equals("ESURFING800")){
//					if(!telOpe.equals("ctcc")){
//						logger.info("[process] ERROR={} 通道配置错误,天翼只能充电信");
//						return;
//					}
					//天翼800
					Esurfing800DetailDto esurfing800DetailDto = new Esurfing800DetailDto();
					esurfing800DetailDto.setMemberNo(event.getMemberNo());
					esurfing800DetailDto.setMobileNo(memberDto.getBindMobileNo());
					esurfing800DetailDto.setInvType(opeType);
					esurfing800DetailDto.setInvAmount(BigDecimal.ZERO);
					esurfing800DetailDto.setInvTime(event.getOperateTime());
					esurfing800DetailDto.setMemberOperecordId(memberOperecord.getId());
					esurfing800DetailDto.setCreateDate(new Date());
					//手机号段
					if(StringUtils.isEmpty(ctccCodes)){
						logger.info("[process] ERROR={} 选择天翼通道却无电信编码");
						return;
					}
					String[] ctccCode = ctccCodes.split("#");
					for( int i = 0 ; i < ctccCode.length ;i ++){
						esurfing800DetailDto.setPlatOfferId(ctccCode[i]);
						newLMACTFluxEsuring800Facade.insertEsurfing800Detail(esurfing800DetailDto);
					}
				}else if(activitySrcFlowPlat.getFluxPlatCode().equals("FLUX_PLAT")){
					//流量公社
					FluxPlatDetailDto fluxPlatDetailDto = new FluxPlatDetailDto();
					fluxPlatDetailDto.setMemberNo(event.getMemberNo());
					fluxPlatDetailDto.setMobileNo(memberDto.getBindMobileNo());
					fluxPlatDetailDto.setInvType(opeType);
					fluxPlatDetailDto.setInvAmount(BigDecimal.ZERO);
					fluxPlatDetailDto.setInvTime(event.getOperateTime());
					fluxPlatDetailDto.setMemberOperecordId(memberOperecord.getId());
					fluxPlatDetailDto.setCreateDate(new Date());
					//手机号段
					if(telOpe.equals("cmcc")){
						if(StringUtils.isEmpty(cmccCodes)){
							logger.info("[process] ERROR={} 选择流量公社无移动编码，规则配置错误");
							return;
						}
						String[] cmccCode = cmccCodes.split("#"); 
						for(int i = 0; i < cmccCode.length; i++){
							fluxPlatDetailDto.setCmccFlowId(cmccCode[i]);
							newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
						}
					}else if(telOpe.equals("cucc")){
						if(StringUtils.isEmpty(cuccCodes)){
							logger.info("[process] ERROR={} 选择流量公社无联通编码，规则配置错误");
							return;
						}
						String[] cuccCode = cuccCodes.split("#");
						for(int i = 0; i < cuccCode.length; i++){
							fluxPlatDetailDto.setCuccFlowId(cuccCode[i]);
							newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
						}
					}else if(telOpe.equals("ctcc")){
						if(StringUtils.isEmpty(ctccCodes)){
							logger.info("[process] ERROR={} 选择流量公社无电信编码，规则配置错误");
							return;
						}
						String[] ctccCode = ctccCodes.split("#");
						for(int i = 0; i < ctccCode.length; i++){
							fluxPlatDetailDto.setCtccFlowId(ctccCode[i]);
							newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
						}
					}
				}else if(activitySrcFlowPlat.getFluxPlatCode().equals("FLUX_CLOUD")){
					FluxCloudDetailDto fluxCloudDetailDto = new FluxCloudDetailDto();
					fluxCloudDetailDto.setMemberNo(event.getMemberNo());
					fluxCloudDetailDto.setMobileNo(memberDto.getBindMobileNo());
					fluxCloudDetailDto.setInvType(opeType);
					fluxCloudDetailDto.setInvAmount(BigDecimal.ZERO);
					fluxCloudDetailDto.setInvTime(event.getOperateTime());
					fluxCloudDetailDto.setMemberOperecordId(memberOperecord.getId());
					fluxCloudDetailDto.setCreateDate(new Date());
					//手机号段
					if(telOpe.equals("cmcc")){
						if(StringUtils.isEmpty(cmccCodes)){
							logger.info("[process] ERROR={} 选择流量云无移动编码，规则配置错误");
							return;
						}
						String[] cmccCode = cmccCodes.split("#"); 
						for(int i = 0; i < cmccCode.length; i++){
							fluxCloudDetailDto.setCmccFlowid(cmccCode[i]);
							newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
						}
					}else if(telOpe.equals("cucc")){
						if(StringUtils.isEmpty(cuccCodes)){
							logger.info("[process] ERROR={} 选择流量云无联通编码，规则配置错误");
							return;
						}
						String[] cuccCode = cuccCodes.split("#");
						for(int i = 0; i < cuccCode.length; i++){
							fluxCloudDetailDto.setCuccFlowid(cuccCode[i]);
							newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
						}
					}else if(telOpe.equals("ctcc")){
						if(StringUtils.isEmpty(ctccCodes)){
							logger.info("[process] ERROR={} 选择流量云却无电信编码，规则配置错误");
							return;
						}
						String[] ctccCode = ctccCodes.split("#");
						for(int i = 0; i < ctccCode.length; i++){
							fluxCloudDetailDto.setCtccFlowid(ctccCode[i]);
							newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
						}
					}
				}
			}catch(Exception e){
				logger.error("[process] ERROR={}",e);
			}
			
		}
			
		
	}

	@Override
	public void sendInvestFlow(LMTradeMessageEvent event,
			ActivityMemberOperecord memberOperecord) {
		logger.info("[sendInvestFlow] event={}",event);
		logger.info("[sendInvestFlow] info={}","投资发放流量");
		String tradeType=event.getTrustTradeType();
		ArenaDetailTypeEnum opeType=null;
		
		//1.查询会员信息，获取渠道号
		MemberDto memberDto = memberManagementFacade.obtainMember(event.getMemberNo());
		logger.info("[sendInvestFlow] memberDto="+memberDto);
		//2.根据渠道号和操作类型和投资金额去查询渠道发放流量规则
		List<ActivitySrcFlowRule> list = new ArrayList<ActivitySrcFlowRule>();
		Map<String,Object> map = new HashMap<String,Object>();
		//新手发送流量规则不需要投资金额
		if("NOVICE".equals(tradeType)){
			opeType=ArenaDetailTypeEnum.NOVICE;
			map.put("opeType",tradeType);
		//首次购买标准和首次购买新手二次购买标准 都需要送流量且送流量规则相同
		}else if("FIRST_GENERAL".equals(tradeType)||"SECOND_GENERAL".equals(tradeType)){
			opeType=ArenaDetailTypeEnum.XTLC;
			map.put("opeType","INVEST");
			map.put("opeAmount", event.getAmount());
		//tradeType为OTHER类型 不参与送流量
		}else{
			return;
		}
		map.put("srcNo", memberDto.getRegisterSrcNo());
		logger.info("[sendInvestFlow] map={}",map);
		List<ActivitySrcFlowRule> ruleList = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
		logger.info("[sendInvestFlow] ruleList={}",ruleList);
		if (ruleList.size() != 0 && ruleList.get(0).getSendFlag() == 1) {
				list = ruleList;
		}else{
			//ruleList为空说明此渠道可能没有特殊的送流量规则 去查询默认
			map.put("srcNo", "default");
			logger.info("[sendInvestFlow] map={}",map);
			List<ActivitySrcFlowRule> rulelist = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
			logger.info("[sendInvestFlow] rulelist={}",rulelist);
			if(rulelist.size() != 0 && rulelist.get(0).getSendFlag() == 1){
				list = rulelist;
			}
		}
		logger.info("[sendInvestFlow] map={}",map);
		if(list.size() == 0){
			logger.info("[sendInvestFlow] info = 不发送流量");
			return;
		}
		Date today = new Date();
		if(list.get(0).getStartDate() != null && today.getTime() < list.get(0).getStartDate().getTime()){
			logger.info("[sendInvestFlow] info = 规则未生效或已过期");
			return;
		}
		if(list.get(0).getEndDate() != null && today.getTime() > list.get(0).getEndDate().getTime()){
			logger.info("[sendInvestFlow] info = 规则未生效或已过期");
			return;
		}
		logger.info("[sendInvestFlow] ruleList ="+list.get(0));
		String cmccCodes = list.get(0).getCmccCode();
//		String[] cmccCode = cmccCodes.split("#"); 
		String cuccCodes = list.get(0).getCuccCode();
//		String[] cuccCode = cuccCodes.split("#");
		String ctccCodes = list.get(0).getCtccCode();
//		String[] ctccCode = ctccCodes.split("#");
		//3.根据渠道号和手机号前三位去查询对应的发放流量通道
		ActivitySrcFlowPlat activitySrcFlowPlat = activitySrcFlowPlatServiceImpl.getSrcPlowPlatBySrcNo(memberDto.getRegisterSrcNo(), event.getMobileNo());
		if(activitySrcFlowPlat == null){
			logger.info("[sendInvestFlow] info = 该渠道无流量发放通道");
			return;
		}
		logger.info("[sendInvestFlow] activitySrcFlowPlat ="+activitySrcFlowPlat);
		//4.根据手机号判断是移动、联通还是电信(统一配置)
		ConfigParam<Map<String,String>> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "telecom_operators");
		String telOpe= config.getValue().get(event.getMobileNo().substring(0, 3));
		//5.根据流量通道选择调用哪个接口
		//天翼800只充电信
		try{
			if(activitySrcFlowPlat.getFluxPlatCode().equals("ESURFING800")){
//					if(!telOpe.equals("ctcc")){
//						logger.info("LMTradeMessageProcessor:[process] ERROR={} 规则配置错误");
//						return;
//					}
				//天翼800
				Esurfing800DetailDto esurfing800DetailDto = new Esurfing800DetailDto();
				esurfing800DetailDto.setMemberNo(event.getMemberNo());
				esurfing800DetailDto.setMobileNo(event.getMobileNo());
				esurfing800DetailDto.setInvType(opeType);
				esurfing800DetailDto.setInvAmount(event.getAmount());
				esurfing800DetailDto.setInvTime(event.getTradeTime());
				esurfing800DetailDto.setMemberOperecordId(memberOperecord.getId());
				esurfing800DetailDto.setCreateDate(new Date());
				logger.info("[sendInvestFlow] esurfing800DetailDto={}",esurfing800DetailDto);
				//手机号段
				if(StringUtils.isEmpty(ctccCodes)){
					logger.info("[sendInvestFlow] ERROR={} 选择天翼通道却无电信编码");
					return;
				}
				String[] ctccCode = ctccCodes.split("#");
				for( int i = 0 ; i < ctccCode.length ;i ++){
					esurfing800DetailDto.setPlatOfferId(ctccCode[i]);
					newLMACTFluxEsuring800Facade.insertEsurfing800Detail(esurfing800DetailDto);
				}
			}else if(activitySrcFlowPlat.getFluxPlatCode().equals("FLUX_PLAT")){
				//流量公社
				FluxPlatDetailDto fluxPlatDetailDto = new FluxPlatDetailDto();
				fluxPlatDetailDto.setMemberNo(event.getMemberNo());
				fluxPlatDetailDto.setMobileNo(event.getMobileNo());
				fluxPlatDetailDto.setInvType(opeType);
				fluxPlatDetailDto.setInvAmount(event.getAmount());
				fluxPlatDetailDto.setInvTime(event.getTradeTime());
				fluxPlatDetailDto.setMemberOperecordId(memberOperecord.getId());
				fluxPlatDetailDto.setCreateDate(new Date());
				//手机号段
				if(telOpe.equals("cmcc")){
					if(StringUtils.isEmpty(cmccCodes)){
						logger.info("[sendInvestFlow] ERROR={} 选择流量公社无移动编码，规则配置错误");
						return;
					}
					String[] cmccCode = cmccCodes.split("#"); 
					for(int i = 0; i < cmccCode.length; i++){
						fluxPlatDetailDto.setCmccFlowId(cmccCode[i]);
						newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
					}
				}else if(telOpe.equals("cucc")){
					if(StringUtils.isEmpty(cuccCodes)){
						logger.info("[sendInvestFlow] ERROR={} 选择流量公社无联通编码，规则配置错误");
						return;
					}
					String[] cuccCode = cuccCodes.split("#");
					for(int i = 0; i < cuccCode.length; i++){
						fluxPlatDetailDto.setCuccFlowId(cuccCode[i]);
						newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
					}
				}else if(telOpe.equals("ctcc")){
					if(StringUtils.isEmpty(ctccCodes)){
						logger.info("[sendInvestFlow] ERROR={} 选择流量公社无电信编码，规则配置错误");
						return;
					}
					String[] ctccCode = ctccCodes.split("#");
					for(int i = 0; i < ctccCode.length; i++){
						fluxPlatDetailDto.setCtccFlowId(ctccCode[i]);
						newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
					}
				}
			}else if(activitySrcFlowPlat.getFluxPlatCode().equals("FLUX_CLOUD")){
				FluxCloudDetailDto fluxCloudDetailDto = new FluxCloudDetailDto();
				fluxCloudDetailDto.setMemberNo(event.getMemberNo());
				fluxCloudDetailDto.setMobileNo(event.getMobileNo());
				fluxCloudDetailDto.setInvType(opeType);
				fluxCloudDetailDto.setInvAmount(event.getAmount());
				fluxCloudDetailDto.setInvTime(event.getTradeTime());
				fluxCloudDetailDto.setMemberOperecordId(memberOperecord.getId());
				fluxCloudDetailDto.setCreateDate(new Date());
				//手机号段
				if(telOpe.equals("cmcc")){
					if(StringUtils.isEmpty(cmccCodes)){
						logger.info("[sendInvestFlow] ERROR={} 选择流量云无移动编码，规则配置错误");
						return;
					}
					String[] cmccCode = cmccCodes.split("#"); 
					for(int i = 0; i < cmccCode.length; i++){
						fluxCloudDetailDto.setCmccFlowid(cmccCode[i]);
						newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
					}
				}else if(telOpe.equals("cucc")){
					if(StringUtils.isEmpty(cuccCodes)){
						logger.info("[sendInvestFlow] ERROR={} 选择流量云无联通编码，规则配置错误");
						return;
					}
					String[] cuccCode = cuccCodes.split("#");
					for(int i = 0; i < cuccCode.length; i++){
						fluxCloudDetailDto.setCuccFlowid(cuccCode[i]);
						newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
					}
				}else if(telOpe.equals("ctcc")){
					if(StringUtils.isEmpty(ctccCodes)){
						logger.info("[sendInvestFlow] ERROR={} 选择流量云却无电信编码，规则配置错误");
						return;
					}
					String[] ctccCode = ctccCodes.split("#");
					for(int i = 0; i < ctccCode.length; i++){
						fluxCloudDetailDto.setCtccFlowid(ctccCode[i]);
						newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
					}
				}
			}
		}catch(Exception e){
			logger.error("[sendInvestFlow] ERROR={}",e);
		}
	}
	
	@Override
	public void sendFlowByOtherWays(String memberNo , ArenaDetailTypeEnum otherType , String otherSrcNo) {
		logger.info("[sendFlowByOtherWays] memberNo={},otherType={},otherSrcNo={}",memberNo,otherType,otherSrcNo);
		//绑卡送流量
		if(null == memberNo || null == otherType || null == otherSrcNo) {
			logger.info("[sendFlowByOtherWays] info = 查询规则不完全");
			return;
		}
		
		//1.查询会员信息，获取渠道号
		MemberDto memberDto = memberManagementFacade.obtainMember(memberNo);
		logger.info("[sendFlowByOtherWays] memberDto="+memberDto);
		//2.根据渠道号和操作类型去查询渠道发放流量规则
		List<ActivitySrcFlowRule> list = new ArrayList<ActivitySrcFlowRule>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("srcNo", otherSrcNo);
		map.put("opeType", otherType);
		List<ActivitySrcFlowRule> ruleList = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
		if(ruleList.size() != 0 && ruleList.get(0).getSendFlag() == 1){
			list = ruleList;
		}else{
			map.put("srcNo", "default");
			map.put("opeType", otherType);
			List<ActivitySrcFlowRule> rulelist = activitySrcFlowRuleServiceImpl.selectRuleListByParams(map);
			if(rulelist.size() != 0 && rulelist.get(0).getSendFlag() == 1){
				list = rulelist;
			}
		}
		
		if(list.size() == 0){
			logger.info("[process] info = 不发送流量");
			return;
		}
		Date today = new Date();
		if(list.get(0).getStartDate() != null && today.getTime() < list.get(0).getStartDate().getTime()){
			logger.info("[process] info = 规则未生效或已过期");
			return;
		}
		if(list.get(0).getEndDate() != null && today.getTime() > list.get(0).getEndDate().getTime()){
			logger.info("[process] info = 规则未生效或已过期");
			return;
		}
		logger.info("[process] ruleList ="+list.get(0));
		String cmccCodes = list.get(0).getCmccCode();
//			String[] cmccCode = cmccCodes.split("#"); 
		String cuccCodes = list.get(0).getCuccCode();
//			String[] cuccCode = cuccCodes.split("#");
		String ctccCodes = list.get(0).getCtccCode();
//			String[] ctccCode = ctccCodes.split("#");
		//3.根据渠道号和手机号前三位去查询对应的发放流量通道
		ActivitySrcFlowPlat activitySrcFlowPlat = activitySrcFlowPlatServiceImpl.getSrcPlowPlatBySrcNo(otherSrcNo.toString(), memberDto.getBindMobileNo());
		if(activitySrcFlowPlat == null){
			logger.info("[process] info = 该渠道无流量发放通道");
			return;
		}
		logger.info("[process] activitySrcFlowPlat ="+activitySrcFlowPlat.getFluxPlatCode());
		//4.根据手机号判断是移动、联通还是电信(统一配置)
//		String telOpe = "ctcc";
		ConfigParam<Map<String,String>> config = ConfigurationUtils.getConfigParam("config_type_text_resources", "telecom_operators");
		String telOpe= config.getValue().get(memberDto.getBindMobileNo().substring(0, 3));
		logger.info("[process] telOpe = " + telOpe);
		//5.根据流量通道选择调用哪个接口
		//天翼800只充电信
		try{
			if(activitySrcFlowPlat.getFluxPlatCode().equals("ESURFING800")){
//					if(!telOpe.equals("ctcc")){
//						logger.info("[process] ERROR={} 通道配置错误,天翼只能充电信");
//						return;
//					}
				//天翼800
				Esurfing800DetailDto esurfing800DetailDto = new Esurfing800DetailDto();
				esurfing800DetailDto.setMemberNo(memberDto.getMemberNo());
				esurfing800DetailDto.setMobileNo(memberDto.getBindMobileNo());
				esurfing800DetailDto.setInvType(otherType);
				esurfing800DetailDto.setInvAmount(BigDecimal.ZERO);
				esurfing800DetailDto.setInvTime(new Date());
				esurfing800DetailDto.setMemberOperecordId(null);
				esurfing800DetailDto.setCreateDate(new Date());
				//手机号段
				if(StringUtils.isEmpty(ctccCodes)){
					logger.info("[process] ERROR={} 选择天翼通道却无电信编码");
					return;
				}
				String[] ctccCode = ctccCodes.split("#");
				for( int i = 0 ; i < ctccCode.length ;i ++){
					esurfing800DetailDto.setPlatOfferId(ctccCode[i]);
					newLMACTFluxEsuring800Facade.insertEsurfing800Detail(esurfing800DetailDto);
				}
			}else if(activitySrcFlowPlat.getFluxPlatCode().equals("FLUX_PLAT")){
				//流量公社
				FluxPlatDetailDto fluxPlatDetailDto = new FluxPlatDetailDto();
				fluxPlatDetailDto.setMemberNo(memberDto.getMemberNo());
				fluxPlatDetailDto.setMobileNo(memberDto.getBindMobileNo());
				fluxPlatDetailDto.setInvType(otherType);
				fluxPlatDetailDto.setInvAmount(BigDecimal.ZERO);
				fluxPlatDetailDto.setInvTime(new Date());
				fluxPlatDetailDto.setMemberOperecordId(null);
				fluxPlatDetailDto.setCreateDate(new Date());
				//手机号段
				if(telOpe.equals("cmcc")){
					if(StringUtils.isEmpty(cmccCodes)){
						logger.info("[process] ERROR={} 选择流量公社无移动编码，规则配置错误");
						return;
					}
					String[] cmccCode = cmccCodes.split("#"); 
					for(int i = 0; i < cmccCode.length; i++){
						fluxPlatDetailDto.setCmccFlowId(cmccCode[i]);
						newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
					}
				}else if(telOpe.equals("cucc")){
					if(StringUtils.isEmpty(cuccCodes)){
						logger.info("[process] ERROR={} 选择流量公社无联通编码，规则配置错误");
						return;
					}
					String[] cuccCode = cuccCodes.split("#");
					for(int i = 0; i < cuccCode.length; i++){
						fluxPlatDetailDto.setCuccFlowId(cuccCode[i]);
						newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
					}
				}else if(telOpe.equals("ctcc")){
					if(StringUtils.isEmpty(ctccCodes)){
						logger.info("[process] ERROR={} 选择流量公社无电信编码，规则配置错误");
						return;
					}
					String[] ctccCode = ctccCodes.split("#");
					for(int i = 0; i < ctccCode.length; i++){
						fluxPlatDetailDto.setCtccFlowId(ctccCode[i]);
						newLMACTFluxPlatFacade.insertFluxPlatDetail(fluxPlatDetailDto);
					}
				}
			}else if(activitySrcFlowPlat.getFluxPlatCode().equals("FLUX_CLOUD")){
				FluxCloudDetailDto fluxCloudDetailDto = new FluxCloudDetailDto();
				fluxCloudDetailDto.setMemberNo(memberDto.getMemberNo());
				fluxCloudDetailDto.setMobileNo(memberDto.getBindMobileNo());
				fluxCloudDetailDto.setInvType(otherType);
				fluxCloudDetailDto.setInvAmount(BigDecimal.ZERO);
				fluxCloudDetailDto.setInvTime(new Date());
				fluxCloudDetailDto.setMemberOperecordId(null);
				fluxCloudDetailDto.setCreateDate(new Date());
				//手机号段
				if(telOpe.equals("cmcc")){
					if(StringUtils.isEmpty(cmccCodes)){
						logger.info("[process] ERROR={} 选择流量云无移动编码，规则配置错误");
						return;
					}
					String[] cmccCode = cmccCodes.split("#"); 
					for(int i = 0; i < cmccCode.length; i++){
						fluxCloudDetailDto.setCmccFlowid(cmccCode[i]);
						newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
					}
				}else if(telOpe.equals("cucc")){
					if(StringUtils.isEmpty(cuccCodes)){
						logger.info("[process] ERROR={} 选择流量云无联通编码，规则配置错误");
						return;
					}
					String[] cuccCode = cuccCodes.split("#");
					for(int i = 0; i < cuccCode.length; i++){
						fluxCloudDetailDto.setCuccFlowid(cuccCode[i]);
						newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
					}
				}else if(telOpe.equals("ctcc")){
					if(StringUtils.isEmpty(ctccCodes)){
						logger.info("[process] ERROR={} 选择流量云却无电信编码，规则配置错误");
						return;
					}
					String[] ctccCode = ctccCodes.split("#");
					for(int i = 0; i < ctccCode.length; i++){
						fluxCloudDetailDto.setCtccFlowid(ctccCode[i]);
						newLMACTFluxCloudFacade.insertFluxCloudDetail(fluxCloudDetailDto);
					}
				}
			}
		}catch(Exception e){
			logger.error("[process] ERROR={}",e);
		}
		
		
		
		
	}

	@Override
	public List<ActivityMemberOperecord> queryActivityMemberOperecordList(
			ActivityMemberOperecord activityMemberOperecord) {
		List<ActivityMemberOperecord> memberOperecords = new ArrayList<ActivityMemberOperecord>();
		memberOperecords = activityMemberOperecordImpl.queryActivityMemberOperecordList(activityMemberOperecord.getMemberNo(), 
				null, null, activityMemberOperecord.getOperateType());
		return memberOperecords;
	}
	
}
