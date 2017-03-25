package com.yeepay.g3.core.activity.dao;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 用户优惠券关联数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午5:00:47
 * @version 1.0
 */
public interface ActivityUsercouponDao extends GenericDao<ActivityUsercoupon> {

	public ActivityUsercoupon selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityUsercoupon record);
	
	/**
	 * 根据用户相关条件查询优惠券列表
	 * @param params
	 * @return
	 */
	public List<ActivityUsercoupon> selectByUserParam(Map<String, Object> params);
}