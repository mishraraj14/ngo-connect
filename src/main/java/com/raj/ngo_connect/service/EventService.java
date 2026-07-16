package com.raj.ngo_connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.ngo_connect.model.Event;
import com.raj.ngo_connect.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Save or Update Event
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    // Get All Events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get Event By Id
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    // Delete Event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> searchEvent(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return eventRepository.findAll();
        }

        return eventRepository.findByEventNameContainingIgnoreCase(keyword);
    }
}