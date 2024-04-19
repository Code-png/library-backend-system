package com.backend.library.system.entities;

import com.backend.library.system.composites.BorrowingRecordCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecord {
    @EmbeddedId
    private BorrowingRecordCompositeKey id;
    @MapsId("bookId")
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @MapsId("patronId")
    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @Column(name = "borrowing_date")
    private LocalDate borrowingDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

}
