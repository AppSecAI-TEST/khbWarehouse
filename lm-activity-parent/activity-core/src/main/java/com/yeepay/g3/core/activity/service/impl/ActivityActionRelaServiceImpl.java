/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:30
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.impl.ActivityActionRelaDaoImpl;
import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.service.ActivityActionRelaService;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:30
 */
@Service
public class ActivityActionRelaServiceImpl implements ActivityActionRelaService {

	@Autowired
	private ActivityActionRelaDaoImpl activityActionRelaDaoImpl;

	@Override
	public List<ActivityAction> queryActionByActivityId(Long id) {
		
		return activityActionRelaDaoImpl.queryActionByActivityId(id);
	}

}
