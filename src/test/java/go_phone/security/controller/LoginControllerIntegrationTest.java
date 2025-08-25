package go_phone.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.request.RegisterRequest;
import go_phone.security.dto.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;

import java.time.Instant;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class LoginControllerIntegrationTest {

    @Container
    static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.29")
            .withDatabaseName("go_phone_test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("db/test/init.sql");

    @DynamicPropertySource
    static void configureDatasource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driverClassName", () -> "com.mysql.cj.jdbc.Driver");
    }

    @Autowired
    private MockMvc mockMvc;

    private LoginRequest loginRequest;
    private TokenResponse tokenResponse;
    private RegisterRequest registerRequest;

    Instant now = Instant.now();
    Instant exp = now.plusSeconds(120 * 60);

    @BeforeEach
    void initData () {
        registerRequest = RegisterRequest.builder()
                .username("minh3")
                .email("minhgalaxy10000@gmail.com")
                .password("123456")
                .fullName("Nguyen Quang Minh")
                .phone("0987654321")
                .address("Viet Nam")
                .build();

        loginRequest = LoginRequest.builder()
                .username("minh3")
                .password("123456")
                .build();

        tokenResponse = TokenResponse.builder()
                .issuedAt(now)
                .expiresAt(exp)
                .issuer("go_phone")
                .username("minh2")
                .build();

    }

    @Test
    void register_validRequest_success() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(registerRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Tạo user thành công"))
                .andExpect(MockMvcResultMatchers.jsonPath("data").value(nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("timestamp").value(notNullValue()));
    }

    @Test
    void login_validRequest_success() throws Exception {
        // GIVEN: tự đăng ký 1 tài khoản mới ngay trong test login
        ObjectMapper objectMapper = new ObjectMapper();

        RegisterRequest reg = RegisterRequest.builder()
                .username("minh")
                .email("minh@gophone.site")
                .password("123456")
                .fullName("Nguyen Quang Minh")
                .phone("0987654321")
                .address("Viet Nam")
                .build();

        // Đăng ký
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(reg)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("success").value(true));

        // WHEN, THEN: login bằng chính user vừa tạo
        LoginRequest login = LoginRequest.builder()
                .username(reg.getUsername())
                .password("123456")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("success").value(true));
    }

}
