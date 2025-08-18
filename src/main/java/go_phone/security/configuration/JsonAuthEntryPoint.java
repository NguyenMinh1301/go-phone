package go_phone.security.configuration;

import go_phone.common.exception.ErrorCode;
import go_phone.common.response.ResponseWriter;
import jakarta.servlet.http.*;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JsonAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res,
                         org.springframework.security.core.AuthenticationException ex) throws IOException {
        ResponseWriter.writeJsonError(res, ErrorCode.UNAUTHORIZED, HttpServletResponse.SC_UNAUTHORIZED, true);
    }
}
