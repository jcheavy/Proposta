package reneiro.jean.proposta.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reneiro.jean.proposta.entities.Categoria;
import reneiro.jean.proposta.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}	
	
	@RequestMapping("/salvar")
	@PostMapping 
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria savedCategoria = categoriaService.adicionar(categoria);
		return ResponseEntity.ok(savedCategoria);
	}
	
}
