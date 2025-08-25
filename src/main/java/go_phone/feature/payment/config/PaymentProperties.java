package go_phone.feature.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.payment")
public class PaymentProperties {
    private String baseUrl; // http://localhost:<port>
    private String returnPath; // /payos/return
    private String webhookPath; // /api/webhooks/payos
}
