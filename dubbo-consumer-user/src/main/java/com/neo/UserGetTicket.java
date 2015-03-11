package com.neo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neo.service.ITicketService;

public class UserGetTicket {
	public static void main( String[] args )
    {
        try {
        	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
    		
    		context.start();

    		final ITicketService ticketService = (ITicketService) context.getBean("ticketService");

    		ExecutorService exec = Executors.newFixedThreadPool(50);
    		for (int i = 0; i < 10; i++) {
    			exec.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
		    				System.out.println(ticketService.getTicketNo("uid00001"));
		    				Thread.sleep(50);
						} catch (Exception e) {
							
							//e.printStackTrace();
						}
					}
				});
    		}
    		exec.shutdown();
//    		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//				
//    			System.out.println("========================ticketNO:" + ticketService.getTicketNo(0));
//    			try {
//    				System.out.println(ticketService.getTicketNo(0));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//    			
//    			Thread.sleep(10);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
