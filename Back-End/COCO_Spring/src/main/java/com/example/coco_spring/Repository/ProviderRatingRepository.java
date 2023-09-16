package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Provider;
import com.example.coco_spring.Entity.ProviderRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRatingRepository extends JpaRepository<ProviderRating, Long> {
    List<ProviderRating> findByProviderProviderId(Long providerId);

    @Query("SELECT AVG(r.rating) FROM ProviderRating r WHERE r.provider.providerId = :providerId")
    Double findAverageRatingByProviderId(@Param("providerId") Long providerId);

    @Query(value = "SELECT p.providerId, p.firstName, AVG(r.rating) AS avg_rating, COUNT(r.id) AS rating_count " +
            "FROM Provider p " +
            "JOIN ProviderRating r ON p.providerId = r.provider.providerId " +
            "GROUP BY p.providerId, p.firstName " +
            "ORDER BY rating_count DESC")
    List<Object[]> findMostRatedProviders();}
