package com.minhthanh.bulk.jpa.entities;

import com.minhthanh.bulk.jpa.enums.ExtendStatus;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by phuongtm on 9/27/16.
 */

@Entity
@Table(name = "bundle_extend")
public class BundleExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private BundleExtendPK id;

    @Temporal(TemporalType.DATE)
    @Column(name="updated_date")
    private Date updatedDate;

    @Enumerated(EnumType.STRING)
    @Column(name="state")
    private ExtendStatus state;

    //bi-directional many-to-one association to PartnerContact
    @ManyToOne
    @JoinColumn(name="bundle_id", insertable = false, updatable = false)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Bundle bundle;

    //bi-directional many-to-one association to PartnerGroupContact
    @ManyToOne
    @JoinColumn(name="partner_id", insertable = false, updatable = false)
    private Partner partner;

    public BundleExtend() {
    }

    public BundleExtendPK getId() {
        return id;
    }

    public void setId(BundleExtendPK id) {
        this.id = id;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ExtendStatus getState() {
        return state;
    }

    public void setState(ExtendStatus state) {
        this.state = state;
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
}
