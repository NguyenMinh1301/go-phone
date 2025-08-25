package go_phone.feature.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import go_phone.common.mapper.BaseMapper;
import go_phone.feature.payment.entity.PaymentEvent;

@Mapper
public interface PaymentEventMapper extends BaseMapper<PaymentEvent> {}
