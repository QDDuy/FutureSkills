package com.example.futureskills.controller;

import com.example.futureskills.dto.request.CreateUserRequest;
import com.example.futureskills.dto.response.UserResponse;
import com.example.futureskills.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private CreateUserRequest createUserRequest;
    private UserResponse userResponse;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2003, 6, 17);
        createUserRequest = CreateUserRequest.builder()
                .userName("testcscsds")
                .password("testcssdsdcds")
                .email("test@test.com")
                .fullName("test")
                .dob(dob)
                .build();
        userResponse = UserResponse.builder()
                .id("3956ad65bf9c")
                .userName("test")
                .email("test@test.com")
                .fullName("test")
                .dob(dob)
                .build();
    }


    @Test
    void createUser_validRequest_success() throws Exception {
        //GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(createUserRequest);

        when(userService.createUser(any())).thenReturn(userResponse);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("3956ad65bf9c")

        );

        //THEN

    }

    @Test
    void createUser_invalidRequest_fail() throws Exception {
        //GIVEN
        createUserRequest.setUserName("te");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(createUserRequest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1009"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 3 characters")

                );


    }
}
