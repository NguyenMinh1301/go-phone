package go_phone.feature.category.service.Impl;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.feature.category.converter.BrandConverter;
import go_phone.feature.category.dto.request.BrandRequest;
import go_phone.feature.category.dto.response.BrandResponse;
import go_phone.feature.category.entity.Brand;
import go_phone.feature.category.mapper.BrandMapper;
import go_phone.feature.category.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;
    private final BrandConverter brandConverter;

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
    public int delete(Integer id) {
        Brand brand = brandMapper.findById(id);
        if (brand == null) {
            throw new AppException(ErrorCode.BRAND_NOT_FOUND);
        }
        brandMapper.softDeleteById(id);
        return brandMapper.softDeleteById(id);
    }

}
