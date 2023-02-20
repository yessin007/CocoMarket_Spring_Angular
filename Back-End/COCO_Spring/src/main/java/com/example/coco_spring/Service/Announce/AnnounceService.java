package com.example.coco_spring.Service.Announce;

import com.example.coco_spring.Entity.Announce;
import com.example.coco_spring.Repository.AnnounceRepository;
import com.example.coco_spring.Service.ICRUDService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class AnnounceService implements ICRUDService<Announce,Long>, IAnnounceService {
    AnnounceRepository announceRepository;

    @Override
    public List<Announce> findAll() {

        return announceRepository.findAll();
    }

    @Override
    public Announce retrieveItem(Long announceId) {

        return announceRepository.findById(announceId).get();
    }

    @Override
    public Announce add(Announce class1) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public Announce update(Announce Classe1) {
        return null;
    }

    public Announce addAnnounce(MultipartFile image, String announceTitle, Date announceStartDate, Date announceExpiryDate,String targetAudience) throws IOException {

        Announce announce = new Announce();
        announce.setAnnounceTitle(announceTitle);
        announce.setImages(image.getBytes());
        announce.setAnnounceStartDate(announceStartDate);
        announce.setAnnounceExpiryDate(announceExpiryDate);
        announce.setTargetAudience(targetAudience);
        announceRepository.save(announce);
        return announce;
    }
}
