//package com.elotech.process.crud.controller;
//
//import com.elotech.process.crud.model.domain.entities.Contact;
//import com.elotech.process.crud.model.domain.entities.Person;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.validation.ConstraintViolationException;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsStringIgnoringCase;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class PersonControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @Order(1)
//    @DisplayName("Shoul create a person")
//    void personPostTest() throws Exception {
//        Person person = new Person(2L, "Vinicius", "82861900920",
//                LocalDate.of(2023, 10, 10),
//                List.of(new Contact(1L, "Vinicius contact", "44998007032", "vinicius@gmail.com")));
//
//        mockMvc.perform(post("/person")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isCreated());
//
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("Should return Http Status Bad Resquest and throw Exception for invalid CPF")
//    void personInvalidCpfTest() throws Exception {
//        Person person = new Person(3L, "Vinicius", "82861900921", LocalDate.of(2023, 10, 10), List.of(new Contact(2L, "Vinicius contact", "44998007032", "vinicius@gmail.com")));
//
//
//        mockMvc.perform(post("/person")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpectAll(status().isBadRequest(), content().string(is("Validation Error: Property: cpf, Message: Invalid CPF, please put a valid cpf; ")));
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("Should return Http Status Bad Resquest and throw Exception for Entity not found")
//    void notFoundTest() throws Exception {
//        Long id = 10L;
//        mockMvc.perform(get("/person/{id}", id))
//                .andExpectAll(status().isNotFound(), content().string(is("Entity not found")));
//    }
//
//    @Test
//    @Order(4)
//    @DisplayName("Should return Http Status Bad Resquest and throw Exception for CPF already exist")
//    void createPersonWithCpfAlreadyExists() throws Exception {
//        Person person = new Person(2L, "Marcos", "82861900920",
//                LocalDate.of(2000, 12, 10),
//                List.of(new Contact(1L, "Marcos contact", "44998007052", "marcos@gmail.com")));
//
//        mockMvc.perform(post("/person")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpectAll(status().isBadRequest(), content().string(is("CPF already exists")));
//
//    }
//
//
////    @Test
////    @Order(5)
////    void getPersonTest() throws Exception {
////        Long id = 1L;
////
////        mockMvc.perform(get("/person/{id}", id))
////                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
////                        status().isOk(),
////                        jsonPath("$.id").value(1),
////                        jsonPath("$.name").value("Vinicius"),
////                        jsonPath("$.cpf").value("82861900920"),
////                        jsonPath("$.birthDate").value(LocalDate.of(2023, 10, 10).toString()),
////                        jsonPath("$.contactList[0].id").value("1"),
////                        jsonPath("$.contactList[0].nameContact").value("Vinicius contact"),
////                        jsonPath("$.contactList[0].telephone").value("44998007032"),
////                        jsonPath("$.contactList[0].email").value("vinicius@gmail.com")
////                );
////    }
//
////    @Test
////    @Order(6)
////    void findPersonName() throws Exception {
////        mockMvc.perform(get("/person?name=Vini"))
////                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
////                        status().isOk(),
////                        jsonPath("$.content[0].id").value(1),
////                        jsonPath("$.content[0].name").value("Vinicius"),
////                        jsonPath("$.content[0].cpf").value("82861900920"),
////                        jsonPath("$.content[0].birthDate").value(LocalDate.of(2023, 10, 10).toString()),
////                        jsonPath("$.content[0].contactList[0].id").value("1"),
////                        jsonPath("$.content[0].contactList[0].nameContact").value("Vinicius contact"),
////                        jsonPath("$.content[0].contactList[0].telephone").value("44998007032"),
////                        jsonPath("$.content[0].contactList[0].email").value("vinicius@gmail.com"));
////    }
//
////    @Test
////    @Order(7)
////    void putPerson() throws Exception {
////        Long id = 1L;
////
////        String requestBody = "{\"name\": \"Vinicius Vilela\", " +
////                "\"cpf\": \"82861900920\", " +
////                "\"birthDate\": \"2023-10-10\"," +
////                "\"contactList\": [{\"id\": \"1\"," +
////                "\"nameContact\": \"Vinicius contact\", " +
////                "\"telephone\": \"44998007032\", " +
////                "\"email\": \"vinicius@gmail.com\"}]}";
////
////        mockMvc.perform(put("/person/{id}", id)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(requestBody))
////                .andExpect(status().isOk());
////    }
//
////    @Test
////    @Order(8)
////    void getPersonAfterUpdate() throws Exception {
////        Long id = 1L;
////
////        mockMvc.perform(get("/person/{id}", id))
////                .andExpectAll(content().contentType(MediaType.APPLICATION_JSON),
////                        status().isOk(),
////                        jsonPath("$.id").value(1),
////                        jsonPath("$.name").value("Vinicius Vilela"),
////                        jsonPath("$.cpf").value("82861900920"),
////                        jsonPath("$.birthDate").value(LocalDate.of(2023, 10, 10).toString()),
////                        jsonPath("$.contactList[0].id").value("1"),
////                        jsonPath("$.contactList[0].nameContact").value("Vinicius contact"),
////                        jsonPath("$.contactList[0].telephone").value("44998007032"),
////                        jsonPath("$.contactList[0].email").value("vinicius@gmail.com")
////                );
////    }
//
//    @Test
//    @Order(9)
//    @DisplayName("Should return Http Status Bad Resquest and throw Exception for contact list not be empty")
//    void tryRemoveContactPerson() throws Exception {
//        Long id = 1L;
//
//        String requestBody = "{\"name\": \"Vinicius Vilela\", " +
//                "\"cpf\": \"82861900920\", " +
//                "\"birthDate\": \"2023-10-10\"," +
//                "\"contactList\": []}";
//
//        mockMvc.perform(put("/person/{id}", id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpectAll(status().isBadRequest(), content().string(is("Validation Error: Field: contactList, Message: must not be empty")));
//    }
//
////    @Test
////    @Order(10)
////     void personDelete() throws Exception {
////        Long id = 1L;
////
////        mockMvc.perform(delete("/person/{id}", id))
////                .andExpect(status().isNoContent());
////    }
//}
