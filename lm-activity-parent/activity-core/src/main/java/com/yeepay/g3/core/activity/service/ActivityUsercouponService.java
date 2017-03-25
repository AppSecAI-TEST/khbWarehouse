package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.core.activity.entity.ActivityUsercouponRecord;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.enums.BizTypeEnum;

/**
 * 
 * @Description 用户优惠券业务逻辑处理接口
 * @author zhenping.zhou
 * @CreateTime 2016年4月4日 上午10:51:11
 * @version 1.0
 */
public interface ActivityUsercouponService {
	
	/**
	 * 新增用户红包
	 * @param params
	 * @return
	 */
	public void addUsercoupon(ActivityUsercoupon usercoupon);
	
	/**
	 * 查询用户优惠券列表
	 * @param params
	 * @return
	 */
	public List<ActivityUsercoupon> selectTradeUsercouponList(Map<String, Object> params);
	
	/**
	 * 根据ID获取用户优惠券信息
	 * @param id
	 * @return
	 */
	public ActivityUsercoupon selectUsercouponById(Long id);
	
	/**
	 * 冻结用户优惠券，新增一条用户优惠券冻结记录
	 * @param usercoupon 用户优惠券信息
	 * @param memberNo 会员编号
	 * @param tradeId 交易ID
	 * @param bizType 业务类型
	 */
	public void updateUserCouponByFrozen(ActivityUsercoupon usercoupon,
			String memberNo, Long tradeId, BizTypeEnum bizType);
	
	/**
	 * 解冻用户优惠券，修改用户优惠券状态为解冻，并且原持有优惠券数+1，使用数减1
	 * @param usercoupon 用户优惠券冻结记录
	 * @param usercouponRecord 用户优惠券使用记录
	 */
	public void updateUserCouponByUnFrozen(ActivityUsercoupon usercoupon,ActivityUsercouponRecord usercouponRecord);
	
	/**
	 * 使用优惠券，将优惠券使用记录状态改为已消费
	 * @param usercouponRecord 用户优惠券使用记录
	 */
	public void updateUserCouponByConsume(ActivityUsercouponRecord usercouponRecord);
	
	/**
	 * 根据状态查询用户优惠券列表
	 * @param params
	 * @return
	 */
	public List<ActivityUsercoupon> selectUserUnuseOrExpireCouponList(Map<String, Object> params);
	
	/**
	 * 批量插入用户优惠券数据
	 * @param usercouponList
	 */
	public void insertByUsercouponList(List<ActivityUsercoupon> usercouponList);

	/**
	 * 更新用户红包记录状态 审核通过或者作废
	 * @param params 用户红包批次号
	 */
	public void updateCouponStatusByBatchId(Map<String, Object> params);
	
	/**
	 * 用户根据优惠券id查询是否已经领取优惠券
	 * @author 陈大涛
	 * 2016-9-13上午10:57:07
	 */
	public List<ActivityUsercoupon> queryTradeUserCouponList(Map<String,Object> params);
	
}
