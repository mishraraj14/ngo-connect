package com.raj.ngo_connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.ngo_connect.model.Contact;
import com.raj.ngo_connect.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Save Contact
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    // Get All Contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get Contact By Id
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    // Delete Contact
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}