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
import com.minhthanh.bulk.admin.form.GroupForm;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.service.GroupContactService;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;
import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;

@Controller
@RequestMapping("/group")
public class GroupContactController {

	public static final String REDIRECT_INDEX = "redirect:/group";
	@Autowired
	private GroupContactService groupContactService;

	@GetMapping(value = { "", "/" })
	public String index(Model model, Authentication authentication) {
		// get current account id
		int accountId = AccountConverter.authenticationToAccount(authentication).getId();

		// get all groups and put into model
		model.addAttribute("groups", groupContactService.findAllByPartnerId(accountId));
		return PathManager.GROUPS_VIEW;
	}

	@GetMapping("/new")
	public String newGroup(Model model) {
		// put group form into model
		model.addAttribute("groupForm", new GroupForm());
		return PathManager.NEW_GROUP_VIEW;
	}

	@PostMapping("/new")
	public String newGroup(@Valid @ModelAttribute GroupForm groupForm, Authentication authentication, Errors errors) {
		if (errors.hasErrors()) {
			return PathManager.NEW_GROUP_VIEW;
		}
		// get new contact from form
		PartnerGroupContact group = groupForm.newGroup();

		// check contact whether is null or not
		if (group == null)
			return PathManager.NEW_GROUP_VIEW;
		else {
			// get current user
			PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
			group.setPartner(account.getPartner());
			groupContactService.save(group);
		}
		return REDIRECT_INDEX;
	}

	@GetMapping("/edit/{id}")
	public String editContact(Model model, @PathVariable("id") int id) {
		// get a contact by id
		PartnerGroupContact group = groupContactService.findOne(id);

		// add the contact into model
		model.addAttribute("groupForm", new GroupForm(group));

		return PathManager.EDIT_GROUP_VIEW;
	}

	@PostMapping("/edit")
	public String editContact(@Valid @ModelAttribute GroupForm groupForm, Errors errors) {
		if (errors.hasErrors()) {
			return PathManager.EDIT_GROUP_VIEW;
		}

		// check group whether is null or not
		PartnerGroupContact group = groupContactService.findOne(groupForm.getId());
		if (group == null) 
			return PathManager.EDIT_GROUP_VIEW;
		else 
			// update group
			group = groupForm.updatedGroup(group);
			groupContactService.save(group);
		
		return REDIRECT_INDEX;
	}

	@GetMapping("/del/{id}")
	public String deleteContact(@PathVariable("id") int id) {
		// delete contact if existed
		groupContactService.delete(id);
		return REDIRECT_INDEX;
	}

	@GetMapping("/groups/{id}")
	public String getGroupsByContactId(@PathVariable("id") int id, Model model) {
		// get all groups and put into model
		model.addAttribute("groups", groupContactService.findGroupsByContactId(id));
		return PathManager.GROUPS_VIEW;
	}

	@GetMapping(value = "/api/list/{id}")
	@ResponseBody
	public List<PartnerGroupContact> getGroupListByContactId(@PathVariable("id") int id) {
		return groupContactService.findGroupsByContactId(id);
	}

	@GetMapping(value = "/api/del/{cId}&{gId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteGroupOfContact(@PathVariable("cId") int cId, @PathVariable("gId") int gId) {
		groupContactService.deleteGroupOfContact(cId, gId);
	}

	@GetMapping(value = "/api/all")
	@ResponseBody
	public List<PartnerGroupContact> getAllGroupsByPartnerId(Authentication authentication) {
		// get current account id
		int accountId = AccountConverter.authenticationToAccount(authentication).getId();
		return groupContactService.findAllByPartnerId(accountId);
	}
	
	

}
