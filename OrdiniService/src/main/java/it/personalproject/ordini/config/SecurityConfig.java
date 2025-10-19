package it.personalproject.ordini.config;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
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
	
	
	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(
	      ClientRegistrationRepository registrations) {

	    var provider = OAuth2AuthorizedClientProviderBuilder.builder()
	        .clientCredentials()
	        .build();
	    
	    var clientService = new InMemoryOAuth2AuthorizedClientService(registrations);

	    var manager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(registrations, clientService);
	    manager.setAuthorizedClientProvider(provider);
	    return manager;
	    
	}

	@Bean(name = "gatewayRestClient")
	public RestClient securedRestClient(OAuth2AuthorizedClientManager manager, @Value("${apigateway.url}") String apigatewayUrl) {
		
		
		logger.info("API GATEWAY URL: " + apigatewayUrl);
		
	    return RestClient.builder()
	    	.baseUrl(apigatewayUrl)
	        .requestInterceptor((request, body, execution) -> {
	          var authorizeReq = OAuth2AuthorizeRequest
	              .withClientRegistrationId("chiama-giacenze")
	              .principal("client-credentials")
	              .build();
	          var authorized = manager.authorize(authorizeReq);
	          if (authorized == null || authorized.getAccessToken() == null) {
	            throw new IllegalStateException("Impossibile ottenere access token per chiama-giacenze");
	          }
	          request.getHeaders().setBearerAuth(authorized.getAccessToken().getTokenValue());
	          return execution.execute(request, body);
	        })
	        .build();
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
