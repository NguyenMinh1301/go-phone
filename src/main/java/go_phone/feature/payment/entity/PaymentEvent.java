package go_phone.feature.payment.entity;

import go_phone.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentEvent extends BaseEntity {

    Long eventId;
    String gatewayPaymentId;
    String payloadHash; // SHA-256
    String type;
}
