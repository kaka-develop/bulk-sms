package com.minhthanh.bulk.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.minhthanh.bulk.admin.config.WebSecurityConfigurationAware;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.service.ContactService;
import com.minhthanh.bulk.jpa.entities.PartnerContact;

public class ContactControllerTest extends WebSecurityConfigurationAware {

	@Autowired
	private ContactService contactService;

	private PartnerContact contact;
	
	

	@Before
	public void before() {
		super.before();
		contact = new PartnerContact("test contact controller", "0123456789", new Date());
		contact = contactService.newContact(contact);
	}

	@Test
	public void testContactIndex() throws Exception {
		mockMvc.perform(get("/contact/").with(admin())).andExpect(status().isOk()).andExpect(model().attributeExists("contacts"))
				.andExpect(view().name(PathManager.CONTACTS_VIEW));

	}

	@Test
	public void testNewContactView() throws Exception {
		mockMvc.perform(get("/contact/new").with(admin())).andExpect(model().attributeExists("contactForm"))
				.andExpect(view().name(PathManager.NEW_CONTACT_VIEW));

	}

	@Test
	public void testNewContact() throws Exception {
		String redirectIndex = ContactController.REDIRECT_INDEX;
		mockMvc.perform(post("/contact/new").with(admin()).param("info", contact.getInfo()).param("phoneNumber",
				contact.getPhoneNumber())).andExpect(view().name(redirectIndex));

	}

	@Test
	public void testGetContact() throws Exception {
		mockMvc.perform(get("/contact/edit/" + contact.getId()).with(admin())).andExpect(model().attributeDoesNotExist("contactForm"))
		.andExpect(view().name(PathManager._404_VIEW));

	}

	@Test
	public void testEditContact() throws Exception {
		String id = contact.getId() + "";
		String info = "Update contact";
		String phoneNumber = "0123456789";
		String date = "12-10-2012 15:25";
		String redirectIndex = ContactController.REDIRECT_INDEX;
		mockMvc.perform(post("/contact/edit").with(admin()).param("id", id).param("info", info).param("phoneNumber", phoneNumber)
				.param("date", date)).andExpect(view().name(redirectIndex));

	}

	@Test
	public void testDeleteContact() throws Exception {
		String id = contact.getId() + "";
		String redirectIndex = ContactController.REDIRECT_INDEX;
		mockMvc.perform(get("/contact/del/" + id).with(admin())).andExpect(view().name(PathManager._404_VIEW));

	}
	
	
	@Test
	public void testGetContactsByGroupId() throws Exception {
		mockMvc.perform(get("/contact/contacts/" + 1).with(admin())).andExpect(status().isOk()).andExpect(model().attributeExists("contacts"))
				.andExpect(view().name(PathManager.CONTACTS_VIEW));

	}

	

	@After
	public void after() {
		contactService.deleteContact(contact.getId());
	}
}
