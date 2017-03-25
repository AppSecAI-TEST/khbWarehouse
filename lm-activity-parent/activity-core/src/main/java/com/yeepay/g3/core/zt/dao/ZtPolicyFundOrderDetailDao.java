package com.yeepay.g3.core.zt.dao;

import com.yeepay.g3.core.zt.entity.ZtPolicyFundOrderDetail;

/**
 * 会员策略交易记录,每次组合交易的基金交易明细操作
 * @author hongbin.kang
 * @date 2016年10月21日下午4:30:15
 */
public interface ZtPolicyFundOrderDetailDao {


    int deleteByPrimaryKey(Long id);

    int insert(ZtPolicyFundOrderDetail record);

    int insertSelective(ZtPolicyFundOrderDetail record);


    ZtPolicyFundOrderDetail selectByPrimaryKey(Long id);



    int updateByPrimaryKeySelective(ZtPolicyFundOrderDetail record);

    int updateByPrimaryKey(ZtPolicyFundOrderDetail record);
}