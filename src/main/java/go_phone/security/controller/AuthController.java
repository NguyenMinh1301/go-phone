package go_phone.security.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.request.RegisterRequest;
import go_phone.security.dto.response.AuthResponse;
import go_phone.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Auth.BASE)
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiConstants.Auth.REGISTER)
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseHandler.success("Registered");
    }

    @PostMapping(ApiConstants.Auth.LOGIN)
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest req) {
        return ResponseHandler.success(authService.login(req));
    }

    @PostMapping(ApiConstants.Auth.REFRESH_TOKEN)
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(@RequestParam String refreshToken) {
        return ResponseHandler.success(authService.refresh(refreshToken));
    }

}
