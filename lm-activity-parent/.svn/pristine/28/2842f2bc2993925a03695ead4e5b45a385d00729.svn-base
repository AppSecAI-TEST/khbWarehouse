/**
 * 
 */
package com.yeepay.g3.core.activity.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityRuleDao;
import com.yeepay.g3.core.activity.entity.ActivityRule;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @Description 活动规则数据服务实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月31日 下午11:31:21
 * @version 1.0
 */
@Repository
public class ActivityRuleDaoImpl extends GenericDaoDefault<ActivityRule> implements
		ActivityRuleDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityRule selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(ActivityRule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ActivityRule> selectByParams(Map<String, Object> params) {
		return this.query("selectByParams", params);
	}

}
