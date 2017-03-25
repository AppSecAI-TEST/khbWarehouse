package com.yeepay.g3.core.activity.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivitySrcFlowPlatDao;
import com.yeepay.g3.core.activity.entity.ActivitySrcFlowPlat;
import com.yeepay.g3.core.activity.service.ActivitySrcFlowPlatService;
import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowPlatDTO;

/**
 * @Description 选择通道业务逻辑处理实现
 * @author ping.zhu
 * @CreateTime 2016年7月20日 下午15:54:10
 * @version 1.0
 */
@Service
public class ActivitySrcFlowPlatServiceImpl implements ActivitySrcFlowPlatService{

	@Autowired
	private ActivitySrcFlowPlatDao activitySrcFlowPlatDaoImpl;
	
	@Override
	public void addSrcFlowPlat(ActivitySrcFlowPlat activitySrcFlowPlat) {
		// TODO Auto-generated method stub
		activitySrcFlowPlatDaoImpl.add(activitySrcFlowPlat);
	}

	@Override
	public void deleteSrcFlowPlat(long id) {
		// TODO Auto-generated method stub
		activitySrcFlowPlatDaoImpl.delete("deleteByPrimaryKey", id);;
	}

	@Override
	public void updateSrcFlowPlat(ActivitySrcFlowPlat activitySrcFlowPlat) {
		// TODO Auto-generated method stub
		activitySrcFlowPlatDaoImpl.update(activitySrcFlowPlat);		
	}

	@Override
	public ActivitySrcFlowPlat selectSrcFlowPlat(long id) {
		// TODO Auto-generated method stub
		ActivitySrcFlowPlat activitySrcFlowPlat=(ActivitySrcFlowPlat)activitySrcFlowPlatDaoImpl.queryOne("selectByPrimaryKey", id);
		return activitySrcFlowPlat;
	}

	@Override
	public ActivitySrcFlowPlat getSrcPlowPlatBySrcNo(String srcNo,
			String mobileNo) {
		// TODO Auto-generated method stub
		String mobileSection=mobileNo.substring(0, 3);
		Map<String,Object> map =new HashMap<String,Object>(); 
		map.put("srcNo", srcNo);
		map.put("mobileSection", mobileSection);
		ActivitySrcFlowPlat activitySrcFlowPlat=(ActivitySrcFlowPlat) activitySrcFlowPlatDaoImpl.queryOne("selectBySrcNo",map);
		return activitySrcFlowPlat;
	}

}
