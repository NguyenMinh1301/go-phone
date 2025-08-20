package go_phone.feature.payment.mapper;

import go_phone.common.mapper.BaseMapper;
import go_phone.feature.payment.entity.PaymentAttempt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentAttemptMapper extends BaseMapper<PaymentAttempt> {

    int updateOnGateway(@Param("orderId") Long orderId,
                        @Param("gatewayPaymentId") String gatewayPaymentId,
                        @Param("checkoutUrl") String checkoutUrl,
                        @Param("status") String status);

    int markStatus(@Param("orderId") Long orderId, @Param("status") String status);

    PaymentAttempt findByOrderId(@Param("orderId") Long orderId);

}
