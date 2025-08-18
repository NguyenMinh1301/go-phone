package go_phone.security.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.security.configuration.AuthService;
import go_phone.security.dto.request.IntrospectRequest;
import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.request.RegisterRequest;
import go_phone.security.dto.response.TokenResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.Auth.BASE)
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    // Register
    @PostMapping(ApiConstants.Auth.REGISTER)
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterRequest req) {
        int rows = authService.register(req);
        if (rows > 0) {
            return ResponseHandler.success("Tạo user thành công", null);
        }
        return ResponseHandler.error("Tạo user thất bại", go_phone.common.exception.ErrorCode.INTERNAL_ERROR,
                org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    // Login
    @PostMapping(ApiConstants.Auth.LOGIN)
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest req) {
        TokenResponse tr = authService.login(req);
        return ResponseHandler.success(tr);
    }

    // Logout (Token có thể lấy từ Authorization hoặc body)
    @PostMapping(ApiConstants.Auth.LOGOUT)
    public ResponseEntity<ApiResponse<Object>> logout(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody(required = false) IntrospectRequest body
    ) {
        String token = extractToken(authorization, body);
        authService.logout(token);
        return ResponseHandler.success("Logout thành công", null);
    }

    // Introspect
    @PostMapping(ApiConstants.Auth.INTROSPECT)
    public ResponseEntity<ApiResponse<TokenResponse>> introspect(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody(required = false) IntrospectRequest req
    ) {
        String token = extractToken(authorization, req);
        TokenResponse tr = authService.introspect(token);
        return ResponseHandler.success("Token hợp lệ", tr);
    }

    private String extractToken(String authorization, IntrospectRequest req) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        if (req != null && req.getToken() != null && !req.getToken().isBlank()) {
            return req.getToken();
        }
        return null;
    }
}
