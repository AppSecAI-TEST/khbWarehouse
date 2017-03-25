package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.entity.ActivityInvForProInfo;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityInvForProInfoDao extends GenericDao<ActivityInvForProInfo> {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityInvForProInfo record);

    int insertSelective(ActivityInvForProInfo record);

    ActivityInvForProInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityInvForProInfo record);

    int updateByPrimaryKeyWithBLOBs(ActivityInvForProInfo record);

    int updateByPrimaryKey(ActivityInvForProInfo record);
}