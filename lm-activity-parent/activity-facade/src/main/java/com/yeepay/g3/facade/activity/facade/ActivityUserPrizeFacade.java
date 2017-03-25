package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityActionDTO;
import com.yeepay.g3.facade.activity.dto.ActivityPrizeDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUserPrizeDTO;

/**
 * @author hongbin.kang
 * 
 */

public interface ActivityUserPrizeFacade{
	
	/**
	 * 根据奖品id获取奖品的DTO
	 * @author hongbin.kang
	 * 
	 */
	public ActivityUserPrizeDTO selectUserPrizeById(Long id);

	/**
	 * 更新奖品的信息
	 * @author hongbin.kang
	 * @param userPrizeDto
	 */
	public void updateActivityUserPrizeById(ActivityUserPrizeDTO userPrizeDto);

	/**
	 * 根据会员编号查询用户的中奖列表
	 * @param memberNo
	 * @return
	 */
	public List<ActivityUserPrizeDTO> selectUserPrizeByMemberNo(String memberNo);

	/**
	 * 查询中奖的最新的5条记录
	 * @return
	 */
	public List<ActivityUserPrizeDTO> selectUserPrizeNewList(int i);

	
	
}