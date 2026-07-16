package com.raj.ngo_connect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.raj.ngo_connect.model.Event;
import com.raj.ngo_connect.service.EventService;
import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.raj.ngo_connect.service.ExcelService;
import com.raj.ngo_connect.service.PdfService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private ExcelService excelService;

    // Event Form
    @GetMapping("/event")
    public String eventForm(Model model) {
        model.addAttribute("event", new Event());
        return "event";
    }

    // Save Event
    @PostMapping("/saveEvent")
    public String saveEvent(@Valid @ModelAttribute("event") Event event,
            BindingResult result) {

        if (result.hasErrors()) {
            return "event";
        }

        eventService.saveEvent(event);
        return "redirect:/events";
    }

    // View Events (with optional search)

    // Edit Event
    @GetMapping("/editEvent/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event";
    }

    // Delete Event
    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/events/pdf")
    public ResponseEntity<InputStreamResource> exportEventPdf() {

        ByteArrayInputStream pdf = pdfService.eventPdf(eventService.getAllEvents());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "inline; filename=events.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("/events/excel")
    public ResponseEntity<InputStreamResource> exportEventExcel() {

        ByteArrayInputStream excel = excelService.eventExcel(eventService.getAllEvents());

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition",
                "attachment; filename=events.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(excel));
    }

    @GetMapping("/events")
    public String viewEvents(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        if (keyword == null || keyword.isEmpty()) {
            model.addAttribute("events", eventService.getAllEvents());
        } else {
            model.addAttribute("events", eventService.searchEvent(keyword));
        }

        model.addAttribute("keyword", keyword);

        return "event-list";
    }
}