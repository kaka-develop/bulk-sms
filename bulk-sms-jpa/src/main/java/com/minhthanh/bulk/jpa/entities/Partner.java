package com.minhthanh.bulk.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the partner database table.
 * 
 */
@Entity
@NamedQuery(name="Partner.findAll", query="SELECT p FROM Partner p")
public class Partner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String about;

	@Column(name="brand_name")
	private String brandName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private String name;

	@Column(name="trigger_deliver_api")
	private String triggerDeliverApi;

	//bi-directional many-to-one association to BlacklistNumber
	@OneToMany(mappedBy="partner")
	private List<BlacklistNumber> blacklistNumbers;

	//bi-directional many-to-one association to BlacklistTopic
	@OneToMany(mappedBy="partner")
	private List<BlacklistTopic> blacklistTopics;

	//bi-directional many-to-one association to PartnerAdminAccount
	@OneToMany(mappedBy="partner")
	private List<PartnerAdminAccount> partnerAdminAccounts;
	
	//bi-directional many-to-one association to PartnerContact
	@OneToMany(mappedBy = "partner")
	private List<PartnerContact> partnerContacts;

	//bi-directional many-to-one association to PartnerGroupContact
	@OneToMany(mappedBy="partner")
	private List<PartnerGroupContact> partnerGroupContacts;

	//bi-directional many-to-one association to Subscription
	@OneToMany(mappedBy="partner")
	private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "partner")
    private List<BundleHistory> lstBundleHistory;

    @OneToMany(mappedBy = "partner")
    private List<SmsHistory> lstSmsHistory;

	public Partner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTriggerDeliverApi() {
		return this.triggerDeliverApi;
	}

	public void setTriggerDeliverApi(String triggerDeliverApi) {
		this.triggerDeliverApi = triggerDeliverApi;
	}

	public List<BlacklistNumber> getBlacklistNumbers() {
		return this.blacklistNumbers;
	}

	public void setBlacklistNumbers(List<BlacklistNumber> blacklistNumbers) {
		this.blacklistNumbers = blacklistNumbers;
	}

	public BlacklistNumber addBlacklistNumber(BlacklistNumber blacklistNumber) {
		getBlacklistNumbers().add(blacklistNumber);
		blacklistNumber.setPartner(this);

		return blacklistNumber;
	}

	public BlacklistNumber removeBlacklistNumber(BlacklistNumber blacklistNumber) {
		getBlacklistNumbers().remove(blacklistNumber);
		blacklistNumber.setPartner(null);

		return blacklistNumber;
	}

	public List<BlacklistTopic> getBlacklistTopics() {
		return this.blacklistTopics;
	}

	public void setBlacklistTopics(List<BlacklistTopic> blacklistTopics) {
		this.blacklistTopics = blacklistTopics;
	}

	public BlacklistTopic addBlacklistTopic(BlacklistTopic blacklistTopic) {
		getBlacklistTopics().add(blacklistTopic);
		blacklistTopic.setPartner(this);

		return blacklistTopic;
	}

	public BlacklistTopic removeBlacklistTopic(BlacklistTopic blacklistTopic) {
		getBlacklistTopics().remove(blacklistTopic);
		blacklistTopic.setPartner(null);

		return blacklistTopic;
	}

	public List<PartnerAdminAccount> getPartnerAdminAccounts() {
		return this.partnerAdminAccounts;
	}

	public List<PartnerContact> getPartnerContacts() {
		return partnerContacts;
	}

	public void setPartnerContacts(List<PartnerContact> partnerContacts) {
		this.partnerContacts = partnerContacts;
	}

	public void setPartnerAdminAccounts(List<PartnerAdminAccount> partnerAdminAccounts) {
		this.partnerAdminAccounts = partnerAdminAccounts;
	}

	public PartnerAdminAccount addPartnerAdminAccount(PartnerAdminAccount partnerAdminAccount) {
		getPartnerAdminAccounts().add(partnerAdminAccount);
		partnerAdminAccount.setPartner(this);

		return partnerAdminAccount;
	}

	public PartnerAdminAccount removePartnerAdminAccount(PartnerAdminAccount partnerAdminAccount) {
		getPartnerAdminAccounts().remove(partnerAdminAccount);
		partnerAdminAccount.setPartner(null);

		return partnerAdminAccount;
	}

	public List<PartnerGroupContact> getPartnerGroupContacts() {
		return this.partnerGroupContacts;
	}

	public void setPartnerGroupContacts(List<PartnerGroupContact> partnerGroupContacts) {
		this.partnerGroupContacts = partnerGroupContacts;
	}

	public PartnerGroupContact addPartnerGroupContact(PartnerGroupContact partnerGroupContact) {
		getPartnerGroupContacts().add(partnerGroupContact);
		partnerGroupContact.setPartner(this);

		return partnerGroupContact;
	}

	public PartnerGroupContact removePartnerGroupContact(PartnerGroupContact partnerGroupContact) {
		getPartnerGroupContacts().remove(partnerGroupContact);
		partnerGroupContact.setPartner(null);

		return partnerGroupContact;
	}

	public List<Subscription> getSubscriptions() {
		return this.subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Subscription addSubscription(Subscription subscription) {
		getSubscriptions().add(subscription);
		subscription.setPartner(this);

		return subscription;
	}

	public Subscription removeSubscription(Subscription subscription) {
		getSubscriptions().remove(subscription);
		subscription.setPartner(null);

		return subscription;
	}

    public List<BundleHistory> getLstBundleHistory() {
        return lstBundleHistory;
    }

    public void setLstBundleHistory(List<BundleHistory> lstBundleHistory) {
        this.lstBundleHistory = lstBundleHistory;
    }

    public List<SmsHistory> getLstSmsHistory() {
        return lstSmsHistory;
    }

    public void setLstSmsHistory(List<SmsHistory> lstSmsHistory) {
        this.lstSmsHistory = lstSmsHistory;
    }
}