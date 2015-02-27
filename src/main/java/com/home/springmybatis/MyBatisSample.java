package com.home.springmybatis;

import com.home.springmybatis.domain.Contact;
import com.home.springmybatis.domain.ContactTelDetail;
import com.home.springmybatis.domain.Hobby;
import com.home.springmybatis.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.*;

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

        // add new contact
        System.out.println("Add new contact");
        contact = new Contact();
        contact.setFirstName("Michael");
        contact.setLastName("Jackson");
        contact.setBirthDate(new Date());
        Set<ContactTelDetail> contactTelDetailSet =
                new HashSet<ContactTelDetail>();
        ContactTelDetail contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("Home");
        contactTelDetail.setTelNumber("5554455");
        contactTelDetailSet.add(contactTelDetail);
        contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("Mobile");
        contactTelDetail.setTelNumber("664466");
        contactTelDetailSet.add(contactTelDetail);
        contact.setContactTelDetails(contactTelDetailSet);
        Set<Hobby> hobbies = new HashSet<Hobby>();
        Hobby hobby = new Hobby();
        hobby.setHobbyId("Jogging");
        hobbies.add(hobby);
        contact.setHobbies(hobbies);
        contactService.save(contact);
        contacts = contactService.findAllWithDetail();
        listContactsWithDetail(contacts);

        // update contact
        System.out.println("Update contact with id 13");
        contact = contactService.findById(13l);
        contact.setFirstName("Soung Yang");
        Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
        ContactTelDetail toDeleteContactTel = null;
        for(ContactTelDetail telDetail: contactTels) {
            toDeleteContactTel = telDetail;
        }
        contactTels.remove(toDeleteContactTel);
        hobby.setHobbyId("Swimming");
        contact.getHobbies().add(hobby);
        contactService.save(contact);
        contacts = contactService.findAllWithDetail();
        listContactsWithDetail(contacts);

        // delete contact
        System.out.println("Delete contact with id 13");
        contact =contactService.findById(13l);
        contactService.delete(contact);
        contacts = contactService.findAllWithDetail();
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
