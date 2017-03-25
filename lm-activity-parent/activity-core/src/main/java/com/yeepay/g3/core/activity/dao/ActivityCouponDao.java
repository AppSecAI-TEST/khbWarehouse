package com.yeepay.g3.core.activity.dao;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 优惠券信息数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:58:12
 * @version 1.0
 */
public interface ActivityCouponDao extends GenericDao<ActivityCoupon> {

    public int deleteByPrimaryKey(Long id);

    public ActivityCoupon selectByPrimaryKey(Long id);

    public int updateByPrimaryKey(ActivityCoupon record);
    
    /**
     * 获取优惠券列表
     * @param params
     * @return
     */
    public List<ActivityCoupon> selectByParams(Map<String, Object> params);
}