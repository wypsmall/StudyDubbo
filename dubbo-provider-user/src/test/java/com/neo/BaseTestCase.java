package com.neo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class BaseTestCase extends TestCase {
	protected static ApplicationContext ctx = null;
	
	static {
		ctx = new ClassPathXmlApplicationContext(new String[]{
				"spring-context.xml"
		});
	}
}
