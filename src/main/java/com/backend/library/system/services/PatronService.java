package com.backend.library.system.services;

import com.backend.library.system.entities.Patron;
import com.backend.library.system.DTOs.PatronDTO;
import com.backend.library.system.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    PatronRepository patronRepository;
    public List<PatronDTO> getAllPatrons(){
        try {
            return patronRepository.findAll().stream().map(PatronDTO::new).toList(); //Mapping every Patron to PatronDTO, to avoid returning an Entity to the client side
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Cacheable(value = "patronDetailsCache")
    public PatronDTO getPatronById(Long patronId){
        Optional<Patron> patronRetrievedOpt = patronRepository.findById(patronId);
        if(patronRetrievedOpt.isPresent())
            return new PatronDTO(patronRetrievedOpt.get());
        else throw new NoSuchElementException("The patron with the provided ID does not exist");
    }
    @Transactional
    public void addPatron(PatronDTO patronDTO){
        patronRepository.saveAndFlush(new Patron(patronDTO));
    }
    @Transactional
    public void updatePatron(Long id,PatronDTO patronDTO){
        patronDTO.setId(id);
        Optional<Patron> patronRetrievedOpt = patronRepository.findById(patronDTO.getId());
        if(patronRetrievedOpt.isPresent())
            patronRepository.saveAndFlush(new Patron(patronDTO));
        else throw new NoSuchElementException("The patron with the provided ID does not exist");
    }
    @Transactional
    public void deletePatron(Long id){
        Optional<Patron> patronRetrievedOpt = patronRepository.findById(id);
        if(patronRetrievedOpt.isPresent())
            patronRepository.deleteById(id);
        else throw new NoSuchElementException("The patron with the provided ID does not exist");
    }
}
