/**
 * @author 陈大涛
 * 2016-8-3上午9:04:56
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityAppAdDao;
import com.yeepay.g3.core.activity.entity.ActivityAppAd;
import com.yeepay.g3.core.activity.service.ActivityAppAdService;
import com.yeepay.g3.facade.activity.dto.ActivityAppAdDTO;

/**
 * @Description
 * @author 陈大涛
 * 2016-8-3上午9:04:56
 */
@Service
public class ActivityAppAdServiceImpl implements ActivityAppAdService {
	@Resource
	private ActivityAppAdDao activityAppAdDaoImpl;
	
	@Override
	public void insertAppAd(ActivityAppAd activityAppAd) {
		activityAppAdDaoImpl.add(activityAppAd);
	}

	@Override
	public void updateAppAd(ActivityAppAd activityAppAd) {
		activityAppAdDaoImpl.update(activityAppAd);
		
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityAppAdService#queryAppAdDetail(java.lang.Long)
	 */
	@Override
	public ActivityAppAd queryAppAdDetail(Long id) {
		ActivityAppAd result = (ActivityAppAd)activityAppAdDaoImpl.queryOne("selectByPrimaryKey", id);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.service.ActivityAppAdService#queryAppAdForApp()
	 */
	@Override
	public ActivityAppAd queryAppAdForApp() {
		List<ActivityAppAd> result = activityAppAdDaoImpl.query("queryOnlyOneAppAd");
		if(result==null||result.size()==0){
			ActivityAppAd activityAppAd = new ActivityAppAd();
			return activityAppAd;
		}else{
			return result.get(0);
		}
		
	}

}
