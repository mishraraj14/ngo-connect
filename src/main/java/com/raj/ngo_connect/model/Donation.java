package com.raj.ngo_connect.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Donor Name is required")
@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
private String donorName;

@NotBlank(message = "Email is required")
@Email(message = "Enter a valid email address")
private String email;

@NotBlank(message = "Phone Number is required")
@Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must be exactly 10 digits")
private String phone;

@NotNull(message = "Donation Amount is required")
@Positive(message = "Amount must be greater than 0")
private Double amount;

@NotBlank(message = "Payment Method is required")
private String paymentMethod;

    public Donation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}