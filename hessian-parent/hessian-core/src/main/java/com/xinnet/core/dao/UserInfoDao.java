package com.xinnet.core.dao;

import com.xinnet.core.entity.UserInfo;


/**
 * 
 * @author hongbin.kang
 * @date 2017年3月25日下午4:53:38
 */
public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo selectByParam(UserInfo record);
}