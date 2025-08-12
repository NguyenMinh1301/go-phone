package go_phone.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    PRODUCT_NOT_FOUND("2000", "Không tìm thấy sản phẩm"),
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

