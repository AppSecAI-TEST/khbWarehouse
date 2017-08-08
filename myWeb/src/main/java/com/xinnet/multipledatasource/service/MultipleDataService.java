package com.xinnet.multipledatasource.service;

import com.xinnet.multipledatasource.entity.DataSourceOne;
import com.xinnet.multipledatasource.entity.DataSourceTwo;
import com.xinnet.multipledatasource.entity.OneAndTwoVO;


public interface MultipleDataService {
	
	public OneAndTwoVO queryData();

	public void updateData(DataSourceOne one,DataSourceTwo two);
	
	public void inster(DataSourceOne one,DataSourceTwo two);
}
