package go_phone.feature.category.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.category.converter.BrandConverter;
import go_phone.feature.category.dto.request.BrandRequest;
import go_phone.feature.category.dto.response.BrandResponse;
import go_phone.feature.category.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Category.BASE)
public class BrandController {

    private final BrandService brandService;
    private final BrandConverter brandConverter;

    // Lấy toàn bộ brand
    @GetMapping(ApiConstants.Category.BRAND + ApiConstants.Category.GET_ALL)
    public ResponseEntity<ApiResponse<List<BrandResponse>>> getAll() {
        return ResponseHandler.success(brandService.findAll());
    }

    // Lấy brand theo id
    @GetMapping(ApiConstants.Category.BRAND + ApiConstants.Category.GET_BY_ID)
    public ResponseEntity<ApiResponse<BrandResponse>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(brandService.findById(id));
    }

    // Thêm mới brand
    @PostMapping(ApiConstants.Category.BRAND + ApiConstants.Category.ADD)
    public ResponseEntity<ApiResponse<Integer>> add(@RequestBody BrandRequest brandRequest) {
        return ResponseHandler.success(brandService.create(brandRequest));
    }

    // Cập nhật brand
    @PutMapping(ApiConstants.Category.BRAND + ApiConstants.Category.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id,
                                                       @RequestBody BrandRequest brandRequest) {
        return ResponseHandler.success(brandService.update(id, brandRequest));
    }

    // Xóa mềm brand
    @DeleteMapping(ApiConstants.Category.BRAND + ApiConstants.Category.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(brandService.delete(id));
    }

}
