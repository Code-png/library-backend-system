package com.backend.library.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.library.system.DTOs.PatronDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatronTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void whenCreatePatron_thenStatus201() throws Exception{
        PatronDTO patronToAdd = new PatronDTO(null,"name 5",5,"genre 5");
        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patronToAdd))) //To convert the DTO into a json and pass it as requestbody
                .andExpect(status().isCreated());
    }

    @Test
    void whenGetPatronById_withValidateRetrieval_thenStatus200() throws Exception{
        PatronDTO patronExpected = new PatronDTO(5L,"name 5",5,"genre 5");
        mockMvc.perform(get("/api/patrons/{id}", patronExpected.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patronExpected))); //convert DTO to json to be able to compare
    }
    @Test
    void whenGetPatronByNonPresentId_thenStatus404() throws Exception{
        Long patronIdToSearch = 10L;
        mockMvc.perform(get("/api/patrons/{id}", patronIdToSearch)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenUpdatePatron_thenStatus200() throws Exception{
        PatronDTO patronToUpdate = new PatronDTO(5L,"name 5 edited",5,"genre 5 edited");
        mockMvc.perform(put("/api/patrons/{id}", patronToUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patronToUpdate))) //To convert the DTO into a json and pass it as requestbody
                .andExpect(status().isOk());
    }

    @Test
    void whenUpdateNonPresentPatron_thenStatus404() throws Exception{
        PatronDTO patronToUpdate = new PatronDTO(10L,"name 5 edited",5,"genre 5 edited");
        mockMvc.perform(put("/api/patrons/{id}", patronToUpdate.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patronToUpdate))) //To convert the DTO into a json and pass it as requestbody
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeletePresentPatron_thenStatus200() throws Exception{
        Long id = 5L;
        mockMvc.perform(delete("/api/patrons/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void whenDeleteNonPresentPatron_thenStatus404() throws Exception{
        Long id = 5L;
        mockMvc.perform(delete("/api/patrons/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
