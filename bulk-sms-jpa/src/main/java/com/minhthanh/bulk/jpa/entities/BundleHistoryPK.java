package com.minhthanh.bulk.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the bundle_history database table.
 * 
 */
@Embeddable
public class BundleHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="partner_id", insertable=false, updatable=false)
	private int partnerId;

	@Column(name="bundle_id", insertable=false, updatable=false)
	private int bundleId;

	public BundleHistoryPK() {
	}

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getBundleId() {
        return bundleId;
    }

    public void setBundleId(int bundleId) {
        this.bundleId = bundleId;
    }

    public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BundleHistoryPK)) {
			return false;
		}
		BundleHistoryPK castOther = (BundleHistoryPK)other;
		return 
			(this.partnerId == castOther.partnerId)
			&& (this.bundleId == castOther.bundleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partnerId;
		hash = hash * prime + this.bundleId;
		
		return hash;
	}
}