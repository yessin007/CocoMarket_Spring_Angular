package com.example.coco_spring.Controller.QRCode;

import com.example.coco_spring.Util.MethodeUtils;
import org.springframework.http.MediaType;
import org.springframework.security.util.MethodInvocationUtils;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.*;

@RestController
@RequestMapping("/api")
public class QrCodeController {
    @GetMapping(value = "/qrcode/{text}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] generateQRCode(@PathVariable("text") String text) {
        return MethodeUtils.generateByteQRCode(text, 200, 200);
    }

}
