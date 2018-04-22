package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the group_n_contact database table.
 * 
 */
@Entity
@Table(name="group_n_contact")
@NamedQuery(name="GroupNContact.findAll", query="SELECT g FROM GroupNContact g")
public class GroupNContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GroupNContactPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	//bi-directional many-to-one association to PartnerContact
	@ManyToOne
	@JoinColumn(name="partner_contact_id",insertable = false, updatable = false)
	private PartnerContact partnerContact;

	//bi-directional many-to-one association to PartnerGroupContact
	@ManyToOne
	@JoinColumn(name="partner_group_contact_id",insertable = false, updatable = false)
	private PartnerGroupContact partnerGroupContact;

	
	public GroupNContact() {
	}

	public GroupNContactPK getId() {
		return this.id;
	}

	public void setId(GroupNContactPK id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public PartnerContact getPartnerContact() {
		return this.partnerContact;
	}

	public void setPartnerContact(PartnerContact partnerContact) {
		this.partnerContact = partnerContact;
	}

	public PartnerGroupContact getPartnerGroupContact() {
		return this.partnerGroupContact;
	}

	public void setPartnerGroupContact(PartnerGroupContact partnerGroupContact) {
		this.partnerGroupContact = partnerGroupContact;
	}

}