package go_phone.feature.category.converter;

import go_phone.common.converter.BaseConverter;
import go_phone.feature.category.dto.request.ColorRequest;
import go_phone.feature.category.dto.response.ColorResponse;
import go_phone.feature.category.entity.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorConverter extends BaseConverter<ColorRequest, ColorResponse, Color> {

}
