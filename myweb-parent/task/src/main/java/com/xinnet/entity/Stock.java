package com.xinnet.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stock {
	
	private List<String> names;  
    
	private List<String> code;  
    
	private List<String> share;  
    
	private List<String> value;
	
	

	public Stock(String name, String code, String share,
			String value) {
		super();
		this.names = getList(name);
		this.code = getList(code);
		this.share = getList(share);
		this.value = getList(value);
	}

	
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getCode() {
		return code;
	}

	public void setCode(List<String> code) {
		this.code = code;
	}

	public List<String> getShare() {
		return share;
	}

	public void setShare(List<String> share) {
		this.share = share;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}


	@SuppressWarnings("unchecked")
	private <T> List<T> getList(String value) {
		List<T> list = new ArrayList<T>();
		if(value.indexOf(",")>-1) {
			list = (List<T>) Arrays.asList(value.split(","));
		} else {
			list.add((T) value);
		}
		return list;
	}

}
