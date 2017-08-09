
package com.xinnet.yeepay.dao.impl;

import org.springframework.stereotype.Repository;

import com.xinnet.yeepay.GenericDaoDefault;
import com.xinnet.yeepay.YeepayDefault;
import com.xinnet.yeepay.dao.YeepayDefaultDao;



@Repository
public class YeepayDefaultDaoImpl extends  GenericDaoDefault<YeepayDefault> implements YeepayDefaultDao {

	@Override
	public int insertSelective(YeepayDefault record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
