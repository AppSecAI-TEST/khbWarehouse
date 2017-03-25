package com.yeepay.g3.core.activity.dao;

import com.yeepay.g3.core.activity.entity.ActivityAction;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityActionDao extends GenericDao<ActivityAction> {

    public int deleteByPrimaryKey(Long id);

    public ActivityAction selectByPrimaryKey(Long id);

    public int updateByPrimaryKey(ActivityAction record);
    
}