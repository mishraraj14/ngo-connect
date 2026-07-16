package com.raj.ngo_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.raj.ngo_connect.service.DonationService;
import com.raj.ngo_connect.service.EventService;
import com.raj.ngo_connect.service.VolunteerService;

import com.raj.ngo_connect.service.ContactService;

@Controller
public class DashboardController {

        @Autowired
        private VolunteerService volunteerService;

        @Autowired
        private DonationService donationService;

        @Autowired
        private EventService eventService;

        @Autowired
        private ContactService contactService;

        @GetMapping("/dashboard")
        public String dashboard(Model model) {

                model.addAttribute("totalVolunteers",
                                volunteerService.getAllVolunteers().size());

                model.addAttribute("totalDonations",
                                donationService.getAllDonations().size());

                model.addAttribute("totalAmount",
                                donationService.getTotalDonationAmount());

                // Recent Data
                model.addAttribute("volunteers",
                                volunteerService.getAllVolunteers());

                model.addAttribute("donations",
                                donationService.getAllDonations());

                model.addAttribute("totalEvents",
                                eventService.getAllEvents().size());
                model.addAttribute("totalContacts",
                                contactService.getAllContacts().size());

                model.addAttribute("events", eventService.getAllEvents());

                return "dashboard";
        }
}