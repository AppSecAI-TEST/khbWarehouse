package com.yeepay.g3.core.zt.dao;
import com.yeepay.g3.core.zt.entity.ZtPolicyProduct;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ZtPolicyProductDao extends GenericDao<ZtPolicyProduct> {
    int deleteByPrimaryKey(Long id);

    int insert(ZtPolicyProduct record);

    int insertSelective(ZtPolicyProduct record);

    ZtPolicyProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZtPolicyProduct record);

    int updateByPrimaryKey(ZtPolicyProduct record);
}