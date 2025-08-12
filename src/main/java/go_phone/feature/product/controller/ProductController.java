package go_phone.feature.product.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.product.converter.ProductConverter;
import go_phone.feature.product.dto.request.ProductRequest;
import go_phone.feature.product.entity.Product;
import go_phone.feature.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Product.BASE)
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping(ApiConstants.Product.GET_ALL)
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        return ResponseHandler.success(productService.getAllProducts());
    }

    @GetMapping(ApiConstants.Product.GET_BY_ID)
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        return ResponseHandler.success(product);
    }

    @PostMapping(ApiConstants.Product.ADD)
    public ResponseEntity<ApiResponse<Integer>> add(@RequestBody ProductRequest req) {
        Product product = productConverter.toEntity(req); // hoặc tên method bạn đang dùng
        return ResponseHandler.success(productService.addProduct(product));
    }

    @PutMapping(ApiConstants.Product.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id,
                                                       @RequestBody ProductRequest req) {
        Product product = productConverter.toEntity(req);
        product.setProductId(id);
        return ResponseHandler.success(productService.updateProduct(product));
    }

    @DeleteMapping(ApiConstants.Product.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id, @RequestParam(required = false) String updatedBy) {
        int rows = productService.softDeleteProduct(id, updatedBy);
        return ResponseHandler.success(rows);
    }

}
