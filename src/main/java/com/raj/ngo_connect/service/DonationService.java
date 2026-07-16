package com.raj.ngo_connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.ngo_connect.model.Donation;
import com.raj.ngo_connect.repository.DonationRepository;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    // Save or Update Donation
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    // Get All Donations
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    // Get Donation By Id
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    // Delete Donation
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    // Total Donation Amount
    public double getTotalDonationAmount() {
        return donationRepository.findAll()
                .stream()
                .mapToDouble(d -> d.getAmount() != null ? d.getAmount() : 0.0)
                .sum();
    }

    public List<Donation> searchDonation(String keyword) {

    if (keyword == null || keyword.trim().isEmpty()) {
        return donationRepository.findAll();
    }

    return donationRepository.findByDonorNameContainingIgnoreCase(keyword);
}
}