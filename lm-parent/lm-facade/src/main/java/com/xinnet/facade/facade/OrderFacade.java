package com.xinnet.facade.facade;

import com.xinnet.facade.dto.OrderDTO;

public interface OrderFacade {
	
	public void insertSelective(OrderDTO record);

    OrderDTO selectByPrimaryKey(Integer id);
}
