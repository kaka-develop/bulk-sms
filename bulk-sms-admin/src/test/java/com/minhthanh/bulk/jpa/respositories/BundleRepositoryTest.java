package com.minhthanh.bulk.jpa.respositories;

import com.minhthanh.bulk.admin.config.ApplicationConfig;
import com.minhthanh.bulk.jpa.entities.Bundle;
import com.minhthanh.bulk.jpa.repositories.BundleRepository;
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
public class BundleRepositoryTest {

    @Autowired
    private BundleRepository bundleRepository;

    @Test
    public void findAllShouldReturnData() throws Exception {
        List<Bundle> lstBundle = bundleRepository.findAll();
        System.out.println(lstBundle.size());
        Assert.assertTrue(lstBundle.size() > 0);
    }

    @Test
    public void saveShouldInsertData() throws Exception {
        Bundle bundle = bundleRepository.save(new Bundle());
        Assert.assertTrue(bundle != null);
    }

    @Test
    public void findOneShouldReturnData() throws Exception {
        Bundle bundle = bundleRepository.findOne(1L);
        Assert.assertTrue(bundle != null);
    }

    @Test
    public void existsShouldReturnData() throws Exception {
        Assert.assertTrue(bundleRepository.exists(1L));
    }

    @Test
    public void countShouldReturnData() throws Exception {
        Assert.assertTrue(bundleRepository.count() > 0);
    }

//    @Test
//    public void deleteAllShouldBeExecuted() throws Exception {
//        bundleRepository.deleteAll();
//        List<Bundle> lstBundle = bundleRepository.findAll();
//        Assert.assertTrue(lstBundle.size() == 0);
//    }

//    @Test
//    public void deleteShouldBeExecuted() throws Exception {
//        bundleRepository.delete(1L);
//        List<Bundle> lstBundle = bundleRepository.findAll();
//        Assert.assertTrue(lstBundle.size() == 1);
//    }


}
