/**
 * @author cdt
 * @date 2016-5-19
 * @time 上午9:28:18
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityActionDTO;

/**
 * @author cdt
 * @date 2016-5-19
 * @time 上午9:28:18
 */
public interface ActivityActionRelaFacade {

	/**
	 * 根据活动id，查询对应事件集合
	 * @author cdt
	 * @date 2016-5-19
	 * @time 上午9:33:16
	 */
	public List<ActivityActionDTO> queryActionByActivityId(Long id);
}
