package go_phone.security.configuration;


import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.security.dto.request.LoginRequest;
import go_phone.security.dto.request.RegisterRequest;
import go_phone.security.dto.response.TokenResponse;
import go_phone.security.mapper.RevokedTokenMapper;
import go_phone.security.mapper.RoleMapper;
import go_phone.security.mapper.UserMapper;
import go_phone.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RevokedTokenMapper revokedTokenMapper;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public int register(RegisterRequest req) {

        if (userMapper.existsByUsername(req.getUsername()) > 0) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXIST);
        }
        if (userMapper.existsByEmail(req.getEmail()) > 0) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXIST);
        }

        User user = User.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .fullName(req.getFullName())
                .phone(req.getPhone())
                .address(req.getAddress())
                .createdBy(req.getCreatedBy() != null ? req.getCreatedBy() : "system")
                .isActive(req.getIsActive() != null ? req.getIsActive() : 1)
                .isDeleted(req.getIsDeleted() != null ? req.getIsDeleted() : 0)
                .build();

        int rows = userMapper.insert(user);

        if (user.getUserId() == null) {
            var created = userMapper.findByUsername(user.getUsername());
            if (created != null) user.setUserId(created.getUserId());
        }

        if (user.getUserId() == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        var role = roleMapper.findByCode("GO_STARTER");
        roleMapper.insertUserRole(user.getUserId(), role.getRoleId());

        return rows;
    }

    public TokenResponse login(LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        // nếu sai sẽ ném AuthenticationException và GlobalExceptionHandler đã map BAD_CREDENTIALS

        String token = jwtService.generateToken(req.getUsername());
        Instant issueAt = jwtService.getIssuedAt(token);
        Instant expiration = jwtService.getExpiration(token);

        return TokenResponse.builder()
                .token(token)
                .issuedAt(issueAt)
                .expiresAt(expiration)
                .issuer(jwtService.getIssuer())
                .username(req.getUsername())
                .build();
    }

    public void logout(String token) {

        if (!jwtService.isValid(token)) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        if (revokedTokenMapper.isRevoked(token) > 0) {
            // đã logout rồi coi như thành công idempotent
            return;
        }

        LocalDateTime exp = LocalDateTime.ofInstant(jwtService.getExpiration(token), java.time.ZoneId.systemDefault());
        revokedTokenMapper.insert(token, exp);
        // dọn rác token quá hạn
        revokedTokenMapper.deleteExpired();

    }

    public TokenResponse introspect(String token) {

        if (token == null || token.isBlank()) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        if (!jwtService.isValid(token) || revokedTokenMapper.isRevoked(token) > 0) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        String username = jwtService.extractUsername(token).orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));

        return TokenResponse.builder()
                .token(token)
                .issuedAt(jwtService.getIssuedAt(token))
                .expiresAt(jwtService.getExpiration(token))
                .issuer(jwtService.getIssuer())
                .username(username)
                .build();
    }

}
