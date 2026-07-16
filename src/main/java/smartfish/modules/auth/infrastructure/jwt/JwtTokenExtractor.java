package smartfish.modules.auth.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtTokenExtractor {
    private String secret;
    private SecretKey key;

    public JwtTokenExtractor(@Value("${app.jwt.secret}") String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Claims extractClaimsFromToken(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = extractClaimsFromToken(token);

            return true;
        } catch (JwtException e) {

            return false;
        }
    }

    public String getSubjectFromToken(String token) {
        Claims claims = extractClaimsFromToken(token);

        return claims.getSubject();
    }
}
