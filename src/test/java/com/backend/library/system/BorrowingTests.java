package com.backend.library.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowingTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void whenPatronBorrowNonPresentBook_thenStatus404() throws Exception{
        Long bookId = 10L;
        Long patronId = 3L;
        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}",bookId,patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void whenNonPresentPatronBorrowBook_thenStatus404() throws Exception{
        Long bookId = 3L;
        Long patronId = 10L;
        mockMvc.perform(post("/api/borrow/{bookId}/patron/{patronId}",bookId,patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void whenReturnNotBorrowedBook_thenStatus404() throws Exception{
        Long bookId = 3L;
        Long patronId = 3L;
        mockMvc.perform(put("/api/return/{bookId}/patron/{patronId}",bookId,patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
