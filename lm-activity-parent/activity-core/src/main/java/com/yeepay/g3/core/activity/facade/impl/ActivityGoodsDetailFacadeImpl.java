package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityGoodsDetail;
import com.yeepay.g3.core.activity.service.ActivityGoodsDetailService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityGoodsDetailDTO;
import com.yeepay.g3.facade.activity.facade.ActivityGoodsDetailFacade;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
/**
 * @Title: 商品详情信息对外服务接口实现类
 * @Description: 兑换码对外服务接口实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-8-31 下午4:30:05
 * @version 2016-8-31
 */
@Service
public class ActivityGoodsDetailFacadeImpl implements ActivityGoodsDetailFacade {
	
	@Autowired
	private ActivityGoodsDetailService activityGoodsDetailServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityGoodsDetailFacadeImpl.class);

	@Override
	public void addGoodsDetail(ActivityGoodsDetailDTO activityGoodsDetailDto) {
		ActivityGoodsDetail activityGoodsDetail = new ActivityGoodsDetail();
		activityGoodsDetail = EntityDTOConvert.toTarget(activityGoodsDetailDto, ActivityGoodsDetail.class);
		activityGoodsDetailServiceImpl.addActivityGoodsDetail(activityGoodsDetail);
	}
	
	@Override
	public List<ActivityGoodsDetailDTO> selectListByParams(
			ActivityGoodsDetailDTO activityGoodsDetailDTO) {
		logger.info("[selectListByParams] activityGoodsDetailDTO={}",activityGoodsDetailDTO);
		Map<String,Object> params = new LinkedHashMap<String,Object>();
		//兑换码
		if(StringUtils.isNotEmpty(activityGoodsDetailDTO.getRedeemCode())){
			params.put("redeemCode", activityGoodsDetailDTO.getRedeemCode());
		}
		//商品id
		if(null != activityGoodsDetailDTO.getGoodId()){
			params.put("goodId", activityGoodsDetailDTO.getGoodId());
		}
		//使用状态
		if(null != activityGoodsDetailDTO.getUsedStatus()){
			params.put("usedStatus", activityGoodsDetailDTO.getUsedStatus());
		}
		//创建时间
		if(null != activityGoodsDetailDTO.getCreateTime()){
			params.put("createTime", activityGoodsDetailDTO.getCreateTime());
		}
		//开始创建时间
		if(null != activityGoodsDetailDTO.getCreateTimeStart()){
			params.put("createTimeStart", activityGoodsDetailDTO.getCreateTimeStart());
		}
		//截止创建时间
		if(null != activityGoodsDetailDTO.getCreateTimeEnd()){
			params.put("createTimeEnd", activityGoodsDetailDTO.getCreateTimeEnd());
		}
		//使用时间
		if(null != activityGoodsDetailDTO.getUsedTime()){
			params.put("usedTime", activityGoodsDetailDTO.getUsedTime());
		}
		//开始使用时间
		if(null != activityGoodsDetailDTO.getUsedTimeStart()){
			params.put("usedTimeStart", activityGoodsDetailDTO.getUsedTimeStart());
		}
		//截止使用时间
		if(null != activityGoodsDetailDTO.getUsedTimeEnd()){
			params.put("usedTimeEnd", activityGoodsDetailDTO.getUsedTimeEnd());
		}
		List<ActivityGoodsDetail> resultList = activityGoodsDetailServiceImpl.selectListByParams(params);
		List<ActivityGoodsDetailDTO> list = new ArrayList<ActivityGoodsDetailDTO>();
		if(resultList != null && resultList.size() != 0){
			list = EntityDTOConvert.toTragetList(resultList, ActivityGoodsDetailDTO.class);
		}
		return list;
	}

	@Override
	public void updateActGoodsDetailById(
			ActivityGoodsDetailDTO activityGoodsDetailDto) {
		if(activityGoodsDetailDto != null && activityGoodsDetailDto.getId() != null){
			ActivityGoodsDetail activityGoodsDetail = new ActivityGoodsDetail();
			activityGoodsDetail = EntityDTOConvert.toTarget(activityGoodsDetailDto, ActivityGoodsDetail.class);
			activityGoodsDetailServiceImpl.updateByParams(activityGoodsDetail);
		}
	}

	@Override
	public void addGoodsDetailList(
			List<ActivityGoodsDetailDTO> activityGoodsDetailDTOList) {
		if(activityGoodsDetailDTOList != null ){
			List<ActivityGoodsDetail> activityGoodsDetailList = new ArrayList<ActivityGoodsDetail>();
			activityGoodsDetailList = EntityDTOConvert.toTragetList(activityGoodsDetailDTOList, ActivityGoodsDetail.class);
		    activityGoodsDetailServiceImpl.addGoodsDetailList(activityGoodsDetailList);
		}
	}

}
