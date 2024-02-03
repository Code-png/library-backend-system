package com.backend.library.system.entities;

import com.backend.library.system.DTOs.PatronDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @Column(name = "favorite_genre")
    private String favoriteGenre;

    public Patron(PatronDTO patronDTO){
        this(patronDTO.getId(),patronDTO.getName(),patronDTO.getAge(),patronDTO.getFavoriteGenre());
    }
    public Patron(Long id){
        this.id = id;
    }
}
