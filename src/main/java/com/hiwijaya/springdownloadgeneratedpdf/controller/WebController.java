package com.hiwijaya.springdownloadgeneratedpdf.controller;

import com.hiwijaya.springdownloadgeneratedpdf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * @author Happy Indra Wijaya
 */
@Controller
public class WebController {

    @Value("${app.message}")
    private String appMessage;

    @Autowired
    private ReportService reportService;


    @GetMapping("/")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("spring-download-generated-pdf");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok(appMessage);
    }


    @GetMapping("/report")
    public ResponseEntity<?> getReport() throws IOException {

        String name = "Happy Indra Wijaya";

        byte[] pdfBytes = reportService.generateReport(name);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .body(pdfBytes);
    }

}
