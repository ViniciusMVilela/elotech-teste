//package com.elotech.process.crud.controller;
//
//import com.elotech.process.crud.model.adapter.PersonAdapter;
//import com.elotech.process.crud.model.domain.entities.Contact;
//import com.elotech.process.crud.model.domain.entities.Person;
//import com.elotech.process.crud.model.service.PersonService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsStringIgnoringCase;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@AutoConfigureMockMvc
//public class ControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private PersonService personService;
//
//    private PersonAdapter personAdapter;
//
//    @Test
//    void pessoaPostTest() throws Exception {
//        Person person = new Person(2L, "Vinicius", "82861900920",
//                LocalDate.of(2023, 10, 10),
//                List.of(new Contact(1L, "Vinicius contact", "44998007032", "vinicius@gmail.com")));
//
//        mockMvc.perform(post("/person")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void pessoaComCpfInvalidoPostTest() throws Exception {
//        Person person = new Person(3L, "Vinicius", "82861900921", LocalDate.of(2023, 10, 10),
//                List.of(new Contact(2L, "Vinicius contact", "44998007032", "vinicius@gmail.com")));
//
//        mockMvc.perform(post("/person")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(containsStringIgnoringCase("Validation Error")));
//    }
//
//    @Test
//    void notFoundTest() throws Exception {
//        Long id = 10L;
//        mockMvc.perform(get("/person/{id}", id))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string(is("Entity not found")));
//    }
//
//    @Test
//    void createPersonWithCpfAlreadyExists() throws Exception {
//        Person person = new Person(2L, "Marcos", "82861900920",
//                LocalDate.of(2000, 12, 10),
//                List.of(new Contact(1L, "Marcos contact", "44998007052", "marcos@gmail.com")));
//
//        mockMvc.perform(post("/person")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(is("CPF already exists")));
//    }
//
//    @Test
//    void getPersonTest() throws Exception {
//        Long id = 1L;
//
//        Person person = new Person(1L, "Vinicius", "82861900920",
//                LocalDate.of(2023, 10, 10),
//                List.of(new Contact(1L, "Vinicius contact", "44998007032", "vinicius@gmail.com")));
//
//        when(personService.findById(id)).thenReturn(personAdapter.toDto(person));
//
//        mockMvc.perform(get("/person/{id}", id))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Vinicius"))
//                .andExpect(jsonPath("$.cpf").value("82861900920"))
//                .andExpect(jsonPath("$.birthDate").value(LocalDate.of(2023, 10, 10).toString()))
//                .andExpect(jsonPath("$.contactList[0].id").value(1))
//                .andExpect(jsonPath("$.contactList[0].nameContact").value("Vinicius contact"))
//                .andExpect(jsonPath("$.contactList[0].telephone").value("44998007032"))
//                .andExpect(jsonPath("$.contactList[0].email").value("vinicius@gmail.com"));
//    }
//
//    @Test
//    void findPersonName() throws Exception {
//        mockMvc.perform(get("/person?name=Vini"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].name").value("Vinicius"))
//                .andExpect(jsonPath("$.content[0].cpf").value("82861900920"))
//                .andExpect(jsonPath("$.content[0].birthDate").value(LocalDate.of(2023, 10, 10).toString()))
//                .andExpect(jsonPath("$.content[0].contactList[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].contactList[0].nameContact").value("Vinicius contact"))
//                .andExpect(jsonPath("$.content[0].contactList[0].telephone").value("44998007032"))
//                .andExpect(jsonPath("$.content[0].contactList[0].email").value("vinicius@gmail.com"));
//    }
//
//    @Test
//    void putPerson() throws Exception {
//        Long id = 1L;
//
//        String requestBody = "{\"name\": \"Vinicius Vilela\", " +
//                "\"cpf\": \"82861900920\", " +
//                "\"birthDate\": \"2023-10-10\"," +
//                "\"contactList\": [{\"id\": 1," +
//                "\"nameContact\": \"Vinicius contact\", " +
//                "\"telephone\": \"44998007032\", " +
//                "\"email\": \"vinicius@gmail.com\"}]}";
//
//        mockMvc.perform(put("/person/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getPersonAfterUpdate() throws Exception {
//        Long id = 1L;
//
//        Person updatedPerson = new Person(1L, "Vinicius Vilela", "82861900920",
//                LocalDate.of(2023, 10, 10),
//                List.of(new Contact(1L, "Vinicius contact", "44998007032", "vinicius@gmail.com")));
//
//        when(personService.findById(id)).thenReturn(personAdapter.toDto(updatedPerson));
//
//        mockMvc.perform(get("/person/{id}", id))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Vinicius Vilela"))
//                .andExpect(jsonPath("$.cpf").value("82861900920"))
//                .andExpect(jsonPath("$.birthDate").value(LocalDate.of(2023, 10, 10).toString()))
//                .andExpect(jsonPath("$.contactList[0].id").value(1))
//                .andExpect(jsonPath("$.contactList[0].nameContact").value("Vinicius contact"))
//                .andExpect(jsonPath("$.contactList[0].telephone").value("44998007032"))
//                .andExpect(jsonPath("$.contactList[0].email").value("vinicius@gmail.com"));
//    }
//
//}
//
