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
	public Usuario editarSenha(Long id, String password) {
		Usuario usuarioSalvo = buscarPorId(id);
		usuarioSalvo.setPassword(password);
		return usuarioSalvo;
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

}
