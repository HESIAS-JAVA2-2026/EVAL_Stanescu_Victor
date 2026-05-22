package com.hesias.rest;

import com.hesias.entity.Book;
import com.hesias.mapper.BookMapperImpl;
import com.hesias.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import({BookMapperImpl.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void getAllBooks_ok() throws Exception {
        var books = new ArrayList<Book>();
        books.add(new Book().setTitle("Book 1"));
        books.add(new Book().setTitle("Book 2"));

        when(bookService.getBooks(any(),any(Pageable.class))).thenReturn(new PageImpl(books));

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(
                        content()
                                .json("""
                                {"content":[{"id":null,"title":"Book 1"},{"id":null,"title":"Book 2"}],"page":{"size":2,"number":0,"totalElements":2,"totalPages":1}}"""))
                .andExpect(status().is2xxSuccessful());
    }


}
