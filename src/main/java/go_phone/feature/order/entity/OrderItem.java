package go_phone.feature.order.entity;

import go_phone.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem  extends BaseEntity {

    Long orderItemId;
    Long orderId;
    Long productId;
    Long skuId;
    String nameSnapshot;
    Long priceUnit;
    Integer qty;
    Long subtotal;
    Double vatRate;
    Integer isActive;

}
