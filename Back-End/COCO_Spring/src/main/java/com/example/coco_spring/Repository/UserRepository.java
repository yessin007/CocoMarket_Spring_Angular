package com.example.coco_spring.Repository;



import com.example.coco_spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.expired = true")
    List<User> findExpiredUsers();

    Optional<User> findByCodeReset(Integer code);

/*
    @Modifying
    @Query("UPDATE User u SET u.authenticationProvider = ?2 WHERE u.username = ?1")
    public void updateAuthenticationType(String username, AuthenticationProvider authType);*/
}
