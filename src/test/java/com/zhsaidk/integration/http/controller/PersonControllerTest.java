package com.zhsaidk.integration.http.controller;

import com.zhsaidk.Service.PersonService;
import com.zhsaidk.database.Entity.Person;
import com.zhsaidk.database.Entity.Role;
import com.zhsaidk.database.repository.PersonRepository;
import com.zhsaidk.dto.PersonCreateEditDto;
import com.zhsaidk.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.function.RequestPredicates;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@IT
@RequiredArgsConstructor
@AutoConfigureMockMvc
@Transactional
class PersonControllerTest {
    private final PersonService personService;
    private final MockMvc mockMvc;
    @Autowired
    private final PersonRepository personRepository;

    @Test
    @WithMockUser(username = "zhavokhir@gmail.com", roles = {"ADMIN"})
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "zhavokhir@gmail.com", roles = {"ADMIN"})
    void findPersonByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/persons/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("person/person"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person", "roles"));
    }

    @Test
    @WithMockUser(username = "zhavokhir@gmail.com", roles = {"ADMIN", "USER"})
    void update() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile(
                "image",
                "avatar_1.png",
                "png/jpg",
                "content".getBytes()
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/persons/{id}/update", 1)
                        .file(mockFile)
                        .param("firstname", "testName")
                        .param("lastname", "testLastName")
                        .param("phoneNumber", "87765432125")
                        .param("email", "test@gmail.com")
                        .param("role", "ADMIN")
                        .param("birthDate", "2002-02-02")
                        .param("password", "123")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/persons/1"));

        Person person = personRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("PersonNotFound!"));

        assertEquals("testName", person.getFirstname(), "Имя изменилась не корректно!");
        assertEquals("testLastName", person.getLastname(), "Фамилия изменилась не корректно!");
        assertEquals("test@gmail.com", person.getEmail(), "Почта изменилась не корректно!");
    }

    @Test
    @WithMockUser(username = "zhavokhir@gmail.com", roles = {"ADMIN"})
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/persons/{id}/delete", 1))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/persons"));

        Person person = personRepository.findById(1L)
                .orElse(null);

        assertNull(person);
    }
}

