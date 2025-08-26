package go_phone.feature.order.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.order.dto.request.OrderCreateRequest;
import go_phone.feature.order.dto.response.OrderResponse;
import go_phone.feature.order.entity.Order;
import go_phone.feature.order.service.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.Order.BASE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(ApiConstants.Order.CREATE)
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @RequestBody @Valid OrderCreateRequest req) {
        return ResponseHandler.success(orderService.create(req));
    }

    @GetMapping(ApiConstants.Order.GET_BY_CODE)
    public ResponseEntity<ApiResponse<Order>> getByCode(@PathVariable Long orderCode) {
        return ResponseHandler.success(orderService.findByOrderCode(orderCode));
    }
}
