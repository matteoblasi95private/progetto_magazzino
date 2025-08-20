package it.personalproject.auth.domain;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	
	@Value("${auth.jwt.secret}")
	private String SECRET_KEY;
	
	@Value("${auth.jwt.issuer:magazzino-auth}")
    private String issuer;

    @Value("${auth.jwt.access-minutes:15}")
    private long accessMinutes;

    @Value("${auth.jwt.refresh-days:7}")
    private long refreshDays;

    @Value("${auth.jwt.allowed-skew-seconds:60}")
    private long allowedSkewSeconds;

    private SecretKey getSigningKey() {
        
    	try {
    		// provo a decodificare in base 64, se non riesco il secret non e codificato
            byte[] decoded = Base64.getDecoder().decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(decoded);
        } catch (IllegalArgumentException e) {
        	// non codificato
            byte[] raw = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            return Keys.hmacShaKeyFor(raw);
        }
    	
    }
    
    private JwtParser parser() {
        return Jwts.parser()
                .clock(() -> Date.from(Instant.now()))
                .clockSkewSeconds(allowedSkewSeconds)
                .requireIssuer(issuer)
                .verifyWith(getSigningKey())
                .build();
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }
    
    public List<? extends GrantedAuthority> extractAuthorities(String token) {
        
    	Claims claims = extractAllClaims(token);
        
        if(!claims.containsKey("roles")) {
        	throw new IllegalStateException("ERRORE ESTRAZIONE AUTORITIES - AUTORITIES NON TROVATE TRA I CLAIMS");
        }
        
        List<String> roles = (List<String>) claims.get("roles");
        
        return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList()); 
        
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    
    public Claims extractAllClaims(String token) {
        return parser().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> autorities) {
        Map<String, Object> claims = new HashMap<>();
        
        if (autorities != null) {
            List<String> roles = autorities.stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList()); 
            claims.put("roles", roles);
        }
        
        return createToken(claims, username, Duration.ofMinutes(accessMinutes), "access");
    }

    private String createToken(Map<String, Object> extraClaims, String subject, Duration ttl, String typ) {
        Instant now = Instant.now();
        Instant exp = now.plus(ttl);
        return Jwts.builder()
                .header().type("JWT").and()
                .issuer(issuer)
                .subject(subject)
                .id(UUID.randomUUID().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("typ", typ)
                .claims(extraClaims)
                .signWith(getSigningKey())
                .compact();
    }

    
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    
    public String generateRefreshToken(String username) {
        return createToken(Map.of(), username, Duration.ofDays(refreshDays), "refresh");
    }

    public boolean isRefreshToken(String token) {
        try {
            return "refresh".equals(extractAllClaims(token).get("typ"));
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public Instant getAccessExpiryInstant() {
        return Instant.now().plus(Duration.ofMinutes(accessMinutes));
    }

    public long getAccessTtlSeconds() {
        return Duration.ofMinutes(accessMinutes).toSeconds();
    }

}
