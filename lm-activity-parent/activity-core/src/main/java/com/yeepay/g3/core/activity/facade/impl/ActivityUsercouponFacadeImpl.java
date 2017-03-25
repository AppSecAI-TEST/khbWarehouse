package com.yeepay.g3.core.activity.facade.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.core.activity.service.ActivityUsercouponService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.facade.ActivityUsercouponFacade;
/**
 * @Description 用户优惠券管理对外接口实现类
 * @author ying.liu
 * @CreateTime 2016-4-1
 * @version 1.0
 */
@Service
public class ActivityUsercouponFacadeImpl implements ActivityUsercouponFacade {

	@Autowired
	private ActivityUsercouponService activityUsercouponServiceImpl;

	@Override
	public ActivityUsercouponDTO selectUsercouponById(long id) {
		ActivityUsercouponDTO usercouponDto = new ActivityUsercouponDTO();
		ActivityUsercoupon usercoupon = activityUsercouponServiceImpl.selectUsercouponById(id);
		usercouponDto = EntityDTOConvert.toTarget(usercoupon, ActivityUsercouponDTO.class);
		ActivityCouponDTO couponDto = new ActivityCouponDTO();
		if(usercoupon != null){
			couponDto = EntityDTOConvert.toTarget(usercoupon.getCoupon(), ActivityCouponDTO.class);
			usercouponDto.setCouponDto(couponDto);
		}
		
		return usercouponDto;
	}

}
