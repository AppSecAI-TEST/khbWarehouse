package com.yeepay.g3.core.activity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityActionDao;
import com.yeepay.g3.core.activity.dao.ActivityActionRelaDao;
import com.yeepay.g3.core.activity.dao.ActivityInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityRaffleTicketDao;
import com.yeepay.g3.core.activity.dao.ActivityUserInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.dao.ActivityUserRaffleticketDao;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.entity.ActivityInfo;
import com.yeepay.g3.core.activity.entity.ActivityRaffleTicket;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.core.activity.entity.ActivityUserRaffleticket;
import com.yeepay.g3.core.activity.service.ActivityMemberOperecordService;
import com.yeepay.g3.core.activity.service.ActivityUserRaffleTicketService;
import com.yeepay.g3.core.activity.utils.DateFormatUtil;
import com.yeepay.g3.facade.activity.dto.ActivityUserRaffleticketDTO;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.activity.enums.UserRaffleticketStatusEnum;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;



@Service
public class ActivityUserRaffleTicketServiceImpl implements ActivityUserRaffleTicketService {
	
	private  Logger logger = LoggerFactory.getLogger(ActivityUserRaffleTicketServiceImpl.class);
	
	@Autowired
	private ActivityUserRaffleticketDao activityUserRaffleticketDaoImpl;
	@Autowired
	private ActivityRaffleTicketDao activityRaffleTicketDaoImpl;
	@Resource
	private ActivityActionRelaDao activityActionRelaDaoImpl;
	@Resource
	private ActivityUserInfoDao activityUserInfoDaoImpl;
	@Resource
	private ActivityInfoDao activityInfoDaoImpl;
	@Resource
	private ActivityActionDao activityActionDaoImpl;
	@Resource
	private ActivityMemberOperecordService activityMemberOperecordServiceImpl;
	@Autowired
	private ActivityUserMessageDao activityUserMessageDaoImpl;
	private MemberManagementFacade memberManagementFacade = RemoteServiceFactory.getService(MemberManagementFacade.class);
	@Override
	public List<ActivityUserRaffleticket> selectListByParams(
			ActivityUserRaffleticketDTO activityUserRaffleTicketDto) {
		List<ActivityUserRaffleticket> userRaffleticketList = activityUserRaffleticketDaoImpl.query("queryUserRaffleticketCountByStatus", activityUserRaffleTicketDto);
		return userRaffleticketList;
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityUserRaffleTicketService#queryUserRaffleticketCountByMemberNO(java.lang.String)
	 */
	@Override
	public ActivityUserRaffleticket queryUserRaffleticketByMemberNO(
			String memberNo) {
		
		return (ActivityUserRaffleticket) activityUserRaffleticketDaoImpl.queryOne("queryUserRaffleticketByMemberNO", memberNo);
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityUserRaffleTicketService#addActivityUserRaffleTicketList(java.lang.String, java.lang.Integer, java.lang.Long)
	 */
	@Override
	public void addActivityUserRaffleTicketList(String memberNoList,
			Integer num, Long id,Long version,Integer grantCount,String actionCode,String activityCode) throws Exception {
		
		//1.更改抽奖券信息表
		String[] memberNoArray = memberNoList.split(";");
		ActivityRaffleTicket activityRaffleTicket = new ActivityRaffleTicket();
		activityRaffleTicket.setId(id);
		activityRaffleTicket.setVersion(version);
		activityRaffleTicket.setGrantCount(grantCount+num*memberNoArray.length);
		activityRaffleTicketDaoImpl.update(activityRaffleTicket);
		//2.验证 activityCode，actionCode，memberNoList是否正确有效
		Map<String,String> params = new HashMap<String, String>();
		params.put("actionCode", actionCode);
		params.put("activityCode", activityCode);
		Integer activityCount = activityActionRelaDaoImpl.queryActivityByActionCode(params);
		if(activityCount==null||activityCount==0){
			throw new Exception("NOACTIVITY");
		}
		
		for(String memberNo :memberNoArray){
			
//			ActivityUserInfo activityUser = (ActivityUserInfo) activityUserInfoDaoImpl.queryOne("selectUserInfoByMemberNo", memberNo);
//			if(activityUser==null){
//				throw new Exception("NOMEMBER");
//			}
			//判断用户是否正确
			MemberDto member = new MemberDto();
			try {
				 member = memberManagementFacade.obtainMember(memberNo);
			} catch (Exception e) {
				logger.error("[addActivityUserRaffleTicketList] 判断用户是否正确获取的异常是:{},用户编号是：{}",e.getMessage(),memberNo);
			}
			ActivityInfo activityInfo = (ActivityInfo) activityInfoDaoImpl.queryOne("queryActivityInfoByActivityCode", activityCode);
			ActivityAction activityAction = new ActivityAction();
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("actionCode", actionCode);
			activityAction=(ActivityAction) activityActionDaoImpl.queryOne("selectByParams",param );
			//循环num次发放抽奖券
			for(int i=0;i<num;i++){
				//3.新增用户抽奖券关系
				//查询用户手机号
				ActivityUserRaffleticket  activityUserRaffleticket = new ActivityUserRaffleticket ();
				activityUserRaffleticket.setRaffleTicketId(id);
				activityUserRaffleticket.setMemberNo(memberNo);
				activityUserRaffleticket.setMemberTel(member==null?null:member.getBindMobileNo());
				activityUserRaffleticket.setValidityTimeStart(new Date());
//				activityUserRaffleticket.setValidityTimeEnd(validityTimeEnd);
				activityUserRaffleticket.setReceiveTime(new Date());
				activityUserRaffleticket.setActivityId(activityInfo.getId());
				activityUserRaffleticket.setActivityName(activityInfo.getActivityName());
				activityUserRaffleticket.setActionId(activityAction.getId());
				activityUserRaffleticket.setStatus(UserRaffleticketStatusEnum.UN_USE);
				activityUserRaffleticketDaoImpl.add(activityUserRaffleticket);
			}
			//4.新增用户消息记录
			ActivityUserMessage userMessage = new ActivityUserMessage();
			userMessage.setMemberNo(memberNo);
			JSONObject msgParam = new JSONObject();
			msgParam.put("num",num.toString());
			msgParam.put("createTime",DateFormatUtil.dateFormat("yyyy-MM-dd HH:mm:ss", new Date()));
			msgParam.put("occurTime", DateFormatUtil.dateFormat("yyyy-MM-dd HH:mm:ss", new Date()));
			userMessage.setMsgContent(msgParam.toString());
			userMessage.setOccurTime(new Date());
			userMessage.setReadStatus(UserMessageReadStatusEnum.UNREAD);
			userMessage.setTemplateName("common_receive_raffle_ticket_4gift.ftl");
			userMessage.setMemberNo(memberNo);
			activityUserMessageDaoImpl.add(userMessage);
		}
		
	}

	
	
	


}

