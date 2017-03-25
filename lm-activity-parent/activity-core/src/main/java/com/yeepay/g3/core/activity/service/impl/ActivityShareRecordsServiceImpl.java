package com.yeepay.g3.core.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanmao.consultant.facade.dto.TblLmUser;
import com.lanmao.consultant.facade.enumtype.Enums.UserType;
import com.lanmao.consultant.facade.service.UserFacade;
import com.yeepay.g3.core.activity.dao.ActivityShareRecordsDao;
import com.yeepay.g3.core.activity.entity.ActivityShareRecords;
import com.yeepay.g3.core.activity.service.ActivityShareRecordsService;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
/**
 * @Title: 分享记录业务逻辑实现类
 * @Description: 业务逻辑实现类
 * @Copyright: 懒猫金服
 * @author ying.liu
 * @createTime 2016-5-4 下午1:55:06
 * @version 2016-5-4
 */
@Service
public class ActivityShareRecordsServiceImpl implements
		ActivityShareRecordsService {

	@Autowired
	private ActivityShareRecordsDao activityShareRecordsDaoImpl;

	@Override
	public Long addShareRecord(ActivityShareRecords activityShareRecords) {
		
		//新增推荐人关系记录
		return activityShareRecordsDaoImpl.insert(activityShareRecords);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityShareRecords> selectListByParams(
			Map<String, Object> params) {
		return activityShareRecordsDaoImpl.query("selectByParams", params);
	}

	@Override
	public ActivityShareRecords selectShareRecordById(Long id) {
		return (ActivityShareRecords) activityShareRecordsDaoImpl.queryOne("selectByPrimaryKey", id);
	}

}
