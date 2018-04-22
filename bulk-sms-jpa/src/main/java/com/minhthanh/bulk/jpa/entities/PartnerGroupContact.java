package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the partner_group_contact database table.
 * 
 */
@Entity
@Table(name="partner_group_contact")
@NamedQuery(name="PartnerGroupContact.findAll", query="SELECT p FROM PartnerGroupContact p")
public class PartnerGroupContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String info;

	private String name;

	//bi-directional many-to-one association to GroupNContact
	@OneToMany(mappedBy = "partnerGroupContact",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<GroupNContact> groupNContacts;

	//bi-directional many-to-one association to Partner
	@ManyToOne(cascade = {CascadeType.ALL})
	@JsonIgnore
	private Partner partner;

	public PartnerGroupContact() {
	}

	public PartnerGroupContact(String name, String info, Date date) {
		this.name = name;
		this.info = info;
		this.createdDate = date;
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

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GroupNContact> getGroupNContacts() {
		return this.groupNContacts;
	}

	public void setGroupNContacts(List<GroupNContact> groupNContacts) {
		this.groupNContacts = groupNContacts;
	}

	public GroupNContact addGroupNContact(GroupNContact groupNContact) {
		getGroupNContacts().add(groupNContact);
		groupNContact.setPartnerGroupContact(this);

		return groupNContact;
	}

	public GroupNContact removeGroupNContact(GroupNContact groupNContact) {
		getGroupNContacts().remove(groupNContact);
		groupNContact.setPartnerGroupContact(null);

		return groupNContact;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}