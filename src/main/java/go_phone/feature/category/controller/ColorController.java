package go_phone.feature.category.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.PageResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.category.converter.ColorConverter;
import go_phone.feature.category.dto.request.ColorRequest;
import go_phone.feature.category.dto.response.ColorResponse;
import go_phone.feature.category.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Category.BASE)
public class ColorController {

    private final ColorService colorService;
    private final ColorConverter colorConverter;

    // Lấy toàn bộ color
    @GetMapping(ApiConstants.Category.COLOR + ApiConstants.Category.GET_ALL)
    public ResponseEntity<ApiResponse<List<ColorResponse>>> getAll() {
        return ResponseHandler.success(colorService.findAll());
    }

    // Lấy toàn bộ color Pageable
    @GetMapping(ApiConstants.Category.COLOR + ApiConstants.Category.GET_ALL_PAGEABLE)
    public ResponseEntity<ApiResponse<PageResponse<ColorResponse>>> getAllPageable(@RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size) {
        return ResponseHandler.success(colorService.findAllPageable(page, size));
    }

    // Tìm kiếm color Pageable
    @GetMapping(ApiConstants.Category.COLOR + ApiConstants.Category.SEARCH_PAGEABLE)
    public ResponseEntity<ApiResponse<PageResponse<ColorResponse>>> searchPageable(@RequestParam(required = false) String keyword,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size) {
        return ResponseHandler.success(colorService.searchPageable(keyword, page, size));
    }

    // Lấy color theo id
    @GetMapping(ApiConstants.Category.COLOR + ApiConstants.Category.GET_BY_ID)
    public ResponseEntity<ApiResponse<ColorResponse>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(colorService.findById(id));
    }

    // Thêm mới color
    @PostMapping(ApiConstants.Category.COLOR + ApiConstants.Category.ADD)
    public ResponseEntity<ApiResponse<Integer>> add(@RequestBody ColorRequest colorRequest) {
        return ResponseHandler.success(colorService.create(colorRequest));
    }

    // Cập nhật color
    @PutMapping(ApiConstants.Category.COLOR + ApiConstants.Category.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id,
                                                       @RequestBody ColorRequest colorRequest) {
        return ResponseHandler.success(colorService.update(id, colorRequest));
    }

    // Xóa mềm color
    @DeleteMapping(ApiConstants.Category.COLOR + ApiConstants.Category.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(colorService.delete(id));
    }

}
