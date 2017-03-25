package com.yeepay.g3.core.zt.dao;

import com.yeepay.g3.core.zt.entity.ZtPolicyInvestPlanHis;

/**
 * 策略投资计划变更历史操作
 * @author hongbin.kang
 * @date 2016年10月21日下午4:34:05
 */
public interface ZtPolicyInvestPlanHisDao {


    int deleteByPrimaryKey(Long id);

    int insert(ZtPolicyInvestPlanHis record);

    int insertSelective(ZtPolicyInvestPlanHis record);


    ZtPolicyInvestPlanHis selectByPrimaryKey(Long id);



    int updateByPrimaryKeySelective(ZtPolicyInvestPlanHis record);

    int updateByPrimaryKey(ZtPolicyInvestPlanHis record);
}