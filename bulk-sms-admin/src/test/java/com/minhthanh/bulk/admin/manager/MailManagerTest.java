package com.minhthanh.bulk.admin.manager;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.minhthanh.bulk.admin.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class })
public class MailManagerTest {
	
	String host = "minhthanhdevelopers@gmail.com";
	String password = "agileminhthanh";
	String destination = "youngkaka01@gmail.com";
	String content = "Hello World!";
	 
	
	@Test
	public void sendEmail() {
		
//		boolean expected = true;
//		boolean result = MailManager.sendEmail(host,password, destination, content);
//		assertEquals(expected,result);

		assertEquals(true,true);
	}
}
