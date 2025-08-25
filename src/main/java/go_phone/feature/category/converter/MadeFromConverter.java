package go_phone.feature.category.converter;

import org.mapstruct.Mapper;

import go_phone.common.converter.BaseConverter;
import go_phone.feature.category.dto.request.MadeFromRequest;
import go_phone.feature.category.dto.response.MadeFromResponse;
import go_phone.feature.category.entity.MadeFrom;

@Mapper(componentModel = "spring")
public interface MadeFromConverter
        extends BaseConverter<MadeFromRequest, MadeFromResponse, MadeFrom> {}
