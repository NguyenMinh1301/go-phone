package go_phone.security.controller;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired private MockMvc mockMvc;

    private LoginRequest loginRequest;
    private TokenResponse tokenResponse;

    Instant now = Instant.now();
    Instant exp = now.plusSeconds(120 * 60);

    @BeforeEach
    void initData() {
        loginRequest = LoginRequest.builder().username("minh2").password("123456").build();

        tokenResponse =
                TokenResponse.builder()
                        .issuedAt(now)
                        .expiresAt(exp)
                        .issuer("go_phone")
                        .username("minh2")
                        .build();
    }

    @Test
    void login_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(loginRequest);

        // WHEN, THEN
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("success").value(true));
    }
}
