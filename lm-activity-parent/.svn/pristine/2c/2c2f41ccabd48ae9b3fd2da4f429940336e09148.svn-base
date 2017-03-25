/**
 * @descrption
 * @author 陈大涛
 * 2016-7-27下午5:03:38
 */
package com.yeepay.g3.core.activity.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.yeepay.g3.core.activity.dao.ActivityActionRelaDao;
import com.yeepay.g3.core.activity.dao.ActivityGoodsDao;
import com.yeepay.g3.core.activity.dao.ActivityInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProOrderDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProOrderFlowDao;
import com.yeepay.g3.core.activity.dao.ActivityPrizeDao;
import com.yeepay.g3.core.activity.dao.ActivityUserMessageDao;
import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.entity.ActivityInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProOrder;
import com.yeepay.g3.core.activity.entity.ActivityInvForProOrderFlow;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.core.activity.dao.ActivityUserPrizeDao;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.facade.activity.enums.ActivityStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeGrantStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeGrantWayEnum;
import com.yeepay.g3.core.activity.service.ActivityInvForProOrderService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderSendMessageStatusEnum;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeTypeEnum;
import com.yeepay.g3.facade.activity.enums.TripSecondDiscountSurplusNumResultCode;
import com.yeepay.g3.facade.activity.enums.UserMessageReadStatusEnum;
import com.yeepay.g3.facade.lmlc.async.event.LMTradeMessageEvent;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.json.JSONObject;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;

/**
 * @author 陈大涛
 * 2016-7-27下午5:03:38
 */
@Service
public class ActivityInvForProOrderServiceImpl implements
		ActivityInvForProOrderService {


	@Autowired
	private ActivityActionDao activityActionDaoImpl;
	
	@Autowired
	private ActivityUserPrizeDao activityUserPrizeDaoImpl;
	@Resource
	private ActivityInvForProOrderDao activityInvForProOrderDaoImpl;
	@Resource
	private ActivityInvForProOrderFlowDao activityInvForProOrderFlowDaoImpl;
	@Autowired
	private ActivityUserMessageDao activityUserMessageDaoImpl;
	@Autowired
	private ActivityInvForProInfoDao ActivityInvForProInfoDaoImpl;
	@Resource
	private ActivityActionRelaDao activityActionRelaDaoImpl;
	@Resource
	private ActivityPrizeDao activityPrizeDaoImpl;
	@Resource
	private ActivityGoodsDao activityGoodsDaoImpl;
	@Autowired
	private ActivityInfoDao activityInfoDaoImpl;
	
	private static Logger logger = LoggerFactory.getLogger(ActivityInvForProOrderServiceImpl.class);
	@Override
	public void insertInvForProOrder(
			ActivityInvForProOrder activityInvForProOrder) {
		//1.新增订单
		 activityInvForProOrderDaoImpl.add(activityInvForProOrder);
		//2.新增订单流水
		 ActivityInvForProOrderFlow activityInvForProOrderFlow =new ActivityInvForProOrderFlow();
		 activityInvForProOrderFlow.setOrderId(activityInvForProOrder.getId());
		 activityInvForProOrderFlow.setStatus(ActivityInvForProOrderStatusEnum.NO_PAID);
		 activityInvForProOrderFlow.setUpdateTime(new Date());
		 activityInvForProOrderFlowDaoImpl.add(activityInvForProOrderFlow);
	}
	@Override
	public void updateInvForProOrder(
			ActivityInvForProOrder activityInvForProOrder) {
		//1.更改订单表
		activityInvForProOrderDaoImpl.update(activityInvForProOrder);
		//2.新增订单流水
		 ActivityInvForProOrderFlow activityInvForProOrderFlow =new ActivityInvForProOrderFlow();
		 activityInvForProOrderFlow.setOrderId(activityInvForProOrder.getId());
		 activityInvForProOrderFlow.setStatus(activityInvForProOrder.getStatus());
		 activityInvForProOrderFlow.setUpdateTime(new Date());
		 activityInvForProOrderFlowDaoImpl.add(activityInvForProOrderFlow);
	}
	@Override
	public ActivityInvForProOrder queryInvForProOrderDetailById(Long id) {
		ActivityInvForProOrder result = (ActivityInvForProOrder) activityInvForProOrderDaoImpl.queryOne("selectByPrimaryKey", id);
		return result;
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityInvForProOrderService#updateOrderStatusTiming()
	 */
	@Override
	public void updateOrderStatusTiming(Date deadLine) {
		//1.查询超时订单列表
		ActivityInvForProOrder deadLineParamForQuery=new ActivityInvForProOrder();
		deadLineParamForQuery.setCreateTime(deadLine);
		List<ActivityInvForProOrder> deadLineList = activityInvForProOrderDaoImpl.query("queryOrderStatusByTiming", deadLineParamForQuery);
		//2.取消所有超时订单
		ActivityInvForProOrder deadLineParamForUpdate=new ActivityInvForProOrder();
		deadLineParamForUpdate.setCreateTime(deadLine);
		deadLineParamForUpdate.setStatus(ActivityInvForProOrderStatusEnum.CANCE);
		deadLineParamForUpdate.setOperator("定时取消");
		deadLineParamForUpdate.setOperatorTime(new Date());
		activityInvForProOrderDaoImpl.update("updateOrderStatusByTiming", deadLineParamForUpdate);
		//3.新增订单状态变动记录
		for(ActivityInvForProOrder itme :  deadLineList){
			ActivityInvForProOrderFlow activityInvForProOrderFlow =new ActivityInvForProOrderFlow();
			 activityInvForProOrderFlow.setOrderId(itme.getId());
			 activityInvForProOrderFlow.setStatus(ActivityInvForProOrderStatusEnum.CANCE);
			 activityInvForProOrderFlow.setUpdateTime(new Date());
			 activityInvForProOrderFlowDaoImpl.add(activityInvForProOrderFlow);
		}
	}
	@Override
	public void sendMessageForNoPaidOrderTiming(Date deadLine) {
		//1.发送消息
		List<ActivityUserMessage> userMessageList = new ArrayList<ActivityUserMessage>();
		//查询需要发送消息的列表
		ActivityInvForProOrder deadLineParamForQuery=new ActivityInvForProOrder();
		deadLineParamForQuery.setCreateTime(deadLine);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Object>> deadLineList = activityInvForProOrderDaoImpl.query("queryMessageNoPaidOrderList", deadLineParamForQuery);
		for(Map<String,Object> itme : deadLineList){
			ActivityUserMessage userMessage = new ActivityUserMessage();
			try {
				JSONObject msgParam = new JSONObject();
				msgParam.put("num", itme.get("NUM").toString());
				msgParam.put("createTime",sdf.format(new Date()) );
				msgParam.put("occurTime", itme.get("CREATE_TIME"));
				msgParam.put("productName", itme.get("NAME"));
				userMessage.setMemberNo(itme.get("MEMBER_NO").toString());
				userMessage.setMsgContent(msgParam.toString());
				userMessage.setOccurTime(new Date());
				userMessage.setReadStatus(UserMessageReadStatusEnum.UNREAD);
				userMessage.setTemplateName("common_invforpro_no_paid_order.ftl");
				userMessageList.add(userMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//2.修改订单发送消息状态
			ActivityInvForProOrder param = new ActivityInvForProOrder();
			param.setId(Long.valueOf(itme.get("ID").toString()));
			param.setVersion(Long.valueOf(itme.get("VERSION").toString()));
			param.setSendMessageStatus(ActivityInvForProOrderSendMessageStatusEnum.SEND_MESSAGE);
			activityInvForProOrderDaoImpl.update(param);
		}
		activityUserMessageDaoImpl.insertUserMessage(userMessageList);
		
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityInvForProOrderService#queryOrderAndProInfoListByMemberNoAndStatus()
	 */
	@Override
	public List<Map<String, Object>> queryOrderAndProInfoListByMemberNoAndStatus(ActivityInvForProOrder activityInvForProOrder ) {
		List<Map<String, Object>> result = activityInvForProOrderDaoImpl.query("queryOrderAndProInfoListByMemberNoAndStatus", activityInvForProOrder);
		return result;
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityInvForProOrderService#queryInvForProOrderDetailByOrderCode(java.lang.String)
	 */
	@Override
	public ActivityInvForProOrder queryInvForProOrderDetailByOrderCode(
			String orderCode) {
		ActivityInvForProOrder result = (ActivityInvForProOrder)activityInvForProOrderDaoImpl.queryOne("queryOrderByOrderCode", orderCode);
		return result;
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityInvForProOrderService#updateOrderStatusByMQProcessor()
	 */
	@Override
	public void updateOrderStatusByMQProcessor(LMTradeMessageEvent event) {

		//先判断参数值是否为空
		if(CheckUtils.isEmpty(event.getGoodsCode()) || CheckUtils.isEmpty(event.getOrderNo())) {
			return;
		}
		//1.查询是否有此订单
		ActivityInvForProOrder orderResult = (ActivityInvForProOrder) activityInvForProOrderDaoImpl.queryOne("queryOrderByOrderCode", event.getGoodsCode());
		if(orderResult==null||orderResult.getStatus()==ActivityInvForProOrderStatusEnum.PAIDED){
			logger.info("ActivityInvForProBuySuccessProcessor事件处理  没有此订单号" +event.getGoodsCode());
			return;
		}
		//2.修改订单状态
		ActivityInvForProOrder param = new ActivityInvForProOrder();
		param.setStatus(ActivityInvForProOrderStatusEnum.PAIDED);
		param.setXtOrderCode(event.getOrderNo());
		param.setVersion(orderResult.getVersion());
		param.setId(orderResult.getId());
		activityInvForProOrderDaoImpl.update(param);
		//3.新增订单变动记录
		 ActivityInvForProOrderFlow activityInvForProOrderFlow =new ActivityInvForProOrderFlow();
		 activityInvForProOrderFlow.setOrderId(orderResult.getId());
		 activityInvForProOrderFlow.setStatus(ActivityInvForProOrderStatusEnum.PAIDED);
		 activityInvForProOrderFlow.setUpdateTime(new Date());
		 activityInvForProOrderFlowDaoImpl.add(activityInvForProOrderFlow);
		//4.更改旅游产品数量
		 ActivityInvForProInfo ActivityInvForProInfoResult = (ActivityInvForProInfo) ActivityInvForProInfoDaoImpl.queryOne("selectByPrimaryKey", orderResult.getProductId());
		 ActivityInvForProInfo activityInvForProInfo = new ActivityInvForProInfo();
		 activityInvForProInfo.setId(orderResult.getProductId());
		 activityInvForProInfo.setVersion(ActivityInvForProInfoResult.getVersion());
		 activityInvForProInfo.setUsedNum(ActivityInvForProInfoResult.getUsedNum()+orderResult.getNum());
		 ActivityInvForProInfoDaoImpl.update(activityInvForProInfo);
	}
	@Override
	public void sendPrizeForOrder(LMTradeMessageEvent event,ActivityMemberOperecord activityMemberOperecord) {
		// TODO Auto-generated method stub
		logger.info("[sendPrizeForOrder] activityMemberOperecord={}",activityMemberOperecord);
		//先判断参数值是否为空
		if(CheckUtils.isEmpty(event.getGoodsCode()) || CheckUtils.isEmpty(event.getOrderNo())) {
			return;
		}
		//1.查询是否有此订单
		ActivityInvForProOrder orderResult = (ActivityInvForProOrder) activityInvForProOrderDaoImpl.queryOne("queryOrderByOrderCode", event.getGoodsCode());
		if(orderResult==null){
			logger.info("[sendPrizeForOrder]  没有此订单号" +event.getGoodsCode());
			return;
		}
		if(orderResult.getNum() != Integer.valueOf(1)){
			logger.info("[sendPrizeForOrder] ERROR={}","该订单的购买数量不为1");
			return;
		}
		//查询当前事件动作
		Map<String, Object> actionParam = new HashMap<String, Object>();
		actionParam.put("actionCode", activityMemberOperecord.getOperateType() + Constant.INVFORPRO_SUFFIX);//INVEST_INVFORPRO 绑卡动作
		logger.info("[sendPrizeForOrder] actionParam={}",actionParam);
		List<ActivityAction> actionList = activityActionDaoImpl.query("selectByParams", actionParam);
		logger.info("[sendPrizeForOrder] actionList={}",actionList);
		if(actionList != null && actionList.size() > 0){
			ActivityAction action = actionList.get(0);
			
			//查询当前事件对应的活动
			Map<String, Object> activityParam = new HashMap<String, Object>();
			activityParam.put("activityStatus", ActivityStatusEnum.EFFECTIVE);
			activityParam.put("actionId", action.getId());
			List<ActivityInfo> activityList = activityInfoDaoImpl.query("selectByActionId", activityParam);
			logger.info("[sendPrizeForOrder] activityList={}",activityList);
			//活动不为空,查询活动期间所有符合条件的，投资换产品的订单
			if(activityList != null && activityList.size() > 0){
				Date currentdate = new Date();
				ActivityInfo activityInfo = activityList.get(0);
				Map<String,Object> map = new LinkedHashMap<String,Object>();
				//取活动开始时间和结束时间
				Date startDate = activityInfo.getStartTime();
				Date endDate = activityInfo.getEndTime();
				//活动是否到期
				if(currentdate.getTime()<startDate.getTime() || currentdate.getTime() > endDate.getTime()){
					logger.info("[sendPrizeForOrder] error={}","活动没开始或已到期");
					return;
				}
				map.put("updateTimeStart", startDate);
				map.put("updateTimeEnd", endDate);
				//订单的购买份额为1
				map.put("num", Long.valueOf(1));
				List<ActivityInvForProOrder> InvForProOrderlist = activityInvForProOrderDaoImpl.selectByFlowParams(map);
				logger.info("[sendPrizeForOrder] InvForProOrderlist={}",InvForProOrderlist);
				//少于10个订单，送京东卡
				if(InvForProOrderlist != null && InvForProOrderlist.size()!=0 && InvForProOrderlist.size()<= 10){
					//查询事件对应的奖品
					List<ActivityPrize> activityPrizeList = activityPrizeDaoImpl.selectByActionId(action.getId());
					logger.info("[sendPrizeForOrder] activityPrizeList={}",activityPrizeList);
					if(activityPrizeList != null && activityPrizeList.size() == 1){
						ActivityPrize activityPrize = activityPrizeList.get(0);
						//有剩余奖品，则发放奖品
						if(activityPrize.getPrizeTotalCount()- activityPrize.getPrizeGrantCount() > 0){
							ActivityUserPrize activityUserPrize = new ActivityUserPrize();
							activityUserPrize.setPrizeId(activityPrize.getId());
							activityUserPrize.setPrizeName(activityPrize.getPrizeName());
							activityUserPrize.setMemberNo(activityMemberOperecord.getMemberNo());
							activityUserPrize.setMemberTel(activityMemberOperecord.getMemberTel());
							activityUserPrize.setGrantStatus(PrizeGrantStatusEnum.NOSEND);
							activityUserPrize.setGrantType(PrizeGrantWayEnum.LOGISTICS);
							activityUserPrize.setCreateTime(new Date());
							activityUserPrize.setPrizeLevel(activityPrize.getPrizeLevel());
							logger.info("[sendPrizeForOrder] activityUserPrize={}",activityUserPrize);
							activityUserPrizeDaoImpl.add(activityUserPrize);
							logger.info("[sendPrizeForOrder] info={},activityUserPrize={}","添加用户奖品表成功",activityUserPrize);
							
							//奖品发放数量加1
							Long prizeCount = activityPrize.getPrizeGrantCount() + Long.valueOf(1);
							activityPrize.setPrizeGrantCount(prizeCount);
							logger.info("[sendPrizeForOrder] activityPrize={}",activityPrize);
							activityPrizeDaoImpl.update(activityPrize);
							logger.info("[sendPrizeForOrder] activityPrize={}",activityPrize);
						}
					}else{
						logger.info("[sendPrizeForOrder] info={},奖品数量={}","奖品不存在或奖品配置错误",activityPrizeList.size());
					}
				}
			}else{
				logger.info("[sendPrizeForOrder] info={},ERROR={}","活动不存在或配置错误",TripSecondDiscountSurplusNumResultCode.NO_ACITITY);
			}
		}
		
		
	}
	@Override
	public void secondDiscountByMQProcessor(LMTradeMessageEvent event) {
		//1.查询是否有活动并有效
		Map<String,String> params = new HashMap<String, String>();
		params.put("actionCode", Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION);
		params.put("activityCode", Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY);
		Integer activityCount = activityActionRelaDaoImpl.queryActivityByActionCode(params);
		//失效则返回NO_ACITITY
		if(activityCount==null||activityCount==0){
			logger.info("[secondDiscountByMQProcessor] 没有活动或活动失效");
			return;
		}
		//2.修改限额已使用数量
		List<ActivityPrize> prizeList = activityPrizeDaoImpl.query("selectByActionCode", Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION);
		if(prizeList==null||prizeList.size()==0){
			logger.info("[secondDiscountByMQProcessor] 没有奖品");
			return;
		}else if(prizeList.size()!=1){
			logger.info("[secondDiscountByMQProcessor] 奖品配置错误");
			return;
		}else if(prizeList.get(0).getPrizeGrantCount()>=prizeList.get(0).getPrizeTotalCount()){
			logger.info("[secondDiscountByMQProcessor] 投资换产品打折剩余份额为0");
			return;
		}else{	//修改奖品使用量及商品使用量
			//根据goodsCode查询订单信息
			//先判断参数值是否为空
			if(CheckUtils.isEmpty(event.getGoodsCode()) || CheckUtils.isEmpty(event.getOrderNo())) {
				return;
			}
			//1.查询是否有此订单
			ActivityInvForProOrder orderResult = (ActivityInvForProOrder) activityInvForProOrderDaoImpl.queryOne("queryOrderByOrderCode", event.getGoodsCode());
			if(orderResult==null){
				logger.info("[secondDiscountByMQProcessor]   没有此订单号" +event.getGoodsCode());
				return;
			}
			//使用折扣份额公式：grantCount=2*（购买份额数*单价-实际总金额）/单价
			BigDecimal grantCount = new BigDecimal(2).multiply((new BigDecimal(orderResult.getNum()).multiply(orderResult.getPrice()).subtract(orderResult.getTotal()).divide(orderResult.getPrice())));
			ActivityPrize param = new ActivityPrize();
			param.setId(prizeList.get(0).getId());
			param.setVersion(prizeList.get(0).getVersion());
			param.setPrizeGrantCount(prizeList.get(0).getPrizeGrantCount()+grantCount.intValue());
			activityPrizeDaoImpl.update(param);
			if(prizeList.get(0).getPrizeType()==PrizeTypeEnum.GOODS){
				ActivityGoods activityGood =(ActivityGoods) activityGoodsDaoImpl.queryOne("selectByPrimaryKey", prizeList.get(0).getRelaGoodsId());
				ActivityGoods paramGood = new ActivityGoods();
				paramGood.setVersion(activityGood.getVersion());
				paramGood.setId(activityGood.getId());
				paramGood.setGrantCount(activityGood.getGrantCount()+grantCount.intValue());
				activityGoodsDaoImpl.update(paramGood);
			}else{
				logger.info("[secondDiscountByMQProcessor] 奖品配置的非商品！！！");
			}
			//判断打折限额是否为0
			if(prizeList.get(0).getPrizeGrantCount()+grantCount.intValue()>=prizeList.get(0).getPrizeTotalCount()){
				//3.取消达到打折限额在活动期间未支付的打折的订单
				//查询活动信息
				ActivityInfo ActivityInfo =(ActivityInfo)activityInfoDaoImpl.queryOne("queryActivityInfoByActivityCode", Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY);
				//查询活动期间未支付打折的订单
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("status", ActivityInvForProOrderStatusEnum.NO_PAID);
				paramMap.put("createTimeStart", ActivityInfo.getStartTime());
				paramMap.put("createTimeEnd", ActivityInfo.getEndTime());
				List<ActivityInvForProOrder> noEffectOrderList = activityInvForProOrderDaoImpl.query("queryNoEffectOrderList", paramMap);
				//遍历取消订单,新增订单变更记录
				for(ActivityInvForProOrder items:noEffectOrderList){
					//修改订单状态
					ActivityInvForProOrder activityInvForProOrder = new ActivityInvForProOrder();
					activityInvForProOrder.setId(items.getId());
					activityInvForProOrder.setVersion(items.getVersion());
					activityInvForProOrder.setStatus(ActivityInvForProOrderStatusEnum.CANCE);
					activityInvForProOrderDaoImpl.update(activityInvForProOrder);
					//新增订单变动记录
					ActivityInvForProOrderFlow activityInvForProOrderFlow =new ActivityInvForProOrderFlow();
					activityInvForProOrderFlow.setOrderId(param.getId());
					activityInvForProOrderFlow.setStatus(ActivityInvForProOrderStatusEnum.CANCE);
					activityInvForProOrderFlow.setUpdateTime(new Date());
					activityInvForProOrderFlowDaoImpl.add(activityInvForProOrderFlow);
				}
			}
		}
		
	
		
	}

}
