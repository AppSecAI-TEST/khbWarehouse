package com.yeepay.g3.core.activity.service;

import com.yeepay.g3.core.activity.entity.ActivitySrcFlowPlat;


/**
 * @Description 选择通道业务逻辑处理接口
 * @author ping.zhu
 * @CreateTime 2016年7月20日 下午15:54:10
 * @version 1.0
 */
public interface ActivitySrcFlowPlatService {

	/**
	 * 添加选择通道记录
	 * @param activitySrcFlowPlat
	 */
	public void addSrcFlowPlat(ActivitySrcFlowPlat activitySrcFlowPlat);
	/**
	 * 
	 * 删除流量通道记录
	 * 
	 */
	public void deleteSrcFlowPlat(long id);
	/**
	 * 
	 * 修改流量通道记录
	 * 
	 */
	public void updateSrcFlowPlat(ActivitySrcFlowPlat activitySrcFlowPlat);
	/**
	 * 
	 * 查询流量通道记录
	 * 
	 */
	public ActivitySrcFlowPlat selectSrcFlowPlat(long id);
	
	/**
	 * 根据渠道号和手机号前三位去查询对应的发放流量通道
	 * @param srcNo
	 * @param mobileNo
	 * @return
	 */
	public ActivitySrcFlowPlat getSrcPlowPlatBySrcNo(String srcNo,
			String mobileNo);
}