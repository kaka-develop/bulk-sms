package com.minhthanh.bulk.admin.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

public class CustomUser extends User {

	private PartnerAdminAccount account;

	public CustomUser(PartnerAdminAccount account, Collection<? extends GrantedAuthority> authorities) {
		super(account.getEmail(), account.getPassword(), authorities);
		this.account = account;		
		
	}

	public PartnerAdminAccount getAccount() {
		return account;
	}
}
