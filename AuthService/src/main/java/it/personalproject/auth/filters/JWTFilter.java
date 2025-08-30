package it.personalproject.auth.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import it.personalproject.auth.domain.AuthServiceImpl;
import it.personalproject.auth.domain.JWTService;
import it.personalproject.auth.domain.MagazzinoUserDetailService;
import it.personalproject.auth.domain.MagazzinoUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.SecurityContext;


@Component
public class JWTFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Autowired
    private Environment env;
	
	@Value("${fakeuser.email}")
	private String fakeUserEmail;
	
	@Value("${fakeuser.phonenumber}")
	private String fakePhoneNumber;
	
	@Value("${fakeuser.password}")
	private String fakeuserPassword;
	
	@Value("${fakeuser.username}")
	private String fakeUsername;
	
	private final JWTService jwtService;
	private final UserDetailsService userDetailsService;

	public JWTFilter(JWTService jwtService, UserDetailsService userDetailsService) {
	   this.jwtService = jwtService;
	   this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String activeProfile = Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("default");
		
		if ("dev".equals(activeProfile)) {
			logger.info("AUTENTICAZIONE IN MODALITA DEV");
		    SecurityContextHolder.getContext().setAuthentication(getFakeAuthentication(request));
		    filterChain.doFilter(request, response);
		    return;
		}
		
		String authHeader = request.getHeader("Authorization");
		String user = null;
		String token = null;
		
		if(StringUtils.isNotBlank(authHeader) && authHeader.startsWith("Bearer")) {
			
			token = authHeader.substring(7);
			
			user = jwtService.extractUsername(token);
			
			if (user != null) {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(user);
	            if (jwtService.validateToken(token)) {
	                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(auth);
	            }
	        }
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	private UsernamePasswordAuthenticationToken getFakeAuthentication(HttpServletRequest request) {
	    MagazzinoUserDetails fakeUserDetails = new MagazzinoUserDetails();
	    fakeUserDetails.setEmail(fakeUserEmail);
	    fakeUserDetails.setPhoneNumber(fakePhoneNumber);
	    fakeUserDetails.setPassword(fakeuserPassword);
	    fakeUserDetails.setUsername(fakeUsername);
	    fakeUserDetails.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

	    UsernamePasswordAuthenticationToken auth =
	        new UsernamePasswordAuthenticationToken(fakeUserDetails, null, fakeUserDetails.getAuthorities());

	    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    return auth;
	}
	
	

}
