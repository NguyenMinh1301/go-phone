package go_phone.security.configuration;

import go_phone.common.exception.ErrorCode;
import go_phone.common.response.ResponseWriter;
import jakarta.servlet.http.*;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res,
                       org.springframework.security.access.AccessDeniedException ex) throws IOException {
        ResponseWriter.writeJsonError(res, ErrorCode.FORBIDDEN, HttpServletResponse.SC_FORBIDDEN, true);
    }
}
