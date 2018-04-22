package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the partner_admin_account database table.
 * 
 */
@Entity
@Table(name="partner_admin_account")
@NamedQuery(name="PartnerAdminAccount.findAll", query="SELECT p FROM PartnerAdminAccount p")
public class PartnerAdminAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="config_notify_by_app")
	private byte configNotifyByApp;

	@Column(name="config_notify_by_email")
	private byte configNotifyByEmail;

	@Column(name="config_notify_by_phone")
	private byte configNotifyByPhone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(unique=true)
	private String email;

	private String info;

	private String password;

	@Column(name="phone_number")
	private String phoneNumber;
	
	private String avatar;
	
	private boolean enable;

	private String username;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	private Partner partner;

	//bi-directional many-to-one association to PartnerAdminRole
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="partner_admin_role_id")
	private PartnerAdminRole partnerAdminRole;
	
	@OneToOne
	@JoinColumn(name = "partner_token_id")
	private PartnerToken partnerToken;

	public PartnerAdminAccount(){
		
	}
	
	public PartnerAdminAccount(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.partnerAdminRole = new PartnerAdminRole(role);
		this.createdDate = new Date();
		this.avatar = "avatar.jpg";
	}
	
	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getConfigNotifyByApp() {
		return this.configNotifyByApp;
	}

	public void setConfigNotifyByApp(byte configNotifyByApp) {
		this.configNotifyByApp = configNotifyByApp;
	}

	public byte getConfigNotifyByEmail() {
		return this.configNotifyByEmail;
	}

	public void setConfigNotifyByEmail(byte configNotifyByEmail) {
		this.configNotifyByEmail = configNotifyByEmail;
	}

	public byte getConfigNotifyByPhone() {
		return this.configNotifyByPhone;
	}

	public void setConfigNotifyByPhone(byte configNotifyByPhone) {
		this.configNotifyByPhone = configNotifyByPhone;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerAdminRole getPartnerAdminRole() {
		return this.partnerAdminRole;
	}

	public void setPartnerAdminRole(PartnerAdminRole partnerAdminRole) {
		this.partnerAdminRole = partnerAdminRole;
	}

	public PartnerToken getPartnerToken() {
		return partnerToken;
	}

	public void setPartnerToken(PartnerToken partnerToken) {
		this.partnerToken = partnerToken;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
	

}