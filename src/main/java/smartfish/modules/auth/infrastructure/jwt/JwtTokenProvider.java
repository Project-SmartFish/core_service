package smartfish.modules.auth.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final SecretKey key;
    private final long EXPIRATION_TIME = 60*60*1000; // 1 hora em milisegundos

    public JwtTokenProvider(@Value("${SECRET_KEY}") String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UUID id, String email) {
        return Jwts
                .builder()
                .setIssuer("auth")
                .setId(id.toString())
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
}
