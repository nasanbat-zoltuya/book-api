package com.example.bookapi.controller;

import com.example.bookapi.model.Book;
import com.example.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService service;
    private final String version;

    public BookController(BookService service, @Value("${app.version:local}") String version) {
        this.service = service;
        this.version = version;
    }

    @GetMapping("/version")
    public Map<String, String> version() {
        return Map.of("app", "book-api", "version", version, "message", "Hello GitOps v2");
    }

    @GetMapping("/books")
    public List<Book> list() {
        return service.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return service.add(book);
    }
}
