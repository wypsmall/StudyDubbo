package com.neo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.neo.service.IProcessData;

/**
 * Hello world!
 *
 */
public class DubboConsumer 
{
    public static void main( String[] args )
    {
        try {
        	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
    		
    		context.start();

    		IProcessData demoService = (IProcessData) context.getBean("demoService");

    		EchoService echoService = (EchoService)demoService;
    		
    		String status = (String) echoService.$echo("OK");
    		System.out.println(status);
    		for (int i = 0; i < Integer.MAX_VALUE; i++) {
				
    			System.out.println("========================");
    			System.out.println(demoService.hello("world"));
    			
    			Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
