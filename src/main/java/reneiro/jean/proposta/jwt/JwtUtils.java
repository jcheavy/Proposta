package reneiro.jean.proposta.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reneiro.jean.proposta.utilitarios.Renlogs;

public class JwtUtils {

	public static final String JWT_BEARER_PREFIX = "Bearer ";
	public static final String JWT_AUTHORIZATION = "Authorization";
	public static final String JWT_SECRET_KEY = "0123456789-0123456789-0123456789";

	public static final long JWT_EXPIRE_DAYS = 0; // 1 day in millisecond
	public static final long JWT_EXPIRE_HOURS = 0; // 1 hour in millisecond
	public static final long JWT_EXPIRE_MINUTES = 10; // 1 minute in millisecond

	public JwtUtils() {

	}

	private static Key generateKey() {

		return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	private static Date toExpireDate(Date start) {
		LocalDateTime localDateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime expireDateTime = localDateTime.plusDays(JWT_EXPIRE_DAYS).plusHours(JWT_EXPIRE_HOURS)
				.plusMinutes(JWT_EXPIRE_MINUTES);
		return Date.from(expireDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static JwtToken createJwtToken(String username, String role) {

		Date now = new Date();
		Date expireDate = toExpireDate(now);

		String token = Jwts.builder().header().add("Typ", "JWT").and().subject(username).issuedAt(now)
				.expiration(expireDate).claim("role", role).signWith(generateKey()).compact();

		return new JwtToken(token);
	}

	private static Claims getClaimsFromToken(String token) {

		try {

			return Jwts.parser().setSigningKey(generateKey()).build().parseClaimsJws(refactorToken(token)).getBody();
		} catch (JwtException e) {

			Renlogs.error(JwtUtils.class, "Token Inválido ou expirado", e);
		}

		return null;
	}

	private static String refactorToken(String token) {

		if (token.contains(JWT_BEARER_PREFIX)) {
			return token.substring(JWT_BEARER_PREFIX.length());
		}
		return token;
	}

	public static String getUsernameFromToken(String token) {

		return getClaimsFromToken(token).getSubject();

	}

	public static boolean isTokenValid(String token) {

		try {
			Jwts.parser().setSigningKey(token).build().parseClaimsJws(refactorToken(token));
			return true;
		} catch (JwtException e) {

			Renlogs.error(JwtUtils.class, "Token Inválido ", e);
		}

		return false;
	}

}
