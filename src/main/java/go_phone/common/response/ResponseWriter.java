package go_phone.common.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import go_phone.common.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseWriter {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private ResponseWriter() {}

    public static void writeJsonError(HttpServletResponse response,
                                      ErrorCode errorCode,
                                      int httpStatus,
                                      boolean useErrorCode) throws IOException {

        response.setStatus(httpStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String code = useErrorCode ? errorCode.getCode() : String.valueOf(httpStatus);

        ApiResponse<Object> errorResponse = new ApiResponse<>(
                false,
                errorCode.getMessage(),
                code,
                null
        );

        MAPPER.writeValue(response.getWriter(), errorResponse);
    }
}
