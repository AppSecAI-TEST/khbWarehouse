package com.yeepay.g3.core.activity.dao;

import java.util.Date;
import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityMemberOperecordDao extends GenericDao<ActivityMemberOperecord> {

	public int deleteByPrimaryKey(Long id);

	public ActivityMemberOperecord selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityMemberOperecord record);
	
	/**
	 * 查询记录表列表
	 * @author 陈大涛
	 * 2016-7-1下午3:20:27
	 */
	public List<ActivityMemberOperecord> queryActivityMemberOperecordList(String memberNo, Date startTime,Date endTime, String type);
}