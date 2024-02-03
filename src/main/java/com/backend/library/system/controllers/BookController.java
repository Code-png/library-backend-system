package com.backend.library.system.controllers;

import com.backend.library.system.DTOs.BookDTO;
import com.backend.library.system.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO bookDTO){
        bookService.addBook(bookDTO);
        return new ResponseEntity<>("The book has been created successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO){
        bookService.updateBook(id,bookDTO);
        return new ResponseEntity<>("The book has been updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("The book has been deleted successfully", HttpStatus.OK);
    }

}
