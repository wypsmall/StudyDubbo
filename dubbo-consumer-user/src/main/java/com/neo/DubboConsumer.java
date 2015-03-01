package com.neo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    		System.out.println(demoService.hello("world"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
