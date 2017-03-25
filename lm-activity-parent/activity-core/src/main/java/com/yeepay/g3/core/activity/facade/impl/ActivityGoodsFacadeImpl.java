package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityGoods;
import com.yeepay.g3.core.activity.service.ActivityGoodsService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityGoodsDTO;
import com.yeepay.g3.facade.activity.enums.GoodsStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityGoodsFacade;
@Service
public class ActivityGoodsFacadeImpl implements ActivityGoodsFacade {
	
	@Autowired
	private ActivityGoodsService activityGoodsServiceImpl;

	@Override
	public ActivityGoodsDTO selectGoodsById(long id) {
		ActivityGoods activityGoods = new ActivityGoods();
		activityGoods = activityGoodsServiceImpl.selectGoodsById(id);
		ActivityGoodsDTO goodsDto = new ActivityGoodsDTO();
		goodsDto = EntityDTOConvert.toTarget(activityGoods, ActivityGoodsDTO.class);
		return goodsDto;
	}

	@Override
	public void addActivityGoods(ActivityGoodsDTO goodsDto) {
		ActivityGoods activityGoods = new ActivityGoods();
		activityGoods = EntityDTOConvert.toTarget(goodsDto, ActivityGoods.class);
		activityGoods.setGoodsStatus(GoodsStatusEnum.CHECKING);//商品状体初始化为待审核
		activityGoodsServiceImpl.addActivityGoods(activityGoods);

	}

	@Override
	public List<ActivityGoodsDTO> selectListByParams(ActivityGoodsDTO goodsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityGoodsDTO> selectEffGoodsList(ActivityGoodsDTO goodsDto) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(null == goodsDto) {
			return null;
		}
		params.put("goodsStatus", goodsDto.getGoodsStatus());
		List<ActivityGoods> goodsList = activityGoodsServiceImpl.selectEffGoodsList(params);
		List<ActivityGoodsDTO> goodsDtoList = new ArrayList<ActivityGoodsDTO>();
		goodsDtoList = EntityDTOConvert.toTragetList(goodsList, ActivityGoodsDTO.class);
		return goodsDtoList;
	}

	@Override
	public void updateActivityGoodsById(ActivityGoodsDTO goodsDto) {
		if(null != goodsDto && null != goodsDto.getId()) {
			ActivityGoods activityGoods = new ActivityGoods();
			activityGoods = EntityDTOConvert.toTarget(goodsDto, ActivityGoods.class);
			activityGoodsServiceImpl.updateActivityGoodsById(activityGoods);
		}

	}

	@Override
	public List<ActivityGoodsDTO> selecEventCouponList(String eventCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
