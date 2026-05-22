package com.hesias.rest;

import com.hesias.dto.OwnerResponseDto;
import com.hesias.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    @MockitoBean
    private OwnerService ownerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll_ok() throws Exception {
        var owners = List.of(
                new OwnerResponseDto(1L, "Victor", "Stanescu", "victor@test.com", "+123"),
                new OwnerResponseDto(2L, "aymeric", "test", "aymeric@test.com", "+456")
        );

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/api/owners"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                          {"id":1,"firstName":"Victor","lastName":"Stanescu","email":"victor@test.com","phone":"+123"},
                          {"id":2,"firstName":"aymeric","lastName":"test","email":"aymeric@test.com","phone":"+456"}
                        ]
                        """));
    }

    @Test
    void findById_ok() throws Exception {
        var owner = new OwnerResponseDto(1L, "Victor", "Stanescu", "victor@test.com", "+123");

        when(ownerService.findById(1L)).thenReturn(owner);

        mockMvc.perform(get("/api/owners/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id":1,"firstName":"Victor","lastName":"Stanescu","email":"victor@test.com","phone":"+123"}
                        """));
    }
}