package com.minhthanh.bulk.admin.controller;

import org.junit.Test;

import com.minhthanh.bulk.admin.config.WebAppConfigurationAware;
import com.minhthanh.bulk.admin.manager.PathManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest extends WebAppConfigurationAware {

	@Test
	public void testViewForgotPassword() throws Exception {
		this.mockMvc.perform(get("/signin/password/forgot")).andExpect(status().isOk())
				.andExpect(view().name(PathManager.FORGOT_PASSWORD_VIEW));
	}

	@Test
	public void testSinginWithToken() throws Exception {
		String token = "11da75c4-4832-4ea9-8b6f-b57ad662f12f";
		this.mockMvc.perform(get("/signintoken?token=" + token))
				.andExpect(view().name(PathManager.ACCOUNT_SECURITY_REDIRECT));
	}
}
