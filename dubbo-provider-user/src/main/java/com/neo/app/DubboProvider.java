package com.neo.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboProvider {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { 
				"spring-context.xml"
				});
			context.start();
			System.out.println("按任意鍵退出");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
