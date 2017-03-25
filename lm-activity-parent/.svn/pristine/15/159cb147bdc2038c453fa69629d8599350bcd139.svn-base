package com.yeepay.g3.core.activity.dao;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityShareRecords;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * @Title: 分享记录数据服务接口
 * @Description: 分享记录操作接口
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-5-4 上午11:45:36
 * @version 2016-5-4
 */
public interface ActivityShareRecordsDao extends GenericDao<ActivityShareRecords> {
	
	public Long insert(ActivityShareRecords record);

	public ActivityShareRecords selectByPrimaryKey(Long id);

	/**
	 * 根据参数查询朋友间关系列表
	 * @param params
	 * @return
	 */
	public List<ActivityShareRecords> selectByParams(Map<String, Object> params);
	
}