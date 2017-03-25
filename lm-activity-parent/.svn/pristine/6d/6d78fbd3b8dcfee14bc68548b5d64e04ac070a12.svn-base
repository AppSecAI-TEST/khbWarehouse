/**
 * @author 陈大涛
 * 2016-8-3上午9:04:12
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yeepay.g3.core.activity.entity.ActivityAppAd;
import com.yeepay.g3.core.activity.service.ActivityAppAdService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;
import com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-3上午9:04:12
 */
@Controller
public class ActivityAppAdFacadeImpl implements ActivityAppAdFacade {

	@Resource
	private ActivityAppAdService activityAppAdServiceImpl;
	@Override
	public void insertAppAd(ActivityAppAdDTO activityAppAdDTO) {
		activityAppAdDTO.setCreateTime(new Date());
		ActivityAppAd activityAppAd = new ActivityAppAd();
		activityAppAd=EntityDTOConvert.toTarget(activityAppAdDTO, ActivityAppAd.class);
		activityAppAdServiceImpl.insertAppAd(activityAppAd);
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade#updateAppAd(com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO)
	 */
	@Override
	public void updateAppAd(ActivityAppAdDTO activityAppAdDTO) {
		activityAppAdDTO.setUpdateTime(new Date());
		ActivityAppAd activityAppAd = new ActivityAppAd();
		activityAppAd=EntityDTOConvert.toTarget(activityAppAdDTO, ActivityAppAd.class);
		activityAppAdServiceImpl.updateAppAd(activityAppAd);
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade#queryAppAdDetail(java.lang.Long)
	 */
	@Override
	public ActivityAppAdDTO queryAppAdDetail(Long id) {
		ActivityAppAd activityAppAd = activityAppAdServiceImpl.queryAppAdDetail(id);
		ActivityAppAdDTO result = EntityDTOConvert.toTarget(activityAppAd, ActivityAppAdDTO.class);
		return result;
	}
	/* (non-Javadoc)
	 * @see com.yeepay.g3.facade.activity.facade.ActivityAppAdFacade#queryAppAdForApp()
	 */
	@Override
	public ActivityAppAdDTO queryAppAdForApp() {
		ActivityAppAd activityAppAd = activityAppAdServiceImpl.queryAppAdForApp();
		ActivityAppAdDTO result = EntityDTOConvert.toTarget(activityAppAd, ActivityAppAdDTO.class);
		return result;
	}

}
