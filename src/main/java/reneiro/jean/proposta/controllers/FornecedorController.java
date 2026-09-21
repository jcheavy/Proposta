package reneiro.jean.proposta.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reneiro.jean.proposta.entities.Fornecedor;
import reneiro.jean.proposta.services.FornecedorService;


@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	private final FornecedorService fornecedorService;
	
	public FornecedorController(FornecedorService categoriaService) {
		this.fornecedorService = categoriaService;
	}	
	
	
	@PostMapping("/salvar")
	public ResponseEntity<Fornecedor> saveCategoria(@RequestBody Fornecedor fornecedor) {
		Fornecedor savedCategoria = fornecedorService.adicionar(fornecedor);
		return ResponseEntity.ok(savedCategoria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoriaById (@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.findById(id);        
        return ResponseEntity.ok(fornecedor);  
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarCategorias() {
		return ResponseEntity.ok(fornecedorService.listar());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCategoria(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
		Fornecedor updatedCategoria = fornecedorService.atualizar(id, fornecedor);
		return ResponseEntity.ok().body(updatedCategoria);
	}
	
	@GetMapping("/deletar/{id}")
	public ResponseEntity<?> deletarCategoria(@PathVariable Long id) {
		fornecedorService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
}
