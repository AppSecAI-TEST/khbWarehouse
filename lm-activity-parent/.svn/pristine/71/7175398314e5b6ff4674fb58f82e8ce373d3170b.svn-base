package com.yeepay.g3.core.activity.dao;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityPrizeDao extends GenericDao<ActivityPrize> {

	public ActivityPrize selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityPrize record);
	
	/** 剩余的奖品（未对应事件id的集合） **/
	public List<ActivityPrize> selectLeaveAll();
	/** 根据actionId查询对应奖品集合 **/
	public List<ActivityPrize> selectByActionId(Long id);
}