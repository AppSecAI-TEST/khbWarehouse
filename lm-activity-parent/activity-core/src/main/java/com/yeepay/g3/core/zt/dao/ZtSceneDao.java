package com.yeepay.g3.core.zt.dao;
import com.yeepay.g3.core.zt.entity.ZtScene;
import com.yeepay.g3.utils.persistence.GenericDao;

public interface ZtSceneDao extends GenericDao<ZtScene> {
    int deleteByPrimaryKey(Long id);

    int insert(ZtScene record);

    int insertSelective(ZtScene record);

    ZtScene selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZtScene record);

    int updateByPrimaryKeyWithBLOBs(ZtScene record);

    int updateByPrimaryKey(ZtScene record);
}