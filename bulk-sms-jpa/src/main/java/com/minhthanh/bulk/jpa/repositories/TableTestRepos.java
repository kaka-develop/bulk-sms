package com.minhthanh.bulk.jpa.repositories;

import com.minhthanh.bulk.jpa.entities.TableTest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tung on 7/14/2016.
 */
@Repository
public interface TableTestRepos extends PagingAndSortingRepository<TableTest, Long> {
}