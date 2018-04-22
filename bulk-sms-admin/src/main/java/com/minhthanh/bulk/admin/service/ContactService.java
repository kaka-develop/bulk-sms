package com.minhthanh.bulk.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhthanh.bulk.jpa.entities.PartnerContact;
import com.minhthanh.bulk.jpa.repositories.ContactRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public List<PartnerContact> getAll() {
		return contactRepository.findAll();
	}

	public List<PartnerContact> findAllByPartnerId(int id) {
		// TODO Auto-generated method stub
		return contactRepository.findAllByPartnerId(id);
	}

	public PartnerContact findOneByPartnerId(int id, int partnerId) {
		// TODO Auto-generated method stub
		return contactRepository.findOneByPartnerId(id, partnerId);
	}

	public PartnerContact findOne(int id) {
		// TODO Auto-generated method stub
		return contactRepository.findOne(id);
	}

	@Transactional
	public boolean save(PartnerContact contact) {
		// TODO Auto-generated method stub
		try {
			return contactRepository.save(contact) != null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public PartnerContact newContact(PartnerContact contact) {
		// TODO Auto-generated method stub
		return contactRepository.save(contact);
	}

	@Transactional
	public boolean deleteOneByPartnerId(int id, int partnerId) {
		// TODO Auto-generated method stub
		try {
			return contactRepository.deleteOneByPartnerId(id, partnerId) > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean deleteContact(int id) {
		// TODO Auto-generated method stub
		try {
			return contactRepository.delete(id) > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public List<PartnerContact> findAllByGroupId(int id) {
		// TODO Auto-generated method stub
		return contactRepository.findAllContactsByGroupId(id);
	}

	@Transactional
	public boolean addContactIntoGroup(int cId, int gId) {
		// TODO Auto-generated method stub
		if (contactRepository.groupNContactExists(cId, gId) != null)
			return false;
		else
			return contactRepository.addContactIntoGroup(cId, gId) > 0;
		
	}

}
