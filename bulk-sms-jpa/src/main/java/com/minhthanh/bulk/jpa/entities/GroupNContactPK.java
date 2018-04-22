package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the group_n_contact database table.
 * 
 */
@Embeddable
public class GroupNContactPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="partner_group_contact_id")
	private int partnerGroupContactId;

	@Column(name="partner_contact_id")
	private int partnerContactId;

	public GroupNContactPK() {
	}
	public int getPartnerGroupContactId() {
		return this.partnerGroupContactId;
	}
	public void setPartnerGroupContactId(int partnerGroupContactId) {
		this.partnerGroupContactId = partnerGroupContactId;
	}
	public int getPartnerContactId() {
		return this.partnerContactId;
	}
	public void setPartnerContactId(int partnerContactId) {
		this.partnerContactId = partnerContactId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupNContactPK)) {
			return false;
		}
		GroupNContactPK castOther = (GroupNContactPK)other;
		return 
			(this.partnerGroupContactId == castOther.partnerGroupContactId)
			&& (this.partnerContactId == castOther.partnerContactId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partnerGroupContactId;
		hash = hash * prime + this.partnerContactId;
		
		return hash;
	}
}