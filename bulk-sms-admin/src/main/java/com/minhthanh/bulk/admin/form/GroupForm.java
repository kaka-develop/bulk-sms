package com.minhthanh.bulk.admin.form;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.minhthanh.bulk.admin.converter.DateConverter;
import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;

public class GroupForm {
	
	private int id;

	@NotBlank(message = "Name is not blank")
	@Length(min = 5, message = "The name length is must be more than 5")
	private String name;

	@NotBlank(message = "Information is not blank")
	@Length(min = 5, message = "The info length is must be more than 5")
	private String info;

	@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}", message = "Must be in date format: MM-dd-yyyy hh:mm")
	private String date;

	public GroupForm() {
		this.date = DateConverter.dateToString(new Date());
	}

	public GroupForm(PartnerGroupContact group) {
		this.id = group.getId();
		this.name = group.getName();
		this.info = group.getInfo();
		this.date = DateConverter.dateToString(group.getCreatedDate());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PartnerGroupContact newGroup() {
		PartnerGroupContact group = new PartnerGroupContact(name, info, new Date());
		return group;

	}

	public PartnerGroupContact updatedGroup(PartnerGroupContact group) {
		Date pdate = DateConverter.stringToDate(date);
		group.setInfo(info);
		group.setName(name);
		group.setCreatedDate(pdate);
		return group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
