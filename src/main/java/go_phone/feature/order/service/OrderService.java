package go_phone.feature.order.service;

import go_phone.feature.order.dto.request.OrderCreateRequest;
import go_phone.feature.order.dto.response.OrderResponse;
import go_phone.feature.order.entity.Order;

public interface OrderService {

    OrderResponse create(OrderCreateRequest req);

    Order findByOrderCode(Long orderCode);

    String toJson(Object any);

}
