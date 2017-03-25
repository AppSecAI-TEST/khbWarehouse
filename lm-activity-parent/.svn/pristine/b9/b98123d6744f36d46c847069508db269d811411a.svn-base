/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityCouponDao;
import com.yeepay.g3.core.activity.dao.ActivityCouponLevelDao;
import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityCouponLevel;
import com.yeepay.g3.core.activity.service.ActivityCouponService;

/**
 * @Description 优惠券信息业务逻辑处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月25日 下午2:15:14
 * @version 1.0
 */
@Service
public class ActivityCouponServiceImpl implements ActivityCouponService {
	
    private static Log logger = LogFactory.getLog(ActivityCouponServiceImpl.class);

	@Autowired
	private ActivityCouponDao activityCouponDaoImpl;
	
	@Autowired
	private ActivityCouponLevelDao activityCouponLevelDaoImpl;

	@Override
	public ActivityCoupon selectCouponById(long id) {
		
		return (ActivityCoupon) activityCouponDaoImpl.queryOne("selectByPrimaryKey", id);
	}
	
	@Override
	public void addActivityCoupon(ActivityCoupon activityCoupon, String couponLevel) {
		activityCouponDaoImpl.add(activityCoupon);
		//创建优惠券使用范围关系bean
		if(activityCoupon.getId() != null && StringUtils.isNotEmpty(couponLevel)) {
			String[] levelArr = couponLevel.split(",");
			ActivityCouponLevel activityCouponLevel = null;
			for(String level : levelArr) {
				activityCouponLevel = new ActivityCouponLevel();
				activityCouponLevel.setCouponId(activityCoupon.getId());
				activityCouponLevel.setChannelCode(level.split("-")[0]);
				activityCouponLevel.setBigColumnCode(level.split("-")[1]);
				activityCouponLevel.setSmallColumnCode(level.split("-")[2]);
				activityCouponLevel.setCreator(activityCoupon.getCreator());
				activityCouponLevelDaoImpl.add(activityCouponLevel);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityCoupon> selectListByParams(Map<String, Object> params) {
		
		return activityCouponDaoImpl.query("selectByParams", params);
	}

	@Override
	public void updateActivityCouponById(ActivityCoupon activityCoupon) {
		activityCouponDaoImpl.update(activityCoupon);
		
//		ActivityCouponLevel activityCouponLevel = new ActivityCouponLevel();
//		activityCouponLevel.setCouponId(activityCoupon.getId());
//		activityCouponLevel.setChannelCode("FIXED");
//		activityCouponLevel.setBigColumnCode("QUAY");
//		activityCouponLevel.setSmallColumnCode("180D");
//		activityCouponLevel.setCreator(activityCoupon.getCreator());
//		activityCouponLevelDaoImpl.add(activityCouponLevel);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityCoupon> selectEffCouponList(Map<String, Object> params) {
		return activityCouponDaoImpl.query("selectByStatus", params);
	}

	@Override
	public List<ActivityCoupon> selectByEventCode(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return activityCouponDaoImpl.query("selectByEventCode", params);
	}

	@Override
	public List<ActivityCouponLevel> getCouponLevelByCouponId(Long couponId) {
		List<ActivityCouponLevel> couponLevelList = activityCouponLevelDaoImpl.query("selectByCouponId", couponId);
		return couponLevelList;
	}

}
