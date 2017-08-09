package com.xinnet.service;

import java.util.List;

import com.xinnet.yeepay.YeepayDefault;

public interface YeepayDefaultService {

    int insertSelective(YeepayDefault record);
    
    void savetestshiwu();
    
    List<YeepayDefault> getAll();
   
    void update(YeepayDefault y);

	YeepayDefault selectMybatisCache(int id);
    

}