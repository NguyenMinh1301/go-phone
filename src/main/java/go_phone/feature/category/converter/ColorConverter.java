package go_phone.feature.category.converter;

import org.mapstruct.Mapper;

import go_phone.common.converter.BaseConverter;
import go_phone.feature.category.dto.request.ColorRequest;
import go_phone.feature.category.dto.response.ColorResponse;
import go_phone.feature.category.entity.Color;

@Mapper(componentModel = "spring")
public interface ColorConverter extends BaseConverter<ColorRequest, ColorResponse, Color> {}
