package com.example.coco_spring.Controller.Announce;

import com.example.coco_spring.Entity.Announce;
import com.example.coco_spring.Service.Announce.AnnounceService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/announce")
public class AnnounceController {


    AnnounceService announceService;
    @GetMapping("/retrive_all_announces")
    public List<Announce> retrieveAnnounceList(){

        return announceService.findAll();
    }

    @GetMapping("/retrive_announce/{announceId}")
    public Announce retrieveAnnounce(@PathVariable("announceId") Long announceId){

        return announceService.retrieveItem(announceId);
    }

    @PostMapping("/add_announce")
        public Announce addAnnounce(@RequestParam("image") MultipartFile image, @RequestParam("announceTitle") String announceTitle, @RequestParam("announceStartDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date announceStartDate, @RequestParam("announceExpiryDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date announceExpiryDate, @RequestParam("targetAudience") String targetAudience) throws
        IOException {


            return announceService.addAnnounce(image,announceTitle,announceStartDate,announceExpiryDate,targetAudience);
    }

    @PutMapping("/update_announce")
    public Announce updateAnnounce(@RequestBody Announce announce){

        return announceService.update(announce);
    }

    @DeleteMapping("/delete_announce/{announceId}")
    public void deleteAnnounce(@PathVariable("announceId") Long announceId){

        announceService.delete(announceId);
    }
}
