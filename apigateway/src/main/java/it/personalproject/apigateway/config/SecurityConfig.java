package it.personalproject.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private Converter<Jwt, AbstractAuthenticationToken> magazzinoJwtAuthConverter() {
    return jwt -> {
      Object raw = jwt.getClaims().get("roles");
      List<String> roles = (raw instanceof List<?> l)
          ? l.stream().map(String::valueOf).collect(Collectors.toList())
          : List.of();
      Collection<GrantedAuthority> auths = roles.stream()
          .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
          .collect(Collectors.toList());
      return new JwtAuthenticationToken(jwt, auths);
    };
  }
  
//  @Bean
//  @Profile("dev")
//  public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
//      
//      return http
//    		  .csrf(csrf -> csrf.disable())
//    	      .authorizeHttpRequests(reg -> reg
//    	        .anyRequest().permitAll()
//    	      )
//    	      .build();
//  }

  @Bean
  //@Profile("!dev")
  public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
	  
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(reg -> reg
        .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oauth -> oauth
        .jwt(jwt -> jwt.jwtAuthenticationConverter(magazzinoJwtAuthConverter()))
      )
      .build();
  }
  
}