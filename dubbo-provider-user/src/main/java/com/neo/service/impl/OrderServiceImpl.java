package com.neo.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neo.dao.OrderDao;
import com.neo.entity.TOrder;
import com.neo.service.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveInfo() {
		TOrder order = new TOrder();
		order.setOrderId("T"+System.currentTimeMillis());
		order.setAmount(1);
		order.setUserId(UUID.randomUUID().toString());
		order.setOrderDate("2015-03-04");
		order.setCreateDt(new Timestamp(System.currentTimeMillis()));
		
		long res = orderDao.insert(order);
		System.out.println(res);
		
		//order.setOrderId("D"+System.currentTimeMillis());
		order.setUserId(UUID.randomUUID().toString()+"");
		res = orderDao.insert(order);

	}

}
