package com.example.demo.service;

import com.example.demo.models.Contact;

import java.util.List;

public interface ContactService {
    public Contact createContact(Contact contact);

    public List<Contact> readAllContacts();
}
