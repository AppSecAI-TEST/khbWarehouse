package com.yeepay.g3.core.activity.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityCouponDao;
import com.yeepay.g3.core.activity.dao.ActivityGrantRecordDao;
import com.yeepay.g3.core.activity.dao.ActivityUsercouponDao;
import com.yeepay.g3.core.activity.entity.ActivityCoupon;
import com.yeepay.g3.core.activity.entity.ActivityGrantRecord;
import com.yeepay.g3.core.activity.entity.ActivityUsercoupon;
import com.yeepay.g3.core.activity.service.ActivityGrantRecordService;
import com.yeepay.g3.facade.activity.enums.GrantStatusEnum;
import com.yeepay.g3.facade.activity.enums.RuleTypeEnum;
import com.yeepay.g3.facade.activity.enums.UsercouponStatusEnum;
import com.yeepay.g3.facade.activity.enums.ValidityTypeEnum;
import com.yeepay.g3.utils.common.DateUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
/**
 * @Description 人工发放记录业务逻辑处理实现类
 * @author ying.liu
 * @CreateTime 2016-3-31
 * @version 1.0
 */
@Service
public class ActivityGrantRecordServiceImpl implements
		ActivityGrantRecordService {

	private Logger logger = LoggerFactory.getLogger(ActivityGrantRecordServiceImpl.class);
	@Autowired
	private ActivityGrantRecordDao activityGrantRecordDaoImpl;
	@Autowired
	private ActivityUsercouponDao activityUsercouponDaoImpl;
	@Autowired
	private ActivityCouponDao activityCouponDaoImpl;
	
	
	@Override
	public ActivityGrantRecord selectGrantRecordById(long id) {
		return (ActivityGrantRecord) activityGrantRecordDaoImpl.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public void addGrantRecord(ActivityGrantRecord activityGrantRecord) {
		logger.info("[addGrantRecord] activityGrantRecord={}",activityGrantRecord);
		activityGrantRecordDaoImpl.add(activityGrantRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityGrantRecord> selectListByParams(
			Map<String, Object> params) {
		return activityGrantRecordDaoImpl.query("selectListByParams", params);
	}

	@Override
	public void updateGrantRecord(ActivityGrantRecord activityGrantRecord) {
		logger.info("[updateGrantRecord] activityGrantRecord={}",activityGrantRecord);
		//更改批量发放记录状态，退回只更改记录状态
		if(activityGrantRecord.getGrantStatus().equals(GrantStatusEnum.RETURN_BACK)){
			activityGrantRecordDaoImpl.update(activityGrantRecord);
		}else{
			//审核通过，更改状态，添加用户优惠券记录
			activityGrantRecordDaoImpl.update(activityGrantRecord);
			List<ActivityUsercoupon> list = new ArrayList<ActivityUsercoupon>();
			ActivityUsercoupon activityUsercoupon = null;
			//根据id查询该批量发放记录的详细信息
			ActivityGrantRecord actGrantRecord = (ActivityGrantRecord) activityGrantRecordDaoImpl.queryOne("selectByPrimaryKey",activityGrantRecord.getId());
			logger.info("[updateGrantRecord] actGrantRecord={}",actGrantRecord);
			//根据优惠券id查询优惠券详细信息
			ActivityCoupon activityCoupon = (ActivityCoupon) activityCouponDaoImpl.queryOne("selectByPrimaryKey", actGrantRecord.getCouponId());
			Date validityTimeEnd = null;
			//有效期截止时间
			if(activityCoupon.getValidityType().equals(ValidityTypeEnum.FLOAT)){
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, activityCoupon.getValidityDays() == null ? 0 : activityCoupon.getValidityDays() - 1);
				validityTimeEnd =c.getTime();
			}else if(activityCoupon.getValidityType().equals(ValidityTypeEnum.FIXED)){
				validityTimeEnd = activityCoupon.getValidityDate();
			}
			//该记录的用户列表
			String memberNoList = new String(actGrantRecord.getMemberNoList());
			String[] memberNo = memberNoList.split(";");
			ActivityCoupon coupon = null;
			for(int i = 0;i < memberNo.length;i ++){
				coupon = new ActivityCoupon();
				coupon.setId(actGrantRecord.getCouponId());
				activityUsercoupon = new ActivityUsercoupon();
				activityUsercoupon.setCoupon(coupon);
				activityUsercoupon.setCouponCount(actGrantRecord.getPerGrantCount());
				activityUsercoupon.setCouponUsedCount(0);
				activityUsercoupon.setMemberNo(memberNo[i]);
				activityUsercoupon.setValidityTimeEnd(DateUtils.getDayEnd(validityTimeEnd)); //获取当天的最后时间
				//TODO       状态待定义
				activityUsercoupon.setStatus(UsercouponStatusEnum.EFFECTIVE);
				//规则ID、名称、类型插入
				activityUsercoupon.setRuleId(actGrantRecord.getId());
				activityUsercoupon.setRuleName(actGrantRecord.getBatchGrantName());
				activityUsercoupon.setRuleType(RuleTypeEnum.BATCH_GRANT);
				
			//	list.add(activityUsercoupon);
				activityUsercouponDaoImpl.add("insert",activityUsercoupon );
			}
			//更新优惠券发放数量
			int grantNum = actGrantRecord.getMemberCount() * actGrantRecord.getPerGrantCount();
			ActivityCoupon couponForUpdate = new ActivityCoupon();
			couponForUpdate.setId(activityCoupon.getId());
			couponForUpdate.setVersion(activityCoupon.getVersion());
			couponForUpdate.setGrantCount(activityCoupon.getGrantCount() == null ? 
					grantNum : (activityCoupon.getGrantCount() + grantNum));
			
			activityCouponDaoImpl.update(couponForUpdate);
			
			//批量插入
//			activityUsercouponDaoImpl.batchInsert("addBatchGrantRecord", list);
		}
	}

}
