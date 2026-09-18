package reneiro.jean.proposta.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	
	@PostMapping("/salvar")
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria savedCategoria = categoriaService.adicionar(categoria);
		return ResponseEntity.ok(savedCategoria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoriaById (@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);        
        return ResponseEntity.ok(categoria);  
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarCategorias() {
		return ResponseEntity.ok(categoriaService.listar());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
		Categoria updatedCategoria = categoriaService.atualizar(id, categoria);
		return ResponseEntity.ok().body(updatedCategoria);
	}
	
	@GetMapping("/deletar/{id}")
	public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
		categoriaService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
}
