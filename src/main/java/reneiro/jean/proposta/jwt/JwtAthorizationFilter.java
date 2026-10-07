package reneiro.jean.proposta.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAthorizationFilter extends OncePerRequestFilter{

	
	@Autowired
	private JwtUserDetailsService jwtUserDatailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);
		
		if (token == null || !token.startsWith(JwtUtils.JWT_BEARER_PREFIX )) {
			
			log.info("Token não encontrado ou inválido no cabeçalho da requisição");
			filterChain.doFilter(request, response);
			return;
		}
		if (JwtUtils.isTokenValid(token)) {
			log.warn("Token expirado ou inválido");
			filterChain.doFilter(request, response);
			return;
		}
		
		String username = JwtUtils.getUsernameFromToken(token);		
		toAuthenticateUser(request, username);
		filterChain.doFilter(request, response); 
		
	}


	private void toAuthenticateUser(HttpServletRequest request, String username) {

		var userDetails = jwtUserDatailsService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
