/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityCouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityCouponLevelDTO;

/**
 * @Description 优惠券信息对外服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午8:18:56
 * @version 1.0
 */
public interface ActivityCouponFacade {
	/**
	 * 根据主键id获取优惠券信息
	 */
	public ActivityCouponDTO selectCouponById(long id);
	/**
	 * 保存优惠券信息
	 */
	public void addActivityCoupon(ActivityCouponDTO activityCouponDto, String couponLevel);
	
	/**
	 * 根据实体类参数查询信息
	 * @param activityCouponDto
	 * @return
	 */
	public List<ActivityCouponDTO> selectListByParams(ActivityCouponDTO activityCouponDto);
	 
	/**
	 * 查询有效优惠券（有效状态，截止日期大于当前期）
	 * @param activityCouponDto
	 * @return
	 */
	public List<ActivityCouponDTO> selectEffCouponList(ActivityCouponDTO activityCouponDto);
	/**
	 * 更新实体类（审批状态、操作人信息）
	 * @param activityCouponDto
	 */
	public void updateActivityCouponById(ActivityCouponDTO activityCouponDto);
	
	/**
	 * 根据优惠券ID获取优惠券使用范围列表
	 * @param couponId
	 * @return
	 */
	public List<ActivityCouponLevelDTO> getCouponLevelByCouponId(Long couponId);

	/**
	 * 查询事件所属的优惠券列表
	 * @param eventCode
	 * @return
	 */
	public List<ActivityCouponDTO> selecEventCouponList(String eventCode);
}
