/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午4:26:57
 */
package com.xinnet.dao.impl;

import org.springframework.stereotype.Repository;

import com.xinnet.dao.OrderDao;
import com.xinnet.entity.Order;
import com.xinnet.yeepay.GenericDaoDefault;



@Repository
public class OrderDaoImpl extends  GenericDaoDefault<Order> implements OrderDao {
	
}
