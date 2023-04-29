package com.example.coco_spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.coco_spring.Entity.*;

import java.util.List;

@Repository
public interface CartRepsitory extends JpaRepository<Cart,Long> {

}
