package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityCouponEventRelation;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 事件优惠券关联数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:59:11
 * @version 1.0
 */
public interface ActivityCouponEventRelationDao extends GenericDao<ActivityCouponEventRelation> {

	public int deleteByPrimaryKey(Long id);

	public int insert(ActivityCouponEventRelation record);

	public ActivityCouponEventRelation selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityCouponEventRelation record);
}