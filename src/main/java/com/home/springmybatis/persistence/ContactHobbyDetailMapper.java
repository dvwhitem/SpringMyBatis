package com.home.springmybatis.persistence;

import com.home.springmybatis.domain.ContactHobbyDetail;

/**
 * Created by vitaliy on 27.02.15.
 */
public interface ContactHobbyDetailMapper {
    public void insertContactHobbyDetail(ContactHobbyDetail contactHobbyDetail);
    public void deleteHobbyDetailForContact(Long contactId);
}
