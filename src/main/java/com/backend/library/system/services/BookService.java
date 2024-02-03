package com.backend.library.system.services;

import com.backend.library.system.entities.Book;
import com.backend.library.system.DTOs.BookDTO;
import com.backend.library.system.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public List<BookDTO> getAllBooks(){
        try {
            return bookRepository.findAll().stream().map(BookDTO::new).toList(); //Mapping every Book to BookDTO, to avoid returning an Entity to the client side
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Cacheable(value = "bookDetailsCache")
    public BookDTO getBookById(Long bookId){
        Optional<Book> bookRetrievedOpt = bookRepository.findById(bookId);
        if(bookRetrievedOpt.isPresent())
            return new BookDTO(bookRetrievedOpt.get());
        else throw new NoSuchElementException("The book with the provided ID does not exist");
    }
    @Transactional
    public void addBook(BookDTO bookDTO){
        bookRepository.saveAndFlush(new Book(bookDTO));
    }
    @Transactional
    public void updateBook(Long id,BookDTO bookDTO){
        bookDTO.setId(id);
        Optional<Book> bookRetrievedOpt = bookRepository.findById(bookDTO.getId());
        if(bookRetrievedOpt.isPresent())
            bookRepository.saveAndFlush(new Book(bookDTO));
        else throw new NoSuchElementException("The book with the provided ID does not exist");
    }
    @Transactional
    public void deleteBook(Long id){
        Optional<Book> bookRetrievedOpt = bookRepository.findById(id);
        if(bookRetrievedOpt.isPresent())
            bookRepository.deleteById(id);
        else throw new NoSuchElementException("The book with the provided ID does not exist");
    }
}
