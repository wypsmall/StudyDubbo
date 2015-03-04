/**
 * <p>Copyright: Copyright(c) 2015</p>
 * <p>Company: gome.com.cn</p>
 * <p>2015年3月3日上午10:25:38</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.neo.dao;

import java.sql.Timestamp;
import java.util.UUID;

import com.neo.BaseTestCase;
import com.neo.entity.TOrder;

/** 
 * desc:
 * <p>创建人：wangyunpeng 创建日期：2015年3月3日上午10:25:38</p>
 * @version V1.0  
 */
public class TestOrderDao extends BaseTestCase {
	private OrderDao orderDao;
	@Override
	protected void setUp() throws Exception {
		orderDao = (OrderDao)ctx.getBean("orderDao");
	}
	
	public void testSave() {
		TOrder order = new TOrder();
		order.setOrderId("T"+System.currentTimeMillis());
		order.setAmount(1);
		order.setUserId(UUID.randomUUID().toString());
		order.setOrderDate("2015-03-04");
		order.setCreateDt(new Timestamp(System.currentTimeMillis()));
		
		long res = orderDao.insert(order);
		System.out.println(res);
		
		order.setOrderId("D"+System.currentTimeMillis());
		order.setUserId(UUID.randomUUID().toString()+"d");
		res = orderDao.insert(order);
		
		assertEquals(1, res);
	}
	
}
