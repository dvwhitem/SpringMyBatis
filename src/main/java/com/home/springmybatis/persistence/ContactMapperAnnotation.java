package com.home.springmybatis.persistence;

import com.home.springmybatis.domain.Contact;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by vitaliy on 25.02.15.
 */
public interface ContactMapperAnnotation {

    @Select(value = "SELECT CONTACT_ID, FIRST_NAME, LAST_NAME, BIRTH_DATE FROM CONTACT")
    @Results(value = {
            @Result(javaType=Contact.class),
            @Result(property = "id", column = "CONTACT_ID"),
            @Result(property = "firstName", column = "FIRST_NAME"),
            @Result(property = "lastName", column = "LAST_NAME"),
            @Result(property = "birthDate", column = "BIRTH_DATE")
    })
    List<Contact> findAll();
}
