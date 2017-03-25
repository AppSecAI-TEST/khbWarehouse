/**
 * 
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityRule;
import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.core.activity.entity.ActivityUsercouponRecord;
import com.yeepay.g3.core.activity.service.ActivityCouponService;
import com.yeepay.g3.core.activity.service.ActivityRuleService;
import com.yeepay.g3.core.activity.service.ActivityUsercouponRecordService;
import com.yeepay.g3.core.activity.service.ActivityUsercouponService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponRecordDTO;
import com.yeepay.g3.facade.activity.dto.CouponUseRespDTO;
import com.yeepay.g3.facade.activity.enums.BizTypeEnum;
import com.yeepay.g3.facade.activity.enums.CouponRuleStatusEnum;
import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.CouponUseResultCodeEnum;
import com.yeepay.g3.facade.activity.enums.CouponUseStatusEnum;
import com.yeepay.g3.facade.activity.enums.RuleTypeEnum;
import com.yeepay.g3.facade.activity.enums.UsercouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.ValidityTypeEnum;
import com.yeepay.g3.facade.activity.facade.ActivityCouponTradeFacade;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.persistence.OptimisticLockingException;

/**
 * @Description 优惠券交易对外实现类
 * @author zhenping.zhou
 * @CreateTime 2016年4月4日 上午9:37:44
 * @version 1.0
 */
@Service
public class ActivityCouponTradeFacadeImpl implements ActivityCouponTradeFacade {

	@Autowired
	private ActivityUsercouponService activityUsercouponServiceImpl;
	
	@Autowired
	private ActivityUsercouponRecordService activityUsercouponRecordServiceImpl;
	
	@Autowired
	private ActivityRuleService activityRuleServiceImpl;
	
	@Autowired
	private ActivityCouponService activityCouponServiceImpl;
	
	protected Logger logger = LoggerFactory.getLogger(ActivityCouponTradeFacadeImpl.class);

	@Override
	public List<ActivityUsercouponDTO> selectTradeUsercouponList(
			String memberNo, String channelCode, String bigColumnCode,
			String smallColumnCode) {
		List<ActivityUsercouponDTO> resultList = new ArrayList<ActivityUsercouponDTO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberNo", memberNo);
		params.put("channelCode", channelCode);
		params.put("bigColumnCode", bigColumnCode);
		params.put("smallColumnCode", smallColumnCode);
		params.put("couponStatus", CouponStatusEnum.EFFECTIVE); //有效状态
		
		List<ActivityUsercoupon> list = activityUsercouponServiceImpl.selectTradeUsercouponList(params);
		if(list != null && list.size() > 0) {
			ActivityUsercouponDTO usercouponDto = null;
			for(ActivityUsercoupon usercoupon : list) {
//				usercouponDto = new ActivityUsercouponDTO();
				usercouponDto = EntityDTOConvert.toTarget(usercoupon, ActivityUsercouponDTO.class);
				usercouponDto.setCouponDto(EntityDTOConvert.toTarget(usercoupon.getCoupon(), ActivityCouponDTO.class));
				resultList.add(usercouponDto);
			}
//			resultList = EntityDTOConvert.toTragetList(list, ActivityUsercouponDTO.class);
		}
		return resultList;
	}

	@Override
	public CouponUseRespDTO frozenUserCoupon(Long usercouponId,
			String memberNo, Long tradeId, BizTypeEnum bizType) {
		
		CouponUseRespDTO resp = new CouponUseRespDTO();
		try {
			CheckUtils.notEmpty(usercouponId,"");
			CheckUtils.notEmpty(memberNo,"");
			CheckUtils.notEmpty(tradeId,"");
			CheckUtils.notEmpty(bizType,"");
		} catch(Exception e) {
			resp.setResultCode(CouponUseResultCodeEnum.OPERATE_PARAM_ERROR);
			resp.setMemberNo(memberNo);
			resp.setReqType(CouponUseStatusEnum.FROZEN);
			resp.setTradeId(tradeId);
			resp.setUsercouponId(usercouponId);
			return resp;
		}
		
		//1 查询当前用户优惠券信息
		ActivityUsercoupon usercoupon = activityUsercouponServiceImpl.selectUsercouponById(usercouponId);
		//2 判断优惠券是否可用
		boolean useableFlag = false;
		if(usercoupon != null) {
			ActivityCoupon coupon = usercoupon.getCoupon();
			Date validityTimeEnd = DateUtils.getDayEnd(usercoupon.getValidityTimeEnd()); //优惠券到期日的最后时间
			Date nowTime = new Date();//当前时间
			if(coupon != null) { //优惠券主体不能为空
				
				if(CouponStatusEnum.EFFECTIVE == coupon.getCouponStatus() && validityTimeEnd.after(nowTime)
						&& usercoupon.getCouponCount() > 0) {
					useableFlag = true;
				}
			}
		}
		//3 冻结优惠券
		if(useableFlag) {
			try {
				activityUsercouponServiceImpl.updateUserCouponByFrozen(usercoupon, memberNo, tradeId, bizType);
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_SUCCESS);
			} catch(OptimisticLockingException e) {
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OPTIMISTICLOCKING);
			} catch(Exception e) {
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OTHER);
			}
		} else {
			resp.setResultCode(CouponUseResultCodeEnum.USERCOUPON_NOT_AVAILABLE);
		}
		//4 返回冻结结果
		resp.setMemberNo(memberNo);
		resp.setReqType(CouponUseStatusEnum.FROZEN);
		resp.setTradeId(tradeId);
		resp.setUsercouponId(usercouponId);
		return resp;
	}

	@Override
	public CouponUseRespDTO unFrozenUserCoupon(Long usercouponId,
			String memberNo, Long tradeId, BizTypeEnum bizType) {
		
		CouponUseRespDTO resp = new CouponUseRespDTO();
		try {
			CheckUtils.notEmpty(usercouponId,"");
			CheckUtils.notEmpty(memberNo,"");
			CheckUtils.notEmpty(tradeId,"");
			CheckUtils.notEmpty(bizType,"");
		} catch(Exception e) {
			resp.setResultCode(CouponUseResultCodeEnum.OPERATE_PARAM_ERROR);
			resp.setMemberNo(memberNo);
			resp.setReqType(CouponUseStatusEnum.UNFROZEN);
			resp.setTradeId(tradeId);
			resp.setUsercouponId(usercouponId);
			return resp;
		}
		
		//1.查询是否存在当前被冻结的用户优惠券使用记录
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usercouponId", usercouponId);
		params.put("memberNo", memberNo);
		params.put("tradeId", tradeId);
		params.put("bizType", bizType);
		params.put("useStatus", CouponUseStatusEnum.FROZEN);
		ActivityUsercouponRecord usercouponRecord = activityUsercouponRecordServiceImpl.selectTradeUsercouponRecord(params);
		//2.解冻优惠券
		if(usercouponRecord != null) {
			try {
				ActivityUsercoupon usercoupon = activityUsercouponServiceImpl.selectUsercouponById(usercouponId);
				activityUsercouponServiceImpl.updateUserCouponByUnFrozen(usercoupon,usercouponRecord);
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_SUCCESS);
			} catch(OptimisticLockingException e) {
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OPTIMISTICLOCKING);
			} catch(Exception e) {
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OTHER);
			}
			
		} else {
			resp.setResultCode(CouponUseResultCodeEnum.OPERATE_QUERY_USERCOUPON_NOTFOUND);
		}
		//3.返回解冻结果
		resp.setMemberNo(memberNo);
		resp.setReqType(CouponUseStatusEnum.UNFROZEN);
		resp.setTradeId(tradeId);
		resp.setUsercouponId(usercouponId);
		return resp;
	}

	@Override
	public CouponUseRespDTO consumeUserCoupon(Long usercouponId,
			String memberNo, Long tradeId, BizTypeEnum bizType) {

		CouponUseRespDTO resp = new CouponUseRespDTO();
		try {
			CheckUtils.notEmpty(usercouponId,"");
			CheckUtils.notEmpty(memberNo,"");
			CheckUtils.notEmpty(tradeId,"");
			CheckUtils.notEmpty(bizType,"");
		} catch(Exception e) {
			resp.setResultCode(CouponUseResultCodeEnum.OPERATE_PARAM_ERROR);
			resp.setMemberNo(memberNo);
			resp.setReqType(CouponUseStatusEnum.CONSUME);
			resp.setTradeId(tradeId);
			resp.setUsercouponId(usercouponId);
			return resp;
		}
		//1 查询被冻结优惠券使用记录
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("usercouponId", usercouponId);
		params.put("memberNo", memberNo);
		params.put("tradeId", tradeId);
		params.put("bizType", bizType);
		params.put("useStatus", CouponUseStatusEnum.FROZEN);
		ActivityUsercouponRecord usercouponRecord = activityUsercouponRecordServiceImpl.selectTradeUsercouponRecord(params);
		
		//2 修改对应状态为已消费
		if(usercouponRecord != null) {
			try {
//				ActivityUsercoupon usercoupon = activityUsercouponServiceImpl.selectUsercouponById(usercouponId);
				activityUsercouponServiceImpl.updateUserCouponByConsume(usercouponRecord);
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_SUCCESS);
			} catch(OptimisticLockingException e) {
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OPTIMISTICLOCKING);
			} catch(Exception e) {
				resp.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OTHER);
			}
			
		} else {
			resp.setResultCode(CouponUseResultCodeEnum.OPERATE_QUERY_USERCOUPON_NOTFOUND);
		}
		//3 返回优惠券使用结果
		resp.setMemberNo(memberNo);
		resp.setReqType(CouponUseStatusEnum.CONSUME);
		resp.setTradeId(tradeId);
		resp.setUsercouponId(usercouponId);
		return resp;
	}
	
	public static void main(String[] args) {
//		System.out.println(DateUtils.getDayEnd(new Date()));
	}

	@Override
	public List<ActivityUsercouponDTO> selectUserCouponUnuseList(String memberNo) {
		
		List<ActivityUsercouponDTO> resultList = new ArrayList<ActivityUsercouponDTO>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberNo", memberNo);
		params.put("couponStatus", CouponStatusEnum.EFFECTIVE);
		params.put("usercouponStatus", UsercouponStatusEnum.EFFECTIVE);
		params.put("validityTimeEndLarge", true);
		
		List<ActivityUsercoupon> list = activityUsercouponServiceImpl.selectUserUnuseOrExpireCouponList(params);
		if(list != null && list.size() > 0) {
			ActivityUsercouponDTO usercouponDto = null;
			for(ActivityUsercoupon usercoupon : list) {
				usercouponDto = EntityDTOConvert.toTarget(usercoupon, ActivityUsercouponDTO.class);
				usercouponDto.setCouponDto(EntityDTOConvert.toTarget(usercoupon.getCoupon(), ActivityCouponDTO.class));
				resultList.add(usercouponDto);
			}
		}
		return resultList;
	}

	@Override
	public List<ActivityUsercouponRecordDTO> selectUserCouponUsedList(String memberNo, BizTypeEnum bizType) {

		List<ActivityUsercouponRecordDTO> resultList = new ArrayList<ActivityUsercouponRecordDTO>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberNo", memberNo);
		params.put("useStatus", CouponUseStatusEnum.CONSUME);
		params.put("bizType", BizTypeEnum.LM_LICAI);

		List<ActivityUsercouponRecord> list = activityUsercouponRecordServiceImpl.selectUserUsedCouponList(params);
		if(list != null && list.size() > 0) {
			ActivityUsercouponRecordDTO recordDto = null;
			for(ActivityUsercouponRecord record : list) {
				recordDto = EntityDTOConvert.toTarget(record, ActivityUsercouponRecordDTO.class);
				recordDto.setCoupon(EntityDTOConvert.toTarget(record.getCoupon(), ActivityCouponDTO.class));
				recordDto.setUsercoupon(EntityDTOConvert.toTarget(record.getUsercoupon(), ActivityUsercouponDTO.class));
				resultList.add(recordDto);
			}
		}
		return resultList;
	}

	@Override
	public List<ActivityUsercouponDTO> selectUserCouponExpiredList(String memberNo) {

		List<ActivityUsercouponDTO> resultList = new ArrayList<ActivityUsercouponDTO>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberNo", memberNo);
		params.put("couponStatus", CouponStatusEnum.EFFECTIVE);
		params.put("usercouponStatus", UsercouponStatusEnum.EFFECTIVE);
		params.put("validityTimeEndLess", true);
		
		List<ActivityUsercoupon> list = activityUsercouponServiceImpl.selectUserUnuseOrExpireCouponList(params);
		if(list != null && list.size() > 0) {
			ActivityUsercouponDTO usercouponDto = null;
			for(ActivityUsercoupon usercoupon : list) {
				usercouponDto = EntityDTOConvert.toTarget(usercoupon, ActivityUsercouponDTO.class);
				usercouponDto.setCouponDto(EntityDTOConvert.toTarget(usercoupon.getCoupon(), ActivityCouponDTO.class));
				resultList.add(usercouponDto);
			}
		}
		return resultList;
	}

	@Override
	public List<ActivityUsercouponDTO> receiveUserCoupon(String eventCode,
			String memberNo) {
		CheckUtils.notEmpty(memberNo,"memberNo");
		CheckUtils.notEmpty(eventCode,"eventCode");
		
		logger.info("receiveUserCoupon memberNo={}, eventCode={}", memberNo, eventCode);
		List<ActivityUsercouponDTO> usercouponDtoList = new ArrayList<ActivityUsercouponDTO>();
		
		//1 查询当前事件对应的规则
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eventCode", eventCode);
		params.put("ruleStatus", CouponRuleStatusEnum.EFFECTIVE);
		List<ActivityRule> ruleList = activityRuleServiceImpl.selectByParams(params);
		Date nowTime = new Date();
		//2 判断规则是否有效
		boolean effectiveFlag = false;
		ActivityRule activityRule = null;
		//目前事件与规则一对一，其实只要查询一个就行。注意：多个规则也不能用下面代码或者说不允许一个事件对多个规则
		if(ruleList != null && ruleList.size() > 0) {
			for(ActivityRule rule : ruleList) {
				if(rule.getTakeEffectTime().before(nowTime)&&rule.getInvalidTime().after(nowTime)) {
					effectiveFlag = true;
					activityRule = rule;
					break;
				}
			}
		}
		if(effectiveFlag) {
			//3 查询当前事件对应的优惠券列表
			Map<String, Object> couponParam = new HashMap<String, Object>();
			couponParam.put("couponStatus", CouponStatusEnum.EFFECTIVE);
			couponParam.put("eventCode", eventCode);
			
			List<ActivityCoupon> couponList = activityCouponServiceImpl.selectByEventCode(couponParam); //获取当前事件下可用优惠券列表
			List<ActivityUsercoupon> usercouponList = new ArrayList<ActivityUsercoupon>();
			ActivityUsercoupon usercoupon = null;
			if(couponList != null && couponList.size() > 0) {
				for(ActivityCoupon coupon : couponList) {
					usercoupon = new ActivityUsercoupon();
					usercoupon.setCoupon(coupon);
					usercoupon.setCouponCount(1);
					usercoupon.setCouponUsedCount(0);
					usercoupon.setMemberNo(memberNo);
					usercoupon.setReceiveTime(nowTime);
					usercoupon.setRuleId(activityRule.getId());
					usercoupon.setRuleName(activityRule.getRuleName());
					usercoupon.setRuleType(RuleTypeEnum.USER_RECEIVE); //用户领取
					usercoupon.setStatus(UsercouponStatusEnum.EFFECTIVE);
					usercoupon.setValidityTimeStart(nowTime);
					
					Date validityTimeEnd = null;
					//有效期截止时间
					if(coupon.getValidityType().equals(ValidityTypeEnum.FLOAT)){
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DAY_OF_MONTH, coupon.getValidityDays() == null ? 0 : coupon.getValidityDays() - 1);
						validityTimeEnd =c.getTime();
					}else if(coupon.getValidityType().equals(ValidityTypeEnum.FIXED)){
						validityTimeEnd = coupon.getValidityDate();
					}
					usercoupon.setValidityTimeEnd(DateUtils.getDayEnd(validityTimeEnd));
					usercouponList.add(usercoupon);
					
				}
			}
			
			//4 领取优惠券
			if(usercouponList != null && usercouponList.size() > 0) {
				activityUsercouponServiceImpl.insertByUsercouponList(usercouponList);
				
				ActivityUsercouponDTO usercouponDto = null;
				for(ActivityUsercoupon usercoupon1 : usercouponList) {
					usercouponDto = EntityDTOConvert.toTarget(usercoupon1, ActivityUsercouponDTO.class);
					usercouponDto.setCouponDto(EntityDTOConvert.toTarget(usercoupon1.getCoupon(), ActivityCouponDTO.class));
					usercouponDtoList.add(usercouponDto);
				}
			}
		}
		return usercouponDtoList;
	}

	@Override
	public List<ActivityUsercouponDTO> queryTradeUserCouponList(
			String eventCode, String memberNo) {
		
		CheckUtils.notEmpty(memberNo,"memberNo");
		CheckUtils.notEmpty(eventCode,"eventCode");
		List<ActivityUsercouponDTO> usercouponDtoList = new ArrayList<ActivityUsercouponDTO>();
		
		//1 查询当前事件对应的规则
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eventCode", eventCode);
		params.put("ruleStatus", CouponRuleStatusEnum.EFFECTIVE);
		List<ActivityRule> ruleList = activityRuleServiceImpl.selectByParams(params);
		Date nowTime = new Date();
		//2 判断规则是否有效
		boolean effectiveFlag = false;
		ActivityRule activityRule = null;
		if(ruleList != null && ruleList.size() > 0) {
			for(ActivityRule rule : ruleList) {
				if(rule.getInvalidTime().after(nowTime)) {
					effectiveFlag = true;
					activityRule = rule;
					logger.info("[queryTradeUserCouponList] info={}","查询有效规则是"+rule);
					break;
				}
			}
		}
		logger.info("[queryTradeUserCouponList] effectiveFlag={}",effectiveFlag);
		if(effectiveFlag) {
			//3 查询当前事件对应的优惠券列表
			Map<String, Object> couponParam = new HashMap<String, Object>();
			couponParam.put("couponStatus", CouponStatusEnum.EFFECTIVE);
			couponParam.put("eventCode", eventCode);
			List<ActivityCoupon> couponList = activityCouponServiceImpl.selectByEventCode(couponParam); //获取当前事件下可用优惠券列表
			//4 查询用户是否已经领取10元优惠券
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("couponId", couponList.get(0).getId());
			param.put("memberNo", memberNo);
			param.put("usercouponStatus",UsercouponStatusEnum.EFFECTIVE );
			List<ActivityUsercoupon> usercouponList = new ArrayList<ActivityUsercoupon>();
			usercouponList = activityUsercouponServiceImpl.queryTradeUserCouponList(param);
			usercouponDtoList=EntityDTOConvert.toTragetList(usercouponList, ActivityUsercouponDTO.class);
		}
		return usercouponDtoList;
	}

}
