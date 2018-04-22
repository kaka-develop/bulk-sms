package com.minhthanh.bulk.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import com.minhthanh.bulk.admin.config.WebSecurityConfigurationAware;
import com.minhthanh.bulk.admin.manager.PathManager;

public class AccountControllerTest extends WebSecurityConfigurationAware {

	@Test
	public void testViewIndex() throws Exception {
		this.mockMvc.perform(get("/account").with(admin())).andExpect(status().isOk())
				.andExpect(view().name(PathManager.PROFILE_VIEW));
	}

	@Test
	public void testViewProfile() throws Exception {
		this.mockMvc.perform(get("/account/profile").with(admin())).andExpect(status().isOk())
				.andExpect(view().name(PathManager.PROFILE_VIEW));
	}

	@Test
	public void testViewEditProfile() throws Exception {
		this.mockMvc.perform(get("/account/profile/edit").with(admin())).andExpect(status().isOk())
				.andExpect(view().name(PathManager.EDIT_PROFILE_VIEW));
	}

//	@Test
//	public void testPostEditProfile() throws Exception {
//		String info = "new info";
//		String username = "new username";
//		this.mockMvc
//				.perform(post("/account/profile/edit").with(admin()).param("info", info).param("username", username))
//				.andExpect(status().isOk()).andExpect(view().name(AccountController.PROFILE_REDIRECT));
//	}

	@Test
	public void testViewSecurity() throws Exception {
		this.mockMvc.perform(get("/account/security").with(admin())).andExpect(status().isOk())
				.andExpect(view().name(PathManager.ACCOUNT_SECURITY_VIEW));
	}

	@Test
	public void testPostChangePassword() throws Exception{
		String oldPassword = "admin";
		String newPassword = "new admin password";
		this.mockMvc.perform(post("/account/security/password").with(admin()).param("oldPassword", oldPassword ).param("newPassword",newPassword)).andExpect(status().isOk())
				.andExpect(view().name(PathManager.ACCOUNT_SECURITY_VIEW));
	}

}
