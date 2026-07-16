package com.raj.ngo_connect.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.raj.ngo_connect.model.Volunteer;
import com.raj.ngo_connect.model.Donation;
import com.raj.ngo_connect.model.Event;

@Service
public class ExcelService {

    public ByteArrayInputStream volunteerExcel(List<Volunteer> volunteers) {

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet("Volunteers");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Skills");

            int rowNum = 1;

            for (Volunteer v : volunteers) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(v.getFullName());
                row.createCell(1).setCellValue(v.getEmail());
                row.createCell(2).setCellValue(v.getSkills());

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayInputStream donationExcel(List<Donation> donations) {

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet("Donations");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Donor Name");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Phone");
            header.createCell(3).setCellValue("Amount");
            header.createCell(4).setCellValue("Payment Method");

            int rowNum = 1;

            for (Donation d : donations) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(d.getDonorName());
                row.createCell(1).setCellValue(d.getEmail());
                row.createCell(2).setCellValue(d.getPhone());
                row.createCell(3).setCellValue(d.getAmount());
                row.createCell(4).setCellValue(d.getPaymentMethod());

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayInputStream eventExcel(List<Event> events) {

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet("Events");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("Event Name");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Location");
            header.createCell(3).setCellValue("Description");

            int rowNum = 1;

            for (Event e : events) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(e.getEventName());
                row.createCell(1).setCellValue(e.getEventDate());
                row.createCell(2).setCellValue(e.getLocation());
                row.createCell(3).setCellValue(e.getDescription());

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}