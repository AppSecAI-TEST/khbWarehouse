/**
 * @descrption
 * @author 陈大涛
 * 2016-7-27下午4:57:14
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProOrder;
import com.yeepay.g3.core.activity.entity.ActivityInvForProRuleXT;
import com.yeepay.g3.core.activity.service.ActivityInvForProInfoService;
import com.yeepay.g3.core.activity.service.ActivityInvForProOrderService;
import com.yeepay.g3.core.activity.service.ActivityInvForProRuleService;
import com.yeepay.g3.core.activity.service.ActivityInvForProTripSecondDiscountService;
import com.yeepay.g3.core.activity.utils.Constant;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProInsertOrderResultDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProOrderAndProInfoDTO;
import com.yeepay.g3.facade.activity.dto.ActivityInvForProOrderDTO;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderInsertOrderResultEnum;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderSendMessageStatusEnum;
import com.yeepay.g3.facade.activity.enums.ActivityInvForProOrderStatusEnum;
import com.yeepay.g3.facade.activity.enums.TripSecondDiscountSurplusNumResultCode;
import com.yeepay.g3.facade.activity.facade.ActivityInvForProOrderFacade;
import com.yeepay.g3.facade.lmlc.trust.dto.product.ProductDetailResultDto;
import com.yeepay.g3.facade.lmlc.trust.service.FiQueryFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;

/**
 * @author 陈大涛 2016-7-27下午4:57:14
 */
@Service
public class ActivityInvForProOrderFacadeImpl implements
		ActivityInvForProOrderFacade {
	@Autowired
	private ActivityInvForProOrderService activityInvForProOrderServiceImpl;
	@Autowired
	private ActivityInvForProInfoService activityInvForProInfoServiceImpl;
	@Autowired
	private ActivityInvForProRuleService activityInvForProRuleServiceImpl;
	@Autowired
	private ActivityInvForProTripSecondDiscountService activityInvForProTripSecondDiscountServiceImpl;
	protected FiQueryFacade fiQueryFacade = RemoteServiceFactory
			.getService(FiQueryFacade.class);
	private Logger logger = LoggerFactory
			.getLogger(ActivityInvForProOrderFacadeImpl.class);

	/*
	 * @Override public String insertInvForProOrder(ActivityInvForProOrderDTO
	 * activityInvForProOrderDTO) { //订单code生成规则 StringBuffer orderCode=new
	 * StringBuffer(); Date date=new Date(); SimpleDateFormat sdfPrefix = new
	 * SimpleDateFormat("MMdd"); String prefix=sdfPrefix.format(date); String
	 * suffix=String.valueOf(date.getTime()); orderCode.append(prefix);
	 * orderCode.append(suffix);
	 * activityInvForProOrderDTO.setOrderCode(orderCode.toString());
	 * activityInvForProOrderDTO
	 * .setStatus(ActivityInvForProOrderStatusEnum.NO_PAID);
	 * activityInvForProOrderDTO.setCreateTime(new Date());
	 * activityInvForProOrderDTO
	 * .setSendMessageStatus(ActivityInvForProOrderSendMessageStatusEnum
	 * .NO_SEND_MESSAGE); // ActivityInvForProOrder activityInvForProOrder =new
	 * ActivityInvForProOrder();
	 * activityInvForProOrder=EntityDTOConvert.toTarget
	 * (activityInvForProOrderDTO, ActivityInvForProOrder.class);
	 * activityInvForProOrderServiceImpl
	 * .insertInvForProOrder(activityInvForProOrder); return
	 * orderCode.toString(); }
	 */

	@Override
	public ActivityInvForProInsertOrderResultDTO insertInvForProOrder(
			String productId, String number, String ruleId, String memberNo) {
		ActivityInvForProInsertOrderResultDTO result = new ActivityInvForProInsertOrderResultDTO();
		// 1.判断参数的合法性
		if (CheckUtils.isEmpty(productId) || CheckUtils.isEmpty(number)
				|| CheckUtils.isEmpty(ruleId)) {
			result.setCode(ActivityInvForProOrderInsertOrderResultEnum.ERROR_PARAM);
			return result;
		}
		// 2.规则详情
		ActivityInvForProRuleXT ruleXT = activityInvForProRuleServiceImpl
				.selectInvForProRuleById(Long.valueOf(ruleId));
		if (null == ruleXT) {
			result.setCode(ActivityInvForProOrderInsertOrderResultEnum.NO_RULE);
			return result;
		}
		// 3.根据产品期限查询理财产品
		// FIXED、MONY、30D 一个月
		// FIXED、QUAY、180D 半年
		// FIXED、YEAY、365D 一年
		String productTerm = ruleXT.getTrem() + "D";
		String typeTime = null;
		if (ruleXT.getTrem() >= 365) {
			typeTime = "YEAY";
		} else if (ruleXT.getTrem() >= 90) {
			typeTime = "QUAY";
		} else {
			typeTime = "MONY";
		}
		ProductDetailResultDto productDetailResultDto = fiQueryFacade
				.obtainProductDetail("FIXED", typeTime, productTerm);
		if (null == productDetailResultDto) {
			result.setCode(ActivityInvForProOrderInsertOrderResultEnum.NO_LCPRODUCT);
			return result;
		}
		Long lcProductId = productDetailResultDto.getProductId();
		// 4.查询产品的详情
		ActivityInvForProOrder order = new ActivityInvForProOrder();
		if (null != productId && null != ruleXT) {
			ActivityInvForProInfo invForProInfo = new ActivityInvForProInfo();
			try {
				invForProInfo = activityInvForProInfoServiceImpl
						.selectInvForProInfoById(Long.valueOf(productId));
				logger.info("[insertInvForProOrder] 产品详情id={}",
						invForProInfo.getId());
			} catch (Exception e) {
				logger.info("[insertInvForProOrder] 查询产品详情信息有误");
				// TODO: handle exception
			}
			if (null != invForProInfo) {
				// 5.判断数量余额
				if (Integer.valueOf(number) > (invForProInfo.getStockNum() - invForProInfo
						.getUsedNum())) {
					result.setCode(ActivityInvForProOrderInsertOrderResultEnum.NO_PRODUCT);
					return result;
				}
				// 6.根据人数，打折限额剩余数计算总金额
				// 查询第二次投资打折限额
				Integer surplus = 0;
				try {
					String surplusNum = activityInvForProTripSecondDiscountServiceImpl
							.queryTripSecondDiscountSurplusNum(
									Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTION,
									Constant.INVFORPRO_SECOND_DISCOUNT_TRIP_ACTIVITY);
					if (TripSecondDiscountSurplusNumResultCode.NO_ACITITY
							.toString().equals(surplusNum)) {
						logger.error("[insertInvForProOrder] 查询第二次投资打折限额 ,没有活动或活动失效");
					} else if (TripSecondDiscountSurplusNumResultCode.NO_PRIZE
							.toString().equals(surplusNum)) {
						logger.error("[insertInvForProOrder] 查询第二次投资打折限额 ,没有奖品");
					} else if (TripSecondDiscountSurplusNumResultCode.ERROR_PRIZE
							.toString().equals(surplusNum)) {
						logger.error("[insertInvForProOrder] 查询第二次投资打折限额,奖品配置错误");
					} else {
						surplus = Integer.valueOf(surplusNum);
					}
				} catch (Exception e) {
					logger.error("[insertInvForProOrder] 查询第二次投资打折限额异常 ", e);
				}
				// 计算打折之后的总金额
				Integer buyNum = Integer.valueOf(number);
				BigDecimal total = ruleXT.getPrice().multiply(
						new BigDecimal(number));
				logger.info(
						"[insertInvForProOrder] 计算打折之后的总金额buyNum={},price={},surplus={}",
						buyNum, ruleXT.getPrice(), surplus);
				if (surplus != 0 && buyNum > 1) {
					Float buyNumF = null;
					if (buyNum / 2 > surplus) {// 超出限额
						buyNumF = (float) (buyNum - surplus * 2 + surplus * 1.5);
					} else {
						buyNumF = (float) (buyNum / 2 * 1.5 + buyNum % 2);
					}
					total = ruleXT.getPrice().multiply(new BigDecimal(buyNumF));
				}

				order.setProductId(invForProInfo.getId());
				order.setActivityCode(invForProInfo.getActivityCode());
				order.setLcProductId(Long.valueOf(lcProductId));
				order.setMemberNo(memberNo);
				order.setPrice(ruleXT.getPrice());
				order.setTotal(total);
				order.setNum(Integer.valueOf(number));
			}
		}
		// 订单code生成规则
		StringBuffer orderCode = new StringBuffer();
		Date date = new Date();
		SimpleDateFormat sdfPrefix = new SimpleDateFormat("MMdd");
		String prefix = sdfPrefix.format(date);
		String suffix = String.valueOf(date.getTime());
		orderCode.append(prefix);
		orderCode.append(suffix);
		order.setOrderCode(orderCode.toString());
		order.setStatus(ActivityInvForProOrderStatusEnum.NO_PAID);
		order.setCreateTime(new Date());
		order.setSendMessageStatus(ActivityInvForProOrderSendMessageStatusEnum.NO_SEND_MESSAGE);
		ActivityInvForProOrder activityInvForProOrder = new ActivityInvForProOrder();
		activityInvForProOrder = EntityDTOConvert.toTarget(order,
				ActivityInvForProOrder.class);
		activityInvForProOrderServiceImpl
				.insertInvForProOrder(activityInvForProOrder);
		result.setCode(ActivityInvForProOrderInsertOrderResultEnum.SUCCESS);
		result.setOrderCode(orderCode.toString());
		return result;
	}

	@Override
	public void updateInvForProOrder(
			ActivityInvForProOrderDTO activityInvForProOrderDTO) {
		ActivityInvForProOrder activityInvForProOrder = new ActivityInvForProOrder();
		// activityInvForProOrder.setStatus(ActivityInvForProOrderStatusEnum.PAIDED);
		activityInvForProOrder = EntityDTOConvert.toTarget(
				activityInvForProOrderDTO, ActivityInvForProOrder.class);
		activityInvForProOrderServiceImpl
				.updateInvForProOrder(activityInvForProOrder);
	}

	/*
	 * @Override public void
	 * updateInvForProOrderForStatus(ActivityInvForProOrderDTO
	 * activityInvForProOrderDTO) { ActivityInvForProOrder
	 * activityInvForProOrder =new ActivityInvForProOrder();
	 * activityInvForProOrder
	 * =EntityDTOConvert.toTarget(activityInvForProOrderDTO,
	 * ActivityInvForProOrder.class);
	 * activityInvForProOrderServiceImpl.updateInvForProOrder
	 * (activityInvForProOrder);
	 * 
	 * }
	 */

	@Override
	public ActivityInvForProOrderDTO queryInvForProOrderDetailById(Long id) {
		ActivityInvForProOrder activityInvForProOrder = activityInvForProOrderServiceImpl
				.queryInvForProOrderDetailById(id);
		ActivityInvForProOrderDTO result = EntityDTOConvert.toTarget(
				activityInvForProOrder, ActivityInvForProOrderDTO.class);
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yeepay.g3.facade.activity.facade.ActivityInvForProOrderFacade#
	 * updateOrderStatusTiming()
	 */
	@Override
	public void updateOrderStatusTiming() {
		// 取统一配置 失效时间
		String deadLineTime = Constant.getUpdateOrderStatusTimingDeadLine();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -Integer.valueOf(deadLineTime));
		activityInvForProOrderServiceImpl.updateOrderStatusTiming(calendar
				.getTime());
	}

	@Override
	public void sendMessageForNoPaidOrderTiming() {
		// 取统一配置 失效时间
		String deadLineTime = Constant
				.getSendMessageForNoPaidOrderTimingDeadLine();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, -Integer.valueOf(deadLineTime));
		activityInvForProOrderServiceImpl
				.sendMessageForNoPaidOrderTiming(calendar.getTime());
	}

	@Override
	public List<ActivityInvForProOrderAndProInfoDTO> queryOrderAndProInfoListByMemberNoAndStatus(
			ActivityInvForProOrderDTO activityInvForProOrderDTO) {
		ActivityInvForProOrder activityInvForProOrder = new ActivityInvForProOrder();
		activityInvForProOrder = EntityDTOConvert.toTarget(
				activityInvForProOrderDTO, ActivityInvForProOrder.class);
		List<Map<String, Object>> result = activityInvForProOrderServiceImpl
				.queryOrderAndProInfoListByMemberNoAndStatus(activityInvForProOrder);
		List<ActivityInvForProOrderAndProInfoDTO> resultList = new ArrayList<ActivityInvForProOrderAndProInfoDTO>();
		for (Map<String, Object> itme : result) {
			ActivityInvForProOrderAndProInfoDTO param = new ActivityInvForProOrderAndProInfoDTO();
			ActivityInvForProInfoDTO activityInvForProInfoDTO = new ActivityInvForProInfoDTO();
			// activityInvForProInfoDTO.setActivityCode(itme.get(""));
			// activityInvForProInfoDTO.setCreatePerson(createPerson);
			// activityInvForProInfoDTO.setCreateTime(createTime);
			activityInvForProInfoDTO.setId(Long.valueOf(itme.get("INFOID")
					.toString()));
			activityInvForProInfoDTO.setName(itme.get("NAME") == null ? null
					: itme.get("NAME").toString());
			// activityInvForProInfoDTO.setOperatorer(operatorer);
			// activityInvForProInfoDTO.setOperatorTime(operatorTime);
			// activityInvForProInfoDTO.setPrice(price);
			// activityInvForProInfoDTO.setProductPriceLow(productPriceLow);
			// activityInvForProInfoDTO.setStatus(status);
			// activityInvForProInfoDTO.setStockNum(stockNum);
			// activityInvForProInfoDTO.setUrl(url);
			// activityInvForProInfoDTO.setUsedNum(usedNum);
			// activityInvForProInfoDTO.setVersion(version);
			ActivityInvForProOrderDTO activityInvForProOrderDTOResult = new ActivityInvForProOrderDTO();
			activityInvForProOrderDTOResult.setActivityCode(itme
					.get("ACTIVITY_CODE") == null ? null : itme.get(
					"ACTIVITY_CODE").toString());
			activityInvForProOrderDTOResult.setCreateTime((Date) itme
					.get("CREATE_TIME"));
			activityInvForProOrderDTOResult.setId((Long) itme.get("ID"));
			activityInvForProOrderDTOResult.setLcProductId((Long) itme
					.get("LC_PRODUCT_ID"));
			activityInvForProOrderDTOResult.setLogisticsNumber(itme
					.get("LOGISTICS_NUMBER") == null ? null : itme.get(
					"LOGISTICS_NUMBER").toString());
			activityInvForProOrderDTOResult
					.setMemberNo(itme.get("MEMBER_NO") == null ? null : itme
							.get("MEMBER_NO").toString());
			activityInvForProOrderDTOResult
					.setMobileNum(itme.get("MOBILE_NUM") == null ? null : itme
							.get("MOBILE_NUM").toString());
			activityInvForProOrderDTOResult.setNum((Integer) itme.get("NUM"));
			activityInvForProOrderDTOResult
					.setOperator(itme.get("OPERATOR") == null ? null : itme
							.get("OPERATOR").toString());
			activityInvForProOrderDTOResult.setOperatorTime((Date) itme
					.get("OPERATOR_TIME"));
			activityInvForProOrderDTOResult
					.setOrderCode(itme.get("ORDER_CODE") == null ? null : itme
							.get("ORDER_CODE").toString());
			activityInvForProOrderDTOResult.setPrice((BigDecimal) itme
					.get("PRICE"));
			activityInvForProOrderDTOResult.setProductId((Long) itme
					.get("PRODUCT_ID"));
			activityInvForProOrderDTOResult.setSendMessageStatus(itme
					.get("SEND_MESSAGE_STATUS") == null ? null
					: ActivityInvForProOrderSendMessageStatusEnum.valueOf(itme
							.get("SEND_MESSAGE_STATUS").toString()));
			activityInvForProOrderDTOResult
					.setStatus(itme.get("STATUS") == null ? null
							: ActivityInvForProOrderStatusEnum.valueOf(itme
									.get("STATUS").toString()));
			activityInvForProOrderDTOResult.setTotal((BigDecimal) itme
					.get("TOTAL"));
			activityInvForProOrderDTOResult
					.setUserName(itme.get("USER_NAME") == null ? null : itme
							.get("USER_NAME").toString());
			activityInvForProOrderDTOResult.setVersion((Long) itme
					.get("VERSION"));
			activityInvForProOrderDTOResult.setXtOrderCode(itme
					.get("XT_ORDER_CODE") == null ? null : itme.get(
					"XT_ORDER_CODE").toString());
			param.setActivityInvForProInfoDTO(activityInvForProInfoDTO);
			param.setActivityInvForProOrderDTO(activityInvForProOrderDTOResult);
			resultList.add(param);
		}
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yeepay.g3.facade.activity.facade.ActivityInvForProOrderFacade#
	 * queryInvForProOrderDetailByOrderCode(java.lang.String)
	 */
	@Override
	public ActivityInvForProOrderAndProInfoDTO queryInvForProOrderDetailByOrderCode(
			String orderCode) {
		ActivityInvForProOrder activityInvForProOrder = activityInvForProOrderServiceImpl
				.queryInvForProOrderDetailByOrderCode(orderCode);
		ActivityInvForProOrderDTO activityInvForProOrderDTO = EntityDTOConvert
				.toTarget(activityInvForProOrder,
						ActivityInvForProOrderDTO.class);
		ActivityInvForProInfo activityInvForProInfo = activityInvForProInfoServiceImpl
				.selectInvForProInfoById(activityInvForProOrder.getProductId());
		ActivityInvForProInfoDTO activityInvForProInfoDTO = EntityDTOConvert
				.toTarget(activityInvForProInfo, ActivityInvForProInfoDTO.class);
		ActivityInvForProOrderAndProInfoDTO result = new ActivityInvForProOrderAndProInfoDTO();
		result.setActivityInvForProInfoDTO(activityInvForProInfoDTO);
		result.setActivityInvForProOrderDTO(activityInvForProOrderDTO);
		return result;
	}

}
