package go_phone.feature.product.service.Impl;

import go_phone.common.exception.AppException;
import go_phone.common.exception.ErrorCode;
import go_phone.feature.product.converter.ProductConverter;
import go_phone.feature.product.dto.request.ProductRequest;
import go_phone.feature.product.dto.response.ProductResponse;
import go_phone.feature.product.entity.Product;
import go_phone.feature.product.mapper.ProductMapper;
import go_phone.feature.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductConverter productConverter;


    @Override
    public int create(ProductRequest dto) {
        if(productMapper.existsByName(dto.getProductName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
        Product product = productConverter.toEntity(dto);
        return productMapper.insert(product);
    }

    @Override
    public int update(Integer id, ProductRequest dto) {
        Product product = productMapper.findById(id);
        if(product == null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productConverter.updateEntity(dto, product);
        return productMapper.update(product);
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = productMapper.findById(id);
        if(product != null) {
            return productConverter.toResponse(product);
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<ProductResponse> findAll() {
        List<Product> products = productMapper.findAll();
        if(products == null || products.isEmpty()) {
            throw new AppException(ErrorCode.PRODUCT_EMPTY);
        }
        return productConverter.toResponseList(products);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        Product product = productMapper.findById(id);
        if(product == null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        return productMapper.softDeleteById(id);
    }
}
