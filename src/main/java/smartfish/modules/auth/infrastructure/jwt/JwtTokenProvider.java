package smartfish.modules.auth.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final SecretKey key;
    private final long EXPIRATION_TIME = 60*60*1000; // 1 hora em milisegundos

    public JwtTokenProvider(@Value("${app.jwt.secret}") String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email, TokenIssuer tokenIssuer) {
        return Jwts
                .builder()
                .issuer(tokenIssuer.name())
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
}
