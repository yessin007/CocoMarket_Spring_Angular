package com.example.coco_spring.Service.Payement;

import com.example.coco_spring.Entity.Payement;
import com.example.coco_spring.Repository.PayementRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PdfService {

    PayementRepository payementRepository;
    public byte[] certif(Long paymentId) throws IOException, InterruptedException{

        //Payement payement = payementRepository.findById(paymentId).get();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yakpdf.p.rapidapi.com/pdf"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "yakpdf.p.rapidapi.com")
                .header("X-RapidAPI-Key", "b648c42070msh2f1e24111397e42p1155f4jsn864d7705eee5")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"pdf\": {\r\n        \"format\": \"A4\",\r\n        \"printBackground\": true,\r\n        " +
                        "\"scale\": 1\r\n    },\r\n    \"source\": {\r\n        \"html\": \"<!DOCTYPE html><html lang=\\\"en\\\"><head><meta charset=\\\"UTF-8\\\"><meta name=\\\"viewport\\" +
                        "\" content=\\\"width=device-width, initial-scale=1.0\\\"></head><body><div style=\\\"width:800px; height:600px; padding:20px; text-align:center; border: 10px solid #DB7093" +
                        "\\\"><div style=\\\"width:750px; height:550px; padding:20px; text-align:center; border: 5px solid #FFC0CB\\\"><span style=\\\"font-size:50px; font-weight:bold\\\"" +
                        ">Certificate of Completion</span><br><br><span style=\\\"font-size:25px\\\"><i>This is to certify that</i></span><br><br><span style=\\\"font-size:30px\\\"><b>"
                        /*+payement.getUser().getName()*/+"</b></span><br/><br/><span style=\\\"font-size:25px\\\"><i>has completed the course</i></span> <br/><br/><span style=\\\"font-size:30px\\\"> " +
                        ""/*+certif.getCourse().getCourseName()+*/+"</span> <br/><br/><br/><br/><br/><br/><span style=\\\"font-size:25px\\\"><i>For "/*+certif.getCourse().getNbHours()*/+"hours length</i>" +
                        "</span><br><span style=\\\"font-size:25px;float:left\\\">Aquired on : "+/*+certif.getObtainingDate()+*/"</span><div style=\\\"float:right\\\"><img src=\\\""
                        /*+certif.getCertificateQR()+*/+"\\\"></div></div></div></body></html>\"\r\n    },\r\n    \"wait\": {\r\n        \"for\": \"navigation\",\r\n        \"timeout\": 250,\r\n       " +
                        " \"waitUntil\": \"load\"\r\n    }\r\n}"))
                .build();
        HttpResponse<byte[]> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] res = response.body();
        return res;
    }
}
