package com.minhthanh.bulk.admin.form;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.minhthanh.bulk.admin.converter.DateConverter;
import com.minhthanh.bulk.jpa.entities.PartnerContact;

public class ContactForm {
	
	private int id;
	
	@NotBlank(message="Information is not blank")
	@Length(min=5, message="The info length is must be more than 5")
	private String info;

	@Pattern(regexp="\\d{10,12}", message="Must be a phone number that  has length size: between 10 and 12")
	private String phoneNumber;
	
	@Pattern(regexp="\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}", message="Must be in date format: MM-dd-yyyy hh:mm")
	private String date;
	
	public ContactForm() {
		this.date = DateConverter.dateToString(new Date());
	}
	
	public ContactForm(PartnerContact contact) {
		this.id= contact.getId();
		this.info = contact.getInfo();
		this.phoneNumber = contact.getPhoneNumber();
		this.date = DateConverter.dateToString(contact.getCreatedDate());
		
	}

	

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public PartnerContact newContact() {
			PartnerContact newContact =new PartnerContact(info,phoneNumber, new Date());
			return newContact;
	}
	
	public PartnerContact updatedContact(PartnerContact contact) {
		contact.setInfo(info);
		contact.setPhoneNumber(phoneNumber);
		contact.setCreatedDate(DateConverter.stringToDate(date));
		return contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
