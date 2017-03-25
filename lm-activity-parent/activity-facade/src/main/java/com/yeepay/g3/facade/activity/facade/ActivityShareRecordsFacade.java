package com.yeepay.g3.facade.activity.facade;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.facade.activity.dto.ActivityShareRecordsDTO;
import com.yeepay.g3.facade.activity.dto.ShareRecordRespDTO;
import com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum;
/**
 * @Title: 分享记录业务逻辑处理接口
 * @Description: 分享记录
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-5-4 下午1:51:00
 * @version 2016-5-4
 */

public interface ActivityShareRecordsFacade {
	
	/**
	 * 添加分享记录
	 * @param in - activityShareRecord
	 * @return 
	 */
	public ShareRecordRespDTO addShareRecord(ActivityShareRecordsDTO activityShareRecordDto);
	
	/**
	 * 根据条件查询分享记录
	 * @param in - params
	 * @return 读取到的固定长度数据
	 */
	public List<ActivityShareRecordsDTO> selectListByParams(ActivityShareRecordsDTO activityShareRecordDto);
	
	/**
	 * 根据id查询分享记录
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	public ActivityShareRecordsDTO selectShareRecordById(Long id);
	
}