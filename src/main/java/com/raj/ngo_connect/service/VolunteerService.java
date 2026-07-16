package com.raj.ngo_connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.ngo_connect.model.Volunteer;
import com.raj.ngo_connect.repository.VolunteerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    // Save Volunteer
    public void saveVolunteer(Volunteer volunteer) {
        volunteerRepository.save(volunteer);
    }

    // Get All Volunteers
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public List<Volunteer> searchVolunteer(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return volunteerRepository.findAll();
        }

        return volunteerRepository.findByFullNameContainingIgnoreCase(keyword);
    }

    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }

    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id).orElse(null);

    }

    public Page<Volunteer> getVolunteerPage(int pageNo) {

    Pageable pageable = PageRequest.of(pageNo, 5);

    return volunteerRepository.findAll(pageable);

}
}