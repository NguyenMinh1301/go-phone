package go_phone.feature.product.converter;

import go_phone.feature.product.dto.request.ProductRequest;
import go_phone.feature.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter {

    Product toEntity(ProductRequest request);

}
