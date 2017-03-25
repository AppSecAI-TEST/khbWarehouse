package com.yeepay.g3.core.zt.dao;

import com.yeepay.g3.core.zt.entity.ZtPolicyOrderDetail;

/**
 * 策略交易明细,每次申购、赎回、追加投资的交易信息操作
 * @author hongbin.kang
 * @date 2016年10月21日下午4:35:47
 */
public interface ZtPolicyOrderDetailDao {


    int deleteByPrimaryKey(Long id);

    int insert(ZtPolicyOrderDetail record);

    int insertSelective(ZtPolicyOrderDetail record);


    ZtPolicyOrderDetail selectByPrimaryKey(Long id);



    int updateByPrimaryKeySelective(ZtPolicyOrderDetail record);

    int updateByPrimaryKey(ZtPolicyOrderDetail record);
}