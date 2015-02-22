package com.home.springmybatis.persistence;

import com.home.springmybatis.domain.Contact;

import java.util.List;

/**
 * Created by vitaliy on 22.02.15.
 */
public interface ContactMapper {
    public List<Contact> findAll();
}
