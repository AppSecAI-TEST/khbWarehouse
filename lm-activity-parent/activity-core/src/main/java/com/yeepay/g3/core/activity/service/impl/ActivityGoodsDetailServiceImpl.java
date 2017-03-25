/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityActionDao;
import com.yeepay.g3.core.activity.dao.ActivityGoodsDao;
import com.yeepay.g3.core.activity.dao.ActivityGoodsDetailDao;
import com.yeepay.g3.core.activity.dao.ActivityInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityPrizeDao;
import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.dao.ActivityUserPrizeDao;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.entity.ActivityGoodsDetail;
import com.yeepay.g3.core.activity.entity.ActivityInfo;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.core.activity.service.ActivityGoodsDetailService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.core.activity.utils.DateFormatUtil;
import com.yeepay.g3.core.activity.utils.GetParamUtils;
import com.yeepay.g3.facade.activity.enums.ActivityStatusEnum;
import com.yeepay.g3.facade.activity.enums.GoodsTypeEnum;
import com.yeepay.g3.facade.activity.enums.GoodsUsedStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeGrantStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeGrantWayEnum;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.lmnotice.dto.SendNoticeParamDto;
import com.yeepay.g3.facade.lmnotice.dto.SendNoticeResultDto;
import com.yeepay.g3.facade.lmnotice.service.NoticeFacade;
import com.yeepay.g3.facade.lmportal.dto.MemberDto;
import com.yeepay.g3.facade.lmportal.service.MemberManagementFacade;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.persistence.OptimisticLockingException;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;


/**
 * @Title: 商品详情业务逻辑处理实现类
 * @Description: 兑换码业务逻辑处理
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-31 下午2:13:21
 * @version 2016-8-31
 */
@Service
public class ActivityGoodsDetailServiceImpl implements ActivityGoodsDetailService {
	
    private static Logger logger = LoggerFactory.getLogger(ActivityGoodsDetailServiceImpl.class);
    
    private MemberManagementFacade memberManagementFacade = RemoteServiceFactory.getService(MemberManagementFacade.class);

    private NoticeFacade noticeFacade = RemoteServiceFactory.getService(NoticeFacade.class);
    @Autowired
    private ActivityGoodsDetailDao activityGoodsDetailDaoImpl;
    
    @Autowired
	private ActivityActionDao activityActionDaoImpl;
    
    @Autowired
	private ActivityInfoDao activityInfoDaoImpl;
    
    @Autowired
	private ActivityGoodsDao activityGoodsDaoImpl;
    
    @Autowired
    private ActivityUserPrizeDao activityUserPrizeDaoImpl;
    
    @Autowired
    private ActivityUserMessageDao activityUserMessageDaoImpl;
    
    @Resource
	private ActivityPrizeDao activityPrizeDaoImpl;
    
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ActivityGoodsDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityGoodsDetail selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityGoodsDetail record) {
		return 0;
	}

	@Override
	public List<ActivityGoodsDetail> selectListByParams(
			Map<String, Object> params) {
		List<ActivityGoodsDetail> list = activityGoodsDetailDaoImpl.query("selectByParams", params);
		return list;
	}

	@Override
	public void updateByParams(ActivityGoodsDetail record) {
		activityGoodsDetailDaoImpl.updateByParams(record);
	}
	@Override
	public void addActivityGoodsDetail(ActivityGoodsDetail record) {
		activityGoodsDetailDaoImpl.add(record);
	}

	@Override
	public void addGoodsDetailList(
			List<ActivityGoodsDetail> activityGoodsDetailList) {
		if(activityGoodsDetailList!=null){
			//1.修改商品库存量
			//先查询库存量
			ActivityGoods activityGoodsOld = (ActivityGoods)activityGoodsDaoImpl.queryOne("selectByPrimaryKey", activityGoodsDetailList.get(0).getGoodId());
			ActivityGoods activityGoods = new ActivityGoods();
			activityGoods.setId(activityGoodsDetailList.get(0).getGoodId());
			activityGoods.setVersion(activityGoodsOld.getVersion());
			activityGoods.setTotalCount(activityGoodsOld.getTotalCount()==null?activityGoodsDetailList.size():activityGoodsOld.getTotalCount()+activityGoodsDetailList.size());
			activityGoodsDaoImpl.update(activityGoods);
			//2.新增商品明细信息
			for(ActivityGoodsDetail items : activityGoodsDetailList){
				items.setUsedStatus(GoodsUsedStatusEnum.UNUSED);//初始化参数
				activityGoodsDetailDaoImpl.add(items);
			}
		}
	}

	@Override
	public void addUserGoodsDetail(ActivityMemberOperecord memberOperecord) {
		logger.info("[addUserGoodsDetail] memberOperecord={}",memberOperecord);
		//1.取统一配置，得map
		Map<String,Object> configMap =  (Map<String, Object>) GetParamUtils.readActionCode();
		//2.取mq中的会员的渠道号和操作类型
		MemberDto memberDto = memberManagementFacade.obtainMember(memberOperecord.getMemberNo());
		logger.info("[addUserGoodsDetail] memberDto={}",memberDto);
		String key = memberOperecord.getOperateType() + GetParamUtils.COMMON_MESSAGE_SPLIT + memberDto.getRegisterSrcNo();
		logger.info("[addUserGoodsDetail] key={}",key);
		Object actionCode = configMap.get(key);
		//该渠道的事件编码不存在，查询是否配置了全渠道
		if(actionCode == null){
			actionCode = configMap.get(memberOperecord.getOperateType());
		}
		if(actionCode == null){
			return;
		}
		logger.info("[addUserGoodsDetail] actionCode={}",actionCode);
		//3.取出活动事件编码，根据事件编码查询当前事件动作信息
		Map<String, Object> actionParam = new HashMap<String, Object>();
		actionParam.put("actionCode", actionCode);
		logger.info("[addUserGoodsDetail] actionParam={}",actionParam);
		List<ActivityAction> actionList = activityActionDaoImpl.query("selectByParams", actionParam);
		logger.info("[addUserGoodsDetail] actionList={}",actionList);
		if(actionList != null && actionList.size() != 0){
			//事件编码是唯一的
			ActivityAction action = actionList.get(0);
			//4.查询当前事件对应的活动，并判断活动是否有效
			Map<String, Object> activityParam = new HashMap<String, Object>();
			activityParam.put("activityStatus", ActivityStatusEnum.EFFECTIVE);
			activityParam.put("actionId", action.getId());
			List<ActivityInfo> activityList = activityInfoDaoImpl.query("selectByActionId", activityParam);
			logger.info("[addUserGoodsDetail] activityList={}",activityList);
			if(activityList != null && activityList.size() != 0){
				Date currentdate = new Date();
				ActivityInfo activityInfo = activityList.get(0);//活动信息
				Map<String,Object> map = new LinkedHashMap<String,Object>();
				//取活动开始时间和结束时间
				Date startDate = activityInfo.getStartTime();
				Date endDate = activityInfo.getEndTime();
				//活动是否到期
				if(currentdate.getTime()<startDate.getTime() || currentdate.getTime() > endDate.getTime()){
					logger.info("[addUserGoodsDetail] error={}","活动没开始或已到期");
					return;
				}
				//5.活动有效，根据事件id查询奖品信息
				List<ActivityPrize> activityPrizeList = activityPrizeDaoImpl.selectByActionId(action.getId());
				logger.info("[addUserGoodsDetail] activityPrizeList={}",activityPrizeList);
				if(activityPrizeList == null || activityPrizeList.size() == 0){
					logger.info("[addUserGoodsDetail] info={}","该事件无奖品发放");
					return;
				}
				//6.奖品信息存在，判断其属于哪种类型的商品，是否是兑换码
				ActivityPrize activityPrize = activityPrizeList.get(0);
				//查询奖品对应的商品信息
				ActivityGoods activityGoods = (ActivityGoods) activityGoodsDaoImpl.queryOne("selectByPrimaryKey", activityPrize.getRelaGoodsId());
				logger.info("[addUserGoodsDetail] activityGoods={}",activityGoods);
				if(activityGoods == null){
					logger.info("[addUserGoodsDetail] activityGoods={}","商品信息不存在");
					return;
				}
				//7.是兑换码，则发送兑换码
				if(GoodsTypeEnum.REDEEMCODE.equals(activityGoods.getGoodsType())){
					this.sendRedeemCode(activityGoods, activityPrize, memberOperecord);
				}else{
					//非兑换码，看后续需求
				}
			}else{
				logger.info("[addUserGoodsDetail] ERROR={}","活动配置错误，该事件应关联一个活动，才能使用活动的有效期限");
			}
		}
		
	}
	/**
	 * 兑换码的消息公告参数
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	public ActivityUserMessage initUserMessage(String memberTel,Date occurTime,
			String templateName,String memberNo,
			String prizeName,String goodsName,String redeemCode){
		ActivityUserMessage userMessage = new ActivityUserMessage();
		try {
			userMessage.setMemberNo(memberNo);
			JSONObject msgParam = new JSONObject();
			msgParam.put("createTime",DateFormatUtil.dateFormat("yyyy-MM-dd HH:mm:ss", new Date()));
			msgParam.put("prizeName", prizeName);
			msgParam.put("goodsName", goodsName);
			msgParam.put("memberTel", memberTel);
			msgParam.put("redeemCode", redeemCode);
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
	 * 发送兑换码
	 * @param in -
	 * @return 
	 */
	public void sendRedeemCode(ActivityGoods activityGoods,ActivityPrize activityPrize,ActivityMemberOperecord memberOperecord){
		logger.info("[sendRedeemCode] activityGoodId={},activityPrize={},memberOperecord={}",activityGoods,activityPrize,memberOperecord);
		//查询未使用的兑换码，取第一条，更新兑换码的状态
		Map<String, Object> params = new LinkedHashMap<String,Object>();
		params.put("goodId", activityGoods.getId());
		params.put("usedStatus", GoodsUsedStatusEnum.UNUSED);
		List<ActivityGoodsDetail> list = activityGoodsDetailDaoImpl.query("selectByParams", params);
		logger.info("[sendRedeemCode] list={}",list);
		if(list == null || list.size() ==0){
			logger.info("[sendRedeemCode] info={}","兑换码不存在已全部发完");
			return;
		}
		ActivityGoodsDetail activityGoodsDetail = list.get(0);
		activityGoodsDetail.setUsedStatus(GoodsUsedStatusEnum.USED);
		activityGoodsDetail.setUsedTime(new Date());
    	try{
    		activityGoodsDetailDaoImpl.updateByParams(activityGoodsDetail);
    	}catch(OptimisticLockingException e){
    		logger.info("[sendRedeemCode] ERROR={}","更新状态失败");
    		sendRedeemCode(activityGoods,activityPrize,memberOperecord);
    		return;
    	}
    	//更新商品已发数量
    	try{
    		activityGoods.setGrantCount(activityGoods.getGrantCount() + Long.valueOf(1));
        	activityGoodsDaoImpl.update(activityGoods);
    	}catch(Exception e){
    		logger.info("[sendRedeemCode] ERROR={}","更新商品已发数量异常");
    	}
    	//添加中奖记录
    	try{
        	ActivityUserPrize activityUserPrize = new ActivityUserPrize();
    		activityUserPrize.setPrizeId(activityPrize.getId());
    		activityUserPrize.setPrizeName(activityPrize.getPrizeName());
    		activityUserPrize.setMemberNo(memberOperecord.getMemberNo());
    		activityUserPrize.setMemberTel(memberOperecord.getMemberTel());
    		activityUserPrize.setGrantStatus(PrizeGrantStatusEnum.SEND);
    		activityUserPrize.setGrantType(PrizeGrantWayEnum.AUTOMATIC);
    		activityUserPrize.setCreateTime(new Date());
    		activityUserPrize.setPrizeLevel(activityPrize.getPrizeLevel());
    		activityUserPrize.setMemo(activityGoodsDetail.getRedeemCode());
    		logger.info("[sendRedeemCode] activityUserPrize={}",activityUserPrize);
    		activityUserPrizeDaoImpl.add(activityUserPrize);
    		logger.info("[sendRedeemCode] info={},activityUserPrize={}","添加用户奖品表成功",activityUserPrize);
    	}catch(Exception e){
    		logger.info("[sendRedeemCode] info={},ERROR={}","添加中奖记录表失败",e);
    	}
		
		//发送短信
		try{
			SendNoticeParamDto param = new SendNoticeParamDto();
			param.setMobileNo(memberOperecord.getMemberTel());
			param.setTemplateNo(Constant.TAMPLATNO);
			Map<String, String> noticeParam = new HashMap<String, String>();
			noticeParam.put("redeemCode", activityGoodsDetail.getRedeemCode());
			param.setNoticeParam(noticeParam);
			logger.info("[sendRedeemCode] sendNoticeParamDto={}",param);
			SendNoticeResultDto sendNoticeResultDto = noticeFacade.sendNotice(param);
			logger.info("[sendRedeemCode] sendNoticeResultDto={}",sendNoticeResultDto);
			if(sendNoticeResultDto.isResultFlag() != true){
				logger.info("[sendRedeemCode] SendNoticeParamDto={}",param);
				logger.info("[sendRedeemCode] info={}","发送短信失败了。。。");
			}
		}catch(Exception e){
			logger.info("[sendRedeemCode] info={},ERROR={}","发送短信验证码异常",e);
		}
		//添加消息公告
		try{
			List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
			userMessageList.add(this.initUserMessage(memberOperecord.getMemberTel(), memberOperecord.getOperateTime(), "get_ticket_redeem_code_message.ftl", memberOperecord.getMemberNo(), activityPrize.getPrizeName(), activityGoods.getGoodsName(),activityGoodsDetail.getRedeemCode()));
			activityUserMessageDaoImpl.insertUserMessage(userMessageList);
		}catch(Exception e){
			logger.error("[sendRedeemCode] info={},ERROR={}","发送消息公告异常",e);
		}
		
	}

}
