package com.minhthanh.bulk.jpa.entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the bundle database table.
 * 
 */
@Entity
@NamedQuery(name="Bundle.findAll", query="SELECT b FROM Bundle b")
public class Bundle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private float price;

	private int quantity;

	@Column(name="renewal_date")
	private Date renewalDate;

	@Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "bundle")
    private List<BundleHistory> lstBundleHistory;

	public Bundle() {
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public List<BundleHistory> getLstBundleHistory() {
        return lstBundleHistory;
    }

    public void setLstBundleHistory(List<BundleHistory> lstBundleHistory) {
        this.lstBundleHistory = lstBundleHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bundle bundle = (Bundle) o;

        return Objects.equal(this.id, bundle.id)
               && Objects.equal(this.name, bundle.name)
               && Objects.equal(this.createdDate, bundle.createdDate)
               && Objects.equal(this.price, bundle.price)
               && Objects.equal(this.quantity, bundle.quantity)
               && Objects.equal(this.renewalDate, bundle.renewalDate)
               && Objects.equal(this.lstBundleHistory, bundle.lstBundleHistory);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id,
                this.createdDate,
                this.price,
                this.quantity,
                this.renewalDate,
                this.name,
                this.lstBundleHistory);
    }
}