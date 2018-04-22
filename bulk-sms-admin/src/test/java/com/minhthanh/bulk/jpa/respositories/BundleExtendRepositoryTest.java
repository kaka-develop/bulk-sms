package com.minhthanh.bulk.jpa.respositories;

import com.google.common.collect.ImmutableList;
import com.minhthanh.bulk.admin.config.ApplicationConfig;
import com.minhthanh.bulk.jpa.entities.BundleExtend;
import com.minhthanh.bulk.jpa.entities.BundleExtendPK;
import com.minhthanh.bulk.jpa.enums.ExtendStatus;
import com.minhthanh.bulk.jpa.repositories.BundleExtendRepository;
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
public class BundleExtendRepositoryTest {

    @Autowired
    private BundleExtendRepository bundleExtendRepository;

    @Test
    public void saveShouldInsertData() throws Exception {
        BundleExtendPK bundleExtendPK = new BundleExtendPK();
        bundleExtendPK.setBundleId(1);
        bundleExtendPK.setPartnerId(2);
        BundleExtend bundleExtend = new BundleExtend();
        bundleExtend.setId(bundleExtendPK);
        BundleExtend retBundleExtend = bundleExtendRepository.save(bundleExtend);
        Assert.assertNotNull(retBundleExtend);
    }

    @Test
    public void saveShouldUpdateStateCorrectly() throws Exception {
        BundleExtendPK bundleExtendPK = new BundleExtendPK();
        bundleExtendPK.setBundleId(1);
        bundleExtendPK.setPartnerId(2);
        BundleExtend bundleExtend = new BundleExtend();
        bundleExtend.setId(bundleExtendPK);
        bundleExtend.setState(ExtendStatus.EXPIRED);
        BundleExtend retBundleExtend = bundleExtendRepository.save(bundleExtend);
        Assert.assertEquals(retBundleExtend.getState(), bundleExtend.getState());
    }

    @Test
    public void findOneShouldReturnData() throws Exception {
        BundleExtendPK bundleExtendPK = new BundleExtendPK();
        bundleExtendPK.setBundleId(1);
        bundleExtendPK.setPartnerId(2);
        BundleExtend bundleExtend = new BundleExtend();
        bundleExtend.setId(bundleExtendPK);
        BundleExtend retBundleExtend = bundleExtendRepository.findOne(bundleExtendPK);
        Assert.assertNotNull(retBundleExtend);
    }

    @Test
    public void existsShouldReturnData() throws Exception {
        BundleExtendPK bundleExtendPK = new BundleExtendPK();
        bundleExtendPK.setBundleId(1);
        bundleExtendPK.setPartnerId(2);
        BundleExtend bundleExtend = new BundleExtend();
        bundleExtend.setId(bundleExtendPK);
        boolean isExists = bundleExtendRepository.exists(bundleExtendPK);
        Assert.assertTrue(isExists);
    }

    @Test
    public void findAllShouldReturnData() throws Exception {
        List<BundleExtend> lstBundleExtend = ImmutableList.copyOf(bundleExtendRepository.findAll());
        Assert.assertTrue(lstBundleExtend.size() > 0);
    }

    @Test
    public void countShouldReturnData() throws Exception {
        Assert.assertTrue(bundleExtendRepository.count() > 0);
    }

//    @Test
//    public void deleteAllShouldBeExecuted() throws Exception {
//        bundleExtendRepository.deleteAll();
//        Assert.assertTrue(bundleExtendRepository.count() == 0);
//    }

//    @Test
//    public void deleteShouldBeExecuted() throws Exception {
//        BundleExtendPK bundleExtendPK = new BundleExtendPK();
//        bundleExtendPK.setBundleId(1);
//        bundleExtendPK.setPartnerId(2);
//        BundleExtend bundleExtend = new BundleExtend();
//        bundleExtend.setId(bundleExtendPK);
//        bundleExtendRepository.delete(bundleExtendPK);
//        boolean isExists = bundleExtendRepository.exists(bundleExtendPK);
//        Assert.assertFalse(isExists);
//    }
}
