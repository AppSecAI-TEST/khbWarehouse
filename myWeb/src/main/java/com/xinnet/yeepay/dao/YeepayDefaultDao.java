package com.xinnet.yeepay.dao;

import com.xinnet.yeepay.GenericDao;
import com.xinnet.yeepay.YeepayDefault;

public interface YeepayDefaultDao extends GenericDao<YeepayDefault>  {

    int insertSelective(YeepayDefault record);

}