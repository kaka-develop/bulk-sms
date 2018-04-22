package com.minhthanh.bulk.jpa.respositories;

import com.google.common.collect.ImmutableList;
import com.minhthanh.bulk.admin.config.ApplicationConfig;
import com.minhthanh.bulk.jpa.entities.BundleHistory;
import com.minhthanh.bulk.jpa.entities.BundleHistoryPK;
import com.minhthanh.bulk.jpa.repositories.BundleHistoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by phuongtm on 10/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional
// ensure that there is always at least one record in resources/import.sql in order to run the test
public class BundleHistoryRepositoryTest {

    @Autowired
    private BundleHistoryRepository bundleHistoryRepository;

    @Test
    public void findAllPartnerByBundleIdShouldReturnData() throws Exception {
        List<BundleHistory> lstBundleHistory = bundleHistoryRepository.findAllPartnerByBundleId(1L);
        Assert.assertTrue(lstBundleHistory.size() > 0);
    }

    @Test
    public void saveShouldReturnData() throws Exception {
        BundleHistoryPK bundleHistoryPk = new BundleHistoryPK();
        bundleHistoryPk.setBundleId(1);
        bundleHistoryPk.setPartnerId(2);
        BundleHistory bundleHistory = new BundleHistory();
        bundleHistory.setId(bundleHistoryPk);
        BundleHistory retBundleHistory = bundleHistoryRepository.save(bundleHistory);
        Assert.assertNotNull(retBundleHistory);
    }

    @Test
    public void findOneShouldReturnData() throws Exception {
        BundleHistoryPK bundleHistoryPk = new BundleHistoryPK();
        bundleHistoryPk.setBundleId(1);
        bundleHistoryPk.setPartnerId(2);
        BundleHistory retBundleHistory = bundleHistoryRepository.findOne(bundleHistoryPk);
        Assert.assertNotNull(retBundleHistory);
    }

    @Test
    public void findAllShouldReturnData() throws Exception {
        List<BundleHistory> lstBundleHistory = ImmutableList.copyOf(bundleHistoryRepository.findAll());
        Assert.assertTrue(lstBundleHistory.size() > 0 );
    }

    @Test
    public void countShouldReturnData() throws Exception {
        Assert.assertTrue(bundleHistoryRepository.count() > 0);
    }

//    @Test
//    public void deleteShouldBeExecuted() throws Exception {
//        BundleHistoryPK bundleHistoryPk = new BundleHistoryPK();
//        bundleHistoryPk.setBundleId(1);
//        bundleHistoryPk.setPartnerId(2);
//        bundleHistoryRepository.delete(bundleHistoryPk);
//        BundleHistory retBundleHistory = bundleHistoryRepository.findOne(bundleHistoryPk);
//        Assert.assertNull(retBundleHistory);
//    }

//    @Test
//    public void deleteAllShouldBeExecuted() throws Exception {
//        bundleHistoryRepository.deleteAll();
//        Assert.assertTrue(bundleHistoryRepository.count() == 0);
//    }
}
