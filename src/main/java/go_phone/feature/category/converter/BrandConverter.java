package go_phone.feature.category.converter;

import go_phone.common.converter.BaseConverter;
import go_phone.feature.category.dto.request.BrandRequest;
import go_phone.feature.category.dto.response.BrandResponse;
import go_phone.feature.category.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandConverter extends BaseConverter<BrandRequest, BrandResponse, Brand> {

}
