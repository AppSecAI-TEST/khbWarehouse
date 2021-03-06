/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityGoodsDTO;

/**
 * @Description 商品信息对外服务接口
 * @author hongbin.kang
 * @CreateTime 2016年5月17日 下午2:18:56
 * @version 1.0
 */
public interface ActivityGoodsFacade {
	/**
	 * 根据主键id获取商品信息
	 */
	public ActivityGoodsDTO selectGoodsById(long id);
	/**
	 * 保存商品信息
	 */
	public void addActivityGoods(ActivityGoodsDTO goodsDto);
	
	/**
	 * 根据实体类参数查询信息
	 * @param ActivityGoodsDTO
	 * @return
	 */
	public List<ActivityGoodsDTO> selectListByParams(ActivityGoodsDTO goodsDto);
	 
	/**
	 * 查询有效商品（有效状态）
	 * @param ActivityGoodsDTO
	 * @return
	 */
	public List<ActivityGoodsDTO> selectEffGoodsList(ActivityGoodsDTO goodsDto);
	/**
	 * 更新实体类（审批状态、操作人信息）
	 * @param ActivityGoodsDTO
	 */
	public void updateActivityGoodsById(ActivityGoodsDTO goodsDto);
	

	/**
	 * 查询事件所属的商品列表
	 * @param eventCode
	 * @return
	 */
	public List<ActivityGoodsDTO> selecEventCouponList(String eventCode);
}
