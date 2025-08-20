package go_phone.feature.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.common.util.HashingUtils;
import go_phone.common.util.Jsons;
import go_phone.feature.order.entity.Order;
import go_phone.feature.order.entity.OrderStatus;
import go_phone.feature.order.mapper.OrderMapper;
import go_phone.feature.payment.entity.PaymentEvent;
import go_phone.feature.payment.mapper.PaymentAttemptMapper;
import go_phone.feature.payment.mapper.PaymentEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vn.payos.PayOS;
import vn.payos.type.Webhook;
import vn.payos.type.WebhookData;

@RestController
@RequiredArgsConstructor
public class PayOSWebhookController {

    private final PayOS payOS;
    private final PaymentAttemptMapper attemptMapper;
    private final PaymentEventMapper eventMapper;
    private final OrderMapper orderMapper;
    private final Jsons jsons;
    private final ObjectMapper om = new ObjectMapper();

    @PostMapping("${app.payment.webhook-path}")
    @Transactional
    public ResponseEntity<ApiResponse<Object>> webhook(@RequestBody Webhook body) {
        try {

            WebhookData data = payOS.verifyPaymentWebhookData(body);

            String hash = HashingUtils.sha256(jsons.toJson(body));
            try {
                eventMapper.insert(PaymentEvent.builder()
                        .gatewayPaymentId(data.getPaymentLinkId())
                        .payloadHash(hash)
                        .type(data.getCode())
                        .build());
            } catch (DuplicateKeyException dup) {
                return ResponseHandler.success("OK", null);
            }

            boolean success = "00".equalsIgnoreCase(data.getCode());    // Map theo code PayOS
            Long orderCode  = data.getOrderCode();
            String payRef   = data.getPaymentLinkId();

            // Lấy order theo orderCode
            Order order = orderMapper.findByOrderCode(orderCode);

            if (order == null) {
                return ResponseHandler.success("OK", null);
            }

            if (success) {
                orderMapper.updateStatusByOrderCode(orderCode, OrderStatus.PAID.name(), payRef);
                attemptMapper.markStatus(order.getOrderId(), "SUCCEEDED");
            } else {
                orderMapper.updateStatusByOrderCode(orderCode, OrderStatus.FAILED.name(), payRef);
                attemptMapper.markStatus(order.getOrderId(), "FAILED");
            }

            return ResponseHandler.success("OK", null);

        } catch (Exception e) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }
    }
}
