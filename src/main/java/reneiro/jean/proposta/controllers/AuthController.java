package reneiro.jean.proposta.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import reneiro.jean.proposta.controllers.exception.UsuarioLoginDto;
import reneiro.jean.proposta.exceptions.ErrorMessage;
import reneiro.jean.proposta.jwt.JwtToken;
import reneiro.jean.proposta.jwt.JwtUserDetailsService;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {

	
	private final JwtUserDetailsService jwtUserDetailsService;
	private final AuthenticationManager authenticationManager;
	
	public AuthController(JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager) {
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.authenticationManager = authenticationManager;
	}
	
	
	@PostMapping("/auth")
	public ResponseEntity<?> authenticate(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto, HttpServletRequest request) {

		log.info("Autenticando usuário: {}", usuarioLoginDto.getUsername());
		
		try {
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
			authenticationManager.authenticate(authenticationToken);
			
			JwtToken jwtToken = jwtUserDetailsService.getTokenAuthenticate(usuarioLoginDto.getUsername());
			
			return ResponseEntity.ok(jwtToken);
			
		} catch (AuthenticationException e) {
			log.warn("Falha na autenticação do usuário: {}", usuarioLoginDto.getUsername());
		}	
		
		return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Falha na autenticação do usuário"));

	}
	
	
	
}
