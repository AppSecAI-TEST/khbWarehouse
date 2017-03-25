/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityGoodsDetailDTO;


/**
 * @Title: 商品详情信息对外服务接口
 * @Description: 兑换码信息对外服务接口
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-31 下午2:55:29
 * @version 2016-8-31
 */
public interface ActivityGoodsDetailFacade {
	
	/**
	 * 添加一条商品详情
	 * @param in - ActivityGoodsDetailDTO
	 * @return 
	 */
	public void addGoodsDetail(ActivityGoodsDetailDTO activityGoodsDetailDTO);
	
	/**
	 * 根据实体类参数查询信息
	 * @param activityCouponDto
	 * @return
	 */
	public List<ActivityGoodsDetailDTO> selectListByParams(ActivityGoodsDetailDTO activityGoodsDetailDTO);
	
	/**
	 * 根据id修改商品详情（兑换码）
	 * @param in - ActivityGoodsDetailDTO
	 * @return 
	 */
	public void updateActGoodsDetailById(ActivityGoodsDetailDTO activityGoodsDetailDto);
	
	/**
	 * 批量导入兑换码信息
	 * @author 陈大涛
	 * 2016-8-31下午5:39:32
	 */
	public void addGoodsDetailList(List<ActivityGoodsDetailDTO> activityGoodsDetailDTOList);
}
