package com.minhthanh.bulk.jpa.respositories;

import com.minhthanh.bulk.admin.config.ApplicationConfig;
import com.minhthanh.bulk.jpa.entities.BlacklistNumber;
import com.minhthanh.bulk.jpa.repositories.BlacklistNumberRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

/**
 * Created by youngkaka on 24/10/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class BlacklistNumberRepositoryTest {

    @Autowired
    private BlacklistNumberRepository numberRepository;
    private BlacklistNumber number;

    @Before
    public void before() {
        number = new BlacklistNumber("0123456789");
        number = numberRepository.save(number);
    }

    @Test
    public void testFindAll() {
        boolean expected = true;
        boolean result = numberRepository.findAll().size() > 0;
        assertEquals(expected,result);
    }

    @Test
    public void testFindAllByPartnerId() {
        boolean expected = true;
        boolean result = numberRepository.findAllByPartnerId(1).size() > 0;
        assertEquals(expected,result);
    }



    @After
    public void after() {
        numberRepository.delete(number);
    }

}
