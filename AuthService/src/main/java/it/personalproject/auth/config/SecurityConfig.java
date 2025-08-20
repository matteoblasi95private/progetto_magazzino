package it.personalproject.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.personalproject.auth.domain.MagazzinoUserDetailService;
import it.personalproject.auth.filters.JWTFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Autowired
	private JWTFilter jwtFilter;
	
//	 @Bean
//	 public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
//		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    	provider.setUserDetailsService(uds);
//    	provider.setPasswordEncoder(encoder);
//    	return new ProviderManager(provider);
//	 }
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(request -> request
				.requestMatchers("/user/login").permitAll()
				.requestMatchers("/fallback/**","/actuator/**").permitAll()
				.requestMatchers("/api/admin").hasRole("ADMIN")
				.anyRequest().authenticated())
				.csrf(AbstractHttpConfigurer::disable)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	 
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
		daoAuthProvider.setUserDetailsService(userDetailsService());
		return daoAuthProvider;
	}
	 
	@Bean
	public UserDetailsService userDetailsService() {
		return new MagazzinoUserDetailService();
	}
	 
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
	}

}
