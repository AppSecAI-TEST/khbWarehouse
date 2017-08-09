package com.xinnet.multipledatasource.entity;

import java.util.List;

public class OneAndTwoVO {

	private List<DataSourceOne> oneList;
	
	private List<DataSourceTwo> TwoList;
	
	

	public OneAndTwoVO(List<DataSourceOne> oneList, List<DataSourceTwo> twoList) {
		this.oneList = oneList;
		this.TwoList = twoList;
	}

	public List<DataSourceOne> getOneList() {
		return oneList;
	}

	public void setOneList(List<DataSourceOne> oneList) {
		this.oneList = oneList;
	}

	public List<DataSourceTwo> getTwoList() {
		return TwoList;
	}

	public void setTwoList(List<DataSourceTwo> twoList) {
		TwoList = twoList;
	}

	@Override
	public String toString() {
		return "OneAndTwoVO [oneList=" + oneList + ", TwoList=" + TwoList + "]";
	}
	
	
}
