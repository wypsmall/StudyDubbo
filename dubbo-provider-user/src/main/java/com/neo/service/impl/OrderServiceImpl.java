package com.neo.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neo.dao.OrderDao;
import com.neo.entity.TOrder;
import com.neo.service.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	public final static String ZK_HOST = "192.168.149.102:2181";
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
		
		zkThrowException();
		
		long res = orderDao.insert(order);
		System.out.println(res);
		
		order.setOrderId("D"+System.currentTimeMillis());
		order.setUserId(UUID.randomUUID().toString()+"");
		res = orderDao.insert(order);

	}
	
	private void zkThrowException() {
		try {
			System.out.println("======================================");
			String path = "/ticket/test";
			ZooKeeper zk = new ZooKeeper(ZK_HOST, 30000, new Watcher(){
				public void process(WatchedEvent event) {
					System.out.println("已经触发了" + event.getType() + "事件！");
				}
				
			});
			
			//zk.create(path, "00".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			Stat stat = zk.exists(path, false);
			zk.setData(path, "00".getBytes(), 1);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
