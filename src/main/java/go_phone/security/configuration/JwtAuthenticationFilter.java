package go_phone.security.configuration;

import go_phone.common.exception.ErrorCode;
import go_phone.common.response.ResponseWriter;
import go_phone.security.mapper.RevokedTokenMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RevokedTokenMapper revokedTokenMapper;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService uds, RevokedTokenMapper revokedTokenMapper) {
        this.jwtService = jwtService;
        this.userDetailsService = uds;
        this.revokedTokenMapper = revokedTokenMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String token;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);

        if (!jwtService.isValid(token) || revokedTokenMapper.isRevoked(token) > 0) {
            ResponseWriter.writeJsonError(response, ErrorCode.INVALID_TOKEN, HttpServletResponse.SC_UNAUTHORIZED, true);
            return;
        }

        String username = jwtService.extractUsername(token).orElse(null);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails ud = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/auth"); // cho phép qua để login/register/introspect/logout xử lý riêng
    }
}
