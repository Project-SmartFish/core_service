package smartfish.modules.auth.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import smartfish.modules.auth.infrastructure.jwt.JwtTokenExtractor;
import smartfish.modules.auth.infrastructure.jwt.JwtTokenProvider;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenExtractor jwtTokenExtractor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(hasBearerToken(token)) {
            // proceder
        }
    }

    public boolean hasBearerToken(String token) {
        return token != null && !token.isBlank() && token.startsWith("Bearer ") && token.length() > 7;
    }
}
