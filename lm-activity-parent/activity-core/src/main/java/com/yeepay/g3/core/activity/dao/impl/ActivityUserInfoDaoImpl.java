
package com.yeepay.g3.core.activity.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeepay.g3.core.activity.dao.ActivityPrizeDao;
import com.yeepay.g3.core.activity.dao.ActivityUserInfoDao;
import com.yeepay.g3.core.activity.dao.ActivityUserPrizeDao;
import com.yeepay.g3.core.activity.entity.ActivityPrize;
import com.yeepay.g3.core.activity.entity.ActivityUserInfo;
import com.yeepay.g3.core.activity.entity.ActivityUserPrize;
import com.yeepay.g3.utils.persistence.mybatis.GenericDaoDefault;

/**
 * @author hongbin.kang
 * 
 */
@Repository
public class ActivityUserInfoDaoImpl extends  GenericDaoDefault<ActivityUserInfo> implements ActivityUserInfoDao {
	
}
