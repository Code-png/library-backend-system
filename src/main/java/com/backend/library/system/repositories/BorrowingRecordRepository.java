package com.backend.library.system.repositories;

import com.backend.library.system.composites.BorrowingRecordCompositeKey;
import com.backend.library.system.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, BorrowingRecordCompositeKey> {
}
