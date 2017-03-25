/**
 * 
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityCouponLevel;
import com.yeepay.g3.core.activity.service.ActivityCouponService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityCouponLevelDTO;
import com.yeepay.g3.facade.activity.enums.CouponStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityCouponFacade;
import com.yeepay.g3.utils.common.StringUtils;

/**
 * @Description 优惠券信息对外接口实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午8:21:23
 * @version 1.0
 */
@Service
public class ActivityCouponFacadeImpl implements ActivityCouponFacade {

    private static Log logger = LogFactory.getLog(ActivityCouponFacadeImpl.class);
    
	@Autowired
	private ActivityCouponService activityCouponServiceImpl;

	@Override
	public ActivityCouponDTO selectCouponById(long id) {
		ActivityCouponDTO activityCouponDto = new ActivityCouponDTO();
		ActivityCoupon activityCoupon = activityCouponServiceImpl.selectCouponById(id);
		activityCouponDto = EntityDTOConvert.toTarget(activityCoupon, ActivityCouponDTO.class);
		return activityCouponDto;
	}
	
	@Override
	public void addActivityCoupon(ActivityCouponDTO activityCouponDto, String couponLevel) {
		
		ActivityCoupon activityCoupon = new ActivityCoupon();
		
		activityCoupon = EntityDTOConvert.toTarget(activityCouponDto, ActivityCoupon.class);
		activityCoupon.setCouponStatus(CouponStatusEnum.CHECKING); //初始化状态为待审核
		
		activityCouponServiceImpl.addActivityCoupon(activityCoupon, couponLevel);
		
	}

	@Override
	public List<ActivityCouponDTO> selectListByParams(ActivityCouponDTO activityCouponDto) {
		List<ActivityCouponDTO> list = new ArrayList<ActivityCouponDTO>();
		if(activityCouponDto != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			//优惠券名称
			if(StringUtils.isNotEmpty(activityCouponDto.getCouponName())) {
				params.put("couponName", activityCouponDto.getCouponName());
			}
			//优惠券类型
			if(activityCouponDto.getCouponType() != null) {
				params.put("couponType", activityCouponDto.getCouponType());
			}
			//创建起始时间
			if(activityCouponDto.getCreateTimeStart() != null) {
				params.put("createTimeStart", activityCouponDto.getCreateTimeStart());
			}
			//创建截止时间
			if(activityCouponDto.getCreateTimeEnd() != null) {
				params.put("createTimeEnd", activityCouponDto.getCreateTimeEnd());
			}
			//审核操作起始时间
			if(activityCouponDto.getCheckedTimeStart() != null) {
				params.put("checkedTimeStart", activityCouponDto.getCheckedTimeStart());
			}
			//审核操作截止时间
			if(activityCouponDto.getCheckedTimeEnd() != null) {
				params.put("checkedTimeEnd", activityCouponDto.getCheckedTimeEnd());
			}
			//优惠券状态
			if(activityCouponDto.getCouponStatus() != null) {
				params.put("couponStatus", activityCouponDto.getCouponStatus());
			}
			List<ActivityCoupon> resultList = activityCouponServiceImpl.selectListByParams(params);
			if(resultList != null && resultList.size() > 0) {
				list = EntityDTOConvert.toTragetList(resultList, ActivityCouponDTO.class);
			}
		}
		return list;
	}

	@Override
	public void updateActivityCouponById(ActivityCouponDTO activityCouponDto) {
		if(activityCouponDto != null && activityCouponDto.getId() != null) {
			//判断是否非法调用接口
			if(StringUtils.isNotEmpty(activityCouponDto.getCheckor())) {
				ActivityCoupon activityCoupon = new ActivityCoupon();
				activityCoupon = EntityDTOConvert.toTarget(activityCouponDto, ActivityCoupon.class);
				activityCouponServiceImpl.updateActivityCouponById(activityCoupon);
			}
		}
	}

	@Override
	public List<ActivityCouponDTO> selectEffCouponList(
			ActivityCouponDTO activityCouponDto) {
		List<ActivityCouponDTO> list = new ArrayList<ActivityCouponDTO>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("couponStatus", activityCouponDto.getCouponStatus());
		params.put("validityDate", activityCouponDto.getValidityDate());
		logger.info("[selectEffCouponList] couponStatus="+activityCouponDto.getCouponStatus());
		/*Date date = new Date();
		String jointSql = "AND VALIDITY_DATE IS NULL OR VALIDITY_DATE>'"+date+"'";
		params.put("jointSql", jointSql);*/
		List<ActivityCoupon> resultList = activityCouponServiceImpl.selectEffCouponList(params);
		if(resultList != null && resultList.size() > 0) {
			list = EntityDTOConvert.toTragetList(resultList, ActivityCouponDTO.class);
		}
		return list;
	}

	@Override
	public List<ActivityCouponLevelDTO> getCouponLevelByCouponId(Long couponId) {
		
		List<ActivityCouponLevelDTO> list = new ArrayList<ActivityCouponLevelDTO>();
		List<ActivityCouponLevel> resultList = activityCouponServiceImpl.getCouponLevelByCouponId(couponId);
		if(resultList != null && resultList.size() > 0) {
			list = EntityDTOConvert.toTragetList(resultList, ActivityCouponLevelDTO.class);
		}
		return list;
	}

	@Override
	public List<ActivityCouponDTO> selecEventCouponList(String eventCode) {
		List<ActivityCouponDTO> list = new ArrayList<ActivityCouponDTO>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("couponStatus", CouponStatusEnum.EFFECTIVE);
		params.put("eventCode", eventCode);
		List<ActivityCoupon> couponList = activityCouponServiceImpl.selectByEventCode(params); //获取当前事件下可用优惠券列表
		if(couponList != null && couponList.size() > 0) {
			list = EntityDTOConvert.toTragetList(couponList, ActivityCouponDTO.class);
		}
		return list;
	}

}
