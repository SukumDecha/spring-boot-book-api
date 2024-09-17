package me.sukumdev.bookapi.controller;

import me.sukumdev.bookapi.dto.UserRegisterDto;
import me.sukumdev.bookapi.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTests {

    @Autowired
    private UserService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void saveUserForTest() {
        UserRegisterDto regDto = UserRegisterDto.builder()
                .email("test@gmail.com")
                .password("12345")
                .firstName("Test")
                .lastName("User")
                .age(5)
                .build();

        service.register(regDto);
    }

    @Test
    void authenticateUserTest() throws Exception {
        String authJson = new JSONObject()
                .put("email", "test@gmail.com")
                .put("password", "12345")
                .toString();

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authJson)
        ).andExpect(status().isOk()).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        System.out.println("Returned JWT: " + response);
    }
}
