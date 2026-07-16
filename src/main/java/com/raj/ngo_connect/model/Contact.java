package com.raj.ngo_connect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {

   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Name is required")
@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
private String name;

@NotBlank(message = "Email is required")
@Email(message = "Enter a valid email address")
private String email;

@NotBlank(message = "Subject is required")
@Size(min = 3, max = 100, message = "Subject must be between 3 and 100 characters")
private String subject;

@NotBlank(message = "Message is required")
@Size(min = 10, message = "Message must contain at least 10 characters")
private String message;

    public Contact() {
    }

    public Contact(Long id, String name, String email, String subject, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}