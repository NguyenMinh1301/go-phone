package go_phone.feature.order.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class OrderCreateRequest {
    private Long userId;

    @NotBlank
    private String customerName;

    private String customerPhone;

    @Email
    private String customerEmail;

    @NotBlank
    private String shippingAddress; // sẽ lưu vào shippingAddressJson

    @NotNull
    private Long amount;           // tạm thời truyền thẳng để test; sau nối từ Cart

}
