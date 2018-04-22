package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the partner_admin_role database table.
 * 
 */
@Entity
@Table(name="partner_admin_role")
@NamedQuery(name="PartnerAdminRole.findAll", query="SELECT p FROM PartnerAdminRole p")
public class PartnerAdminRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String description;

	private int role;

	//bi-directional many-to-one association to PartnerAdminAccount
	@OneToMany(mappedBy="partnerAdminRole")
	private List<PartnerAdminAccount> partnerAdminAccounts;

	public PartnerAdminRole(){
		
	}
	
	public PartnerAdminRole(String name) {
		this.description = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public List<PartnerAdminAccount> getPartnerAdminAccounts() {
		return this.partnerAdminAccounts;
	}

	public void setPartnerAdminAccounts(List<PartnerAdminAccount> partnerAdminAccounts) {
		this.partnerAdminAccounts = partnerAdminAccounts;
	}

	public PartnerAdminAccount addPartnerAdminAccount(PartnerAdminAccount partnerAdminAccount) {
		getPartnerAdminAccounts().add(partnerAdminAccount);
		partnerAdminAccount.setPartnerAdminRole(this);

		return partnerAdminAccount;
	}

	public PartnerAdminAccount removePartnerAdminAccount(PartnerAdminAccount partnerAdminAccount) {
		getPartnerAdminAccounts().remove(partnerAdminAccount);
		partnerAdminAccount.setPartnerAdminRole(null);

		return partnerAdminAccount;
	}

}