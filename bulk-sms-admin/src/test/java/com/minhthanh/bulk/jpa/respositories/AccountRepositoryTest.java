package com.minhthanh.bulk.jpa.respositories;

import static org.junit.Assert.*;

import com.minhthanh.bulk.jpa.entities.PartnerToken;
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
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;
import com.minhthanh.bulk.jpa.repositories.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class })
public class AccountRepositoryTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	private PartnerAdminAccount account;
	
	@Before
	public void before(){
		account = new PartnerAdminAccount("test mail", "test password", "test role");
		account = accountRepository.save(account);
	}
	
	@Test
	public void testAll() {
		boolean expected = true;
		boolean result = accountRepository.findAll().size() > 0;
		assertEquals(expected, result);
	}
	
	@Test
	public void testFindByEmail() {
		boolean expected = true;
		boolean result = accountRepository.findFirstByEmail(account.getEmail()) != null;
		assertEquals(expected, result);
	}
	
	@Test 
	public void testVerify(){
		boolean expected = true;
		boolean result = accountRepository.verify("11da75c4-4832-4ea9-8b6f-b57ad662f12f")  > 0;
		assertEquals(expected, result);
	}
	
	@Test 
	public void testCheckOldPassword(){
		boolean expected = true;
		boolean result = accountRepository.checkOldPassword(account.getId(),account.getPassword()) != null;
		assertEquals(expected, result);
	}
	
	@Test 
	public void testGetAccountByToken(){
		boolean expected = true;
		boolean result = accountRepository.FindAccountByToken("11da75c4-4832-4ea9-8b6f-b57ad662f12f") != null;
		assertEquals(expected, result);
	}
	
	@After
	public void after(){
		if(accountRepository.exists(account.getId()))
			accountRepository.delete(account);
	}
}
