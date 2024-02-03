package com.backend.library.system.controllers;

import com.backend.library.system.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class BorrowingRecordController {
    @Autowired
    BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook (@PathVariable Long bookId , @PathVariable Long patronId){
        borrowingRecordService.borrowBook(bookId,patronId);
        return new ResponseEntity<>("The action has succeeded", HttpStatus.OK);
    }
    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook (@PathVariable Long bookId , @PathVariable Long patronId){
        borrowingRecordService.returnBook(bookId,patronId);
        return new ResponseEntity<>("The action has succeeded", HttpStatus.OK);
    }

}
