package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
