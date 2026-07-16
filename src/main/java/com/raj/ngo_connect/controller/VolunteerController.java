package com.raj.ngo_connect.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raj.ngo_connect.model.Volunteer;
import com.raj.ngo_connect.service.PdfService;
import com.raj.ngo_connect.service.VolunteerService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.raj.ngo_connect.service.ExcelService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private ExcelService excelService;

    @GetMapping("/volunteer")
    public String volunteerForm(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "volunteer";
    }

    @PostMapping("/saveVolunteer")
    public String saveVolunteer(@Valid @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "volunteer";
        }

        volunteerService.saveVolunteer(volunteer);

        redirectAttributes.addFlashAttribute("success",
                volunteer.getId() == null
                        ? "Volunteer added successfully!"
                        : "Volunteer updated successfully!");

        return "redirect:/volunteers";
    }

    @GetMapping("/editVolunteer/{id}")
    public String editVolunteer(@PathVariable Long id, Model model) {
        Volunteer volunteer = volunteerService.getVolunteerById(id);
        model.addAttribute("volunteer", volunteer);
        return "volunteer";
    }

    @GetMapping("/deleteVolunteer/{id}")
    public String deleteVolunteer(@PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        volunteerService.deleteVolunteer(id);

        redirectAttributes.addFlashAttribute("success",
                "Volunteer deleted successfully!");

        return "redirect:/volunteers";
    }

    @GetMapping("/volunteers")
    public String viewVolunteers(
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Volunteer> volunteerPage = volunteerService.getVolunteerPage(page);

        model.addAttribute("volunteerPage", volunteerPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", volunteerPage.getTotalPages());
        return "volunteer-list";
    }

    @GetMapping("/volunteers/pdf")
    public ResponseEntity<InputStreamResource> exportVolunteerPdf() {

        ByteArrayInputStream pdf = pdfService.volunteerPdf(volunteerService.getAllVolunteers());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "inline; filename=volunteers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));

    }

    @GetMapping("/volunteers/excel")
    public ResponseEntity<InputStreamResource> exportVolunteerExcel() {

        ByteArrayInputStream excel = excelService.volunteerExcel(volunteerService.getAllVolunteers());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "attachment; filename=volunteers.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(excel));
    }
}