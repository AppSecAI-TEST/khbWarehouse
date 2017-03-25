/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityUsercouponRecordDao;
import com.yeepay.g3.core.activity.entity.ActivityUsercouponRecord;
import com.yeepay.g3.core.activity.service.ActivityUsercouponRecordService;

/**
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2016年4月5日 上午12:44:39
 * @version 1.0
 */
@Service
public class ActivityUsercouponRecordServiceImpl implements
		ActivityUsercouponRecordService {
	
	@Autowired
	private ActivityUsercouponRecordDao activityUsercouponRecordDaoImpl;

	@Override
	public ActivityUsercouponRecord selectTradeUsercouponRecord(
			Map<String, Object> params) {
		List<ActivityUsercouponRecord> list = activityUsercouponRecordDaoImpl.query("selectByParams", params);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addActivityUsercouponRecord(
			ActivityUsercouponRecord usercouponRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivityUsercouponRecord> selectUserUsedCouponList(
			Map<String, Object> params) {
		
		return activityUsercouponRecordDaoImpl.query("selectByParams", params);
	}

}
