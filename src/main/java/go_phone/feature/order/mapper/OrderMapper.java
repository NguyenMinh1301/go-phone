package go_phone.feature.order.mapper;

import go_phone.common.mapper.BaseMapper;
import go_phone.feature.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    Order findByOrderCode(@Param("orderCode") Long orderCode);

    int updateStatusByOrderCode(@Param("orderCode") Long orderCode,
                                @Param("status") String status,
                                @Param("payRef") String payRef);

    boolean existsByOrderCode(@Param("orderCode") Long orderCode);

}
