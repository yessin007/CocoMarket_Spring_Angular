package com.example.coco_spring.Service.Announce;

import com.example.coco_spring.Entity.Announce;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public interface IAnnounceService {
    Announce addAnnounce(MultipartFile image, String announceTitle, Date announceStartDate, Date announceExpiryDate, String targetAudience) throws IOException;

    }
