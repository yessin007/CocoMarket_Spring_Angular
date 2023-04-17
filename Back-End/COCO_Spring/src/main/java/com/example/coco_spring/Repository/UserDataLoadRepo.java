package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.UserDataLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataLoadRepo extends JpaRepository<UserDataLoad,Long> {
}