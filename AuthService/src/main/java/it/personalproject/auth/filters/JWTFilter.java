package it.personalproject.auth.filters;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import it.personalproject.auth.domain.JWTService;
import it.personalproject.auth.domain.MagazzinoUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTFilter extends OncePerRequestFilter {
	
	private final JWTService jwtService;
	private final UserDetailsService userDetailsService;

	public JWTFilter(JWTService jwtService, UserDetailsService userDetailsService) {
	   this.jwtService = jwtService;
	   this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String user = null;
		String token = null;
		
		if(StringUtils.isNotBlank(authHeader) && authHeader.startsWith("Beearer")) {
			
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
	
	

}
