package go_phone.feature.product.service;

import go_phone.feature.product.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Integer id);

    int addProduct(Product product);

    int updateProduct(Product product);

    int softDeleteProduct(Integer id, String updatedBy);

}
