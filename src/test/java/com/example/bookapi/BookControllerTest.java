package com.example.bookapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void listsBooks() throws Exception {
        mvc.perform(get("/api/books")).andExpect(status().isOk())
           .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    void createsBook() throws Exception {
        mvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Accelerate\",\"author\":\"Nicole Forsgren\",\"year\":2018}"))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void returnsVersion() throws Exception {
        mvc.perform(get("/api/version")).andExpect(status().isOk())
           .andExpect(jsonPath("$.app").value("book-api"));
    }
}
