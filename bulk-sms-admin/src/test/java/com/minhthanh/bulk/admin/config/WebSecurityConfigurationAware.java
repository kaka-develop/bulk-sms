package com.minhthanh.bulk.admin.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Collections;

import javax.inject.Inject;

import com.minhthanh.bulk.jpa.entities.Partner;
import com.minhthanh.bulk.jpa.entities.PartnerToken;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.minhthanh.bulk.admin.manager.RoleManager;
import com.minhthanh.bulk.admin.model.CustomUser;
import com.minhthanh.bulk.admin.service.AccountService;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

public abstract class WebSecurityConfigurationAware extends WebAppConfigurationAware {

	protected static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
	
	@Autowired 
	private AccountService accountService;
	
	public RequestPostProcessor admin() {
		PartnerAdminAccount account = createAdminAccount();
		CustomUser principal = new CustomUser(account, Collections.singleton(createAuthority(account.getPartnerAdminRole().getDescription())));
		Authentication auth = new UsernamePasswordAuthenticationToken(principal, account.getPassword(), principal.getAuthorities());
		return authentication(auth);
	}

	private PartnerAdminAccount createAdminAccount() {
		String email = "admin@gmail.com";
		String password = "admin";
		String role = RoleManager.PARTNER_ADMIN_ROLE;
		PartnerAdminAccount account = new PartnerAdminAccount(email,password,role);
		account.setPartner(new Partner());
		account.setPartnerToken(new PartnerToken());
		return account;
	}

	private GrantedAuthority createAuthority(String role) {
		return new SimpleGrantedAuthority(role);
	}

	@Inject
	private FilterChainProxy springSecurityFilterChain;

	@Before
	public void before() {
		this.mockMvc = webAppContextSetup(this.wac).addFilters(this.springSecurityFilterChain).build();
	}

}
