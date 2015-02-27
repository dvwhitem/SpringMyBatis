package com.home.springmybatis.persistence;

import com.home.springmybatis.domain.Contact;
import com.home.springmybatis.domain.SearchCriteria;

import java.util.List;

/**
 * Created by vitaliy on 22.02.15.
 */
public interface ContactMapper {
    public List<Contact> findAll();
    public List<Contact> findAllWithDetail();
    public Contact findById(Long id);
    public List<Contact> findByFirstNameAndLastName(SearchCriteria searchCriteria);
    public void insertContact(Contact contact);
    public void updateContact(Contact contact);
    public void deleteContact(Long id);
}
