/**
 * @author cdt
 * @date 2016-5-18
 * @time 下午4:39:42
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityInfoDTO;

/**
 * @author cdt
 * @date 2016-5-18
 * @time 下午4:39:42
 */
public interface ActivityFacade {

	/**
	 * 新增活动
	 * @author cdt
	 * @date 2016-5-18
	 * @time 下午4:34:03
	 */
	public void insertActivity(ActivityInfoDTO activityInfoDto,String actions,String creator) throws Exception ;
	
	
	/**
	 * 根据活动id查询活动实体
	 * @author cdt
	 * @date 2016-5-19
	 * @time 上午9:08:53
	 */
	public ActivityInfoDTO queryActivityById(Long id);
	
	/**
	 * 修改活动
	 * @author cdt
	 * @date 2016-5-19
	 * @time 上午11:24:01
	 */
	public void updateActivity(ActivityInfoDTO activityInfoDto,String actions,String creator) throws Exception ;
	
	/**
	 * 审核活动
	 * @author cdt
	 * @date 2016-5-19
	 * @time 下午2:21:51
	 */
	public void checkActivity(ActivityInfoDTO activityInfoDto,String status,String CHECKOR);
	
	/**
	 * 查询有效的活动的列表
	 * @author hongbin.kang
	 * @return
	 */
	public List<ActivityInfoDTO> selectEffActivityList();


	/**
	 * 根据actionCode查询activityCode
	 * @param actionCode
	 * @return
	 */
	public String queryActivityCodeByActionCode(String actionCode);
}
