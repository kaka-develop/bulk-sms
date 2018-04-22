package com.minhthanh.bulk.jpa.repositories;

import com.minhthanh.bulk.jpa.entities.Bundle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by phuongtm on 9/26/16.
 */
@Repository
public interface BundleRepository extends PagingAndSortingRepository<Bundle, Long> {
    List<Bundle> findAll();

    @Override
    void delete(Long aLong);
}
