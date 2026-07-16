package smartfish.modules.auth.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import smartfish.modules.auth.infrastructure.jwt.JwtTokenExtractor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenExtractor jwtTokenExtractor;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(!hasBearerToken(token)) {
            filterChain.doFilter(request,response);
            return;
        }

        token = token.substring(7);

        if(!jwtTokenExtractor.isValidToken(token)) {
            filterChain.doFilter(request,response);
            return;
        }

        String email = jwtTokenExtractor.getSubjectFromToken(token);

        UserDetails userDetails;

        try{
            userDetails = userDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            filterChain.doFilter(request,response);
            return;
        }

        if( !userDetails.isAccountNonLocked()
                || !userDetails.isEnabled()
                || !userDetails.isAccountNonExpired()
                || !userDetails.isCredentialsNonExpired()
        ) {
            filterChain.doFilter(request,response);
            return;
        }

        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

    private boolean hasBearerToken(String token) {
        return token != null && !token.isBlank() && token.startsWith("Bearer ") && token.length() > 7;
    }
}
