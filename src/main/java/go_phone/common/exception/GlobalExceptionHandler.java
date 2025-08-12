package go_phone.common.exception;

import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;

public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException e) {
        return ResponseHandler.error(e.getErrorCode());
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<ApiResponse<Object>> handleSQLException(SQLSyntaxErrorException ex) {
        return ResponseHandler.error(ErrorCode.BAD_SQL, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleOther(Exception ex) {
        return ResponseHandler.error(ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
