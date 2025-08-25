package go_phone.feature.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.order.entity.Order;
import go_phone.feature.order.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PayOSReturnController {

    private final OrderService orderService;

    @GetMapping("${app.payment.return-path}")
    public ResponseEntity<ApiResponse<Object>> handleReturn(
            @RequestParam(required = false) Long orderCode,
            @RequestParam(required = false) String status) {
        if (orderCode == null) {
            return ResponseHandler.success(
                    "Return from PayOS",
                    java.util.Map.of("message", "Thiếu orderCode", "orderStatus", "UNKNOWN"));
        }
        Order o = orderService.findByOrderCode(orderCode);
        return ResponseHandler.success(
                java.util.Map.of(
                        "message",
                        "Trở về từ PayOS",
                        "orderCode",
                        orderCode,
                        "orderStatus",
                        o.getStatus() // PENDING_PAYMENT / PAID / ...
                        ));
    }
}
