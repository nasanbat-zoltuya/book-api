package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    public BookService() {
        add(new Book(null, "Clean Code", "Robert C. Martin", 2008));
        add(new Book(null, "The Phoenix Project", "Gene Kim", 2013));
    }

    public List<Book> findAll() {
        return books.values().stream().sorted((a, b) -> a.id().compareTo(b.id())).toList();
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public Book add(Book book) {
        long id = seq.incrementAndGet();
        Book saved = new Book(id, book.title(), book.author(), book.year());
        books.put(id, saved);
        return saved;
    }
}
