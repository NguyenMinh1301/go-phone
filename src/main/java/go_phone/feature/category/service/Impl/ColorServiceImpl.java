package go_phone.feature.category.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.common.handler.CalculateOffset;
import go_phone.common.response.PageResponse;
import go_phone.feature.category.converter.ColorConverter;
import go_phone.feature.category.dto.request.ColorRequest;
import go_phone.feature.category.dto.response.ColorResponse;
import go_phone.feature.category.entity.Color;
import go_phone.feature.category.mapper.ColorMapper;
import go_phone.feature.category.service.ColorService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorMapper colorMapper;
    private final ColorConverter colorConverter;
    private final CalculateOffset calculateOffset = new CalculateOffset();

    @Override
    public int create(ColorRequest dto) {
        if (colorMapper.existsByName(dto.getName())) {
            throw new AppException(ErrorCode.COLOR_EXISTED);
        }
        Color color = colorConverter.toEntity(dto);
        return colorMapper.insert(color);
    }

    @Override
    public int update(Integer id, ColorRequest dto) {
        Color color = colorMapper.findById(id);
        if (color == null) {
            throw new AppException(ErrorCode.COLOR_NOT_FOUND);
        }
        colorConverter.updateEntity(dto, color);
        return colorMapper.update(color);
    }

    @Override
    public ColorResponse findById(Integer id) {
        Color color = colorMapper.findById(id);
        if (color != null) {
            return colorConverter.toResponse(color);
        } else {
            throw new AppException(ErrorCode.COLOR_NOT_FOUND);
        }
    }

    @Override
    public List<ColorResponse> findAll() {
        List<Color> colors = colorMapper.findAll();
        if (colors == null || colors.isEmpty()) {
            throw new AppException(ErrorCode.COLOR_EMPTY);
        }
        return colorConverter.toResponseList(colors);
    }

    @Override
    public PageResponse<ColorResponse> findAllPageable(int page, int size) {

        int offset = calculateOffset.calculateOffset(page, size);

        List<Color> colors = colorMapper.findAllPageable(offset, size);
        int totalColor = colorMapper.countAll();
        return colorConverter.toResponsePage(colors, page, size, totalColor);
    }

    @Override
    public PageResponse<ColorResponse> searchPageable(String keyword, int page, int size) {

        int offset = calculateOffset.calculateOffset(page, size);

        List<Color> colors = colorMapper.searchPageable(keyword, offset, size);
        int totalColor = colorMapper.countSearch(keyword);
        return colorConverter.toResponsePage(colors, page, size, totalColor);
    }

    @Override
    public int delete(Integer id) {
        Color color = colorMapper.findById(id);
        if (color == null) {
            throw new AppException(ErrorCode.COLOR_NOT_FOUND);
        }
        colorMapper.softDeleteById(id);
        return colorMapper.softDeleteById(id);
    }
}
