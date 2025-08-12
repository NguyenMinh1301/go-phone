package go_phone.feature.product.service.Impl;

import go_phone.feature.product.entity.Product;
import go_phone.feature.product.mapper.ProductMapper;
import go_phone.feature.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.findById(id);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.update(product);
    }

    @Override
    public int softDeleteProduct(Integer id, String updatedBy) {
        return productMapper.softDeleteById(id);
    }
}
