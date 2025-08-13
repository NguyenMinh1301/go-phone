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

    BRAND_NOT_FOUND("2100", "Thuộc tính đã tồn tại"),
    BRAND_EXISTED("2101", "Không tìm thấy thuộc tính"),
    BRAND_EMPTY("2102", "Không có thuộc tính tồn tại"),


    BAD_SQL("1001", "Sai câu lệnh sql"),
    INTERNAL_ERROR("5000", "Lỗi hệ thống"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

