package com.neo.impl;

import com.neo.BaseTestCase;
import com.neo.service.IOrderService;

public class TestOrderService extends BaseTestCase {
	private IOrderService orderService;
	@Override
	protected void setUp() throws Exception {
		orderService = (IOrderService)ctx.getBean("orderService");
		
	}
	
	public void testSaveInfo() {
		try {
			orderService.saveInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
