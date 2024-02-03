package com.backend.library.system.entities;

import com.backend.library.system.DTOs.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(name = "publication_year")
    private LocalDate publicationYear;
    private String isbn;

    public Book(BookDTO bookDTO){
        this(bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getPublicationYear() == null ? null : LocalDate.parse(bookDTO.getPublicationYear()),
                bookDTO.getIsbn()
        );
    }
    public Book(Long id){
        this.id = id;
    }
}
