package com.yeepay.g3.core.zt.dao;


import com.yeepay.g3.core.zt.entity.ZtPolicy;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ZtPolicyDao extends GenericDao<ZtPolicy>{
    int deleteByPrimaryKey(Long id);

    int insert(ZtPolicy record);

    int insertSelective(ZtPolicy record);

    ZtPolicy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZtPolicy record);

    int updateByPrimaryKey(ZtPolicy record);
}