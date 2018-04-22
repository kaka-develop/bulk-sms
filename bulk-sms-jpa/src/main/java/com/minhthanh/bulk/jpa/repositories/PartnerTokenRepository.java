package com.minhthanh.bulk.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhthanh.bulk.jpa.entities.PartnerToken;

@Repository
public interface PartnerTokenRepository  extends JpaRepository<PartnerToken, Integer>{
	
}
