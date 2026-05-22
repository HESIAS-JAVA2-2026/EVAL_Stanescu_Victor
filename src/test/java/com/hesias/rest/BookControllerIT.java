package com.hesias.rest;


import com.hesias.entity.Book;
import com.hesias.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(
                        content()
                                .json("""
                                        {"content":[{"id":1,"title":"Book1"},{"id":2,"title":"Book2"}],"page":{"size":50,"number":0,"totalElements":2,"totalPages":1}}"""
                               ));

    }

    @BeforeEach
    public void beforeEach(){
        var book = new Book().setTitle("Book1");
        var book2 = new Book().setTitle("Book2");
        bookRepository.save(book);
        bookRepository.save(book2);
    }

    @AfterEach
    public void afterEach(){
        bookRepository.deleteAll();
    }

}
