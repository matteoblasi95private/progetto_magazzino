package it.personalproject.spedizioni.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(req -> req
					.requestMatchers("/actuator/**").permitAll()
					.anyRequest().authenticated()
				)
				.oauth2ResourceServer(oauth -> oauth
				        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
				      ).build();
						
	}

	private Converter<Jwt, AbstractAuthenticationToken> jwtAuthConverter() {
		
		return jwt -> {
		      var raw = jwt.getClaims().get("roles");
		      var roles = (raw instanceof List<?> l)
		          ? l.stream().map(String::valueOf).collect(Collectors.toList())
		          : List.of();
		      var auths = roles.stream()
		          .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
		          .collect(Collectors.toList());
		      return new JwtAuthenticationToken(jwt, auths);
		    };
		
	}

}
