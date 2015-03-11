package com.neo.impl;

import com.neo.BaseTestCase;
import com.neo.service.ITicketService;

public class TestTicketService extends BaseTestCase {
	private ITicketService ticketService;
	@Override
	protected void setUp() throws Exception {
		ticketService = (ITicketService)ctx.getBean("ticketService");
		
	}
	
	public void testSaveInfo() {
		ticketService.getTicketNo("uid0001");
	}
}
