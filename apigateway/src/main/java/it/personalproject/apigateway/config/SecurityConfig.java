package it.personalproject.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {

  // Converte il claim roles in authorities
  private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthConverter() {
    return jwt -> {
      Object raw = jwt.getClaims().get("roles");
      List<String> roles = (raw instanceof List<?> l)
          ? l.stream().map(String::valueOf).collect(Collectors.toList())
          : List.of();
      Collection<GrantedAuthority> auths = roles.stream()
          .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
          .collect(Collectors.toList());
      return Mono.just(new JwtAuthenticationToken(jwt, auths));
    };
  }
  
  @Bean
  @Profile("dev")
  public SecurityWebFilterChain devSecurityFilterChain(ServerHttpSecurity http) {
      return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                 .authorizeExchange(reg -> reg.anyExchange().permitAll())
                 .build();
  }

  @Bean
  @Profile("!dev")
  public SecurityWebFilterChain prodSecurityFilterChain(ServerHttpSecurity http) {
	  
    return http
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(reg -> reg
        .pathMatchers("/actuator/**", "/fallback/**").permitAll()
        .pathMatchers("/api/admin/**").hasRole("ADMIN")
        .anyExchange().authenticated()
      )
      .oauth2ResourceServer(oauth -> oauth
        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
      )
      .build();
  }
}