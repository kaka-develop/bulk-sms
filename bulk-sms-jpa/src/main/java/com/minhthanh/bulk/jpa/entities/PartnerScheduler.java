package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the partner_scheduler database table.
 * 
 */
@Entity
@Table(name="partner_scheduler")
@NamedQuery(name="PartnerScheduler.findAll", query="SELECT p FROM PartnerScheduler p")
public class PartnerScheduler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="crontab_expression")
	private String crontabExpression;

	private String description;

	private String params;

	public PartnerScheduler() {
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

	public String getCrontabExpression() {
		return this.crontabExpression;
	}

	public void setCrontabExpression(String crontabExpression) {
		this.crontabExpression = crontabExpression;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParams() {
		return this.params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}