package com.example.coco_spring.Service.Payement;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.*;
import com.example.coco_spring.Repository.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

@Service
@AllArgsConstructor
public class PdfService {

    PayementRepository payementRepository;
    UserRepository userRepository;
    public byte[] certif(Long userId) throws IOException, InterruptedException{


        User user = userRepository.findById(userId).get();

        //Payement payement = payementRepository.findById(paymentId).get();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://yakpdf.p.rapidapi.com/pdf"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "yakpdf.p.rapidapi.com")
                .header("X-RapidAPI-Key", "b648c42070msh2f1e24111397e42p1155f4jsn864d7705eee5")
                .method("POST", HttpRequest.BodyPublishers.ofString("" +
                        "{\n" +
                        "    \"pdf\": {\n" +
                        "        \"format\": \"A4\",\n" +
                        "        \"printBackground\": true,\n" +
                        "        \"scale\": 1\n" +
                        "    },\n" +
                        "    \"source\": {\n" +
                        "        \"html\": \"<!DOCTYPE html><html><head><style>.container {margin: 0 auto;width: 80%;border: 2px solid chocolate;padding: 20px;}.header {text-align: center;font-size: 32px;margin-bottom: 20px;color:chocolate;font-family:Monospace;}.bill-info {display: flex;justify-content: space-between;margin-bottom: 20px;}.bill-info p {margin: 0;}.items {border-collapse: collapse;width: 100%;margin-bottom: 20px;}.items th, .items td {border: 1px dotted #ccc;padding: 8px;text-align: left;}.items th {background-color: #f2f2f2;font-weight: bold;}.total {text-align: right;font-weight: bold;font-size: 20px;}body {background-color: #FAEBD7;}</style></head><body background-color: #FAEBD7;><div class='container'><div class='header'><h1>Coco Market</h1></div><div class='bill-info'><div><p>Bill To:</p><p>"+ user.getName()+"</p><p>"+ user.getAddress()+"</p><p>Anytown, USA</p></div><div><p>Phone Number:</p><p>"+user.getTelNum()+"</p><p>Date:</p><p>"+user.getPayement().getPayementDate()+"</p></div></div><table class='items'><thead><tr><th>Description</th><th>Quantity</th><th>Price</th><th>Total</th></tr></thead><tbody><tr><td>Item 1</td><td>2</td><td>$10.00</td><td>$20.00</td></tr><tr><td>Item 2</td><td>1</td><td>$20.00</td><td>$20.00</td></tr><tr><td>Item 3</td><td>3</td><td>$5.00</td><td>$15.00</td></tr></tbody><tfoot><tr><td colspan='3' class='total'>Total:</td><td>$"+user.getPayement().getOrder().getAmountBill()+"</td></tr></tfoot></table></div></body></html>\"\n" +
                        "    },\n" +
                        "    \"wait\": {\n" +
                        "        \"for\": \"navigation\",\n" +
                        "        \"timeout\": 250,\n" +
                        "        \"waitUntil\": \"load\"\n" +
                        "    }\n" +
                        "}"))
                .build();
        HttpResponse<byte[]> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        byte[] res = response.body();
        return res;
    }
}
