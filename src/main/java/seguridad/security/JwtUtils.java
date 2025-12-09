package seguridad.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import seguridad.entity.Role;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    // ✅ Clave fija y segura (mínimo 32 caracteres)
    private static final String SECRET = "CLAVE_SUPER_LARGA_Y_SEGURA_PARA_JWT_2025_ABCDEF123456";

    // ✅ Convertimos la clave fija en una SecretKey válida para HS256
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", List.of(role.name()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // ✅ Firma correcta
                .compact();
    }
}
