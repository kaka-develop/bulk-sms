package com.minhthanh.bulk.jpa.repositories;

import com.minhthanh.bulk.jpa.entities.BlacklistNumber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by youngkaka on 10/14/16.
 */
public interface BlacklistNumberRepository extends CrudRepository<BlacklistNumber,Integer> {

    @Query(value = "SELECT * FROM blacklist_numbers WHERE partner_id = ?1", nativeQuery = true)
    List<BlacklistNumber> findAllByPartnerId(int id);

    List<BlacklistNumber> findAll();
}
