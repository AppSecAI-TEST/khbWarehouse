/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityCouponEventRelationDao;
import com.yeepay.g3.core.activity.dao.ActivityEventDao;
import com.yeepay.g3.core.activity.entity.ActivityCouponEventRelation;
import com.yeepay.g3.core.activity.entity.ActivityEvent;
import com.yeepay.g3.core.activity.service.ActivityEventService;

/**
 * @Description 活动事件业务逻辑处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午4:35:38
 * @version 1.0
 */
@Service
public class ActivityEventServiceImpl implements ActivityEventService {

	@Autowired
	private ActivityEventDao activityEventDaoImpl;
	
	@Autowired
	private ActivityCouponEventRelationDao activityCouponEventRelationDaoImpl;
	
	@Override
	public void addEvent(ActivityEvent event, String coupons) {
		activityEventDaoImpl.add(event);
		//创建优惠券事件关系bean
		if(event.getId() != null && StringUtils.isNotEmpty(coupons)) {
			String[] couponIdArr = coupons.split(",");
			ActivityCouponEventRelation activityCouponEventRelation = null;
			for(String couponId : couponIdArr) {
				activityCouponEventRelation = new ActivityCouponEventRelation();
				activityCouponEventRelation.setEventId(event.getId());
				activityCouponEventRelation.setEventCode(event.getEventCode());
				activityCouponEventRelation.setCouponId(Long.valueOf(couponId));
				activityCouponEventRelation.setCreator(event.getCreator());
				
				activityCouponEventRelationDaoImpl.add(activityCouponEventRelation);
			}
		}
	}

	@Override
	public void deleteEventById(Long id) {
		
	}

	@Override
	public List<ActivityEvent> getAllEventList() {
		return activityEventDaoImpl.getAll();
	}

	@Override
	public ActivityEvent selectEventById(Long id) {
		return (ActivityEvent) activityEventDaoImpl.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public List<ActivityEvent> selectByRuleId(Long ruleId) {
		return activityEventDaoImpl.selectByRuleId(ruleId);
	}

	@Override
	public void updateEvent(ActivityEvent event, String coupons) {
		//根据事件id查询旧的事件信息
		ActivityEvent activityEvent = (ActivityEvent) activityEventDaoImpl.queryOne("selectByPrimaryKey", event.getId());
		//根据id删除事件与优惠券关联信息
		activityCouponEventRelationDaoImpl.delete("deleteByEventId", activityEvent.getId());
		//根据id修改事件信息
		event.setVersion(activityEvent.getVersion());
		activityEventDaoImpl.update("updateByPrimaryKeySelective", event);
		//添加事件与优惠券关联信息
		if(StringUtils.isNotEmpty(coupons)){
			String[] couponIdArr = coupons.split(",");
			ActivityCouponEventRelation activityCouponEventRelation = null;
			for(String couponId : couponIdArr) {
				activityCouponEventRelation = new ActivityCouponEventRelation();
				activityCouponEventRelation.setEventId(event.getId());
				activityCouponEventRelation.setEventCode(event.getEventCode());
				activityCouponEventRelation.setCouponId(Long.valueOf(couponId));
				activityCouponEventRelation.setCreator(event.getCreator());
				
				activityCouponEventRelationDaoImpl.add(activityCouponEventRelation);
			}
		}
	}

}
