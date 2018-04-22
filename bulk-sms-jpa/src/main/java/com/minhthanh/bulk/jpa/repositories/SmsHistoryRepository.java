package com.minhthanh.bulk.jpa.repositories;

import com.minhthanh.bulk.jpa.entities.Bundle;
import com.minhthanh.bulk.jpa.entities.SmsHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by phuongtm on 9/26/16.
 */
@Repository
public interface SmsHistoryRepository extends PagingAndSortingRepository<SmsHistory, Long> {

    List<SmsHistory> findAll();

}
