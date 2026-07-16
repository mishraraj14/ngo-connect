package com.raj.ngo_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.raj.ngo_connect.model.Donation;
import com.raj.ngo_connect.service.DonationService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;

import com.raj.ngo_connect.service.ExcelService;
import com.raj.ngo_connect.service.PdfService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/donation")
    public String donationForm(Model model) {
        model.addAttribute("donation", new Donation());
        return "donation";
    }

    @PostMapping("/saveDonation")
    public String saveDonation(@Valid @ModelAttribute("donation") Donation donation,
            BindingResult result) {

        if (result.hasErrors()) {
            return "donation";
        }

        donationService.saveDonation(donation);
        return "redirect:/donations";
    }

    @Autowired
    private PdfService pdfService;

    @Autowired
    private ExcelService excelService;

    @GetMapping("/editDonation/{id}")
    public String editDonation(@PathVariable Long id, Model model) {
        Donation donation = donationService.getDonationById(id);
        model.addAttribute("donation", donation);
        return "donation";
    }

    @GetMapping("/deleteDonation/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return "redirect:/donations";
    }

    @GetMapping("/donations")
    public String viewDonations(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        model.addAttribute("donations",
                donationService.searchDonation(keyword));

        model.addAttribute("keyword", keyword);

        return "donation-list";
    }

    @GetMapping("/donations/pdf")
    public ResponseEntity<InputStreamResource> exportDonationPdf() {

        ByteArrayInputStream pdf = pdfService.donationPdf(donationService.getAllDonations());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "inline; filename=donations.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("/donations/excel")
    public ResponseEntity<InputStreamResource> exportDonationExcel() {

        ByteArrayInputStream excel = excelService.donationExcel(donationService.getAllDonations());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "attachment; filename=donations.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(excel));
    }
}