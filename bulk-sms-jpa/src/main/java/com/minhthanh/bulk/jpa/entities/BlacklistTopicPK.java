package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the blacklist_topic database table.
 * 
 */
@Embeddable
public class BlacklistTopicPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="partner_id")
	private int partnerId;

	public BlacklistTopicPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPartnerId() {
		return this.partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BlacklistTopicPK)) {
			return false;
		}
		BlacklistTopicPK castOther = (BlacklistTopicPK)other;
		return 
			(this.id == castOther.id)
			&& (this.partnerId == castOther.partnerId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.partnerId;
		
		return hash;
	}
}