package com.example.coco_spring.Controller.Payement;

import com.example.coco_spring.Service.Payement.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
@AllArgsConstructor
@RequestMapping("/api/payement/PDF/")
public class PDFController {

    PdfService pdfService;
    @PostMapping(path="certifGen/{userId}")
    public ResponseEntity<byte[]> certif(@PathVariable("userId") Long userId) throws IOException, InterruptedException{

        byte[] res = pdfService.certif(userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Certificate.pdf")
                .contentType(MediaType.APPLICATION_PDF).body(res);
    }
}
