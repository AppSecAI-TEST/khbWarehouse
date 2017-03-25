package com.yeepay.g3.core.zt.dao;
import com.yeepay.g3.core.zt.entity.ZtYieldRate;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ZtYieldRateDao extends GenericDao<ZtYieldRate> {
    int deleteByPrimaryKey(Long id);

    int insert(ZtYieldRate record);

    int insertSelective(ZtYieldRate record);

    ZtYieldRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZtYieldRate record);

    int updateByPrimaryKey(ZtYieldRate record);
}