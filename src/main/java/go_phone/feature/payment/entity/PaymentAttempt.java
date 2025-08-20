package go_phone.feature.payment.entity;

import go_phone.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentAttempt extends BaseEntity {

    Long attemptId;
    Long orderId;
    String gateway;               // PAYOS
    String gatewayPaymentId;      // paymentLinkId
    String checkoutUrl;
    String status;                // INITIATED / PENDING / SUCCEEDED / FAILED / CANCELLED
    Long amount;
    String currency;              // VND
    String idempotencyKey;
    String rawPayload;            // LONGTEXT JSON

}
