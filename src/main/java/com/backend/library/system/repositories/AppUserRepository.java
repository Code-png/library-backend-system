package com.backend.library.system.repositories;

import com.backend.library.system.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    @Query(value = "select * from users where username= :username ",nativeQuery = true)
    AppUser getUserByUsername(String username);
}
