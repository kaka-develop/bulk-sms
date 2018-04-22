package com.minhthanh.bulk.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minhthanh.bulk.jpa.entities.GroupNContact;
import com.minhthanh.bulk.jpa.entities.PartnerContact;
import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<PartnerContact, Integer> {
	List<PartnerContact> findAll();

	List<PartnerContact> findByInfo(String info);

	@Query(value = "SELECT * FROM partner_contact WHERE partner_id = ?1", nativeQuery = true)
	List<PartnerContact> findAllByPartnerId(int id);

	@Query(value = "SELECT * FROM partner_contact WHERE id = ?1 AND partner_id = ?2 LIMIT 1", nativeQuery = true)
	PartnerContact findOneByPartnerId(int id, int partnerId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM partner_contact WHERE id = ?1 AND partner_id = ?2", nativeQuery = true)
	int deleteOneByPartnerId(int id, int partnerId);

	@Query(value = "SELECT * FROM partner_group_contact WHERE id IN (SELECT partner_group_contact_id FROM group_n_contact WHERE partner_contact_id = ?1) LIMIT 1;", nativeQuery = true)
	PartnerGroupContact findGroupByContactId(int id);

	@Query(value = "SELECT * FROM partner_contact WHERE id IN (SELECT partner_contact_id FROM group_n_contact WHERE partner_group_contact_id = ?1);", nativeQuery = true)
	List<PartnerContact> findAllContactsByGroupId(int i);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM partner_contact WHERE id = ?1", nativeQuery = true)
	int delete(int id);
	
	@Query(value="SELECT * FROM group_n_contact WHERE partner_contact_id = ?1 AND partner_group_contact_id = ?2 LIMIT 1;", nativeQuery = true)
	Object groupNContactExists(int cId, int gId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO group_n_contact(partner_contact_id,partner_group_contact_id,created_date) VALUES (?1,?2,NOW());", nativeQuery = true)
	int addContactIntoGroup(int cId, int gId);

}
