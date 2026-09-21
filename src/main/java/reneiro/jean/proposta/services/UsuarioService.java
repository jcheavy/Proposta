package reneiro.jean.proposta.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reneiro.jean.proposta.entities.Usuario;
import reneiro.jean.proposta.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
	}

	@Transactional
	public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaNovaSenha) {
		
		if (!novaSenha.equals(confirmaNovaSenha)) {
			throw new RuntimeException("A nova senha e a confirmação da nova senha não coincidem.");
		}		
		
		Usuario usuarioSalvo = buscarPorId(id);
		
		if (!usuarioSalvo.getPassword().equals(senhaAtual)) {
			throw new RuntimeException("Senha atual INCORRETA.");
		}
				
		usuarioSalvo.setPassword(novaSenha);
		return usuarioSalvo;
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

}
