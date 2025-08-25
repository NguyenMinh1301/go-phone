package go_phone.feature.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import go_phone.common.mapper.BaseMapper;
import go_phone.feature.product.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {}
