package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.Store;
import com.example.coco_spring.Entity.StoreCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface StoreCatalogRepository extends JpaRepository<StoreCatalog,Long> {
    StoreCatalog findStoreCatalogByCatalogName(String catalogName);
    StoreCatalog findStoreCatalogByCatalogDescription(String descirption);

    StoreCatalog findByDate(Date date);
}
