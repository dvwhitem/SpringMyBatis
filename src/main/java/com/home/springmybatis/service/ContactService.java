package com.home.springmybatis.service;

import com.home.springmybatis.domain.Contact;

import java.util.List;

/**
 * Created by vitaliy on 21.02.15.
 */
public interface ContactService {

    public List<Contact> findAll();

    public List<Contact> findAllWithDetail();

    public Contact findById(Long id);

    public Contact save(Contact contact);

    public void delete(Contact contact);

    public List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
