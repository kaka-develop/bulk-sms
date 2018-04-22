package com.minhthanh.bulk.admin.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.minhthanh.bulk.admin.manager.RoleManager;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

public class SignupForm {

	
	@Email(message = "Must be in email format")
	private String email;
	
	@Length(min=8, message="The password length is must be more than 8")
	private String password;
	
	public SignupForm(){
		
	}
	

	public SignupForm(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public PartnerAdminAccount createAccount() {
		return new PartnerAdminAccount(email, password, RoleManager.USER_ROLE);
	}

	
}
