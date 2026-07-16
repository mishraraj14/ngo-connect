package com.raj.ngo_connect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotBlank(message = "Event Name is required")
@Size(min = 3, max = 100, message = "Event Name must be between 3 and 100 characters")
private String eventName;

@NotBlank(message = "Event Date is required")
private String eventDate;

@NotBlank(message = "Location is required")
@Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters")
private String location;

@NotBlank(message = "Description is required")
@Size(min = 10, message = "Description must contain at least 10 characters")
private String description;

    public Event() {
    }

    public Event(Long id, String eventName, String eventDate, String location, String description) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}