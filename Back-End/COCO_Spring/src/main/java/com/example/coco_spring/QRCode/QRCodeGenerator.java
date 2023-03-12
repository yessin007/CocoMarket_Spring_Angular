package com.example.coco_spring.QRCode;

import com.example.coco_spring.Entity.Store;
import com.example.coco_spring.Entity.StoreCatalog;
import com.example.coco_spring.Repository.StoreCatalogRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {
    static StoreCatalogRepository storeCatalogRepository;

    public static void generateQRCode(Store store) throws WriterException, IOException {

        String qrCodePath = "C:\\Users\\Ahmed\\Documents\\GitHub\\PiDev_Spring_Angular\\Back-End\\COCO_Spring\\src\\main\\QRCode\\";
        String qrCodeName = qrCodePath+store.getStoreName()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =qrCodeWriter.encode(store.getLink()+"\n",
                BarcodeFormat.QR_CODE,200,200);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);

    }

}
