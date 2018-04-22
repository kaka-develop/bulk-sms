package com.minhthanh.bulk.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;

@Repository
public interface GroupContactRepository extends PagingAndSortingRepository<PartnerGroupContact, Integer> {
	List<PartnerGroupContact> findAll();

	List<PartnerGroupContact> findByName(String name);

	@Query(value = "SELECT * FROM partner_group_contact WHERE partner_id = ?1", nativeQuery = true)
	List<PartnerGroupContact> findAllByPartnerId(int id);

	@Query(value = "SELECT * FROM partner_group_contact WHERE id = ?1 AND partner_id = ?2 LIMIT 1", nativeQuery = true)
	PartnerGroupContact findOneByPartnerId(int id, int partnerId);

	@Query(value = "SELECT delPartnerGroupContact(?1,?2);", nativeQuery = true)
	boolean deleteOneByPartnerId(int id, int partnerId);

	@Query(value="SELECT * FROM partner_group_contact WHERE id IN (SELECT partner_group_contact_id FROM group_n_contact WHERE partner_contact_id = ?1);", nativeQuery = true)
	List<PartnerGroupContact> findGroupsByContactId(int id);

	@Transactional
	@Modifying
	@Query(value="DELETE FROM partner_group_contact WHERE id = ?1", nativeQuery=true)
	int delete(int id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM group_n_contact WHERE partner_contact_id = ?1 AND partner_group_contact_id = ?2", nativeQuery=true)
	int deleteGroupOfContact(int cId, int gId);
	
}
