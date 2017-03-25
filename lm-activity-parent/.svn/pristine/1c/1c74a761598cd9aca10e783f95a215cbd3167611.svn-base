package com.yeepay.g3.core.activity.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityUsercouponDao;
import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;
/**
 * @Description 用户与优惠券关联服务类
 * @author ying.liu
 * @CreateTime 2016-4-3
 * @version 1.0
 */
@Repository
public class ActivityUsercouponDaoImpl extends GenericDaoDefault<ActivityUsercoupon> implements ActivityUsercouponDao {

	@Override
	public ActivityUsercoupon selectByPrimaryKey(Long id) {
		List<ActivityUsercoupon> list = this.query("selectByPrimaryKey", id);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityUsercoupon record) {
		return 0;
	}

	@Override
	public List<ActivityUsercoupon> selectByUserParam(Map<String, Object> params) {
		return this.query("selectByUserParam", params);
	}

}
