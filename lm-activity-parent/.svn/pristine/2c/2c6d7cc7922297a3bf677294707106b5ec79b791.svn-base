/**
 * 
 */
package com.yeepay.g3.core.activity.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @Description 会员操作记录数据服务实现类
 * @author zhenping.zhou
 * @CreateTime 2016年5月16日 下午6:14:37
 * @version 1.0
 */
@Repository
public class ActivityMemberOperecordDaoImpl extends GenericDaoDefault<ActivityMemberOperecord>
		implements ActivityMemberOperecordDao {

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public ActivityMemberOperecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao#updateByPrimaryKey(com.yeepay.g3.core.activity.entity.ActivityMemberOperecord)
	 */
	@Override
	public int updateByPrimaryKey(ActivityMemberOperecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.yeepay.g3.core.activity.dao.ActivityMemberOperecordDao#queryActivityMemberOperecordList(java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public List<ActivityMemberOperecord> queryActivityMemberOperecordList(
			String memberNo, Date startTime,Date endTime, String type) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberNo", memberNo);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("type", type);
		List<ActivityMemberOperecord> resultList = this.query("queryActivityMemberOperecordList", paramMap);
		return resultList;
	}

}
