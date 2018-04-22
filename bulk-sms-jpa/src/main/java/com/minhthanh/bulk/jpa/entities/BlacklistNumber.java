package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the blacklist_numbers database table.
 * 
 */
@Entity
@Table(name="blacklist_numbers")
@NamedQuery(name="BlacklistNumber.findAll", query="SELECT b FROM BlacklistNumber b")
public class BlacklistNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String number;

	//bi-directional many-to-one association to Partner
	@ManyToOne(cascade = {CascadeType.ALL})
	private Partner partner;

	public BlacklistNumber() {
	}

	public BlacklistNumber(String number) {
		this.createdDate = new Date();
		this.number  = number;
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

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}