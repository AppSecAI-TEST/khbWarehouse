package com.yeepay.g3.core.activity.dao;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityUserPrizeDao extends GenericDao<ActivityUserPrize> {

	public int deleteByPrimaryKey(Long id);

	public ActivityUserPrize selectByPrimaryKey(Long id);

	public int updateByPrimaryKey(ActivityUserPrize record);
	/**
	 * 根据奖品id查询发奖记录
	 * @param in -
	 * @return 读取到的固定长度数据
	 */
	public List<ActivityUserPrize> selectByPrizeId(Long id);
}