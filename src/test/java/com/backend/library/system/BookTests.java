package com.backend.library.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.backend.library.system.DTOs.BookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void whenCreateBook_thenStatus201() throws Exception{
		BookDTO bookToAdd = new BookDTO(null,"title 11","author 11","2023-01-01","isbn 11");
		mockMvc.perform(post("/api/books")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bookToAdd))) //To convert the DTO into a json and pass it as requestbody
				.andExpect(status().isCreated());
	}

	@Test
	void whenGetBookById_withValidateRetrieval_thenStatus200() throws Exception{
		BookDTO bookExpected = new BookDTO(10L,"title 10", "author 10", "2023-01-01","isbn 10");
		mockMvc.perform(get("/api/books/{id}", bookExpected.getId())
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(bookExpected))); //convert DTO to json to be able to compare
	}
	@Test
	void whenGetBookByNonPresentId_thenStatus404() throws Exception{
		Long bookIdToSearch = 100L;
		mockMvc.perform(get("/api/books/{id}", bookIdToSearch)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void whenUpdateBook_thenStatus200() throws Exception{
		BookDTO bookToUpdate = new BookDTO(3L,"title 3 edited","author 3 edited","2023-01-01","isbn 3 edited");
		mockMvc.perform(put("/api/books/{id}", bookToUpdate.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bookToUpdate))) //To convert the DTO into a json and pass it as requestbody
				.andExpect(status().isOk());
	}

	@Test
	void whenUpdateNonPresentBook_thenStatus404() throws Exception{
		BookDTO bookToUpdate = new BookDTO(6L,"title 3 edited","author 3 edited","2023-01-01","isbn 3 edited");
		mockMvc.perform(put("/api/books/{id}", bookToUpdate.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bookToUpdate))) //To convert the DTO into a json and pass it as requestbody
				.andExpect(status().isNotFound());
	}

	@Test
	void whenDeletePresentBook_thenStatus200() throws Exception{
		Long id = 7L;
		mockMvc.perform(delete("/api/books/{id}",id)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	void whenDeleteNonPresentBook_thenStatus404() throws Exception{
		Long id = 2L;
		mockMvc.perform(delete("/api/books/{id}",id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
