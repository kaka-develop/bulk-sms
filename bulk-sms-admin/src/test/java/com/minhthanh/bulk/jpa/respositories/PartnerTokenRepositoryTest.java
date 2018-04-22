package com.minhthanh.bulk.jpa.respositories;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.minhthanh.bulk.admin.config.ApplicationConfig;
import com.minhthanh.bulk.jpa.entities.PartnerToken;
import com.minhthanh.bulk.jpa.repositories.PartnerTokenRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class })
public class PartnerTokenRepositoryTest {
	
	@Autowired
	private PartnerTokenRepository tokenRepository;
	
	private PartnerToken token;
	
	@Before
	public void before() {
		token = new PartnerToken();
		token = tokenRepository.save(token);
	}
	
	@Test
	public void findAll() {
		boolean expected = true;
		boolean result = tokenRepository.findAll().size() > 0;
		assertEquals(expected,result);
	}
	
	@After
	public void after() {
		tokenRepository.delete(token);
	}
	
	
}
