package com.home.springmybatis.service.mybatis;

import com.home.springmybatis.domain.Contact;
import com.home.springmybatis.domain.ContactTelDetail;
import com.home.springmybatis.persistence.ContactMapper;
import com.home.springmybatis.service.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Contact findById(Long id) {
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public void delete(Contact contact) {

    }

    @Override
    public List<Contact> findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }

    private void populateContactTelDetail(Contact contact) {
        if(contact.getContactTelDetails() != null) {
            for(ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
                contactTelDetail.setContact(contact);
            }
        }
    }
}
