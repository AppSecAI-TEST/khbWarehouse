package com.xinnet.multipledatasource.service;

import java.util.List;

import com.xinnet.multipledatasource.entity.DataSourceTwo;


public interface DataSourceTwoService {
    int deleteByPrimaryKey(Long id);

    int insert(DataSourceTwo record);

    int insertSelective(DataSourceTwo record);

    DataSourceTwo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSourceTwo record);

    int updateByPrimaryKey(DataSourceTwo record);
    
    List<DataSourceTwo> getAll();	
}
