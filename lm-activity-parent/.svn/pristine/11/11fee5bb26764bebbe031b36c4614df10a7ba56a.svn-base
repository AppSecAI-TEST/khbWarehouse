/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;
import com.yeepay.g3.facade.activity.dto.ActivityGrantRecordDTO;

/**
 * @Description 人工记录表对外接口
 * @author ying.liu
 * @CreateTime 2016-3-31
 * @version 1.0
 */
public interface ActivityGrantRecordFacade {

	/**
	 * 根据id获取记录信息
	 */
	public ActivityGrantRecordDTO selectGrantRecordById(long id);
	/**
	 * 保存发放记录表
	 */
	public void addGrantRecord(ActivityGrantRecordDTO activityGrantRecordDto);
	
	/**
	 * 根据实体类中的参数值，查询列表
	 * @param params
	 * @return
	 */
	public List<ActivityGrantRecordDTO> selectListByParams(ActivityGrantRecordDTO activityGrantRecordDto);
	
	/**
	 * 审核批量发放
	 * @param ActivityGrantRecordDTO
	 */
	public void checkGrantRecord(ActivityGrantRecordDTO activityGrantRecordDto);
}
