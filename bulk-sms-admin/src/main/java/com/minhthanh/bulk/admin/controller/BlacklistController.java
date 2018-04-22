package com.minhthanh.bulk.admin.controller;

import com.minhthanh.bulk.admin.converter.AccountConverter;
import com.minhthanh.bulk.admin.form.NumberForm;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.service.BlacklistNumberService;
import com.minhthanh.bulk.jpa.entities.BlacklistNumber;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by youngkaka on 24/10/2016.
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistNumberService numberService;

    @GetMapping(value = {"/number","/number/"})
    public String viewNumbers(Model model, Authentication authentication) {
        // get current account id
        int accountId = AccountConverter.authenticationToAccount(authentication).getId();

        // get blacklist numbers by partner Id and bind to view
        List<BlacklistNumber> numbers = numberService.findAllByPartnerId(accountId);
        model.addAttribute("numbers",numbers);
        return PathManager.BLACKLIST_NUMBER_VIEW;
    }

    @GetMapping("/number/new")
    public String viewNewNumber(Model model){
        model.addAttribute("numberForm", new NumberForm());
        return PathManager.BLACKLIST_NEW_NUMBER_VIEW;
    }

    @PostMapping("/number/new")
    public String postNewNumber(@Valid @ModelAttribute NumberForm numberForm, Errors errors,Authentication authentication) {
        if(errors.hasErrors())
            return PathManager.BLACKLIST_NEW_NUMBER_VIEW;

        BlacklistNumber number = numberForm.createNew();
        if(number != null){
            PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
            number.setPartner(account.getPartner());
            numberService.save(number);
        }
        return "redirect:/blacklist/number";
    }
}
