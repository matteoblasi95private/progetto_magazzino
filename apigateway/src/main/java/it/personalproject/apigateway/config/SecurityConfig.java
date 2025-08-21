package it.personalproject.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {

  // decodifica token jwt
  
	@Bean
	public ReactiveJwtDecoder jwtDecoder(
	    @Value("${spring.security.oauth2.resourceserver.jwt.secret}") String secret) {

	  SecretKey key;
	  try {
	    key = new SecretKeySpec(Base64.getDecoder().decode(secret), "HmacSHA256");
	  } catch (IllegalArgumentException e) {
	    key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
	  }

	  return NimbusReactiveJwtDecoder
	      .withSecretKey(key)
	      .macAlgorithm(MacAlgorithm.HS256)
	      .build();
	}

  // Converte il claim roles in authorities
  private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthConverter() {
    return jwt -> {
      Object raw = jwt.getClaims().get("roles");
      List<String> roles = (raw instanceof List<?> l)
          ? l.stream().map(String::valueOf).collect(Collectors.toList())
          : List.of();
      Collection<GrantedAuthority> auths = roles.stream()
          .map(SimpleGrantedAuthority::new)
          .collect(Collectors.toList());
      return Mono.just(new JwtAuthenticationToken(jwt, auths));
    };
  }

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    return http
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(reg -> reg
        .pathMatchers("/api/auth/**", "/auth/**", "/actuator/**", "/fallback/**").permitAll()
        .pathMatchers("/api/admin/**").hasRole("ADMIN")
        .anyExchange().authenticated()
      )
      .oauth2ResourceServer(oauth -> oauth
        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
      )
      .build();
  }
}