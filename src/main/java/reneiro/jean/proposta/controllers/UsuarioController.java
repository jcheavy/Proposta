package reneiro.jean.proposta.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reneiro.jean.proposta.entities.Usuario;
import reneiro.jean.proposta.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		Usuario usuarioSalvo = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(usuarioSalvo);
	}	
	
	@PatchMapping("/{id}")
	public ResponseEntity<Usuario> updateSenha(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.editarSenha(id, usuario.getPassword());
		return ResponseEntity.ok(usuarioSalvo);
	}	
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> usuarios = usuarioService.buscarTodos();
		return ResponseEntity.ok(usuarios);
	}	
	
}
