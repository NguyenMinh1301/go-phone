package go_phone.security.service.Impl;

import go_phone.security.config.JwtUtil;
import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.request.RegisterRequest;
import go_phone.security.dto.response.AuthResponse;
import go_phone.security.entity.User;
import go_phone.security.mapper.UserMapper;
import go_phone.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest registerRequest) {

        if (userMapper.existsByUsername(registerRequest.getUsername()) > 0) {
            throw new RuntimeException("Username existed");
        }

        if (userMapper.existsByEmail(registerRequest.getEmail()) > 0) {
            throw new RuntimeException("Email existed");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setPhone(registerRequest.getPhone());
        user.setAddress(registerRequest.getAddress());
        user.setIsActive(1);
        user.setIsDeleted(0);

        userMapper.insert(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        // Cho phép login bằng username hoặc email
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword())
        );

        String subject = loginRequest.getUsernameOrEmail();
        Map<String,Object> claims = new HashMap<>();
        claims.put("typ", "access");
        String access = jwtUtil.generateAccessToken(subject, claims);
        String refresh = jwtUtil.generateRefreshToken(subject);

        AuthResponse res = new AuthResponse();
        res.setAccessToken(access);
        res.setRefreshToken(refresh);

        // fill info
        User user = userMapper.findByUsernameOrEmail(loginRequest.getUsernameOrEmail());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());

        return res;
    }

    @Override
    public AuthResponse refresh(String refreshToken) {

        String subject = jwtUtil.getSubject(refreshToken);
        Map<String,Object> claims = new HashMap<>();
        claims.put("typ", "access");

        String newAccess = jwtUtil.generateAccessToken(subject, claims);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(newAccess);
        authResponse.setRefreshToken(refreshToken);

        // optional: refill username/email
        User u = userMapper.findByUsernameOrEmail(subject);
        if (u != null) {
            authResponse.setUsername(u.getUsername());
            authResponse.setEmail(u.getEmail());
        }

        return authResponse;
    }

}
