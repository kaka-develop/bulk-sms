package com.minhthanh.bulk.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.minhthanh.bulk.admin.converter.AccountConverter;
import com.minhthanh.bulk.admin.form.ContactForm;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.service.ContactService;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;
import com.minhthanh.bulk.jpa.entities.PartnerContact;

@Controller
@RequestMapping("/contact")
public class ContactController {

	public static final String REDIRECT_INDEX = "redirect:/contact";
	
	@Autowired
	private ContactService contactService;

	@GetMapping(value = { "/", "" })
	public String index(Model model, Authentication authentication) {
		// get current account id
		int accountId = AccountConverter.authenticationToAccount(authentication).getId();

		// get all contacts from a partner
		List<PartnerContact> list = contactService.findAllByPartnerId(accountId);

		// create modelview with view path and contact model
		model.addAttribute("contacts", list);

		return PathManager.CONTACTS_VIEW;
	}

	@GetMapping("/new")
	public String newContact(Model model) {
		// add contact form attribute
		model.addAttribute("contactForm", new ContactForm());

		return PathManager.NEW_CONTACT_VIEW;
	}

	@PostMapping("/new")
	public String newContact(@Valid @ModelAttribute ContactForm contactForm, Authentication authentication,
			Errors errors) {
		if (errors.hasErrors()) {
			return PathManager.NEW_CONTACT_VIEW;
		}
		// get new contact from form
		PartnerContact contact = contactForm.newContact();

		// check contact whether is null or not
		if (contact == null)
			return PathManager.NEW_CONTACT_VIEW;
		else {
			// get current account and set partner
			PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
			contact.setPartner(account.getPartner());
			contactService.save(contact);
		}
		return REDIRECT_INDEX;
	}
	

	@GetMapping("/edit/{id}")
	public String editContact(Model model, Authentication authentication, @PathVariable("id") int id) {

		// get current account id
		int accountId = AccountConverter.authenticationToAccount(authentication).getId();

		// get a contact by id
		PartnerContact contact = contactService.findOneByPartnerId(id, accountId);

		// if contact is null, go to 404
		if (contact == null)
			return PathManager._404_VIEW;
		else
		// add the contact into model
		model.addAttribute("contactForm", new ContactForm(contact));

		return PathManager.EDIT_CONTACT_VIEW;
	}

	@PostMapping("/edit")
	public String editContact(Model model,@Valid @ModelAttribute ContactForm contactForm, Errors errors) {
		if (errors.hasErrors()) {
			return PathManager.EDIT_CONTACT_VIEW;
		}

		// check contact whether is null or not
		PartnerContact contact = contactService.findOne(contactForm.getId());
		if (contact == null) {
			return PathManager.EDIT_CONTACT_VIEW;
		} else {
			// update contact
			contact = contactForm.updatedContact(contact);
			contactService.save(contact);
		}
		return REDIRECT_INDEX;
	}

	@GetMapping("/del/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteContact(@PathVariable("id") int id, Authentication authentication) {
		// get current account id
		int accountId = AccountConverter.authenticationToAccount(authentication).getId();

		// delete contact
		if (contactService.deleteOneByPartnerId(id, accountId))
			return REDIRECT_INDEX;
		else
			return PathManager._404_VIEW;
	}

	
	@GetMapping("/contacts/{id}")
	public String index(@PathVariable("id") int id,Model model) {
		// get all contacts from a partner
		List<PartnerContact> list = contactService.findAllByGroupId(id);

		// create modelview with view path and contact model
		model.addAttribute("contacts", list);

		return PathManager.CONTACTS_VIEW;
	}
	
	@GetMapping("/api/new/{cId}&{gId}")
	@ResponseStatus(HttpStatus.OK)
	public void addContactIntoGroup(@PathVariable("cId") int cId,@PathVariable("gId") int gId) {
		contactService.addContactIntoGroup(cId,gId);
	}
	
	@GetMapping(value = "/api/list/{id}")
	@ResponseBody
	public List<PartnerContact> getGroupListByContactId(@PathVariable("id") int id) {
		return contactService.findAllByGroupId(id);
	}


	@GetMapping(value = "/api/all")
	@ResponseBody
	public List<PartnerContact> getAllGroupsByPartnerId(Authentication authentication) {
		// get current account id
		int accountId = AccountConverter.authenticationToAccount(authentication).getId();
		return contactService.findAllByPartnerId(accountId);
	}
}
