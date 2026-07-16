package com.raj.ngo_connect.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.raj.ngo_connect.model.Volunteer;
import java.io.ByteArrayOutputStream;
import com.raj.ngo_connect.model.Donation;
import com.raj.ngo_connect.model.Event;

@Service
public class PdfService {

    public ByteArrayInputStream volunteerPdf(List<Volunteer> volunteers) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

            Paragraph p = new Paragraph("NGO Connect - Volunteers Report", font);

            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(3);

            table.setWidthPercentage(100);

            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Skills");

            for (Volunteer v : volunteers) {

                table.addCell(v.getFullName());
                table.addCell(v.getEmail());
                table.addCell(v.getSkills());

            }

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());

    }

    public ByteArrayInputStream donationPdf(List<Donation> donations) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

            Paragraph p = new Paragraph("NGO Connect - Donations Report", font);

            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);

            table.setWidthPercentage(100);

            table.addCell("Donor");
            table.addCell("Email");
            table.addCell("Phone");
            table.addCell("Amount");
            table.addCell("Payment");

            for (Donation d : donations) {

                table.addCell(d.getDonorName());
                table.addCell(d.getEmail());
                table.addCell(d.getPhone());
                table.addCell(String.valueOf(d.getAmount()));
                table.addCell(d.getPaymentMethod());

            }

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream eventPdf(List<Event> events) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

            Paragraph p = new Paragraph("NGO Connect - Events Report", font);

            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);

            table.addCell("Event");
            table.addCell("Date");
            table.addCell("Location");
            table.addCell("Description");

            for (Event e : events) {

                table.addCell(e.getEventName());
                table.addCell(e.getEventDate());
                table.addCell(e.getLocation());
                table.addCell(e.getDescription());

            }

            document.add(table);

            document.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}