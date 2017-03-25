/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:13:40
 */
package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;

/**
 * @author hongbin.kang
 */
public interface ActivityUserPrizeService {
	
	/**
	 * 根据发奖记录id获取奖品的Entity
	 * @author hongbin.kang
	 * 
	 */
	public ActivityUserPrize selectUserPrizeById(Long id);
	
	/**
	 * 根据主键ID更新奖品信息（审核时更新商品状态、操作人信息）
	 * @author hongbin.kang
	 * @param ActivityPrize
	 */
	public void updateActivityUserPrizeById(ActivityUserPrize activityUserPrize);

	/**
	 * 根据会员编号查询用户的抽奖列表
	 * @param params
	 * @return
	 */
	public List<ActivityUserPrize> selectUserPrizeByMemberNo(Map<String, Object> params);

	/**
	 * 查询最新中奖的列表
	 * @param i
	 * @return
	 */
	public List<ActivityUserPrize> selectUserPrizeNewList(int i);
	
	/**
	 * 根据奖品id查询中奖列表
	 */
	public List<ActivityUserPrize> selectUserPrizeByPrizeId(Long id);

}
