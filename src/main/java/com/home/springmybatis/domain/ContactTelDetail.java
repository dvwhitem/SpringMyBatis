package com.home.springmybatis.domain;

import java.io.Serializable;

/**
 * Created by vitaliy on 25.02.15.
 */
public class ContactTelDetail implements Serializable {

    private Long id;
    private Contact contact;
    private String telType;
    private String telNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "ContactTelDetail{" +
                "id=" + id +
                ", telType='" + telType + '\'' +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }
}
