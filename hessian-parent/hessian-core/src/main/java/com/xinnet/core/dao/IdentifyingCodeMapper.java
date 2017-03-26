package com.xinnet.core.dao;

import java.util.Map;

import com.xinnet.core.entity.IdentifyingCode;

public interface IdentifyingCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdentifyingCode record);

    int insertSelective(IdentifyingCode record);

    IdentifyingCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IdentifyingCode record);

    int updateByPrimaryKey(IdentifyingCode record);

	IdentifyingCode selectByParam(Map<Object, Object> param);
	
	int updateFalseByEmail(String email);
}