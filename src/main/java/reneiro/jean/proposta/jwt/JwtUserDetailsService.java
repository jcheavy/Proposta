package reneiro.jean.proposta.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import reneiro.jean.proposta.entities.Usuario;
import reneiro.jean.proposta.enums.Role;
import reneiro.jean.proposta.services.UsuarioService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UsuarioService usuarioService;
	
	
	public JwtUserDetailsService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	
		Usuario usuario = usuarioService.buscarPorUsername(username);		
		return  new JwtUserDetails(usuario);		
	}
	
	public JwtToken getTokenAuthenticate(String username) {
		
		Role role = usuarioService.buscarRolePorUsuario(username);
		return JwtUtils.createJwtToken(username, role.name().substring("ROLE_".length()));
	}
}
