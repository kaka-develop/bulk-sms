package com.minhthanh.bulk.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhthanh.bulk.jpa.entities.PartnerGroupContact;
import com.minhthanh.bulk.jpa.repositories.GroupContactRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupContactService {

    @Autowired
    private GroupContactRepository groupContactRepository;

    public List<PartnerGroupContact> findAllByPartnerId(int id) {
        // TODO Auto-generated method stub
        return groupContactRepository.findAllByPartnerId(id);
    }

    public boolean save(PartnerGroupContact group) {
        // TODO Auto-generated method stub
        try {
            return groupContactRepository.save(group) != null;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }


    public PartnerGroupContact findOne(int id) {
        // TODO Auto-generated method stub
        return groupContactRepository.findOne(id);
    }

    public List<PartnerGroupContact> findGroupsByContactId(int id) {
        // TODO Auto-generated method stub
        return groupContactRepository.findGroupsByContactId(id);
    }

    @Transactional
    public boolean deleteGroupOfContact(int cId, int gId) {
        // TODO Auto-generated method stub
        return groupContactRepository.deleteGroupOfContact(cId, gId) > 0;
    }

    @Transactional
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return groupContactRepository.delete(id) > 0;
    }

    @Transactional
    public PartnerGroupContact newGroup(PartnerGroupContact groupContact) {
        // TODO Auto-generated method stub
        return groupContactRepository.save(groupContact);
    }


}
