package reneiro.jean.proposta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reneiro.jean.proposta.entities.Categoria;
import reneiro.jean.proposta.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Categoria adicionar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
}
