package com.minhthanh.bulk.admin.service;

import com.minhthanh.bulk.jpa.entities.BlacklistNumber;
import com.minhthanh.bulk.jpa.repositories.BlacklistNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by youngkaka on 24/10/2016.
 */
@Service
public class BlacklistNumberService {

    @Autowired
    private BlacklistNumberRepository numberRepository;


    public List<BlacklistNumber> findAllByPartnerId(int accountId) {
        return numberRepository.findAllByPartnerId(accountId);
    }

    @Transactional
    public boolean save(BlacklistNumber number) {
        try {
            return numberRepository.save(number) != null;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
}
