package com.xinnet.multipledatasource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinnet.multipledatasource.dao1.DataSourceOneMapper;
import com.xinnet.multipledatasource.dao2.DataSourceTwoMapper;
import com.xinnet.multipledatasource.entity.DataSourceOne;
import com.xinnet.multipledatasource.entity.DataSourceTwo;
import com.xinnet.multipledatasource.entity.OneAndTwoVO;
import com.xinnet.multipledatasource.service.DataSourceOneService;
import com.xinnet.multipledatasource.service.MultipleDataService;

@Service
public class DataSourceOneServiceImpl implements DataSourceOneService {
	
	@Autowired
	private DataSourceOneMapper dataSourceOneMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DataSourceOne record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DataSourceOne record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataSourceOne selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DataSourceOne record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DataSourceOne record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataSourceOne> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
