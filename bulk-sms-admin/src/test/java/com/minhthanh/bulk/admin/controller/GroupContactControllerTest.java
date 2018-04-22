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
import com.minhthanh.bulk.admin.service.GroupContactService;
import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;

public class GroupContactControllerTest extends WebSecurityConfigurationAware {

	@Autowired
	private GroupContactService groupContactService;

	private PartnerGroupContact groupContact;

	@Before
	public void before() {
		super.before();
		groupContact = new PartnerGroupContact("test group controller", "info group controller", new Date());
		groupContact = groupContactService.newGroup(groupContact);
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/group").with(admin())).andExpect(status().isOk()).andExpect(view().name("group/groups"))
				.andExpect(model().attributeExists("groups"));
	}

	@Test
	public void newGroupView() throws Exception {
		this.mockMvc.perform(get("/group/new").with(admin())).andExpect(status().isOk()).andExpect(view().name("group/new_group"));
	}

	@Test
	public void newGroup() throws Exception {
		groupContact.setId(10);
		String redirectIndex = GroupContactController.REDIRECT_INDEX;
		this.mockMvc
				.perform(post("/group/new").with(admin()).param("name", groupContact.getName()).param("info", groupContact.getInfo()))
				.andExpect(view().name(redirectIndex));
	}
	
	@Test
	public void testGetContact() throws Exception {
		mockMvc.perform(get("/group/edit/" + groupContact.getId()).with(admin())).andExpect(model().attributeExists("groupForm"))
				.andExpect(view().name("group/edit_group"));

	}
	
	@Test
	public void testDeleteConatct() throws Exception {
		String id = groupContact.getId() + "";
		String redirectIndex = GroupContactController.REDIRECT_INDEX;
		mockMvc.perform(get("/group/del/" + id).with(admin())).andExpect(view().name(redirectIndex));

	}

	
	@Test
	public void testEditContact() throws Exception {
		String id = groupContact.getId() + "";
		String info = "Update group info";
		String name = "Update group name";
		String date = "12-10-2012 15:25";
		String redirectIndex = GroupContactController.REDIRECT_INDEX;
		mockMvc.perform(post("/group/edit").with(admin()).param("id", id).param("info", info).param("name", name)
				.param("date", date)).andExpect(view().name(redirectIndex));

	}

	@Test
	public void testGetGroupsByContactId() throws Exception {
		mockMvc.perform(get("/group/groups/" + 1).with(admin())).andExpect(status().isOk()).andExpect(model().attributeExists("groups"))
				.andExpect(view().name(PathManager.GROUPS_VIEW));

	}
	@Test
	public void testGetGroupListByContactId() throws Exception {
		mockMvc.perform(get("/group/list/" + 1).with(admin())).andExpect(status().isOk());
	}
	
	@After
	public void after() {
		groupContactService.delete(groupContact.getId());
	}
}
