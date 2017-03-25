package com.yeepay.g3.core.activity.dao;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityInvForProOrder;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ActivityInvForProOrderDao extends GenericDao<ActivityInvForProOrder> {

    int deleteByPrimaryKey(Long id);


    int insertSelective(ActivityInvForProOrder record);


    ActivityInvForProOrder selectByPrimaryKey(Long id);


    int updateByPrimaryKey(ActivityInvForProOrder record);
    
    List<ActivityInvForProOrder> selectByFlowParams(Map<String,Object> params);
}