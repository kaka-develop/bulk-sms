package com.minhthanh.bulk.admin.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.minhthanh.bulk.admin.converter.AccountConverter;
import com.minhthanh.bulk.admin.form.SignupForm;
import com.minhthanh.bulk.admin.manager.MailManager;
import com.minhthanh.bulk.admin.manager.PathManager;
import com.minhthanh.bulk.admin.service.AccountService;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

@Controller
public class HomeController {

	private AccountService accountService;

	public HomeController(AccountService accountService) {
		// TODO Auto-generated constructor stub
		this.accountService = accountService;
	}

	@GetMapping(value = { "/", "index" })
	public String index(Principal principal) {
		return principal != null ? "home/index" : "home/signin";
	}

	@GetMapping("/signin")
	public String signin(Principal principal) {
		return principal != null ? "home/index" : PathManager.SIGNIN_VIEW;
	}

	@GetMapping("/signintoken")
	public String signin(@RequestParam("token") String token) {
		PartnerAdminAccount account = accountService.getAccountByToken(token);
		if (account != null) {
			accountService.signin(account);
			return PathManager.ACCOUNT_SECURITY_REDIRECT;
		} else
			return "redirect:/signin";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return PathManager.SIGNUP_VIEW;
	}

	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return PathManager.SIGNUP_VIEW;
		}

		PartnerAdminAccount account = accountService.getAccountByEmail(signupForm.getEmail());
		if (account == null)
			account = accountService.singup(signupForm.createAccount());

		return "redirect:/verify";

	}

	@GetMapping("/verification")
	public String requireVerificationWithToken(Model model,
			@RequestParam(value = "token", required = false) String token) {
		if (token != null) {
			if (accountService.verify(token))
				model.addAttribute("success", true);
			else
				model.addAttribute("success", false);
		} else
			model.addAttribute("success", false);
		return PathManager.VERIFY_VIEW;
	}

	@GetMapping("/verify")
	public String requireVerification(Authentication authentication) {
		PartnerAdminAccount account = AccountConverter.authenticationToAccount(authentication);
		if (!account.isEnable()) {
			return PathManager.VERIFY_VIEW;
		} else
			return "redirect:/index";

	}

	@PostMapping("/verify")
	public String sendVerification(@RequestParam("email") String email, @RequestParam("token") String token) {
		if (!email.isEmpty() && !token.isEmpty())
			accountService.sendEmailByToken(email, token);

		return PathManager.VERIFY_VIEW;
	}

	@GetMapping("/signin/password/forgot")
	public String viewForgotPassword() {

		return PathManager.FORGOT_PASSWORD_VIEW;
	}

	@PostMapping("/signin/password/forgot")
	public String postForgotPassword(Model model, @RequestParam("email") String email) {
		boolean success = false;
		String message = "Failed! This email does not exist.";
		PartnerAdminAccount account = accountService.getAccountByEmail(email);
		if (account != null) {
			if (MailManager.sendEmailByTokenAndPassword(email, account.getPartnerToken().getToken(),
					account.getPassword())) {
				success = true;
				message = "Successfully! Please check your email.";
			}
		}
		model.addAttribute("success", success);
		model.addAttribute("message", message);
		return PathManager.FORGOT_PASSWORD_VIEW;
	}

}
