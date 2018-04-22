package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the blacklist_topic database table.
 * 
 */
@Entity
@Table(name="blacklist_topic")
@NamedQuery(name="BlacklistTopic.findAll", query="SELECT b FROM BlacklistTopic b")
public class BlacklistTopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BlacklistTopicPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="key_word")
	private String keyWord;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partner_id",insertable = false, updatable = false)
	private Partner partner;

	public BlacklistTopic() {
	}

	public BlacklistTopicPK getId() {
		return this.id;
	}

	public void setId(BlacklistTopicPK id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}