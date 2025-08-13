package go_phone.feature.category.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.PageResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.category.converter.MadeFromConverter;
import go_phone.feature.category.dto.request.MadeFromRequest;
import go_phone.feature.category.dto.response.MadeFromResponse;
import go_phone.feature.category.service.MadeFromService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Category.BASE)
public class MadeFromController {

    private final MadeFromService madeFromService;
    private final MadeFromConverter madeFromConverter;

    // Lấy toàn bộ made_from
    @GetMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.GET_ALL)
    public ResponseEntity<ApiResponse<List<MadeFromResponse>>> getAll() {
        return ResponseHandler.success(madeFromService.findAll());
    }

    // Lấy toàn bộ made_from Pageable
    @GetMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.GET_ALL_PAGEABLE)
    public ResponseEntity<ApiResponse<PageResponse<MadeFromResponse>>> getAllPageable(@RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "10") int size) {
        return ResponseHandler.success(madeFromService.findAllPageable(page, size));
    }

    // Tìm kiếm made_from Pageable
    @GetMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.SEARCH_PAGEABLE)
    public ResponseEntity<ApiResponse<PageResponse<MadeFromResponse>>> searchPageable(@RequestParam(required = false) String keyword,
                                                                                      @RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "10") int size) {
        return ResponseHandler.success(madeFromService.searchPageable(keyword, page, size));
    }

    // Lấy made_from theo id
    @GetMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.GET_BY_ID)
    public ResponseEntity<ApiResponse<MadeFromResponse>> getById(@PathVariable("id") Integer id) {
        return ResponseHandler.success(madeFromService.findById(id));
    }

    // Thêm mới made_from
    @PostMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.ADD)
    public ResponseEntity<ApiResponse<Integer>> add(@RequestBody MadeFromRequest madeFromRequest) {
        return ResponseHandler.success(madeFromService.create(madeFromRequest));
    }

    // Cập nhật made_from
    @PutMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.UPDATE)
    public ResponseEntity<ApiResponse<Integer>> update(@PathVariable("id") Integer id,
                                                       @RequestBody MadeFromRequest madeFromRequest) {
        return ResponseHandler.success(madeFromService.update(id, madeFromRequest));
    }

    // Xóa mềm made_from
    @DeleteMapping(ApiConstants.Category.MADE_FROM + ApiConstants.Category.SOFT_DELETE)
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable("id") Integer id) {
        return ResponseHandler.success(madeFromService.delete(id));
    }

}
