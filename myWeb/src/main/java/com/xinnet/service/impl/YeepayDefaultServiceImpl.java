package com.xinnet.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xinnet.service.YeepayDefaultService;
import com.xinnet.yeepay.YeepayDefault;
import com.xinnet.yeepay.dao.YeepayDefaultDao;

@Service
public class YeepayDefaultServiceImpl implements YeepayDefaultService {
	@Resource
	private YeepayDefaultDao yeepayDefaultDao;
	@Override
	public int insertSelective(YeepayDefault record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void savetestshiwu() {
		YeepayDefault yeepay = new YeepayDefault();
		yeepay.setAge(12);
		yeepay.setCity("china");
		yeepay.setName("kk");
		yeepayDefaultDao.add("insertSelective", yeepay);
		System.out.println(yeepayDefaultDao.query("selectByPrimaryKey", 1));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "kk");
		System.out.println(yeepayDefaultDao.query("selectByParam", map));
		yeepay.setId(3L);
		yeepay.setName("BB");
		yeepayDefaultDao.update(yeepay);
		
		int array[] = null; //声明数组
		array = new int[3]; //为数组开辟空间，大小为3
		for(int i=0;i<array.length;i++){
		System.out.println("array["+i+"]="+i);
		}
		//访问数组没有开辟的下标,这时会报异常
		System.out.println("array[3]="+array[3]);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YeepayDefault> getAll() {
		return yeepayDefaultDao.getAll();
	}

	@Override
	public void update(YeepayDefault y) {
		yeepayDefaultDao.update(y);
	}
	
	@Override
	public YeepayDefault selectMybatisCache(int id) {
		//同一事物之中共享sqlsession，可以使用mybatis一级缓存，但是在两次相同查询之间任何更新或者删除操作都会清除sqlsession的一级缓存
		//使用一级缓存，执行相同语句只会调用一次sql
		YeepayDefault firstSelect = (YeepayDefault)yeepayDefaultDao.queryOne("selectByPrimaryKey", id);
		YeepayDefault secondSelect = (YeepayDefault)yeepayDefaultDao.queryOne("selectByPrimaryKey", id);
		YeepayDefault y = new YeepayDefault();
		y.setId(5L);
		y.setAge(90);
		yeepayDefaultDao.update(y);
		if(firstSelect == secondSelect) {
			System.out.println("YeepayDefault是统一sqlsession");
		} else {
			System.out.println("YeepayDefault是不同sqlsession");
		}
		return (YeepayDefault)yeepayDefaultDao.queryOne("selectByPrimaryKey", id);
	}
	
}
