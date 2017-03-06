package com.xinnet.dao;

import com.xinnet.entity.IdentifyingCode;

public interface IdentifyingCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdentifyingCode record);

    int insertSelective(IdentifyingCode record);

    IdentifyingCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdentifyingCode record);

    int updateByPrimaryKey(IdentifyingCode record);
}