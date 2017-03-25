package com.yeepay.g3.core.activity.dao.impl;


import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityGoodsDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityInvForProRuleXTDao;
import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.core.activity.entity.ActivityInvForProRuleXT;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @Description 商品信息数据服务实现类
 * @author hongbin.knag
 * @CreateTime 2016年5月17日 下午7:05:44
 * @version 1.0
 */
@Repository
public class ActivityInvForProRuleDaoImpl extends GenericDaoDefault<ActivityInvForProRuleXT> implements
		ActivityInvForProRuleXTDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ActivityInvForProRuleXT record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ActivityInvForProRuleXT record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActivityInvForProRuleXT selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityInvForProRuleXT record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ActivityInvForProRuleXT record) {
		// TODO Auto-generated method stub
		return 0;
	}}
