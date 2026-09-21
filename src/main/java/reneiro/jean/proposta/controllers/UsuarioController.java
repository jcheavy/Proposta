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

import reneiro.jean.proposta.dtos.UsuarioCreateDto;
import reneiro.jean.proposta.dtos.UsuarioResponseDto;
import reneiro.jean.proposta.dtos.UsuarioSenhaDto;
import reneiro.jean.proposta.dtos.mapper.UsuarioMapper;
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
	public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody UsuarioCreateDto createDto) {
		Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.mpaToUsuario(createDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(usuarioSalvo));
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
		Usuario usuarioSalvo = usuarioService.buscarPorId(id);
		return ResponseEntity.ok(UsuarioMapper.toDto( usuarioSalvo));
	}	
	
	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> updateSenha(@PathVariable Long id, @RequestBody UsuarioSenhaDto usuarioSenhaDto) {
		Usuario usuarioSalvo = usuarioService.editarSenha(id, usuarioSenhaDto.getSenhaAutal(), usuarioSenhaDto.getNovaSenha(),
				 usuarioSenhaDto.getConfimaNovaSenha());
		return ResponseEntity.ok(UsuarioMapper.toDto(usuarioSalvo));
	}	
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDto>> getAll() {
		List<Usuario> usuarios = usuarioService.buscarTodos();		
		return ResponseEntity.ok(UsuarioMapper.toDtoList(usuarios));
	}	
	
}
