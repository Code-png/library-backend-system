package com.backend.library.system.DTOs;

import com.backend.library.system.entities.Patron;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatronDTO implements Serializable {
    private Long id;
    private String name;
    @Positive
    private Integer age;
    private String favoriteGenre;

    public PatronDTO(Patron patron){
        this(patron.getId(),patron.getName(),patron.getAge(),patron.getFavoriteGenre());
    }
}
