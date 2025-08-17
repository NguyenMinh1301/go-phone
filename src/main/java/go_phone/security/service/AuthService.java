package go_phone.security.service;

import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.request.RegisterRequest;
import go_phone.security.dto.response.AuthResponse;

public interface AuthService {

    void register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse refresh(String refreshToken);

}
