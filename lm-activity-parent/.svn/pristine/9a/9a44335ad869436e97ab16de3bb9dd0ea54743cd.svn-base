package com.yeepay.g3.core.activity.facade.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.service.ActivityUserPacketService;
import com.yeepay.g3.core.activity.service.ActivityUsercouponService;
import com.yeepay.g3.facade.activity.dto.ImportRedPacketDTO;
import com.yeepay.g3.facade.activity.facade.ActivityUserPacketFacade;

/**
 * 
 * @Description 
 * @author zhenping.zhou
 * @CreateTime 2016年8月3日 下午1:32:59
 * @version 1.0
 */
@Service
public class ActivityUserPacketFacadeImpl implements ActivityUserPacketFacade {
	
	@Autowired
	private ActivityUserPacketService activityUserPacketServiceImpl;
	
	@Autowired
	private ActivityUsercouponService activityUsercouponServiceImpl;
	
	private DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public void batchAddUserPacket(List<ImportRedPacketDTO> redPacketDtoList) {
		
		activityUserPacketServiceImpl.saveBatchUserPacket(redPacketDtoList);
	}

	@Override
	public void userPacketCheck(Map<String, Object> params) {
		activityUsercouponServiceImpl.updateCouponStatusByBatchId(params);
	}
	
}
