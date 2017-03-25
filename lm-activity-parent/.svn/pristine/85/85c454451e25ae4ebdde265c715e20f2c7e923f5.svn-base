/**
 * 
 */
package com.yeepay.g3.facade.activity.facade;

import java.util.List;

import com.yeepay.g3.facade.activity.dto.ActivityUsercouponDTO;
import com.yeepay.g3.facade.activity.dto.ActivityUsercouponRecordDTO;
import com.yeepay.g3.facade.activity.dto.CouponUseRespDTO;
import com.yeepay.g3.facade.activity.enums.BizTypeEnum;

/**
 * @Description 优惠券交易对外接口
 * @author zhenping.zhou
 * @CreateTime 2016年4月3日 下午2:02:46
 * @version 1.0
 */
public interface ActivityCouponTradeFacade {

	/**
	 * 查询用户可用优惠券列表
	 * @param memberNo 会员编号
	 * @param channelCode 频道
	 * @param bigColumnCode 大栏目
	 * @param smallColumnCode 小栏目
	 * @return
	 */
	public List<ActivityUsercouponDTO> selectTradeUsercouponList(String memberNo, String channelCode, 
			String bigColumnCode, String smallColumnCode);
	
	/**
	 * 冻结优惠券
	 * @param usercouponId 用户优惠券ID
	 * @param memberNo 会员编号
	 * @param tradeId 交易订单ID
	 * @param bizType 业务类型
	 * @return
	 */
	public CouponUseRespDTO frozenUserCoupon(Long usercouponId, String memberNo, Long tradeId, BizTypeEnum bizType);
	
	/**
	 * 解冻优惠券
	 * @param usercouponId 用户优惠券ID
	 * @param memberNo 会员编号
	 * @param tradeId 交易订单ID
	 * @param bizType 业务类型
	 * @return
	 */
	public CouponUseRespDTO unFrozenUserCoupon(Long usercouponId, String memberNo, Long tradeId, BizTypeEnum bizType);
	
	/**
	 * 消费优惠券
	 * @param usercouponId 用户优惠券ID
	 * @param memberNo 会员编号
	 * @param tradeId 交易订单ID
	 * @param bizType 业务类型
	 * @return
	 */
	public CouponUseRespDTO  consumeUserCoupon(Long usercouponId, String memberNo, Long tradeId, BizTypeEnum bizType);
	
	/**
	 * 未使用优惠券列表
	 * @param memberNo
	 * @return
	 */
	public List<ActivityUsercouponDTO> selectUserCouponUnuseList(String memberNo);
	
	/**
	 * 已使用优惠券列表
	 * @param memberNo
	 * @param bizType
	 * @return
	 */
	public List<ActivityUsercouponRecordDTO> selectUserCouponUsedList(String memberNo, BizTypeEnum bizType);
	
	/**
	 * 已过期优惠券列表
	 * @param memberNo
	 * @return
	 */
	public List<ActivityUsercouponDTO> selectUserCouponExpiredList(String memberNo);
	
	/**
	 * 用户根据事件领取优惠券
	 * @param eventCode
	 * @param memberNo
	 * @return
	 */
	public List<ActivityUsercouponDTO> receiveUserCoupon(String eventCode, String memberNo);
	
	/**
	 * 用户根据事件查询是否已经领取优惠券
	 * @author 陈大涛
	 * 2016-9-13上午10:57:07
	 */
	public List<ActivityUsercouponDTO> queryTradeUserCouponList(String eventCode, String memberNo);
	
}
