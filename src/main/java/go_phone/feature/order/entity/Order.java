package go_phone.feature.order.entity;

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
public class Order extends BaseEntity {

    Long   orderId;
    Long   orderCode;              // BẮT BUỘC: số long (PayOS)
    Long   userId;
    String status;                 // PENDING_PAYMENT / PAID / CANCELLED / EXPIRED / REFUNDED
    Long   amountItems;
    Long   discountAmount;
    Long   shippingFee;
    Long   taxAmount;
    Long   grandTotal;
    String currency;               // VND
    String customerName;
    String customerPhone;
    String customerEmail;
    String shippingAddressJson;    // LONGTEXT lưu JSON
    String payGateway;             // PAYOS
    String payRef;                 // paymentLinkId / ref
    Integer isActive;

}
