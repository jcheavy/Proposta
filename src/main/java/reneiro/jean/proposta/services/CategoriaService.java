package reneiro.jean.proposta.services;

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
}
