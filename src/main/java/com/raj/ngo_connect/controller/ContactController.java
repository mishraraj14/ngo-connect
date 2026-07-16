package com.raj.ngo_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.raj.ngo_connect.model.Contact;
import com.raj.ngo_connect.service.ContactService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Open Contact Form
    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    // Save Contact
    @PostMapping("/saveContact")
    public String saveContact(@Valid @ModelAttribute("contact") Contact contact,
            BindingResult result) {

        if (result.hasErrors()) {
            return "contact";
        }

        contactService.saveContact(contact);
        return "redirect:/contacts";
    }

    // View All Contacts
    @GetMapping("/contacts")
    public String viewContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contact-list";
    }

    // Edit Contact
    @GetMapping("/editContact/{id}")
    public String editContact(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "contact";
    }

    // Delete Contact
    @GetMapping("/deleteContact/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }
}