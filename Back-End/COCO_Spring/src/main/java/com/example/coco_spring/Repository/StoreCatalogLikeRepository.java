package com.example.coco_spring.Repository;

import com.example.coco_spring.Entity.StoreCatalogLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface StoreCatalogLikeRepository extends JpaRepository<StoreCatalogLike, Long> {
    @Query(value =" SELECT * from users u  INNER JOIN store_catalog_like scl ON scl.user_user_id = u.user_id ORDER BY count(*)",nativeQuery=true)
    public Set<Object> USer_order_by_Like ();
}
