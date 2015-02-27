package com.home.springmybatis.domain;

/**
 * Created by vitaliy on 27.02.15.
 */
public class ContactHobbyDetail {
    private Long ContactId;
    private String hobbyId;

    public Long getContactId() {
        return ContactId;
    }

    public void setContactId(Long contactId) {
        this.ContactId = contactId;
    }

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @Override
    public String toString() {
        return "ContactHobbyDetail{" +
                "ContactId=" + ContactId +
                ", hobbyId='" + hobbyId + '\'' +
                '}';
    }
}
