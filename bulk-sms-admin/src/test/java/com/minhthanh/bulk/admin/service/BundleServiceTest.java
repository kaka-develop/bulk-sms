package com.minhthanh.bulk.admin.service;

import com.minhthanh.bulk.jpa.entities.*;
import com.minhthanh.bulk.jpa.repositories.BundleExtendRepository;
import com.minhthanh.bulk.jpa.repositories.BundleHistoryRepository;
import com.minhthanh.bulk.jpa.repositories.BundleRepository;
import org.hibernate.TransactionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by phuongtm on 10/4/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class BundleServiceTest {

    @InjectMocks
    private BundleService bundleService;

    @Mock
    private BundleRepository bundleRepository;

    @Mock
    private BundleHistoryRepository bundleHistoryRepository;

    @Mock
    private BundleExtendRepository bundleExtendRepository;

    @Captor
    ArgumentCaptor<Bundle> argumentBundle;

    @Captor
    private ArgumentCaptor<BundleExtend> argumentBundleExtend;

    private Bundle mockBundle;
    private BundleHistory mockBundleHistory;
    private BundleExtend mockBundleExtend;
    private Partner mockPartner;
    private Random random;

    @Before
    public void setUp() throws Exception {

        random = new Random();

        mockBundle = new Bundle();
        mockBundle.setId(1);
        mockBundle.setName("MockBundle");

        mockPartner = new Partner();
        mockPartner.setId(1);
        mockPartner.setName("MockPartner");

        BundleExtendPK bundleExtendPK = new BundleExtendPK();
        bundleExtendPK.setBundleId(1);
        bundleExtendPK.setPartnerId(1);

        mockBundleExtend = new BundleExtend();
        mockBundleExtend.setId(bundleExtendPK);
        mockBundleExtend.setBundle(mockBundle);
        mockBundleExtend.setPartner(mockPartner);

        BundleHistoryPK bundleHistoryPK = new BundleHistoryPK();
        bundleHistoryPK.setBundleId(1);
        bundleHistoryPK.setPartnerId(1);

        mockBundleHistory = new BundleHistory();
        mockBundleHistory.setId(bundleHistoryPK);
        mockBundleHistory.setBundle(mockBundle);
        mockBundleHistory.setPartner(mockPartner);

    }

    @Test
    public void getListBundleShouldNotNull() throws Exception {

        Mockito.when(bundleRepository.findAll())
                .thenReturn(new ArrayList<Bundle>());

        List<Bundle> lstBundle = bundleService.getListBundle();

        Assert.assertNotNull("Failure - not null expected", lstBundle);
    }

    @Test
    public void getListBundleShouldBeEmpty() throws Exception {

        Mockito.when(bundleRepository.findAll())
                .thenReturn(new ArrayList<Bundle>());

        List<Bundle> lstBundle = bundleService.getListBundle();

        Assert.assertTrue(0 == lstBundle.size());
    }

    @Test
    public void getListBundleShouldNotEmpty() throws Exception {

        Bundle mockBundle = new Bundle();

        Mockito.when(bundleRepository.findAll())
                .thenReturn(new ArrayList<Bundle>() {{
                    add(mockBundle);
                }});

        List<Bundle> lstBundle = bundleService.getListBundle();

        Assert.assertTrue(0 != lstBundle.size());
    }

    @Test
    public void findBundleByIdShouldPassedRightArgument() throws Exception {
        long randomId = random.nextLong();
        bundleService.findBundleById(randomId);
        Mockito.verify(bundleRepository).findOne(randomId);
    }

    @Test
    public void findBundleByIdShouldNotNull() throws Exception {

        Mockito.when(bundleRepository.findOne(1L))
                .thenReturn(mockBundle);

        Bundle retBundle = bundleService.findBundleById(1);

        Assert.assertNotNull(retBundle);
    }

    @Test
    public void findBundleByIdShouldBeNull() throws Exception {

        Mockito.when(bundleRepository.findOne(1L))
                .thenReturn(null);

        Bundle retBundle = bundleService.findBundleById(1);

        Assert.assertNull(retBundle);
    }

    @Test
    public void findAllPartnerByBundleIdShouldNotNull() throws Exception {

        Mockito.when(bundleHistoryRepository.findAllPartnerByBundleId(1L))
                .thenReturn(new ArrayList<BundleHistory>() {{
                    add(mockBundleHistory);
                }});

        List<BundleHistory> lstBundleHistory = bundleService.findAllPartnerByBundleId(1);

        Assert.assertNotNull(lstBundleHistory);

    }

    @Test
    public void findAllPartnerByBundleIdShouldPassedRightArgument() throws Exception {
        long randomId = random.nextLong();
        bundleService.findAllPartnerByBundleId(randomId);
        Mockito.verify(bundleHistoryRepository).findAllPartnerByBundleId(randomId);
    }

    @Test
    public void findAllPartnerByBundleIdShouldBeEmpty() throws Exception {

        Mockito.when(bundleHistoryRepository.findAllPartnerByBundleId(1L))
                .thenReturn(new ArrayList<BundleHistory>());

        List<BundleHistory> lstBundleHistory = bundleService.findAllPartnerByBundleId(1);

        Assert.assertTrue(0 == lstBundleHistory.size());

    }

    @Test
    public void findAllPartnerByBundleIdShouldNotEmpty() throws Exception {

        Mockito.when(bundleHistoryRepository.findAllPartnerByBundleId(1L))
                .thenReturn(new ArrayList<BundleHistory>() {{
                    add(mockBundleHistory);
                }});

        List<BundleHistory> lstBundleHistory = bundleService.findAllPartnerByBundleId(1);

        Assert.assertTrue(0 != lstBundleHistory.size());

    }

    @Test
    public void notifyBundleExtendShouldBeDone() throws Exception {

        Mockito.when(bundleExtendRepository.save(Mockito.isA(BundleExtend.class)))
                .thenReturn(mockBundleExtend);

        boolean isSaved = bundleService.notifyBundleExtend(1, 1);

        Assert.assertTrue(isSaved);
    }

    @Test
    public void notifyBundleExtendShouldBeFail() throws Exception {

        Mockito.when(bundleExtendRepository.save(Mockito.isA(BundleExtend.class)))
                .thenReturn(null);

        boolean isSaved = bundleService.notifyBundleExtend(1, 1);

        Assert.assertFalse(isSaved);
    }

    @Test(expected = TransactionException.class)
    public void notifyBundleExtendShouldThrowTransactionException() throws Exception {

        Mockito.doThrow(TransactionException.class)
                .when(bundleExtendRepository)
                .save(Mockito.isA(BundleExtend.class));

        boolean isSaved = bundleService.notifyBundleExtend(1, 1);

        Assert.assertFalse(isSaved);
    }

    @Test
    public void notifyBundleExtendShouldPassRightArgument() throws Exception {
        int randomBundleId = random.nextInt();
        int randomPartnerID = random.nextInt();
        bundleService.notifyBundleExtend(randomBundleId, randomPartnerID);
        Mockito.verify(bundleExtendRepository).save(argumentBundleExtend.capture());
        Assert.assertEquals(randomBundleId, argumentBundleExtend.getValue().getId().getBundleId());
        Assert.assertEquals(randomPartnerID, argumentBundleExtend.getValue().getId().getPartnerId());
    }

    @Test
    public void saveBundleShouldBeDone() throws Exception {

        Mockito.when(bundleRepository.save(mockBundle))
                .thenReturn(mockBundle);

        boolean isSaved = bundleService.saveBundle(mockBundle);

        Assert.assertTrue(isSaved);

    }

    @Test
    public void saveBundleShouldBeFail() throws Exception {

        Mockito.when(bundleRepository.save(mockBundle))
                .thenReturn(null);

        boolean isSaved = bundleService.saveBundle(mockBundle);

        Assert.assertFalse(isSaved);

    }

    @Test
    public void saveBundleShouldPassRightArgument() throws Exception {

        int oldHashCode = mockBundle.hashCode();
        bundleService.saveBundle(mockBundle);
        Mockito.verify(bundleRepository).save(argumentBundle.capture());
        Assert.assertEquals(oldHashCode, argumentBundle.getValue().hashCode());

    }

    @Test(expected = TransactionException.class)
    public void saveBundleShouldThrowTransactionException() throws Exception {

        Mockito.doThrow(TransactionException.class)
                .when(bundleRepository)
                .save(mockBundle);

        boolean isSaved = bundleService.saveBundle(mockBundle);

        Assert.assertFalse(isSaved);

    }

    @Test
    public void deleteBundleShouldBeDone() throws Exception {
        long randomId = random.nextLong();
        bundleService.deleteBundle(randomId);
        Mockito.verify(bundleRepository, times(1)).delete(randomId);
    }

    @Test(expected = TransactionException.class)
    public void deleteBundleShouldThrowTransactionException() throws Exception {

        Mockito.doThrow(TransactionException.class).when(bundleRepository).delete(1L);
        bundleService.deleteBundle(1);

    }

    @Test
    public void deleteBundleShouldPassRightArgument() throws Exception {
        long randomId = random.nextLong();
        bundleService.deleteBundle(randomId);
        Mockito.verify(bundleRepository).delete(randomId);
    }
}
