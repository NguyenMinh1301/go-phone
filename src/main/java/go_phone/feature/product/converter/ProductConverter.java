package go_phone.feature.product.converter;

import go_phone.common.converter.BaseConverter;
import go_phone.feature.product.dto.request.ProductRequest;
import go_phone.feature.product.dto.response.ProductResponse;
import go_phone.feature.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter extends BaseConverter<ProductRequest, ProductResponse, Product> {

}
