package com.backend.library.system.services;

import com.backend.library.system.repositories.BorrowingRecordRepository;
import com.backend.library.system.composites.BorrowingRecordCompositeKey;
import com.backend.library.system.entities.Book;
import com.backend.library.system.entities.BorrowingRecord;
import com.backend.library.system.entities.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BorrowingRecordService {
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    BookService bookService;
    @Autowired
    PatronService patronService;

    @Transactional
    public void borrowBook(Long bookId, Long patronId){
        bookService.getBookById(bookId); //call it only to raise an exception if book is not present in DB
        patronService.getPatronById(patronId); //call it only to raise an exception if patron is not present in DB
        BorrowingRecord borrowingRecordToAdd = new BorrowingRecord(new BorrowingRecordCompositeKey(bookId,patronId),new Book(bookId),new Patron(patronId), LocalDate.now(), null);
        borrowingRecordRepository.saveAndFlush(borrowingRecordToAdd);
    }
    @Transactional
    public void returnBook(Long bookId,Long patronId){
        Optional<BorrowingRecord> presentBorrowOpt = borrowingRecordRepository.findById(new BorrowingRecordCompositeKey(bookId,patronId));
        if(presentBorrowOpt.isPresent()) {
            BorrowingRecord borrowingRecordRetrieved = presentBorrowOpt.get();
            borrowingRecordRetrieved.setReturnDate(LocalDate.now());
            borrowingRecordRepository.saveAndFlush(borrowingRecordRetrieved);
        }
        else throw new NoSuchElementException("The entered borrowing record does not exist");
    }
}
