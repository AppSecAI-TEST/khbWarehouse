package com.yeepay.g3.core.activity.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.core.activity.service.ActivityCouponService;
import com.yeepay.g3.core.activity.service.ActivityUserPacketService;
import com.yeepay.g3.core.activity.service.ActivityUsercouponService;
import com.yeepay.g3.facade.activity.dto.ImportRedPacketDTO;
import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.CouponTypeEnum;
import com.yeepay.g3.facade.activity.enums.DiscountTypeEnum;
import com.yeepay.g3.facade.activity.enums.RuleTypeEnum;
import com.yeepay.g3.facade.activity.enums.UsercouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.ValidityTypeEnum;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * 
 * @Description 用户发放红包逻辑处理类
 * @author zhenping.zhou
 * @CreateTime 2016年8月3日 下午1:32:59
 * @version 1.0
 */
@Service
public class ActivityUserPacketServiceImpl implements ActivityUserPacketService {
	@Autowired
	private ActivityCouponService activityCouponServiceImpl;
	@Autowired
	private ActivityUsercouponService activityUsercouponServiceImpl;
	
	private DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public void saveBatchUserPacket(List<ImportRedPacketDTO> redPacketDtoList) {
		
		if(redPacketDtoList != null && redPacketDtoList.size() > 0) {
			ActivityCoupon coupon = null;
//			List<ActivityCoupon> couponList = new ArrayList<ActivityCoupon>();
			
			ActivityUsercoupon usercoupon = null;
//			List<ActivityUsercoupon> usercouponList = new ArrayList<ActivityUsercoupon>();
			String couponLevel = (String) ConfigurationUtils.getSysConfigParam("activity_red_packet_couponlevel").getValue();
			if(StringUtils.isEmpty(couponLevel)) {
				couponLevel = "ADVISOR-ADVISOR-ADVISOR";
			}
			
			Date curTime = new Date();
			
			for(ImportRedPacketDTO redPacket : redPacketDtoList) {
				//1 生成对应的红包优惠券
				coupon = new ActivityCoupon();
				coupon.setCouponName(redPacket.getPacketName());
				coupon.setCouponType(CouponTypeEnum.FULL_ADD);
				coupon.setDiscountType(DiscountTypeEnum.PRINCIPAL);
				coupon.setMinInvestAmount(new BigDecimal(10));
				coupon.setCouponAmount(redPacket.getPacketAmount());
				coupon.setIncreaseInterest(null);
				coupon.setRuleDesc(null);
				coupon.setTotalCount(1);
				coupon.setGrantCount(1);
				coupon.setValidityType(ValidityTypeEnum.FLOAT);
				coupon.setValidityDays(redPacket.getValidityDays());
				coupon.setValidityDate(null);
				coupon.setCouponStatus(CouponStatusEnum.EFFECTIVE);
				coupon.setCreator(redPacket.getCreator());
				coupon.setCheckedTime(curTime);
				coupon.setCheckor(redPacket.getCreator());
				coupon.setCouponRemark(null);
				coupon.setIsRedPacket(1); //理财红包
				activityCouponServiceImpl.addActivityCoupon(coupon, couponLevel);
				
//				couponList.add(coupon);
				
				//2 生成对应的用户红包记录
				usercoupon = new ActivityUsercoupon();
				usercoupon.setCoupon(coupon);
				usercoupon.setCouponCount(1);
				usercoupon.setCouponUsedCount(0);
				usercoupon.setMemberNo(redPacket.getMemberNo());
				usercoupon.setValidityTimeStart(curTime);
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
				usercoupon.setRuleId(null);
				usercoupon.setRuleName(null);
				usercoupon.setStatus(UsercouponStatusEnum.CHECKING);
				usercoupon.setRuleType(RuleTypeEnum.BATCH_GRANT);
				usercoupon.setBatchId(df.format(curTime));
				
				activityUsercouponServiceImpl.addUsercoupon(usercoupon);
				
//				usercouponList.add(usercoupon);
			}
			
		}
	}
	
}
