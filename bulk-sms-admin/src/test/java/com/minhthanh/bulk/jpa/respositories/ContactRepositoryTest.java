package com.minhthanh.bulk.jpa.respositories;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.minhthanh.bulk.admin.config.ApplicationConfig;
import com.minhthanh.bulk.jpa.entities.PartnerContact;
import com.minhthanh.bulk.jpa.repositories.ContactRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationConfig.class })
public class ContactRepositoryTest {
	@Autowired
	private ContactRepository contactRepository;

	private PartnerContact contact;

	@Before
	public void before() {
		contact = new PartnerContact("test repository", "0123456789", new Date());
		contact = contactRepository.save(contact);
	}

	@Test
	public void testFindAll() {
		List<PartnerContact> list = contactRepository.findAll();
		boolean expected = true;
		boolean result = list.size() > 0;
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testFindOne() {
		boolean expected = true;
		boolean result = contactRepository.findOne(contact.getId()) != null;
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testFindGroupNContacts() {
		boolean expected = true;
		boolean result = contactRepository.findAllByPartnerId(1).size() > 0;
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testFindOneByPartnerId() {
		boolean expected = true;
		boolean result = contactRepository.findOneByPartnerId(contact.getId(), 1) == null;
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testDeleteOneByPartnerId() {
		boolean expected = true;
		boolean result =  contactRepository.deleteOneByPartnerId(3, 2) > 0;
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testFindAllContactsByGroupId() {
		boolean expected = true;
		boolean result = contactRepository.findAllContactsByGroupId(1).size() > 0;
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void testAddContactIntoGroup() {
		boolean expected = true;
		boolean result = contactRepository.addContactIntoGroup(1, 3) > 0;
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void testCheckGroupNContactExists() {
		boolean expected = true;
		boolean result = contactRepository.groupNContactExists(1,1) != null;
		Assert.assertEquals(expected, result);
		
		expected = false;
		result = contactRepository.groupNContactExists(1,6) != null;
		Assert.assertEquals(expected, result);
	}

	@After
	public void after() {
		if (contactRepository.exists(contact.getId()))
			contactRepository.delete(contact);
	}

}
