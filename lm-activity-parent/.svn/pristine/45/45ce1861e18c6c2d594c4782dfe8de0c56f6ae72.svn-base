/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:10:41
 */
package com.yeepay.g3.core.activity.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.core.activity.service.ActivityActionRelaService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityActionDTO;
import com.yeepay.g3.facade.activity.facade.ActivityActionRelaFacade;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:10:41
 */
@Service
public class ActivityActionRelaFacadeImpl implements ActivityActionRelaFacade {
	@Autowired
	private ActivityActionRelaService activityActionRelaServiceImpl;

	@Override
	public List<ActivityActionDTO> queryActionByActivityId(Long id) {
		List<ActivityAction> activityActionList=activityActionRelaServiceImpl.queryActionByActivityId(id);
		List<ActivityActionDTO> activityActionListDto=EntityDTOConvert.toTragetList(activityActionList, ActivityActionDTO.class);
		return activityActionListDto;
	}
}
