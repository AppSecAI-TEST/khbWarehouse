/**
 * 
 */
package com.yeepay.g3.core.activity.dao.impl;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityUsercouponRecordDao;
import com.yeepay.g3.core.activity.entity.ActivityUsercouponRecord;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @Description 用户优惠券使用记录数据服务实现类
 * @author zhenping.zhou
 * @CreateTime 2016年4月5日 上午12:02:54
 * @version 1.0
 */
@Repository
public class ActivityUsercouponRecordDaoImpl extends GenericDaoDefault<ActivityUsercouponRecord>
		implements ActivityUsercouponRecordDao {

	@Override
	public ActivityUsercouponRecord selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityUsercouponRecord record) {
		return 0;
	}

}
