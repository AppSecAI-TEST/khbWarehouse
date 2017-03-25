package com.yeepay.g3.core.activity.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityShareRecordsDao;
import com.yeepay.g3.core.activity.entity.ActivityShareRecords;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;
/**
 * @Title: 分享记录服务实现类
 * @Description: 实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-5-4 下午1:52:28
 * @version 2016-5-4
 */
@Repository
public class ActivityShareRecordsDaoImpl extends GenericDaoDefault<ActivityShareRecords> implements ActivityShareRecordsDao {
	
	@Override
	public Long insert(ActivityShareRecords record) {
		super.add(record);
		return record.getId();
	}

	@Override
	public ActivityShareRecords selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public List<ActivityShareRecords> selectByParams(Map<String, Object> params) {
		return this.query("selectByParams", params);
	}

}
