package com.yeepay.g3.facade.activity.facade;

import com.yeepay.g3.facade.activity.dto.ActivitySrcFlowPlatDTO;

/**
 * @Description 选择流量通道对外接口
 * @author ping.zhu
 * @CreateTime 2016年7月20日 下午17:06:10
 * @version 1.0
 */
public interface ActivitySrcFlowPlatFacade {
	
	/**
	 * 
	 * 添加流量通道记录
	 * 
	 * @param activitySrcFlowPlatDTO
	 */
	public void addSrcFlowPlat(ActivitySrcFlowPlatDTO activitySrcFlowPlatDTO);
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
	public void updateSrcFlowPlat(ActivitySrcFlowPlatDTO activitySrcFlowPlatDTO);
	/**
	 * 
	 * 查询流量通道记录
	 * 
	 */
	public ActivitySrcFlowPlatDTO selectSrcFlowPlat(long id);
	
	/**
	 * 根据渠道号和手机号前三位去查询对应的发放流量通道
	 * @param srcNo
	 * @param mobileNo
	 * @return
	 */
	public ActivitySrcFlowPlatDTO getSrcPlowPlatBySrcNo(String srcNo,String mobileNo);
}
