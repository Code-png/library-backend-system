package com.backend.library.system.DTOs;

import com.backend.library.system.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {
    private Long id;
    private String title;
    private String author;
    @Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
    private String publicationYear;
    private String isbn;

    public BookDTO(Book book){
        this(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear() == null ? null : book.getPublicationYear().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                book.getIsbn()
        );
    }
}
