package com.xinnet.dao;

import java.util.List;

import com.xinnet.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByParam(User record);

	void batchInsert(List<User> uList);
}