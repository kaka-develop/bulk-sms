package com.minhthanh.bulk.admin.service;

import com.minhthanh.bulk.jpa.entities.Bundle;
import com.minhthanh.bulk.jpa.entities.BundleExtend;
import com.minhthanh.bulk.jpa.entities.BundleExtendPK;
import com.minhthanh.bulk.jpa.entities.BundleHistory;
import com.minhthanh.bulk.jpa.enums.ExtendStatus;
import com.minhthanh.bulk.jpa.repositories.BundleExtendRepository;
import com.minhthanh.bulk.jpa.repositories.BundleHistoryRepository;
import com.minhthanh.bulk.jpa.repositories.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by phuongtm on 9/30/16.
 */
@Service
public class BundleService {

    @Autowired
    private BundleRepository bundleRepository;

    @Autowired
    private BundleHistoryRepository bundleHistoryRepository;

    @Autowired
    private BundleExtendRepository bundleExtendRepository;

    public List<Bundle> getListBundle() {
        return bundleRepository.findAll();
    }

    public Bundle findBundleById(long id) {
        return bundleRepository.findOne(id);
    }

    public List<BundleHistory> findAllPartnerByBundleId(long id) {
        return bundleHistoryRepository.findAllPartnerByBundleId(id);
    }

    @Transactional
    public boolean notifyBundleExtend(int bundleId, int partnerId) {

        BundleExtend bundleExtend = new BundleExtend();

        BundleExtendPK bundleExtendPK = new BundleExtendPK();
        bundleExtendPK.setBundleId(bundleId);
        bundleExtendPK.setPartnerId(partnerId);

        bundleExtend.setId(bundleExtendPK);
        bundleExtend.setState(ExtendStatus.RENEWAL_WAITING);
        bundleExtend.setUpdatedDate(new Date());

        return bundleExtendRepository.save(bundleExtend) != null;
    }

    @Transactional
    public boolean saveBundle(Bundle bundle) {
        return bundleRepository.save(bundle) != null;
    }

    @Transactional
    public void deleteBundle(long id) {
        bundleRepository.delete(id);
    }
}
