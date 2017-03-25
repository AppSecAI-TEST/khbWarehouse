package com.yeepay.g3.core.activity.dao;

import java.util.List;

import com.yeepay.g3.core.activity.entity.ActivitySrcFlowPlat;
import com.yeepay.g3.utils.persistence.GenericDao;
/**
 * @Description 送流量活动选择流量通道数据服务接口
 * @author ping.zhu
 * @CreateTime 2016年7月20日 上午11:49:14
 * @version 1.0
 */
public interface ActivitySrcFlowPlatDao extends GenericDao<ActivitySrcFlowPlat>{
	
	public int deleteByPrimaryKey(Long id);

	public int insert(ActivitySrcFlowPlat record);

	public ActivitySrcFlowPlat selectByPrimaryKey(Long id);
	
	public List<ActivitySrcFlowPlat> selectByParams();

	public int updateByPrimaryKeySelective(ActivitySrcFlowPlat record);

	public int updateByPrimaryKey(ActivitySrcFlowPlat record);
	
	/**
	 * 根据渠道号和手机号前三位去查询对应的发放流量通道
	 * @return
	 */
	public ActivitySrcFlowPlat selectBySrcNo(String srcNo,String mobileSection);
}
