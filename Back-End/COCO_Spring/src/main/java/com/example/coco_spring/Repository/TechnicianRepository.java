package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnicianRepository extends JpaRepository<Technician,Long> {
    Optional<Technician> findByName(String name);
}
