package com.yeepay.g3.core.activity.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeepay.g3.core.activity.entity.ActivityShareRecords;
import com.yeepay.g3.core.activity.service.ActivityShareRecordsService;
import com.yeepay.g3.core.activity.utils.EntityDTOConvert;
import com.yeepay.g3.facade.activity.dto.ActivityGrantRecordDTO;
import com.yeepay.g3.facade.activity.dto.ActivityShareRecordsDTO;
import com.yeepay.g3.facade.activity.dto.ShareRecordRespDTO;
import com.yeepay.g3.facade.activity.enums.CouponUseResultCodeEnum;
import com.yeepay.g3.facade.activity.enums.ShareBizTypeEnum;
import com.yeepay.g3.facade.activity.facade.ActivityShareRecordsFacade;
/**
 * @Title: 分享记录对外接口实现类
 * @Description: 对外接口实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-5-4 下午6:24:54
 * @version 2016-5-4
 */
@Service
public class ActivityShareRecordsFacadeImpl implements
		ActivityShareRecordsFacade {

	@Autowired
	private ActivityShareRecordsService activityShareRecordsServiceImpl;
	
	@Override
	public ShareRecordRespDTO addShareRecord(ActivityShareRecordsDTO activityShareRecordDto) {
		
		ShareRecordRespDTO shareRecordRespDTO = new ShareRecordRespDTO();
		Long id = null;
		try{
			ActivityShareRecords activityShareRecords = new ActivityShareRecords();
			activityShareRecords = EntityDTOConvert.toTarget(activityShareRecordDto, ActivityShareRecords.class);
			//添加分享记录，返回记录id
			id = activityShareRecordsServiceImpl.addShareRecord(activityShareRecords);
		}catch(Exception e){
			shareRecordRespDTO.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OTHER);
			return shareRecordRespDTO;
		}
		//根据id查询记录信息并返回
		ActivityShareRecords record = activityShareRecordsServiceImpl.selectShareRecordById(id);
		if(record != null){
			shareRecordRespDTO.setBizType(record.getBizType());
			shareRecordRespDTO.setCreateTime(record.getCreateTime());
			shareRecordRespDTO.setMemberNo(record.getMemberNo());
			shareRecordRespDTO.setMemberTel(record.getMemberTel());
			shareRecordRespDTO.setRecommendMemberNo(record.getRecommendMemberNo());
			shareRecordRespDTO.setRecommendMemberTel(record.getRecommendMemberTel());
			shareRecordRespDTO.setResultCode(CouponUseResultCodeEnum.OPERATE_SUCCESS);
			shareRecordRespDTO.setSrcNo(record.getSrcNo());
			shareRecordRespDTO.setSrcType(record.getSrcType());
		}else{
			shareRecordRespDTO.setResultCode(CouponUseResultCodeEnum.OPERATE_FAILED_OTHER);
		}
		
		return shareRecordRespDTO;
	}

	@Override
	public List<ActivityShareRecordsDTO> selectListByParams(ActivityShareRecordsDTO activityShareRecordDto) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberNo", activityShareRecordDto.getMemberNo());
		params.put("memberTel", activityShareRecordDto.getMemberTel());
		params.put("recommendMemberNo", activityShareRecordDto.getRecommendMemberNo());
		params.put("recommendMemberTel", activityShareRecordDto.getRecommendMemberTel());
		params.put("bizType", activityShareRecordDto.getBizType());
		params.put("srcNo", activityShareRecordDto.getSrcNo());
		params.put("srcType", activityShareRecordDto.getSrcType());
		params.put("createTime", activityShareRecordDto.getCreateTime());
		params.put("createTimeStart", activityShareRecordDto.getCreateTimeStart());
		params.put("createTimeEnd", activityShareRecordDto.getCreateTimeEnd());
		List<ActivityShareRecordsDTO> dtoList = new ArrayList<ActivityShareRecordsDTO>();
		List<ActivityShareRecords> list = activityShareRecordsServiceImpl.selectListByParams(params);
		if(list != null && list.size()>0){
			dtoList = EntityDTOConvert.toTragetList(list, ActivityShareRecordsDTO.class);
		}
		return dtoList;
	}

	@Override
	public ActivityShareRecordsDTO selectShareRecordById(Long id) {
		return null;
	}

}
