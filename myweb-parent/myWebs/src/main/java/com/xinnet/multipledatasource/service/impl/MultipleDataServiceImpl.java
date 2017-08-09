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
import com.xinnet.multipledatasource.service.MultipleDataService;

@Service
public class MultipleDataServiceImpl implements MultipleDataService {
	
	@Autowired
	private DataSourceOneMapper dataSourceOneMapper;
	
	@Autowired
	private DataSourceTwoMapper dataSourceTwoMapper;

	@Override
	public OneAndTwoVO queryData() {
		List<DataSourceOne> oneList = dataSourceOneMapper.getAll();
		List<DataSourceTwo> TwoList = dataSourceTwoMapper.getAll();
		
		return new OneAndTwoVO(oneList,TwoList);
	}

	@Override
	public void updateData(DataSourceOne one, DataSourceTwo two) {
		dataSourceOneMapper.updateByPrimaryKey(one);
		dataSourceTwoMapper.updateByPrimaryKey(two);
	}

	@Override
	@Transactional("tran_one")
//	@Transactional("tran_two")
	public void inster(DataSourceOne one, DataSourceTwo two) {
		dataSourceOneMapper.insertSelective(one);
		dataSourceTwoMapper.insertSelective(two);
//		updateData(new DataSourceOne(1L,"kang-1","china"),new DataSourceTwo(1L,"hong-1","AM"));
		int array[] = null; //声明数组
		array = new int[3]; //为数组开辟空间，大小为3
		for(int i=0;i<array.length;i++){
		System.out.println("array["+i+"]="+i);
		}
		//访问数组没有开辟的下标,这时会报异常
		System.out.println("array[3]="+array[3]);
	}
	
}
