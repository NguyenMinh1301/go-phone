package go_phone.feature.category.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.common.handler.CalculateOffset;
import go_phone.common.response.PageResponse;
import go_phone.feature.category.converter.BrandConverter;
import go_phone.feature.category.dto.request.BrandRequest;
import go_phone.feature.category.dto.response.BrandResponse;
import go_phone.feature.category.entity.Brand;
import go_phone.feature.category.mapper.BrandMapper;
import go_phone.feature.category.service.BrandService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;
    private final BrandConverter brandConverter;
    private final CalculateOffset calculateOffset = new CalculateOffset();

    @Override
    public int create(BrandRequest dto) {
        if (brandMapper.existsByName(dto.getName())) {
            throw new AppException(ErrorCode.BRAND_EXISTED);
        }
        Brand brand = brandConverter.toEntity(dto);
        return brandMapper.insert(brand);
    }

    @Override
    public int update(Integer id, BrandRequest dto) {
        Brand brand = brandMapper.findById(id);
        if (brand == null) {
            throw new AppException(ErrorCode.BRAND_NOT_FOUND);
        }
        brandConverter.updateEntity(dto, brand);
        return brandMapper.update(brand);
    }

    @Override
    public BrandResponse findById(Integer id) {
        Brand brand = brandMapper.findById(id);
        if (brand != null) {
            return brandConverter.toResponse(brand);
        } else {
            throw new AppException(ErrorCode.BRAND_NOT_FOUND);
        }
    }

    @Override
    public List<BrandResponse> findAll() {
        List<Brand> brands = brandMapper.findAll();
        if (brands == null || brands.isEmpty()) {
            throw new AppException(ErrorCode.BRAND_EMPTY);
        }
        return brandConverter.toResponseList(brands);
    }

    @Override
    public PageResponse<BrandResponse> findAllPageable(int page, int size) {

        int offset = calculateOffset.calculateOffset(page, size);

        List<Brand> brands = brandMapper.findAllPageable(offset, size);
        int totalBrand = brandMapper.countAll();
        return brandConverter.toResponsePage(brands, page, size, totalBrand);
    }

    @Override
    public PageResponse<BrandResponse> searchPageable(String keyword, int page, int size) {

        int offset = calculateOffset.calculateOffset(page, size);

        List<Brand> brands = brandMapper.searchPageable(keyword, offset, size);
        int totalBrand = brandMapper.countSearch(keyword);
        return brandConverter.toResponsePage(brands, page, size, totalBrand);
    }

    @Override
    public int delete(Integer id) {
        Brand brand = brandMapper.findById(id);
        if (brand == null) {
            throw new AppException(ErrorCode.BRAND_NOT_FOUND);
        }
        brandMapper.softDeleteById(id);
        return brandMapper.softDeleteById(id);
    }
}
