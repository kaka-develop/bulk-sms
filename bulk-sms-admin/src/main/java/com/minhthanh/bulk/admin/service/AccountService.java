package com.minhthanh.bulk.admin.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhthanh.bulk.admin.manager.MailManager;
import com.minhthanh.bulk.admin.model.CustomUser;
import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;
import com.minhthanh.bulk.jpa.entities.PartnerToken;
import com.minhthanh.bulk.jpa.repositories.AccountRepository;
import com.minhthanh.bulk.jpa.repositories.PartnerTokenRepository;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PartnerTokenRepository tokenRepository;
	
	@Override
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
		PartnerAdminAccount account = getAccountByEmail(username);
		if (account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	private CustomUser createUser( PartnerAdminAccount account) {
		final CustomUser user = new CustomUser(account, Collections.singleton(createAuthority(account)));
		return user;
	}

	private GrantedAuthority createAuthority(PartnerAdminAccount account) {
		return new SimpleGrantedAuthority(account.getPartnerAdminRole().getDescription());
	}

	public PartnerAdminAccount getAccountByEmail(String email) {
		return accountRepository.findFirstByEmail(email);
	}
	
	public void signin(PartnerAdminAccount account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	@Transactional
	public Authentication authenticate(PartnerAdminAccount account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null,
				Collections.singleton(createAuthority(account)));
	}

	public PartnerAdminAccount save(PartnerAdminAccount account) {
		// TODO Auto-generated method stub
		try {
			return accountRepository.save(account);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return null;
	}

	public boolean sendEmailByToken(String email,String token) {
		// TODO Auto-generated method stub
		return MailManager.sendEmailByToken(email, token);
	}

	public PartnerAdminAccount singup(PartnerAdminAccount createAccount) {
		// TODO Auto-generated method stub
		PartnerToken token = new PartnerToken();
		token = tokenRepository.save(token);
		createAccount.setPartnerToken(token);
		PartnerAdminAccount account = save(createAccount);
		sendEmailByToken(account.getEmail(),account.getPartnerToken().getToken());
		signin(account);
		return account;
	}

	public boolean verify(String token) {
		// TODO Auto-generated method stub
		return accountRepository.verify(token) > 0;
	}

	public boolean updateProfile(PartnerAdminAccount newAccount) {
		// TODO Auto-generated method stub
		return accountRepository.save(newAccount) != null;
	}

	public boolean checkOldPassword(int id, String oldPassword) {
		// TODO Auto-generated method stub
		
		return accountRepository.checkOldPassword(id,oldPassword)!= null;
	}

	public boolean checkEmailExists(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findFirstByEmail(email) != null;
	}

	public PartnerAdminAccount getAccountByToken(String token) {
		// TODO Auto-generated method stub
		return accountRepository.FindAccountByToken(token);
	}


}

