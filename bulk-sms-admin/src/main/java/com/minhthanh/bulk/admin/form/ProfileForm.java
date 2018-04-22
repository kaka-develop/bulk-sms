package com.minhthanh.bulk.admin.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

public class ProfileForm {

	@NotBlank(message = "Information is not blank")
	@Length(min = 5, message = "The info length is must be more than 5")
	private String info;

	@NotBlank(message = "Username is not blank")
	@Length(min = 5, message = "The username length is must be more than 5")
	private String username;

	public ProfileForm() {
		// TODO Auto-generated constructor stub
	}

	public ProfileForm(PartnerAdminAccount account) {
		this.info = account.getInfo();
		this.username = account.getUsername();

	}

	public PartnerAdminAccount updateAccount(PartnerAdminAccount account) {

		account.setInfo(this.info);
		account.setUsername(this.username);
		return account;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
