/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:13:40
 */
package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.facade.activity.dto.ActivityActionDTO;

/**
 * 事件service
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:13:40
 */
public interface ActivityActionService {
	/**
	 * 新增事件
	 * @author cdt
	 * @date 2016-5-16
	 * @time 下午8:14:42
	 */
	public void insertActivityAction(ActivityAction actionDto,String prizes,String odds,String versions,String levels);
	
	/**
	 * 获取事件详情
	 * @author cdt
	 * @date 2016-5-17
	 * @time 下午1:42:56
	 */
	public ActivityAction getActionDetail(Long ActionId);
	
	/**
	 * 更新事件与奖品信息
	 * @author cdt
	 * @date 2016-5-17
	 * @time 下午3:33:30
	 */
	public void updateActivityAction(ActivityAction action,String prizes,String odds,String versions,String levels);
	
	/**
	 * 获取所有事件
	 * @author cdt
	 * @date 2016-5-18
	 * @time 下午3:57:30
	 */
	public List<ActivityAction> queryActionAll();
}
