package com.xinnet.multipledatasource.dao2;

import java.util.List;

import com.xinnet.multipledatasource.entity.DataSourceTwo;

public interface DataSourceTwoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataSourceTwo record);

    int insertSelective(DataSourceTwo record);

    DataSourceTwo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataSourceTwo record);

    int updateByPrimaryKey(DataSourceTwo record);
    
    List<DataSourceTwo> getAll();
}