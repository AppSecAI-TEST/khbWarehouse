package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityGrantRecord;
import com.yeepay.g3.utils.persistence.GenericDao;

/**
 * 
 * @Description 人工发放记录数据服务接口
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午5:02:32
 * @version 1.0
 */
public interface ActivityGrantRecordDao extends GenericDao<ActivityGrantRecord> {

	public int deleteByPrimaryKey(Long id);

	public int insert(ActivityGrantRecord record);

	public ActivityGrantRecord selectByPrimaryKey(Long id);

	public int updateByPrimaryKeyWithBLOBs(ActivityGrantRecord record);

	public int updateByPrimaryKey(ActivityGrantRecord record);
}