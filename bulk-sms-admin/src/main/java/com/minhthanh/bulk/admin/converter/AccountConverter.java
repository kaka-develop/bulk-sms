package com.minhthanh.bulk.admin.converter;

import org.springframework.security.core.Authentication;

import com.minhthanh.bulk.admin.model.CustomUser;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

public class AccountConverter {

	public static PartnerAdminAccount authenticationToAccount(Authentication authentication) {
		CustomUser user = (CustomUser) authentication.getPrincipal();
		return user.getAccount();
	}
}
