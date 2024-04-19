package com.backend.library.system.repositories;

import com.backend.library.system.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
}
