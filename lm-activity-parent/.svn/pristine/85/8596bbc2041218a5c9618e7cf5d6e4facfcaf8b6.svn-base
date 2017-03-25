/**
 * 
 */
package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.dao.ActivityGoodsDao;
import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.service.ActivityGoodsService;

/**
 * @Description 优惠券信息业务逻辑处理实现类
 * @author zhenping.zhou
 * @CreateTime 2016年3月25日 下午2:15:14
 * @version 1.0
 */
@Service
public class ActivityGoodsServiceImpl implements ActivityGoodsService {
	
    private static Log logger = LogFactory.getLog(ActivityGoodsServiceImpl.class);

	@Autowired
	private ActivityGoodsDao activityGoodsDaoImpl;

	@Override
	public ActivityGoods selectGoodsById(long id) {
		
		return (ActivityGoods)activityGoodsDaoImpl.queryOne("selectByPrimaryKey", id);
	}

	@Override
	public void addActivityGoods(ActivityGoods activityGoods) {
		activityGoodsDaoImpl.add(activityGoods);
		
	}

	@Override
	public List<ActivityGoods> selectListByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityGoods> selectEffGoodsList(Map<String, Object> params) {
		List<ActivityGoods> goodsList = activityGoodsDaoImpl.query("selectByStatus", params);
		return goodsList;
	}

	@Override
	public List<ActivityGoods> selectByEventCode(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateActivityGoodsById(ActivityGoods activityGoods) {
		activityGoodsDaoImpl.update(activityGoods);
		
	}
	
	

}
