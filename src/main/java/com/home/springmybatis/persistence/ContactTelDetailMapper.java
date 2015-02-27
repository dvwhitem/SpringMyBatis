package com.home.springmybatis.persistence;

import com.home.springmybatis.domain.ContactTelDetail;

import java.util.List;

/**
 * Created by vitaliy on 27.02.15.
 */
public interface ContactTelDetailMapper {
    public void insertContactTelDetail(ContactTelDetail contactTelDetail);
    public List<ContactTelDetail> selectTelDetailForContact(Long contactId);
    public void updateContactTelDetail(ContactTelDetail contactTelDetail);
    public void deleteOrphanContactTelDetail(List<Long> ids);
    public void deleteTelDetailForContact(Long contactId);
}
