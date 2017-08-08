package com.xinnet.multipledatasource.dao1;

import java.util.List;

import com.xinnet.multipledatasource.entity.DataSourceOne;
import com.xinnet.multipledatasource.entity.DataSourceTwo;

public interface DataSourceOneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataSourceOne record);

    int insertSelective(DataSourceOne record);

    DataSourceOne selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSourceOne record);

    int updateByPrimaryKey(DataSourceOne record);
    
    List<DataSourceOne> getAll();
}