package com.hesias.rest;

import com.hesias.entity.Owner;
import com.hesias.repository.OwnerRepository;
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

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerIT {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetOwnerById() throws Exception {
        mockMvc.perform(get("/api/owners/{id}", 1))
                .andDo(print())
                .andExpect(
                        content()
                                .json("""
                                                     {
                                                       "id": 1,
                                                       "firstName": "Victor",
                                                       "lastName": "Stanescu",
                                                       "email": "victor@test.com",
                                                       "phone": "+123"
                                                     }
                                                   """
                                ));

    }


    @BeforeEach
    public void setUp(){
        ownerRepository.deleteAll();

        Owner owner = Owner.builder().firstName("Victor").lastName("Stanescu").phone("+123").email("victor@test.com").build();

        ownerRepository.save(owner);
    }


    @AfterEach
    public void afterEach(){
        ownerRepository.deleteAll();
    }




}
