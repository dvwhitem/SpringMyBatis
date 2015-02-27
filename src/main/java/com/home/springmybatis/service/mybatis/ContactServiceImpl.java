package com.home.springmybatis.service.mybatis;

import com.home.springmybatis.domain.*;
import com.home.springmybatis.persistence.ContactHobbyDetailMapper;
import com.home.springmybatis.persistence.ContactMapper;
import com.home.springmybatis.persistence.ContactTelDetailMapper;
import com.home.springmybatis.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy on 22.02.15.
 */
@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private Log log = LogFactory.getLog(ContactService.class);

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ContactTelDetailMapper contactTelDetailMapper;

    @Autowired
    private ContactHobbyDetailMapper contactHobbyDetailMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        List<Contact> contacts = contactMapper.findAll();
        return contacts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        List<Contact> contacts = contactMapper.findAllWithDetail();
        for(Contact contact: contacts) {
            populateContactTelDetail(contact);
        }
        return contacts;
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        Contact contact = contactMapper.findById(id);
        populateContactTelDetail(contact);
        return contact;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findByFirstNameAndLastName(String firstName, String lastName) {
        log.info("Finding contact with first name: " + firstName + " last name: " + lastName);
//        Contact contact = new Contact();
//        contact.setFirstName(firstName);
//        contact.setLastName(lastName);
        SearchCriteria criteria = new SearchCriteria();
        criteria.setFirstName(firstName);
        criteria.setLastName(lastName);

        List<Contact> contacts = contactMapper.findByFirstNameAndLastName(criteria);
        for(Contact contactTemp: contacts) {
            populateContactTelDetail(contactTemp);
        }
        return contacts;
    }

    public Contact save(Contact contact) {
        if(contact.getId() == null) {
            insert(contact);
        } else {
            update(contact);
        }
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Long contactId = contact.getId();
        contactTelDetailMapper.deleteTelDetailForContact(contactId);
        contactHobbyDetailMapper.deleteHobbyDetailForContact(contactId);
        contactMapper.deleteContact(contactId);
    }

    private void populateContactTelDetail(Contact contact) {
        if(contact.getContactTelDetails() != null) {
            for(ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
                contactTelDetail.setContact(contact);
            }
        }
    }

    private Contact insert(Contact contact) {
        contactMapper.insertContact(contact);
        Long id = contact.getId();
        log.info("CONTACT ID: " + id);
        ContactHobbyDetail contactHobbyDetail;
        if(contact.getContactTelDetails() != null) {
            for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
                contactTelDetail.setContact(contact);
                contactTelDetailMapper.insertContactTelDetail(contactTelDetail);
            }
        }
        if(contact.getHobbies() != null) {
            for(Hobby hobby: contact.getHobbies()) {
                contactHobbyDetail = new ContactHobbyDetail();
                contactHobbyDetail.setContactId(id);
                contactHobbyDetail.setHobbyId(hobby.getHobbyId());
                contactHobbyDetailMapper.insertContactHobbyDetail(contactHobbyDetail);
            }
        }
        return contact;
    }

    private Contact update(Contact contact) {
        contactMapper.updateContact(contact);
        Long contactId = contact.getId();
        log.info("CONTACT ID: " + contactId);
        ContactHobbyDetail contactHobbyDetail;

        List<Long> ids = new ArrayList<Long>();

        List<ContactTelDetail> oldContactTelDetails =
                contactTelDetailMapper.selectTelDetailForContact(contactId);

        for(ContactTelDetail contactTelDetail: oldContactTelDetails) {
            ids.add(contactTelDetail.getId());
        }

        if(contact.getContactTelDetails() != null) {
            for(ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
                if(contactTelDetail.getId() == null) {
                    contactTelDetailMapper.insertContactTelDetail(contactTelDetail);
                } else {
                    contactTelDetailMapper.updateContactTelDetail(contactTelDetail);
                    ids.remove(contactTelDetail.getId());
                }
            }
            if(ids.size() > 0) {
                contactTelDetailMapper.deleteOrphanContactTelDetail(ids);
            }
        }

        contactHobbyDetailMapper.deleteHobbyDetailForContact(contact.getId());
        if(contact.getHobbies() != null) {
            for(Hobby hobby: contact.getHobbies()) {
                contactHobbyDetail = new ContactHobbyDetail();
                contactHobbyDetail.setContactId(contactId);
                contactHobbyDetail.setHobbyId(hobby.getHobbyId());
                contactHobbyDetailMapper.insertContactHobbyDetail(contactHobbyDetail);
            }
        }
        return contact;
    }
}
