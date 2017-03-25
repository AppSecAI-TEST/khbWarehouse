package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityUsercouponRecord;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 用户优惠券使用记录数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午5:00:14
 * @version 1.0
 */
public interface ActivityUsercouponRecordDao extends GenericDao<ActivityUsercouponRecord> {

	public ActivityUsercouponRecord selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityUsercouponRecord record);
}