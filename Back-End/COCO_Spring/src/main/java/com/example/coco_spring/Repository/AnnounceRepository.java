package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnounceRepository extends JpaRepository<Announce,Long> {
}
