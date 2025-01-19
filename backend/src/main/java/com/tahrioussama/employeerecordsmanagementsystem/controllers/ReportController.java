package com.tahrioussama.employeerecordsmanagementsystem.controllers;

import com.tahrioussama.employeerecordsmanagementsystem.services.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportEmployeeReport() throws IOException {
        ByteArrayInputStream reportStream = reportService.generateEmployeeReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employee_report.csv");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportStream.readAllBytes());
    }
}
