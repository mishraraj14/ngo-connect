package com.raj.ngo_connect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @NotBlank(message = "Full Name is required")
@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
private String fullName;

@NotBlank(message = "Email is required")
@Email(message = "Enter a valid email address")
private String email;

@NotBlank(message = "Phone Number is required")
@Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be exactly 10 digits")
private String phone;

@NotBlank(message = "Address is required")
private String address;

@NotBlank(message = "Please select an Area of Interest")
private String skills;

    public Volunteer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}