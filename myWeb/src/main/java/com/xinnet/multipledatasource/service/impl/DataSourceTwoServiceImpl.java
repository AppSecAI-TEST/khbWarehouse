package com.xinnet.multipledatasource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinnet.multipledatasource.dao2.DataSourceTwoMapper;
import com.xinnet.multipledatasource.entity.DataSourceTwo;
import com.xinnet.multipledatasource.service.DataSourceTwoService;

@Service
public class DataSourceTwoServiceImpl implements DataSourceTwoService {
	
	
	@Autowired
	private DataSourceTwoMapper dataSourceTwoMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DataSourceTwo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DataSourceTwo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataSourceTwo selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DataSourceTwo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DataSourceTwo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataSourceTwo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
