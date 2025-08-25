package go_phone.feature.payment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.order.entity.Order;
import go_phone.feature.order.service.OrderService;
import go_phone.feature.payment.entity.PaymentAttempt;
import go_phone.feature.payment.mapper.PaymentAttemptMapper;
import lombok.RequiredArgsConstructor;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;

@RestController
@RequestMapping(ApiConstants.Payment.BASE)
@RequiredArgsConstructor
public class PaymentController {

    private final PayOS payOS;
    private final OrderService orderService;
    private final PaymentAttemptMapper attemptMapper;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.payment.return-path}")
    private String returnPath;

    @PostMapping(ApiConstants.Payment.CREATE)
    public ResponseEntity<ApiResponse<Object>> create(@RequestParam("orderCode") Long orderCode)
            throws Exception {
        Order o = orderService.findByOrderCode(orderCode);
        int amount = o.getGrandTotal().intValue();

        ItemData item =
                ItemData.builder().name("GoPhone #" + orderCode).quantity(1).price(amount).build();

        String returnUrl = baseUrl + returnPath + "?orderCode=" + orderCode;
        String cancelUrl = baseUrl + returnPath + "?orderCode=" + orderCode + "&status=cancel";

        PaymentData pd =
                PaymentData.builder()
                        .orderCode(orderCode)
                        .amount(amount)
                        .description("Thanh toan don #" + orderCode)
                        .returnUrl(returnUrl)
                        .cancelUrl(cancelUrl + "?status=cancel")
                        .item(item)
                        .build();

        CheckoutResponseData rs = payOS.createPaymentLink(pd);

        PaymentAttempt a =
                PaymentAttempt.builder()
                        .orderId(o.getOrderId())
                        .gateway("PAYOS")
                        .gatewayPaymentId(rs.getPaymentLinkId())
                        .checkoutUrl(rs.getCheckoutUrl())
                        .status("INITIATED")
                        .amount(o.getGrandTotal())
                        .currency(o.getCurrency())
                        .build();
        attemptMapper.insert(a);

        return ResponseHandler.success(
                Map.of(
                        "checkoutUrl", rs.getCheckoutUrl(),
                        "paymentLinkId", rs.getPaymentLinkId(),
                        "orderCode", rs.getOrderCode(),
                        "amount", rs.getAmount()));
    }
}
