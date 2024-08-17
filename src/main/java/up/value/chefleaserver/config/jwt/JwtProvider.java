package up.value.chefleaserver.config.jwt;

import static up.value.chefleaserver.config.jwt.JwtValidationType.EMPTY_TOKEN;
import static up.value.chefleaserver.config.jwt.JwtValidationType.EXPIRED_TOKEN;
import static up.value.chefleaserver.config.jwt.JwtValidationType.INVALID_TOKEN;
import static up.value.chefleaserver.config.jwt.JwtValidationType.UNSUPPORTED_TOKEN;
import static up.value.chefleaserver.config.jwt.JwtValidationType.VALID_TOKEN;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private static final String USER_ID = "userId";
    private static final String ISSUER = "cheflea.value.up";
    private static final Long JWT_EXPIRATION_TIME = 6 * 30 * 24 * 60 * 60 * 1000L;

    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;

    public String generateJWT(Authentication authentication) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(generateClaims(authentication))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims generateClaims(Authentication authentication) {
        final Claims claims = Jwts.claims()
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME));
        claims.put(USER_ID, authentication.getPrincipal());
        return claims;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes());
    }

    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getClaim(token);
            return VALID_TOKEN;
        } catch (MalformedJwtException ex) {
            return INVALID_TOKEN;
        } catch (ExpiredJwtException ex) {
            return EXPIRED_TOKEN;
        } catch (UnsupportedJwtException ex) {
            return UNSUPPORTED_TOKEN;
        } catch (IllegalArgumentException ex) {
            return EMPTY_TOKEN;
        }
    }

    private Claims getClaim(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserFromJWT(String token) {
        Claims claims = getClaim(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }
}
