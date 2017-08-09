package com.xinnet.multipledatasource.service;

import java.util.List;

import com.xinnet.multipledatasource.entity.DataSourceOne;


public interface DataSourceOneService {
    int deleteByPrimaryKey(Long id);

    int insert(DataSourceOne record);

    int insertSelective(DataSourceOne record);

    DataSourceOne selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSourceOne record);

    int updateByPrimaryKey(DataSourceOne record);
    
    List<DataSourceOne> getAll();
}
