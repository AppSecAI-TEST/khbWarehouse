package com.yeepay.g3.core.activity.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yeepay.g3.core.activity.entity.ActivityGoodsDetail;
import com.yeepay.g3.core.activity.entity.ActivityMemberOperecord;
import com.yeepay.g3.core.activity.entity.ActivityUserMessage;

/**
 * @Title: 商品详情业务处理接口
 * @Description: 兑换码业务逻辑处理
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-31 下午2:16:04
 * @version 2016-8-31
 */
public interface ActivityGoodsDetailService {

	/**
	 * 添加一条商品详情
	 * @param in -
	 * @return 
	 */
	public void addActivityGoodsDetail(ActivityGoodsDetail record);
	/**
	 * 根据id删除一条记录
	 * @param in - id
	 * @return 
	 */
    public int deleteByPrimaryKey(Long id);

    /**
     * 插入一条商品详情（兑换码）
     * @param in - ActivityGoodsDetail
     * @return 
     */
    public int insert(ActivityGoodsDetail record);

    /**
     * 根据id查询商品详情信息（兑换码信息）
     * @param in - id
     * @return 
     */
    public ActivityGoodsDetail selectByPrimaryKey(Long id);

    /**
     * 更新商品详情信息
     * @param in - ActivityGoodsDetail
     * @return 
     */
    public int updateByPrimaryKey(ActivityGoodsDetail record);
    
    /**
     * 根据参数更新商品详情信息
     * @param in - ActivityGoodsDetail
     * @return 
     */
    public void updateByParams(ActivityGoodsDetail record);
    
    /**
     * 根据参数列表查询商品详情信息（兑换码）
     * @param in - params
     * @return 
     */
    public List<ActivityGoodsDetail> selectListByParams(Map<String, Object> params);
    
    /**
	 * 批量导入兑换码信息
	 * @author 陈大涛
	 * 2016-8-31下午5:39:32
	 */
	public void addGoodsDetailList(List<ActivityGoodsDetail> activityGoodsDetailList);
    
    /**
     * 会员基础操作送兑换码
     * @param in - event
     * @param in - memberOperecord
     * @return 
     */
    public void addUserGoodsDetail(ActivityMemberOperecord memberOperecord);
    
    /**
     * 赠送兑换码消息公告初始化参数信息
     * @param in - memberTel
     * @param in - occurTime
     * @param in - templateName
     * @param in - memberNo
     * @param in - prizeName
     * @param in - goodsName
     * @return 读取到的固定长度数据
     */
    public ActivityUserMessage initUserMessage(String memberTel,Date occurTime,
			String templateName,String memberNo,
			String prizeName,String goodsName,String redeemCode);
}