package com.backend.library.system.composites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRecordCompositeKey implements Serializable {
    private Long bookId;
    private Long patronId;
}
