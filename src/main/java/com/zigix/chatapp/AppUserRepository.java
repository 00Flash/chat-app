package com.zigix.chatapp;

import com.zigix.chatapp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    @Modifying
    @Query("update AppUser a set a.enabled=true where a.email=?1")
    int enableAppUserAccount(String email);
}
