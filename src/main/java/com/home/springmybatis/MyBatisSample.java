package com.home.springmybatis;

import com.home.springmybatis.domain.Contact;
import com.home.springmybatis.domain.ContactTelDetail;
import com.home.springmybatis.domain.Hobby;
import com.home.springmybatis.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaliy on 22.02.15.
 */
public class MyBatisSample {

    public static void main(String[] args) {

        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:app-context.xml");
        context.refresh();

        ContactService contactService = context.getBean("contactService", ContactService.class);

        List<Contact> contacts;

        // all contact
        contacts = contactService.findAll();
        listContacts(contacts);

        // contact with detail
        contacts = contactService.findAllWithDetail();
        listContactsWithDetail(contacts);

        // find contact by id
        contacts = new ArrayList<Contact>();
        System.out.println("Finding contact with id 1");
        Contact contact = contactService.findById(1l);
        contacts.add(contact);
        listContactsWithDetail(contacts);

        // find by first name and last name
        contacts = contactService.findByFirstNameAndLastName(null, "Ho");
        listContactsWithDetail(contacts);
    }

    private static void listContacts(List<Contact> contacts) {
        System.out.println();
        System.out.println("Listining contacts without details: ");
        for (Contact contact: contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }

    private static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println();
        System.out.println("Listinig contacts with details:");
        for(Contact contact: contacts) {
            System.out.println(contact);
            if(contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }
            if(contact.getHobbies() != null) {
                for(Hobby hobby: contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }
            System.out.println();
        }
    }
}
