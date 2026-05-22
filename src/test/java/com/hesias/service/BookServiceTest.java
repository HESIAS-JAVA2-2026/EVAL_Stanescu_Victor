package com.hesias.service;

import com.hesias.entity.Book;
import com.hesias.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({BookService.class})
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    void getAll_ok() {
        // ARRANGE
        var books = new ArrayList<Book>();
        books.add(new Book().setTitle("Book 1"));
        books.add(new Book().setTitle("Book 2"));
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl(books));

        // ACT
        var booksfromMockito = bookService.getBooks(Optional.empty(), PageRequest.of(0, 10));

        // ASSERT
        assertEquals(2, booksfromMockito.getSize());
    }


}
