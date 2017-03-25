package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityUsercouponRecord;

/**
 * 
 * @Description 用户优惠券使用记录业务逻辑处理接口
 * @author zhenping.zhou
 * @CreateTime 2016年4月4日 上午10:51:11
 * @version 1.0
 */
public interface ActivityUsercouponRecordService {
	
	/**
	 * 查询冻结的用户优惠券使用记录
	 * @param params
	 * @return
	 */
	public ActivityUsercouponRecord selectTradeUsercouponRecord(Map<String, Object> params);
	
	/**
	 * 新增一条用户优惠券记录
	 * @param usercouponRecord
	 */
	public void addActivityUsercouponRecord(ActivityUsercouponRecord usercouponRecord);
	
	/**
	 * 用户已使用优惠券查询
	 * @param params
	 * @return
	 */
	public List<ActivityUsercouponRecord> selectUserUsedCouponList(Map<String, Object> params);
	
	
}
