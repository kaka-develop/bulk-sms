package com.minhthanh.bulk.jpa.repositories;

import com.minhthanh.bulk.jpa.entities.BundleHistory;
import com.minhthanh.bulk.jpa.entities.BundleHistoryPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by phuongtm on 9/28/16.
 */
@Repository
public interface BundleHistoryRepository extends PagingAndSortingRepository<BundleHistory, BundleHistoryPK> {

    @Query("select h from BundleHistory h where h.bundle.id = :id ")
    List<BundleHistory> findAllPartnerByBundleId(@Param("id") long id);

}
