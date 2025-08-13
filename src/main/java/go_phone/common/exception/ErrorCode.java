package go_phone.common.exception;

import lombok.Getter;

/**
 * Mã lỗi hệ thống chia theo nhóm:
 * - 5000: Lỗi hệ thống / bảo mật / xác thực
 * - 4000: Lỗi từ dịch vụ bên ngoài (Google, Vonage,...)
 * - 3000: Lỗi định dạng / dữ liệu không hợp lệ
 * - 2000: Không tìm thấy / đã tồn tại
 */

@Getter
public enum ErrorCode {

    //=========== PRODUCT ===========
    PRODUCT_NOT_FOUND("2000", "Không tìm thấy sản phẩm"),
    PRODUCT_EXISTED("2001", "Sản phẩm đã tồn tại"),
    PRODUCT_EMPTY("2002", "Không có sản phẩm tồn tại"),

    //=========== BRAND ============
    BRAND_NOT_FOUND("2100", "Không tìm thấy thương hiệu"),
    BRAND_EXISTED("2101", "Thương hiệu đã tồn tại"),
    BRAND_EMPTY("2102", "Không có thương hiệu tồn tại"),

    //=========== COLOR ============
    COLOR_NOT_FOUND("2200", "Không tìm thấy màu sắc"),
    COLOR_EXISTED("2201", "Màu sắc đã tồn tại"),
    COLOR_EMPTY("2202", "Không có màu sắc tồn tại"),

    //=========== MADE FROM ============
    MADE_FROM_NOT_FOUND("2300", "Không tìm thấy xuất xứ"),
    MADE_FROM_EXISTED("2301", "Xuất xứ đã tồn tại"),
    MADE_FROM_EMPTY("2302", "Không có xuất xứ tồn tại"),

    //=========== SYSTEM ============
    BAD_SQL("1001", "Sai câu lệnh SQL"),
    INTERNAL_ERROR("5000", "Lỗi hệ thống");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

