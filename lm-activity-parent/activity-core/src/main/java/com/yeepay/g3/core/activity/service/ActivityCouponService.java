package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityCouponLevel;

/**
 * @Description 优惠券信息业务逻辑处理接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月25日 下午2:13:50
 * @version 1.0
 */
public interface ActivityCouponService {

	/**
	 * 新增优惠券信息
	 * @param activityCoupon
	 * @return
	 */
	public ActivityCoupon selectCouponById(long id);
	/**
	 * 新增优惠券信息
	 * @param activityCoupon
	 * @return
	 */
	public void addActivityCoupon(ActivityCoupon activityCoupon, String couponLevel);
	
	/**
	 * 根据条件查询优惠券列表
	 * 条件包含（优惠券名称、类型、状态、时间有效期等）
	 * @param params
	 * @return
	 */
	public List<ActivityCoupon> selectListByParams(Map<String, Object> params);
	
	/**
	 * 查询有效优惠券列表
	 * 条件包含（优惠券状态，隐含条件：截止日期大于当前日期）
	 * @param params
	 * @return
	 */
	public List<ActivityCoupon> selectEffCouponList(Map<String, Object> params);
	
	/**
	 * 查询事件关联优惠券
	 * 条件包含（优惠券状态，隐含条件：截止日期大于当前日期）
	 * @param params
	 * @return
	 */
	public List<ActivityCoupon> selectByEventCode(Map<String, Object> params);
	
	/**
	 * 根据主键ID更新优惠券信息（审核时更新优惠券状态、操作人信息）
	 * @param activityCoupon
	 */
	public void updateActivityCouponById(ActivityCoupon activityCoupon);
	
	/**
	 * 根据优惠券ID获取优惠券使用范围列表
	 * @param couponId
	 * @return
	 */
	public List<ActivityCouponLevel> getCouponLevelByCouponId(Long couponId);
}
