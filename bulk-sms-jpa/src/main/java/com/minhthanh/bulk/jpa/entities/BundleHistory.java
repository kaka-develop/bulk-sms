package com.minhthanh.bulk.jpa.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by phuongtm on 9/27/16.
 */

@Entity
@Table(name = "bundle_history")
public class BundleHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private BundleHistoryPK id;

    @Temporal(TemporalType.DATE)
    @Column(name="created_date")
    private Date createdDate;

    //bi-directional many-to-one association to PartnerContact
    @ManyToOne
    @JoinColumn(name="bundle_id", insertable = false, updatable = false)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Bundle bundle;

    //bi-directional many-to-one association to PartnerGroupContact
    @ManyToOne
    @JoinColumn(name="partner_id", insertable = false, updatable = false)
    private Partner partner;

    public BundleHistory() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public BundleHistoryPK getId() {
        return id;
    }

    public void setId(BundleHistoryPK id) {
        this.id = id;
    }
}
