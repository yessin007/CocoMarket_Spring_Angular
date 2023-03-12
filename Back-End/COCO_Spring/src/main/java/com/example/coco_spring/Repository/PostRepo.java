package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.PostStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PostRepo extends JpaRepository<PostStore, Long> {
    @Query(value="SELECT DATEDIFF(NOW(),:d  )   ", nativeQuery=true)
    public int diffrence_entre_date(@Param("d") Date d);
}
