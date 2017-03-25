package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityGrantRecord;
import com.yeepay.g3.facade.activity.dto.ActivityGrantRecordDTO;

/**
 * @Description 人工发放记录业务逻辑处理接口
 * @author ying.liu
 * @CreateTime 2016-3-31
 * @version 1.0
 */
public interface ActivityGrantRecordService {

	/**
	 * 根据id查询记录信息
	 */
	public ActivityGrantRecord selectGrantRecordById(long id);
	/**
	 * 保存发放记录表
	 */
	public void addGrantRecord(ActivityGrantRecord activityGrantRecord);
	
	/**
	 * 根据条件，查询列表
	 * @param ActivityGrantRecord
	 * @return
	 */
	public List<ActivityGrantRecord> selectListByParams(Map<String, Object> params);
	
	/**
	 * 审核批量发放（更改记录状态，审核人，审核时间，添加用户优惠券信息表）
	 * @param ActivityGrantRecord
	 */
	public void updateGrantRecord(ActivityGrantRecord activityGrantRecord);
}
