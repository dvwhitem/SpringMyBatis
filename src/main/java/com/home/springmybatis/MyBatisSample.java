package com.home.springmybatis;

import com.home.springmybatis.domain.Contact;
import com.home.springmybatis.service.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

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

        contacts = contactService.findAll();
        listContacts(contacts);
    }

    private static void listContacts(List<Contact> contacts) {
        System.out.println();
        System.out.println("Listining contacts without details: ");
        for (Contact contact: contacts) {
            System.out.println(contact);
            System.out.println();
        }
    }
}
