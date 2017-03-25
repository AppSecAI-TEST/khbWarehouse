package com.yeepay.g3.core.activity.service;

import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityGoods;

/**
 * @Description 商品信息业务逻辑处理接口
 * @author hongbin.kang
 * @CreateTime 2016年5月17日 下午2:13:50
 * @version 1.0
 */
public interface ActivityGoodsService {

	/**
	 * 新增商品信息
	 * @param ActivityGoods
	 * @return
	 */
	public ActivityGoods selectGoodsById(long id);
	/**
	 * 新增商品信息
	 * @param ActivityGoods
	 * @return
	 */
	public void addActivityGoods(ActivityGoods activityGoods);
	
	/**
	 * 根据条件查询商品列表
	 * 条件包含（商品名称、类型、状态、时间有效期等）
	 * @param params
	 * @return
	 */
	public List<ActivityGoods> selectListByParams(Map<String, Object> params);
	
	/**
	 * 查询有效商品列表
	 * 条件包含（商品状态为审核通过）
	 * @param params
	 * @return
	 */
	public List<ActivityGoods> selectEffGoodsList(Map<String, Object> params);
	
	/**
	 * 查询事件关联商品
	 * 条件包含（商品状态，隐含条件：截止日期大于当前日期）
	 * @param params
	 * @return
	 */
	public List<ActivityGoods> selectByEventCode(Map<String, Object> params);
	
	/**
	 * 根据主键ID更新商品信息（审核时更新商品状态、操作人信息）
	 * @param ActivityGoods
	 */
	public void updateActivityGoodsById(ActivityGoods activityGoods);
}
