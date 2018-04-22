package com.minhthanh.bulk.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minhthanh.bulk.jpa.entities.PartnerAdminAccount;

@Repository
public interface AccountRepository extends JpaRepository<PartnerAdminAccount, Integer> {
	PartnerAdminAccount findFirstByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "UPDATE partner_admin_account SET enable=true, partner_admin_role_id = 1 WHERE partner_token_id IN (SELECT id FROM partner_token WHERE token = ?1);", nativeQuery = true)
	int verify(String token);

	@Query(value = "SELECT * FROM partner_admin_account WHERE id = ?1 AND password = ?2 LIMIT 1;", nativeQuery = true)
	PartnerAdminAccount checkOldPassword(int id, String oldPassword);

	@Query(value = "SELECT * FROM partner_admin_account WHERE partner_token_id IN (SELECT id FROM partner_token WHERE token = ?1) LIMIT 1;", nativeQuery = true)
	PartnerAdminAccount FindAccountByToken(String token);

}
