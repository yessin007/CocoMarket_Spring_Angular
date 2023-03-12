package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.ProviderRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRatingRepository extends JpaRepository<ProviderRating, Long> {
    List<ProviderRating> findByProvider(Provider provider);
}
