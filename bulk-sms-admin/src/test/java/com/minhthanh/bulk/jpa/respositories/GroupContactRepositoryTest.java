package com.minhthanh.bulk.jpa.respositories;

import static org.junit.Assert.*;

import java.util.Date;

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
import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;
import com.minhthanh.bulk.jpa.repositories.GroupContactRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class })
public class GroupContactRepositoryTest {
	
	@Autowired
	private GroupContactRepository groupContactRepository;
	private PartnerGroupContact groupContact;
	
	@Before
	public void before(){
		groupContact = new PartnerGroupContact("test group repository", "info",new Date());
		groupContact = groupContactRepository.save(groupContact);
	}
	
	@Test
	public void testAll(){
		boolean expected = true;
		boolean result = groupContactRepository.findAll().size() > 0;
		assertEquals(expected,result);
	}
	
	@Test
	public void testFindByName(){
		boolean expected = true;
		boolean result = groupContactRepository.findByName(groupContact.getName()).size() > 0;
		assertEquals(expected,result);
	}
	
	@Test
	public void testGetPartner(){
		boolean expected = true;
		boolean result = groupContactRepository.findOne(1).getPartner() != null;
		assertEquals(expected,result);
	}
	
	@Test
	public void testFindAllByPartnerId(){
		boolean expected = true;
		boolean result = groupContactRepository.findAllByPartnerId(1).size() > 0;
		assertEquals(expected,result);
	}
	
	@Test
	public void testFindOneByPartnerId(){
		boolean expected = true;
		boolean result = groupContactRepository.findOneByPartnerId(1,1) != null;
		assertEquals(expected,result);
	}
	
	@Test
	public void testFindGroupsByContactId(){
		boolean expected = true;
		boolean result = groupContactRepository.findGroupsByContactId(1).size() > 0;
		assertEquals(expected,result);
	}
	
	@Test
	public void testDeleteGroupOfContact(){
		boolean expected = true;
		boolean result = groupContactRepository.deleteGroupOfContact(2, 2) > 0;
		assertEquals(expected,result);
	}
	
	@After
	public void after(){
		if(groupContactRepository.exists(groupContact.getId()))
			groupContactRepository.delete(groupContact);
	}
}
