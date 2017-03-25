package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityGrantRecord;
import com.yeepay.g3.core.activity.service.ActivityGrantRecordService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityGrantRecordDTO;
import com.yeepay.g3.facade.activity.enums.GrantStatusEnum;
import com.yeepay.g3.facade.activity.facade.ActivityGrantRecordFacade;
/**
 * @Description 人工发放记录表对外接口实现类
 * @author ying.liu
 * @CreateTime 2016-3-31
 * @version 1.0
 */
@Service
public class ActivityGrantRecordFacadeImpl implements ActivityGrantRecordFacade {

	@Autowired
	private ActivityGrantRecordService activityGrantRecordServiceImpl;
	
	@Override
	public ActivityGrantRecordDTO selectGrantRecordById(long id) {
		ActivityGrantRecordDTO activityGrantRecordDto = new ActivityGrantRecordDTO();
		ActivityGrantRecord activityGrantRecord =activityGrantRecordServiceImpl.selectGrantRecordById(id);
		activityGrantRecordDto = EntityDTOConvert.toTarget(activityGrantRecord, ActivityGrantRecordDTO.class);
		return activityGrantRecordDto;
	}
	
	@Override
	public void addGrantRecord(ActivityGrantRecordDTO activityGrantRecordDto) {
		ActivityGrantRecord activityGrantRecord = new ActivityGrantRecord();
		activityGrantRecord = EntityDTOConvert.toTarget(activityGrantRecordDto, ActivityGrantRecord.class);
		activityGrantRecord.setGrantStatus(GrantStatusEnum.CHECKING);
		activityGrantRecordServiceImpl.addGrantRecord(activityGrantRecord);
		// TODO Auto-generated method stub

	}

	@Override
	public List<ActivityGrantRecordDTO> selectListByParams(
			ActivityGrantRecordDTO activityGrantRecordDto) {
		List<ActivityGrantRecordDTO> list = new ArrayList<ActivityGrantRecordDTO>();
		if(activityGrantRecordDto != null){
			Map<String,Object> params = new HashMap<String,Object>();
			//批量发放名称
			if(activityGrantRecordDto.getBatchGrantName() != null){
				params.put("batchGrantName",activityGrantRecordDto.getBatchGrantName() );
			}
			//批次
			if(activityGrantRecordDto.getBatchId() != null){
				params.put("batchId", activityGrantRecordDto.getBatchId());
			}
			//审核时间
			if(activityGrantRecordDto.getCheckedTime() != null){
				params.put("checkedTime", activityGrantRecordDto.getCheckedTime());
			}
			//
			if(activityGrantRecordDto.getCheckedTimeStart() != null){
				params.put("checkedTimeStart", activityGrantRecordDto.getCheckedTimeStart());
			}
			//
			if(activityGrantRecordDto.getCheckedTimeEnd() != null){
				params.put("checkedTimeEnd", activityGrantRecordDto.getCheckedTimeEnd());
			}
			//审核人员
			if(activityGrantRecordDto.getCheckor() != null){
				params.put("checkor", activityGrantRecordDto.getCheckor());
			}
			//优惠券id
			if(activityGrantRecordDto.getCouponId() != null){
				params.put("couponId",activityGrantRecordDto.getCouponId());
			}
			//优惠券名称
			if(activityGrantRecordDto.getCouponName() != null){
				params.put("couponName", activityGrantRecordDto.getCouponName());
			}
			//创建时间
			if(activityGrantRecordDto.getCreateTime() != null){
				params.put("createTime", activityGrantRecordDto.getCreateTime());
			}
			//
			if(activityGrantRecordDto.getCreateTimeStart() != null){
				params.put("createTimeStart", activityGrantRecordDto.getCreateTimeStart());
			}
			//
			if(activityGrantRecordDto.getCreateTimeEnd() != null){
				params.put("createTimeEnd", activityGrantRecordDto.getCreateTimeEnd());
			}
			//创建者
			if(activityGrantRecordDto.getCreator() != null){
				params.put("creator", activityGrantRecordDto.getCreator());
			}
			//发放状态
			if(activityGrantRecordDto.getGrantStatus() != null){
				params.put("grantStatus", activityGrantRecordDto.getGrantStatus());
			}
			//ID
			if(activityGrantRecordDto.getId() != null){
				params.put("id", activityGrantRecordDto.getId());
			}
			//用户总数量
			if(activityGrantRecordDto.getMemberCount() != null){
				params.put("memberCount",activityGrantRecordDto.getMemberCount());
			}
			//用户列表
			if(activityGrantRecordDto.getMemberNoList() != null){
				params.put("memberNoList",activityGrantRecordDto.getMemberNoList());
			}
			//每个人发放数量
			if(activityGrantRecordDto.getPerGrantCount() != null){
				params.put("perGrantCount",activityGrantRecordDto.getPerGrantCount());
			}
			//版本号
			if(activityGrantRecordDto.getVersion() != null){
				params.put("version",activityGrantRecordDto.getVersion());
			}
			List<ActivityGrantRecord> resultList = activityGrantRecordServiceImpl.selectListByParams(params);
			if(resultList != null && resultList.size()>0){
				list = EntityDTOConvert.toTragetList(resultList, ActivityGrantRecordDTO.class);
			}
		}
		
		return list;
	}

	@Override
	public void checkGrantRecord(
			ActivityGrantRecordDTO activityGrantRecordDto) {
		ActivityGrantRecord activityGrantRecord = new ActivityGrantRecord();
		activityGrantRecord = EntityDTOConvert.toTarget(activityGrantRecordDto, ActivityGrantRecord.class);
		activityGrantRecordServiceImpl.updateGrantRecord(activityGrantRecord);
	}

	

}
