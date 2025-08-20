package go_phone.feature.order.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.feature.order.dto.request.OrderCreateRequest;
import go_phone.feature.order.dto.response.OrderResponse;
import go_phone.feature.order.entity.Order;
import go_phone.feature.order.entity.OrderStatus;
import go_phone.feature.order.mapper.OrderMapper;
import go_phone.feature.order.service.OrderService;
import go_phone.common.util.OrderNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderNumberGenerator generator;
    private final ObjectMapper om = new ObjectMapper();

    @Override
    @Transactional
    public OrderResponse create(OrderCreateRequest req) {

        long orderCode;
        int tries = 0;
        do {
            orderCode = generator.next10Digits();
            tries++;
        } while (orderMapper.existsByOrderCode(orderCode) && tries < 10);

        if (orderMapper.existsByOrderCode(orderCode)) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

        long amount = req.getAmount();

        Order o = Order.builder()
                .orderCode(orderCode)
                .userId(req.getUserId())
                .status(OrderStatus.PENDING_PAYMENT.name())
                .amountItems(amount)
                .discountAmount(0L)
                .shippingFee(0L)
                .taxAmount(0L)
                .grandTotal(amount)
                .currency("VND")
                .customerName(req.getCustomerName())
                .customerPhone(req.getCustomerPhone())
                .customerEmail(req.getCustomerEmail())
                .shippingAddressJson(toJson(req.getShippingAddress()))
                .payGateway("PAYOS")
                .isActive(1)
                .build();

        int rows = orderMapper.insert(o);
        if (rows != 1) throw new AppException(ErrorCode.INTERNAL_ERROR);

        return OrderResponse.builder()
                .orderId(o.getOrderId())
                .orderCode(o.getOrderCode())
                .status(o.getStatus())
                .grandTotal(o.getGrandTotal())
                .currency(o.getCurrency())
                .build();
    }

    @Override
    public Order findByOrderCode(Long orderCode) {
        Order o = orderMapper.findByOrderCode(orderCode);
        if (o == null) throw new AppException(ErrorCode.NOT_FOUND);
        return o;
    }

    @Override
    public String toJson(Object any) {
        try { return om.writeValueAsString(any); }
        catch (Exception e) { return null; }
    }
}
