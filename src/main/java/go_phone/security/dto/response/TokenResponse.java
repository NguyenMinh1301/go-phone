package go_phone.security.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenResponse {

    String token;
    Instant issuedAt;  // epoch seconds
    Instant expiresAt; // epoch seconds
    String issuer;
    String username;

}
