package smartfish.modules.auth.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import static org.assertj.core.api.Assertions.*;

public class JwtTokenExtractorTest {
    private SecretKey key;
    private JwtTokenExtractor jwtExtractor;

    @BeforeEach
    void setUp() {
        String secret = "dGwWk9Ibcn2m2esUFGFnEAeyKQc5OrkHcx5FUHvWX70=";
        key = Keys.hmacShaKeyFor(secret.getBytes());
        jwtExtractor = new JwtTokenExtractor(secret);
    }

    @Test
    @DisplayName(
            """
            Deve retornar um objeto Claims que contem os claims enviados na criacao do token
            quando o token enviado para o parser esta valido
            """
    )
    public void shouldReturnClaimsSuccessfully() {
         // Arrange
        long expirationTime = 60*60*1000;
        String issuer = TokenIssuer.AUTH.name();
        String subject = "testeaqui@gmail.com";
        Date issuedAt = new Date();
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);

        String token = Jwts
                .builder()
                .issuer(issuer)
                .subject(subject)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key)
                .compact();

        // Act
        Claims claims = jwtExtractor.extractClaimsFromToken(token);

        // Assert
        assertThat(claims).isNotNull();
        assertThat(claims.getIssuer()).isEqualTo(issuer);
        assertThat(claims.getSubject()).isEqualTo(subject);
        // Jwts nao guarda milisegundos, entao truncamos os valores originais pra comparar igual
        assertThat(claims.getIssuedAt()).isEqualTo(Date.from(issuedAt.toInstant().truncatedTo(ChronoUnit.SECONDS)));
        assertThat(claims.getExpiration()).isEqualTo(Date.from(expiration.toInstant().truncatedTo(ChronoUnit.SECONDS)));
    }
}
