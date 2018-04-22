package com.minhthanh.bulk.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.minhthanh.bulk.admin.converter.AccountConverter;
import com.minhthanh.bulk.admin.form.ProfileForm;
import com.minhthanh.bulk.admin.manager.FileManager;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.manager.ValidationManager;
import com.minhthanh.bulk.admin.service.AccountService;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    public static final String PROFILE_REDIRECT = "redirect:/account/profile";

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = {"/", ""})
    public String index(Model model) {

        return PathManager.PROFILE_VIEW;
    }

    @GetMapping("/profile")
    public String profile() {
        return PathManager.PROFILE_VIEW;
    }

    @GetMapping("/profile/edit")
    public String viewEditProfile(Model model, Authentication authentication) {
        PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
        model.addAttribute("profileForm", new ProfileForm(account));
        return PathManager.EDIT_PROFILE_VIEW;
    }

    @PostMapping("/profile/edit")
    public String postEditProfile(@Valid @ModelAttribute ProfileForm profileForm, Errors errors,
                                  Authentication authentication) {
        if (errors.hasErrors()) {
            return PathManager.EDIT_PROFILE_VIEW;
        }
        // update account
        PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
        account = profileForm.updateAccount(account);
        accountService.updateProfile(account);

        return PROFILE_REDIRECT;
    }

    @PostMapping("/avatar")
    public String postChangeAvatar(@RequestParam(value = "file") MultipartFile file, Authentication authentication) {
        if (!file.isEmpty()) {
            if (FileManager.storeFile(file)) {
                // update account
                PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
                String avatar = file.getOriginalFilename();
                account.setAvatar(avatar);
                accountService.save(account);
            }
        }
        return PROFILE_REDIRECT;
    }

    @GetMapping("/security")
    public String viewSecurity() {

        return PathManager.ACCOUNT_SECURITY_VIEW;
    }

    @PostMapping("/security/password")
    public String postChangePassword(@RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword, Authentication authentication, Model model) {
        boolean success = true;
        String message = "";
        if (ValidationManager.validatePassword(newPassword)) {
            PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
            if (accountService.checkOldPassword(account.getId(), oldPassword)) {
                account.setPassword(newPassword);
                accountService.save(account);
                message = "Change password successfully.";
            } else {
                success = false;
                message = "This current password is incorrect!";
            }
        } else {
            success = false;
            message = "This new password length must be more than 8 characters!";
        }

        model.addAttribute("success", success);
        model.addAttribute("message", message);
        return PathManager.ACCOUNT_SECURITY_VIEW;
    }
}
