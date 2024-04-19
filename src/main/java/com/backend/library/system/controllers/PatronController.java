package com.backend.library.system.controllers;

import com.backend.library.system.DTOs.PatronDTO;
import com.backend.library.system.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    PatronService patronService;

    @GetMapping
    public ResponseEntity<?> getAllPatrons(){
        return new ResponseEntity<>(patronService.getAllPatrons(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable Long id){
        return new ResponseEntity<>(patronService.getPatronById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addPatron(@Valid @RequestBody PatronDTO patronDTO){
        patronService.addPatron(patronDTO);
        return new ResponseEntity<>("The patron has been created successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDTO patronDTO){
        patronService.updatePatron(id,patronDTO);
        return new ResponseEntity<>("The patron has been updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatron(@PathVariable Long id){
        patronService.deletePatron(id);
        return new ResponseEntity<>("The patron has been deleted successfully", HttpStatus.OK);
    }
}
