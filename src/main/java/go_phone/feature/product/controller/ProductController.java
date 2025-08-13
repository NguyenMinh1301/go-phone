package go_phone.feature.product.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.product.converter.ProductConverter;
import go_phone.feature.product.dto.request.ProductRequest;
import go_phone.feature.product.dto.response.ProductResponse;
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

    // Lấy toàn bộ sản phẩm
    @GetMapping(ApiConstants.Product.GET_ALL)
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
        return ResponseHandler.success(productService.findAll());
    }

    // Lấy sản phẩm theo ID
    @GetMapping(ApiConstants.Product.GET_BY_ID)
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(productService.findById(id));
    }

    // Thêm mới sản phẩm
    @PostMapping(ApiConstants.Product.ADD)
    public ResponseEntity<ApiResponse<Integer>> add(@RequestBody ProductRequest productRequest) {
        return ResponseHandler.success(productService.create(productRequest));
    }

    // Cập nhật sản phẩm
    @PutMapping(ApiConstants.Product.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id,
                                                       @RequestBody ProductRequest productRequest) {
        return ResponseHandler.success(productService.update(id, productRequest));
    }

    // Xóa mềm sản phẩm
    @DeleteMapping(ApiConstants.Product.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(productService.delete(id));
    }

}
