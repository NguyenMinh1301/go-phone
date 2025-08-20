package go_phone.feature.payment.config;

import lombok.Getter; import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "app.payment")
public class PaymentProperties {
    private String baseUrl;        // http://localhost:<port>
    private String returnPath;     // /payos/return
    private String webhookPath;    // /api/webhooks/payos
}
